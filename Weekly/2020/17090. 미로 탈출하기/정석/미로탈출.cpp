#include <iostream>
#define MAX 500

using namespace std;
char input[MAX][MAX];
bool visit[MAX][MAX];
int possible[MAX][MAX], ans, row, col;

int escapeMaze(int startx, int starty) {
	if (startx < 0 || row <= startx || starty < 0 || col <= starty)	return 1;
	if (possible[startx][starty])	return possible[startx][starty];
	if (visit[startx][starty])	return -1;

	int curx = startx, cury = starty;
	visit[startx][starty] = true;
	switch (input[startx][starty]) {
	case 'U':
		curx--;		break;
	case 'R':
		cury++;		break;
	case 'D':
		curx++;		break;
	case 'L':
		cury--;
	}
	possible[startx][starty] = escapeMaze(curx, cury);
	if (possible[startx][starty] == 1) ans++;
	return possible[startx][starty];
}

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	int i, j;
	cin >> row >> col;
	for (i = 0; i < row; i++)
		for (j = 0; j < col; j++)
			cin >> input[i][j];
	for (i = 0; i < row; i++)
		for (j = 0; j < col; j++)
			escapeMaze(i, j);
	cout << ans << '\n';
	return 0;
}