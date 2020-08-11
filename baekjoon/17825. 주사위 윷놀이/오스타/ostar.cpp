#include <iostream>
#include <vector>
#include <utility>
#include <algorithm>
#define pii pair<int, int>

using namespace std;

const int maze[4][22] = {
	{ 0,  2,  4,  6,  8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30, 32, 34, 36, 38, 40, -1 },
	{10, 13, 16, 19, 25, 30, 35, 40, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
	{20, 22, 24, 25, 30, 35, 40, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
	{30, 28, 27, 26, 25, 30, 35, 40, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 }
};	// maze를 array로 정렬한것
int ans = 0, dice[10];
vector<pii> horse;

bool overlapCheck(int x, int y) {
	int posi, posj, posval, compval;
	for (int i = 0; i < 4; i++) {
		posi = horse[i].first; posj = horse[i].second;
		posval = maze[posi][posj]; compval = maze[x][y];
		if ((posi == x && posj == y && posval != -1) || (compval == posval && posval > 0 && ((x > 0 && posi > 0 && y > 2 && posj > 2) || posval == 40)))
			return true;
	}
	return false;
}

void routeSearch(int count, int sum) {
	if (count >= 10) {
		ans = max(ans, sum);
		return;
	}

	int compi, compj, nexti, nextj, compval, move = dice[count];
	for (int i = 0; i < 4; i++) {
		compi = horse[i].first; compj = horse[i].second; compval = maze[compi][compj];
		if (compval == -1)	continue;
		if ((compval == 10 || compval == 20 || compval == 30) && compi == 0) {
			nexti = compval / 10; nextj = move;
		}
		else {
			nexti = compi; nextj = min(compj + move, 21);
		}
		if (overlapCheck(nexti, nextj))	continue;
		horse[i].first = nexti; horse[i].second = nextj;
		routeSearch(count + 1, sum + ((maze[nexti][nextj] == -1) ? 0 : maze[nexti][nextj]));
		horse[i].first = compi; horse[i].second = compj;
	}
}

int main() {
	int i;
	for (i = 0; i < 10; i++)
		cin >> dice[i];
	for (i = 0; i < 4; i++)
		horse.push_back(pii(0, 0));	// 말 위치 초기화
	routeSearch(0, 0);
	cout << ans << '\n';
	return 0;
}