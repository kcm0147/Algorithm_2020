#include <iostream>
#include <queue>
#include <utility>
#include <cstring>

using namespace std;

int map[20][20], map_size, answer = 0, shark_weight = 2, di[4] = { 0, 1, 0, -1 }, dj[4] = { 1, 0, -1, 0 };
bool visit[20][20];

void bfs_fishing(int i_pos, int j_pos, int eat_count) {
	int cur_i, cur_j, comp_i, comp_j, k, count = 0;

	priority_queue<pair<int, int>, vector<pair<int, int>>, greater<pair<int, int>>> pq;
	queue<pair<pair<int, int>, int>> q;
	q.push(make_pair(make_pair(i_pos, j_pos), 0));
	visit[i_pos][j_pos] = true;
	while (1) {
		if (q.empty() || q.front().second > count) {
			if (q.empty() && pq.empty())
				return;
			count++;
			if (!pq.empty()) {
				comp_i = pq.top().first;
				comp_j = pq.top().second;
				answer += count;
				map[comp_i][comp_j] = 0;
				memset(visit, false, sizeof(visit));
				if (shark_weight == eat_count + 1) {
					shark_weight++;
					bfs_fishing(comp_i, comp_j, 0);
				}
				else
					bfs_fishing(comp_i, comp_j, eat_count + 1);
				return;
			}
		}

		cur_i = q.front().first.first;
		cur_j = q.front().first.second;
		q.pop();
		for (k = 0; k < 4; k++) {
			comp_i = cur_i + di[k];
			comp_j = cur_j + dj[k];
			if (0 <= comp_i && comp_i < map_size && 0 <= comp_j && comp_j < map_size && !visit[comp_i][comp_j])
				if (map[comp_i][comp_j] <= shark_weight) {
					q.push(make_pair(make_pair(comp_i, comp_j), count + 1));
					visit[comp_i][comp_j] = true;
					if (0 < map[comp_i][comp_j] && map[comp_i][comp_j] < shark_weight)
						pq.push(make_pair(comp_i, comp_j));
				}
		}
	}
}

int main() {
	int shark_i, shark_j, i, j, num;
	cin >> map_size;
	for (i = 0; i < map_size; i++)
		for (j = 0; j < map_size; j++) {
			cin >> num;
			if (num == 9) {
				shark_i = i;
				shark_j = j;
			}
			else
				map[i][j] = num;
		}
	bfs_fishing(shark_i, shark_j, 0);
	cout << answer << endl;

	return 0;
}