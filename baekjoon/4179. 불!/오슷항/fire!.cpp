#include <iostream>
#include <queue>
#include <utility>
#define pii pair<int, int>

using namespace std;
char maze[1000][1000];
bool visit[1000][1000];
int di[4] = { 0, 1, 0, -1 }, dj[4] = { 1, 0, -1, 0 }, row, col;
queue<pii> fire_q, jh_q;

void fire_bfs(int level) {
	int comp_i, comp_j, cur_i, cur_j, cycle = fire_q.size();
	while (cycle--) {
		cur_i = fire_q.front().first;
		cur_j = fire_q.front().second;
		fire_q.pop();
		for (int k = 0; k < 4; k++) {
			comp_i = cur_i + di[k];
			comp_j = cur_j + dj[k];
			if (0 <= comp_i && comp_i < row && 0 <= comp_j && comp_j < col && maze[comp_i][comp_j] == '.') {
				maze[comp_i][comp_j] = 'F';
				fire_q.push(pii(comp_i, comp_j));
			}
		}
	}
}

int jh_bfs(int level) {
	int comp_i, comp_j, cur_i, cur_j, cycle = jh_q.size();
	while (cycle--) {
		cur_i = jh_q.front().first;
		cur_j = jh_q.front().second;
		jh_q.pop();
		for (int k = 0; k < 4; k++) {
			comp_i = cur_i + di[k];
			comp_j = cur_j + dj[k];
			if (0 <= comp_i && comp_i < row && 0 <= comp_j && comp_j < col && maze[comp_i][comp_j] == '.' && !visit[comp_i][comp_j]) {
				if (comp_i == 0 || comp_i == (row - 1) || comp_j == 0 || comp_j == (col - 1))
					return level + 1;
				visit[comp_i][comp_j] = true;
				jh_q.push(pii(comp_i, comp_j));
			}
		}
	}
	if (jh_q.empty())
		return -1;
	return 0;
}

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);

	int ans, level = 1;
	char input;
	cin >> row >> col;

	for (int i = 0; i < row; i++)
		for (int j = 0; j < col; j++) {
			cin >> input;
			maze[i][j] = input;
			if (input == 'J') {
				if (i == 0 || i == row || j == 0 || j == col) {
					cout << 1 << "\n";
					return 0;
				}
				jh_q.push(pii(i, j));
				maze[i][j] = '.';
				visit[i][j] = true;
			}
			else if (input == 'F')
				fire_q.push(pii(i, j));
		}
	while (1) {
		fire_bfs(level);
		ans = jh_bfs(level++);
		if (ans == -1) {
			cout << "IMPOSSIBLE" << "\n";
			break;
		}
		else if (ans != 0) {
			cout << ans << "\n";
			break;
		}
	}

	return 0;
}