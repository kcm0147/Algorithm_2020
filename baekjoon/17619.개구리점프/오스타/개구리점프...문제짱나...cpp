#include <iostream>
#include <algorithm>
using namespace std;

struct logInfo {
	int start;
	int fin;
	int num;
} input[100000];
int log_group[100000];

bool comp(logInfo& a, logInfo& b)
{
	return a.start < b.start;
}

void makeGrouping(int n) {
	int i, start = input[0].start, fin = input[0].fin, grouping = 1;
	log_group[input[0].num] = 1;
	for (i = 1; i < n; i++) {
		if (fin < input[i].start) {
			log_group[input[i].num] = ++grouping;
			start = input[i].start;
			fin = input[i].fin;
		}
		else {
			log_group[input[i].num] = grouping;
			fin = max(fin, input[i].fin);
		}
	}
}

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	int n, q, i, start, fin, num;
	cin >> n >> q;
	for (i = 0; i < n; i++) {
		cin >> input[i].start >> input[i].fin >> num;
		input[i].num = i;
	}
	sort(input, input + n, comp);
	makeGrouping(n);

	for (i = 0; i < q; i++) {
		cin >> start >> fin;
		if (log_group[start - 1] == log_group[fin - 1])
			cout << 1 << "\n";
		else
			cout << 0 << "\n";
	}
	return 0;
}