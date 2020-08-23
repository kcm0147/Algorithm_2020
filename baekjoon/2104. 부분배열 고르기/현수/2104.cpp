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

ll tree[400001], a[100002], n;
void init()
{
	FASTIO
		cin >> n;
	for (int i = 1; i <= n; ++i) cin >> a[i];
}
ll treeInit(int start, int end, int node)
{
	if (start == end) return tree[node] = a[start];
	int mid = (start + end) / 2;
	return tree[node] = treeInit(start, mid, node * 2) + treeInit(mid + 1, end, node * 2 + 1);
}
ll sum(int start, int end, int left, int right, int node)
{
	if (end < left || right < start) return 0;
	if (left <= start && end <= right) return tree[node];
	int mid = (start + end) / 2;
	return sum(start, mid, left, right, node * 2) + sum(mid + 1, end, left, right, node * 2 + 1);
}
ll calc(ll left, ll right)
{
	if (left == right) return a[left] * a[left];
	ll mid = (left + right) / 2, low = mid, high = mid + 1;
	ll max_value = max(calc(left, mid), calc(mid + 1, right));
	ll height = min(a[low], a[high]);

	max_value = max(max_value, height * (a[low] + a[high]));

	while (left < low || right > high)
	{
		if (high < right && (low == left || a[low - 1] < a[high + 1]))
		{
			++high;
			height = min(height, a[high]);
		}
		else
		{
			--low;
			height = min(height, a[low]);
		}
		max_value = max(max_value, height * sum(1, n, low, high, 1));
	}
	return max_value;
}
int main()
{
	init();
	treeInit(1, n, 1);
	cout << calc(1, n) << endl;
}
