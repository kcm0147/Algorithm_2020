#include <iostream>
#include <utility>
#include <algorithm>
#include <vector>
#define pii pair<int, int>
#define vec vector<pii>
#define vec2 vector<vector<pii>>

using namespace std;
int di[8] = { -1, -1, 0, 1, 1, 1, 0, -1 }, dj[8] = { 0, -1, -1, -1, 0, 1, 1, 1 }, ans;

void sharkMove(vec2 map, vec fish, int x, int y, int sum) {
	int i, j, count = 0, feed = map[x][y].first, dir, num, curx, cury, cmpdir, cmpx, cmpy, chnum;
	sum += feed;
	ans = max(ans, sum);
	map[x][y] = pii(-2, map[x][y].second);
	fish[feed] = pii(-1, -1);
	for (i = 1; i < 17; i++) {
		curx = fish[i].first;	cury = fish[i].second;
		if (curx == -1) continue;
		dir = map[curx][cury].second;
		num = map[curx][cury].first;
		for (j = 0; j < 8; j++) {
			cmpdir = (dir + j) % 8;
			cmpx = curx + di[cmpdir];	cmpy = cury + dj[cmpdir];
			if (0 <= cmpx && cmpx < 4 && 0 <= cmpy && cmpy < 4 && map[cmpx][cmpy].first != -2) {
				fish[map[curx][cury].first] = pii(cmpx, cmpy);
				chnum = map[cmpx][cmpy].first;
				if (chnum != -1)	fish[chnum] = pii(curx, cury);
				map[curx][cury] = map[cmpx][cmpy];
				map[cmpx][cmpy] = pii(num, cmpdir);
				break;
			}
		}
	}
	map[x][y].first = -1;
	dir = map[x][y].second;
	while (1) {
		x += di[dir];		y += dj[dir];
		if (x < 0 || 4 <= x || y < 0 || 4 <= y)	break;
		if (map[x][y].first != -1) {
			sharkMove(map, fish, x, y, sum);
		}
	}
}

int main() {
	vec2 map; vec fish;
	int num, dir, i, j;
	fish.resize(17);
	map.resize(4);
	for (i = 0; i < 4; i++)
		map[i].resize(4);
	for (i = 0; i < 4; i++)
		for (j = 0; j < 4; j++) {
			cin >> num >> dir;
			map[i][j] = pii(num, dir - 1);
			fish[num] = pii(i, j);
		}
	sharkMove(map, fish, 0, 0, 0);
	cout << ans << '\n';
	return 0;
}