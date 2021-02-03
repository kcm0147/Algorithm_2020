#include <iostream>
#include <queue>

using namespace std;

bool map[33][33];
int di[3] = { 0, 1, 1 }, dj[3] = { 1, 1, 0 }, mapsize;
long long dp[3][33][33];

bool path_availability_test(int x_pos, int y_pos, int dir) {
	int comp_x, comp_y, i;
	if (dir == 1) {
		for (i = 0; i < 3; i++) {
			comp_x = x_pos + di[i];
			comp_y = y_pos + dj[i];
			if (0 >= comp_x || comp_x > mapsize || 0 >= comp_y || comp_y > mapsize || map[comp_x][comp_y])
				break;
		}
		if (i >= 3)
			return true;
	}
	else {
		comp_x = x_pos + di[dir];
		comp_y = y_pos + dj[dir];
		if (0 < comp_x && comp_x <= mapsize && 0 < comp_y && comp_y <= mapsize && !map[comp_x][comp_y])
			return true;
	}
	return false;
}

long long dfs(int x_pos, int y_pos, int dir) {
	if (dp[dir][x_pos][y_pos])
		return dp[dir][x_pos][y_pos];

	int i;
	switch (dir) {
	case 0:
		for (i = 0; i < 2; i++)
			if (path_availability_test(x_pos, y_pos, i))
				dp[dir][x_pos][y_pos] += dfs(x_pos + di[i], y_pos + dj[i], i);
		break;
	case 1:
		for (i = 0; i < 3; i++)
			if (path_availability_test(x_pos, y_pos, i))
				dp[dir][x_pos][y_pos] += dfs(x_pos + di[i], y_pos + dj[i], i);
		break;
	case 2:
		for (i = 1; i < 3; i++)
			if (path_availability_test(x_pos, y_pos, i))
				dp[dir][x_pos][y_pos] += dfs(x_pos + di[i], y_pos + dj[i], i);
	}
	return dp[dir][x_pos][y_pos];
}

int main() {
	int i, j;
	cin >> mapsize;
	for (i = 0; i < 3; i++)
		dp[i][mapsize][mapsize] = 1;

	for (i = 1; i <= mapsize; i++)
		for (j = 1; j <= mapsize; j++)
			cin >> map[i][j];
	cout << dfs(1, 2, 0) << "\n";
	return 0;
}