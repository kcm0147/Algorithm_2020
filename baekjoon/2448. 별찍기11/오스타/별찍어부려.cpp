#include <iostream>

using namespace std;

void drawStar(int row, int col, int curR) {
	if (row <= 3) {
		switch (curR % row) {
		case 0:	cout << "  *  ";	break;
		case 1: cout << " * * ";	break;
		case 2: cout << "*****";
		}
		return;
	}

	int compr = row / 2, comp4c = col / 4, comp2c = col / 2, comp3c = comp4c + comp2c;
	if (curR >= compr) {
		drawStar(compr, comp2c, curR % compr);
		cout << ' ';
		drawStar(compr, comp2c, curR % compr);
	}
	else
		for (int j = 0; j < col; j++)
			if (j <= comp4c || comp3c < j) cout << " ";
			else {
				drawStar(compr, comp2c, curR);
				cout << " ";
				j += comp2c;
			}
}

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	int totalR, totalC;
	cin >> totalR;
	totalC = totalR * 2 - 1;
	for (int i = 0; i < totalR; i++) {
		drawStar(totalR, totalC, i);
		cout << '\n';
	}

	return 0;
}