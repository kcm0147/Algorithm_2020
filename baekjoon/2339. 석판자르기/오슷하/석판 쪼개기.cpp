#include <iostream>
#include <utility>
#include <vector>
#define pii pair<int, int>

using namespace std;

int input[20][20];

int divSearch(pii rangeX, pii rangeY, bool vert) {
	int i, j, count = 0, impuresize, compx, compy, cur = 0;
	vector<pii> impure;
	for (i = rangeX.first; i < rangeX.second; i++)
		for (j = rangeY.first; j < rangeY.second; j++) {
			if (input[i][j] == 1) impure.push_back(pii(i, j));
			else if (input[i][j] == 2) count++;
		}
	impuresize = impure.size();
	if (count == 1 && !impuresize)
		return 1;
	else if ((count > 1 && !impuresize) || !count)
		return 0;

	for (i = 0; i < impuresize; i++) {
		compx = impure[i].first; compy = impure[i].second;
		if (vert) {
			for (j = rangeX.first; j < rangeX.second; j++)
				if (input[j][compy] == 2) break;
			if (j == rangeX.second)
				cur += divSearch(rangeX, pii(rangeY.first, compy), false) * divSearch(rangeX, pii(compy + 1, rangeY.second), false);
		}
		else {
			for (j = rangeY.first; j < rangeY.second; j++)
				if (input[compx][j] == 2) break;
			if (j == rangeY.second)
				cur += divSearch(pii(rangeX.first, compx), rangeY, true) * divSearch(pii(compx + 1, rangeX.second), rangeY, true);
		}
	}
	return cur;
}

int main() {
	int size, ans;
	cin >> size;

	for (int i = 0; i < size; i++)
		for (int j = 0; j < size; j++)
			cin >> input[i][j];
	ans = divSearch(pii(0, size), pii(0, size), false) + divSearch(pii(0, size), pii(0, size), true);
	cout << ((ans == 0) ? -1 : ans) << '\n';
	return 0;
}