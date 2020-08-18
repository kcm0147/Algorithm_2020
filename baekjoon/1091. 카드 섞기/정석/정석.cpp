#include <iostream>
#include <vector>
#include <algorithm>
#include <string>
#include <set>

using namespace std;
vector<vector<int>> real;
set<string> record;
int* P, * S, N, ans;

int shuffleCard(int card1[], int card2[]) {
	int i, comp;
	string result = "";
	for (i = 0; i < N; i++) {
		comp = i % 3;
		if (find(real[comp].begin(), real[comp].end(), card1[i]) == real[comp].end())
			break;

	}
	if (i == N)		return 1;
	for (i = 0; i < N; i++)
		result += to_string(card1[i]);
	if (record.find(result) != record.end())	return -1;

	record.insert(result);
	for (i = 0; i < N; i++)
		card2[S[i]] = card1[i];
	return -2;
}

void realDetect() {
	real.resize(3);
	for (int i = 0; i < N; i++)
		real[P[i]].push_back(i);
}

int main() {
	int i, res;
	cin >> N;
	P = new int[N];	S = new int[N];
	int* card1 = new int[N], * card2 = new int[N];
	for (i = 0; i < N; i++) {
		cin >> P[i];
		card1[i] = i;
	}
	for (i = 0; i < N; i++)
		cin >> S[i];
	realDetect();

	while (1) {
		res = shuffleCard(card1, card2);
		if (res != -2) {
			cout << ((res == -1) ? -1 : ans) << '\n';
			break;
		}
		ans++;
		res = shuffleCard(card2, card1);
		if (res != -2) {
			cout << ((res == -1) ? -1 : ans) << '\n';
			break;
		}
		ans++;
	}

	return 0;
}
