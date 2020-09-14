#include <iostream>
#include <algorithm>
#define MAX 500

using namespace std;
int accsum[MAX][MAX], dp[MAX][MAX], n;

void accumulation() {
	for (int i = 0; i < n; i++)
		for (int j = i + 1; j < n; j++)
			accsum[i][j] = accsum[i][j - 1] + accsum[j][j];
}

void dpsum() {
	int diagonal = 0, maxj = n - 1, maxk, i, j, k, comp;
	for (i = 1; i < n; i++) {
		diagonal++;
		for (j = 0; j < maxj; j++) {
			maxk = j + diagonal;
			comp = dp[j][j] + dp[j + 1][maxk];
			for (k = j + 1; k < maxk; k++)
				comp = min(comp, dp[j][k] + dp[k + 1][maxk]);
			dp[j][maxk] = comp + accsum[j][maxk];
		}
		maxj--;
	}
	cout << dp[0][n - 1] << '\n';
}

void filetest() {
	cin >> n;
	for (int i = 0; i < n; i++)
		cin >> accsum[i][i];
	accumulation();
	dpsum();
}

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	int tc;
	cin >> tc;
	while (tc--)
		filetest();

	return 0;
}