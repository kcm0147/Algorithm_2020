#include <iostream>

using namespace std;
int sudoku[9][9], empary[5][2];

bool sudokuValidity() {
	int i, j, k, l, comp;
	bool visit[10];
	for (i = 0; i < 9; i++) {
		fill_n(visit, 10, false);
		for (j = 0; j < 9; j++) {
			comp = sudoku[i][j];
			if (comp && visit[comp]) return false;
			visit[comp] = true;
		}
	}
	for (i = 0; i < 9; i++) {
		fill_n(visit, 10, false);
		for (j = 0; j < 9; j++) {
			comp = sudoku[j][i];
			if (comp && visit[comp]) return false;
			visit[comp] = true;
		}
	}
	for (i = 0; i < 3; i++)
		for (j = 0; j < 3; j++) {
			fill_n(visit, 10, false);
			for (k = 0; k < 3; k++)
				for (l = 0; l < 3; l++) {
					comp = sudoku[i * 3 + k][j * 3 + l];
					if (comp && visit[comp]) return false;
					visit[comp] = true;
				}
		}
	return true;
}

bool fillBlank(int count) {
	if (count >= 5) return true;

	bool usable[10];
	int empx = empary[count][0], empy = empary[count][1], i, j, boxr = empx / 3 * 3, boxc = empy / 3 * 3;
	fill_n(usable, 10, true);
	for (i = 0; i < 9; i++) {
		usable[sudoku[empx][i]] = false;
		usable[sudoku[i][empy]] = false;
	}
	for (i = 0; i < 3; i++)
		for (j = 0; j < 3; j++)
			usable[sudoku[boxr + i][boxc + j]] = false;
	for (i = 1; i < 10; i++)
		if (usable[i]) {
			sudoku[empx][empy] = i;
			if (fillBlank(count + 1))	return true;
		}
	return false;
}

void testCase() {
	int i, j, count = 0;
	char num;
	for (i = 0; i < 9; i++)
		for (j = 0; j < 9; j++) {
			cin >> num;
			sudoku[i][j] = num - '0';
			if (!sudoku[i][j]) {
				empary[count][0] = i;
				empary[count++][1] = j;
			}
		}
	if (sudokuValidity() && fillBlank(0))
		for (i = 0; i < 9; i++) {
			for (j = 0; j < 9; j++)
				cout << sudoku[i][j];
			cout << '\n';
		}
	else	cout << "Could not complete this grid." << '\n';
	cout << '\n';
}

int main() {
	ios_base::sync_with_stdio(false);	cin.tie(NULL);	cout.tie(NULL);
	int tc;
	cin >> tc;
	while (tc--)
		testCase();
	return 0;
}