#include <iostream>
#define MAX 1000000007

using namespace std;

char input[2000][2000], di[4] = { 0, 1, 0, -1 }, dj[4] = { 1, 0, -1, 0 };

int main() {
	long long answer = 1;
	int n, m, i, j, k, count = 0, comp_i, comp_j;
	char cur_char;

	cin >> n >> m;
	for (i = 0; i < n; i++)
		for (j = 0; j < m; j++)
			cin >> input[i][j];
	for (i = 0; i < n; i++)
		for (j = 0; j < m; j++) {
			cur_char = input[i][j];
			for (k = 0; k < 4; k++) {
				comp_i = i + di[k];
				comp_j = j + dj[k];
				if (0 <= comp_i && comp_i < n && 0 <= comp_j && comp_j < m && cur_char != input[comp_i][comp_j])
					break;
			}
			if (k >= 4)
				count++;
		}
	for (i = 0; i < count; i++) {
		answer *= 2;
		if (answer >= MAX)
			answer %= MAX;
	}
	cout << answer << "\n";
	return 0;
}