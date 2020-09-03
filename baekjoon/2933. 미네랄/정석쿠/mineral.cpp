#include <iostream>
#include <algorithm>
#include <queue>
#include <utility>
#include <vector>
#include <cstring>
#define pii pair<int, int>
#define MAX 100

using namespace std;
char input[MAX][MAX];
int R, C, di[4] = { 0, 1, 0, -1 }, dj[4] = { 1, 0, -1, 0 };
bool visit[MAX][MAX];

bool bfs(int x, int y) {
	bool cluster[MAX][MAX]{ false };
	vector<pii> floating;
	queue<pii> q;

	q.push(pii(x, y));
	floating.push_back(pii(x, y));
	cluster[x][y] = true;
	visit[x][y] = true;
	int curx, cury, i, j, k, compx, compy, size, minval = MAX + 1;
	bool minavail, breakp = false;
	while (!q.empty()) {
		curx = q.front().first;
		cury = q.front().second;
		q.pop();
		for (k = 0; k < 4; k++) {
			compx = curx + di[k];
			compy = cury + dj[k];
			if (0 <= compx && compx < R && 0 <= compy && compy < C && !visit[compx][compy] && input[compx][compy] == 'x') {
				if (compx == R - 1) breakp = true;
				floating.push_back(pii(compx, compy));
				cluster[compx][compy] = true;
				q.push(pii(compx, compy));
				visit[compx][compy] = true;
			}
		}
	}
	if (breakp) return false;
	size = floating.size();
	for (i = 0; i < size; i++) {
		compx = floating[i].first;
		compy = floating[i].second;
		minavail = true;
		for (j = compx + 1; j < R; j++) {
			if (cluster[j][compy]) {
				minavail = false;
				break;
			}
			if (input[j][compy] == 'x')	break;
		}
		if (minavail)	minval = min(minval, j - compx - 1);
	}
	for (i = 0; i < size; i++)
		input[floating[i].first][floating[i].second] = '.';
	for (i = 0; i < size; i++)
		input[floating[i].first + minval][floating[i].second] = 'x';
	return true;
}

void floatable(int x, int y) {
	int compx, compy;
	for (int k = 0; k < 4; k++) {
		compx = x + di[k];
		compy = y + dj[k];
		if (0 <= compx && compx < R && 0 <= compy && compy < C && !visit[compx][compy] && input[compx][compy] == 'x') {
			if (bfs(compx, compy))	return;
		}
	}
}

void throwStick(bool flag) {
	int height, i;
	cin >> height;
	height = R - height;
	memset(visit, false, sizeof(visit));
	if (flag) {
		for (i = 0; i < C; i++)
			if (input[height][i] == 'x') {
				input[height][i] = '.';
				floatable(height, i);
				break;
			}
	}
	else
		for (i = C - 1; i >= 0; i--)
			if (input[height][i] == 'x') {
				input[height][i] = '.';
				floatable(height, i);
				break;
			}
}

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	int N, i, j;
	cin >> R >> C;
	for (i = 0; i < R; i++)
		for (j = 0; j < C; j++)
			cin >> input[i][j];

	cin >> N;
	for (i = 1; i <= N; i++)
		throwStick(i % 2);

	for (i = 0; i < R; i++) {
		for (j = 0; j < C; j++)
			cout << input[i][j];
		cout << '\n';
	}

	return 0;
}