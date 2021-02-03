#include <iostream>
#include <algorithm>
#define MAX 100000

using namespace std;
int input[MAX], ans;

void maxArea(int fir, int fin) {
	if (fir >= fin) return;

	int mid = (fir + fin) / 2, minval = input[mid], left = mid - 1, right = mid + 1, count = 1;
	ans = max(ans, minval);
	while (left >= fir || right < fin) {
		if (right >= fin || input[left] > input[right]) {
			minval = min(minval, input[left--]);
			ans = max(ans, minval * (++count));
		}
		else {
			minval = min(minval, input[right++]);
			ans = max(ans, minval * (++count));
		}
	}
	maxArea(fir, mid);
	maxArea(mid + 1, fin);
}

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	int n;
	cin >> n;
	for (int i = 0; i < n; i++)
		cin >> input[i];
	maxArea(0, n);
	cout << ans << '\n';
	return 0;
}