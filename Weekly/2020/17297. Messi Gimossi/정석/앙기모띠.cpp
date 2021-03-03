#include <iostream>
#include <vector>
#include <string>

using namespace std;
string mes = "Messi Gimossi";
vector<int> dp;
int input;

void makeDp() {
	dp.push_back(5);	dp.push_back(13);
	int count = 1;
	while (dp[count++] < input)
		dp.push_back(dp[count - 1] + dp[count - 2] + 1);
}

char gimossiTest() {
	int size = dp.size();
	while (input > 13) {
		for (int i = 2; i < size; i++)
			if (dp[i] >= input) {
				input -= dp[i - 1] + 1;
				break;
			}
		if (!input) return ' ';
	}
	return mes[input - 1];
}

int main() {
	cin >> input;
	makeDp();
	char ans = gimossiTest();
	if (ans == ' ') cout << "Messi Messi Gimossi" << '\n';
	else cout << ans << '\n';
	return 0;
}