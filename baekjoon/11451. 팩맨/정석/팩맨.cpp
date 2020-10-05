#include <iostream>
#include <cstring>
#include <queue>
#include <vector>
#include <utility>
#include <string>
#define pii pair<int, int>

using namespace std;
struct qnode {
	int x1;
	int y1;
	int x2;
	int y2;
	string route;
};
string rutans;
char map[50][50], dir[4] = { 'E', 'S', 'W', 'N' };
int di[4] = { 0, 1, 0, -1 }, dj[4] = { 1, 0, -1, 0 }, n, m;
bool visit[50][50][50][50];

int bfs(vector<pii> org) {
	queue<qnode> q;
	q.push(qnode{ org[0].first, org[0].second, org[1].first, org[1].second, "" });
	visit[org[0].first][org[0].second][org[1].first][org[1].second] = true;
	int size, count = 0, k, x1, y1, x2, y2, cmpx1, cmpy1, cmpx2, cmpy2;
	string cur;
	while (!q.empty()) {
		count++;
		size = q.size();
		while (size--) {
			x1 = q.front().x1;	y1 = q.front().y1;
			x2 = q.front().x2;	y2 = q.front().y2;
			cur = q.front().route;
			q.pop();
			for (k = 0; k < 4; k++) {
				cmpx1 = (x1 + di[k] + n) % n;		cmpy1 = (y1 + dj[k] + m) % m;
				cmpx2 = (x2 + di[k] + n) % n;		cmpy2 = (y2 + dj[k] + m) % m;
				if (map[cmpx1][cmpy1] == 'X') { cmpx1 = x1;  cmpy1 = y1; }
				if (map[cmpx2][cmpy2] == 'X') { cmpx2 = x2;  cmpy2 = y2; }
				if (map[cmpx1][cmpy1] == '.' && map[cmpx2][cmpy2] == '.' && !visit[cmpx1][cmpy1][cmpx2][cmpy2]) {
					visit[cmpx1][cmpy1][cmpx2][cmpy2] = true;
					if (cmpx1 == cmpx2 && cmpy1 == cmpy2) {
						rutans = cur + dir[k];
						return count;
					}
					q.push(qnode{ cmpx1, cmpy1, cmpx2, cmpy2, cur + dir[k] });
				}
			}
		}
	}
	return 0;
}

void testCase() {
	vector<pii> pac;
	int i, j, count;
	cin >> n >> m;
	memset(visit, false, sizeof(visit));
	for (i = 0; i < n; i++)
		for (j = 0; j < m; j++) {
			cin >> map[i][j];
			if (map[i][j] == 'P') {
				map[i][j] = '.';
				pac.push_back(pii(i, j));
			}
		}
	count = bfs(pac);
	if (count) {
		cout << count << ' ' << rutans << '\n';
	}
	else cout << "IMPOSSIBLE" << '\n';
}

int main() {
	ios_base::sync_with_stdio(false);  cin.tie(NULL);  cout.tie(NULL);
	int tc;
	cin >> tc;
	while (tc--)
		testCase();
	return 0;
}