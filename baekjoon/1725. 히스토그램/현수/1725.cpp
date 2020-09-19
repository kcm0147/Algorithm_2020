#include <iostream>
#include <queue>
#include <stack>
#include <vector>
#include <list>
#include <functional>
#include <algorithm>
#include <string>
#include <cstring>
#include <cmath>
#include <map>
#include <set>
#include <bitset>
#include <unordered_map>
#include <unordered_set>
#define FASTIO ios_base::sync_with_stdio(false); cin.tie(NULL); cout.tie(NULL);
#define ll long long
#define pii pair<int, int>
#define pll pair<ll, ll>
#define INF 987654321
using namespace std;
int n, arr[100001], ans;
int calc(int start, int end)
{
	if (start >= end) return arr[start];
	int mid = (start + end) / 2, ret = max(calc(start, mid), calc(mid + 1, end));
	int left = mid, right = mid + 1, height = min(arr[left], arr[right]);
	ret = max(ret, 2 * height);
	while (left > start || right < end)
	{
		if (right < end && (left == start || arr[left - 1] < arr[right + 1]))
		{
			right++;
			height = min(height, arr[right]);
		}
		else
		{
			left--;
			height = min(height, arr[left]);
		}
		ret = max(ret, (right - left + 1) * height);
	}
	return ret;
}
int main()
{
	FASTIO
		cin >> n;
	for (int i = 0; i < n; ++i) cin >> arr[i];
	cout << calc(0, n - 1) << endl;
}
