#include <iostream>
#include <algorithm>
#define MAXSIZE 100000
#define ll long long
using namespace std;
int input[MAXSIZE];
ll ans = 0;

void maxCalcul(int fir, int last) {
	if (fir == last) {
		ans = max(ans, (ll)input[fir] * (ll)input[fir]);
		return;
	}

	int mid = (fir + last) / 2, minval = input[mid], left = mid, right = mid;
	ll psum = minval;
	while (left > fir || right < last - 1) {
		if (right < last - 1 && (left <= fir || input[left - 1] < input[right + 1])) {
			right++;
			psum += input[right];
			minval = min(minval, input[right]);
		}
		else {
			left--;
			psum += input[left];
			minval = min(minval, input[left]);
		}
		ans = max(ans, minval * psum);
	}

	maxCalcul(fir, mid);
	maxCalcul(mid + 1, last);
}

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);

	int size;
	cin >> size;
	for (int i = 0; i < size; i++)
		cin >> input[i];
	maxCalcul(0, size);
	cout << ans << '\n';
	return 0;
}