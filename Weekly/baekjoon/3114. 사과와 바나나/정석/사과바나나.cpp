#include <iostream>
#include <cstdlib>
#include <algorithm>

using namespace std;
int a[1500][1500], b[1500][1500], dp[1500][1500], inputnum[1500][1500];
char input[1500][1500];

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);

	int row, col, i, j;
	cin >> row >> col;

	for (i = 0; i < row; i++) {
		for (j = 0; j < col; j++) {
			cin >> input[i][j] >> inputnum[i][j];
			(input[i][j] == 'A' ? a[i][j] : b[i][j]) += inputnum[i][j];
		}
	}
	for (j = 0; j < col; j++) {
		for (i = 0; i < row - 1; i++) {
			a[row - i - 2][j] += a[row - i - 1][j];
			b[i + 1][j] += b[i][j];
		}
	}
	for (i = 0; i < row; i++)
		dp[i][0] = a[i][0] + b[i][0] - inputnum[i][0];
	for (j = 1; j < col; j++)
		dp[0][j] = dp[0][j - 1] + a[0][j] + b[0][j] - inputnum[0][j];

	for (i = 1; i < row; i++)
		for (j = 1; j < col; j++) {
			dp[i][j] = max(dp[i - 1][j - 1], dp[i][j - 1]) + a[i][j] + b[i][j] - inputnum[i][j];
			dp[i][j] = max(dp[i][j], dp[i - 1][j] - ((input[i][j] == 'A') ? inputnum[i][j] : 0));
		}

	cout << dp[row - 1][col - 1] << endl;
	return 0;
}