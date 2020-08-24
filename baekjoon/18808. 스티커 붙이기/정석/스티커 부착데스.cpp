#include <iostream>
#include <algorithm>
#include <vector>
#define vec2 vector<vector<int>>

using namespace std;
int input[40][40], row, col, st_row, st_col;

vec2 stickerRotate(vec2 comp) {
	vec2 comp2;
	comp2.resize(st_col);
	for (int i = 0; i < st_col; i++) {
		comp2[i].resize(st_row);
		for (int j = 0; j < st_row; j++)
			comp2[i][j] = comp[st_row - j - 1][i];
	}
	swap(st_row, st_col);
	return comp2;
}

bool locateCheck(vec2 comp, int row, int col) {
	for (int i = 0; i < st_row; i++)
		for (int j = 0; j < st_col; j++)
			if (input[row + i][col + j] && comp[i][j]) return false;
	return true;
}

void stickerAttach(vec2 comp, int row, int col) {
	for (int i = 0; i < st_row; i++)
		for (int j = 0; j < st_col; j++)
			input[row + i][col + j] = input[row + i][col + j] | comp[i][j];
}

bool stickerLocation(vec2 comp) {
	for (int i = 0; i <= row - st_row; i++)
		for (int j = 0; j <= col - st_col; j++)
			if (locateCheck(comp, i, j)) {
				stickerAttach(comp, i, j);
				return true;
			}
	return false;
}

void stickerProcess() {
	vec2 comp, comp2;
	cin >> st_row >> st_col;
	comp.resize(st_row);
	for (int i = 0; i < st_row; i++) {
		comp[i].resize(st_col);
		for (int j = 0; j < st_col; j++)
			cin >> comp[i][j];
	}
	if (stickerLocation(comp)) return;
	comp2 = stickerRotate(comp);
	if (stickerLocation(comp2)) return;
	comp = stickerRotate(comp2);
	if (stickerLocation(comp)) return;
	comp2 = stickerRotate(comp);
	if (stickerLocation(comp2)) return;
}

int stickerCount() {
	int count = 0;
	for (int i = 0; i < row; i++)
		for (int j = 0; j < col; j++)
			if (input[i][j]) count++;
	return count;
}

int main() {
	int stickersize;
	cin >> row >> col >> stickersize;
	while (stickersize--)
		stickerProcess();
	cout << stickerCount() << '\n';
	return 0;
}