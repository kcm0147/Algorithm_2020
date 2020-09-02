#include <iostream>
#include <string>
#include <algorithm>
#define MAX 1000000001

using namespace std;
int dp[101][101] = { 1, }, order;
int a, z;

string makeStr() {
	string ans = "";
	int acc = 0;

	if (dp[z][a] < order)	return "-1";
	while (a) {
		for (int j = 0; j <= a; j++) {
			if (acc + dp[z][j] >= order) {
				if (j >= a) {
					ans += 'z';
					acc += dp[z][j - 1];
					z--;
				}
				else {
					ans += 'a';
					a--;
				}
				break;
			}
		}
	}
	while (z--)
		ans += 'z';
	return ans;
}

int main() {
	int i, j;
	cin >> a >> z >> order;
	for (i = 1; i <= a; i++)
		dp[0][i] = 1;
	for (i = 1; i <= z; i++)
		dp[i][0] = 1;
	for (i = 1; i <= z; i++)
		for (j = 1; j <= a; j++)
			dp[i][j] = min(MAX, dp[i - 1][j] + dp[i][j - 1]);
	cout << makeStr() << '\n';
	return 0;
}