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
int n, m, tc;
int chap[501], sum[501][501], pSum[501];
void init()
{
	cin >> n;
	memset(chap, 0, sizeof(chap));
	memset(sum, -1, sizeof(sum));
	for (int i = 0; i < n; ++i)
		cin >> chap[i];
	pSum[0] = 0;
	for (int i = 1; i <= n; ++i)
		pSum[i] = pSum[i - 1] + chap[i - 1];
}
int dp(int start, int end)
{
	if (start == end) return chap[start];
	int &ret = sum[start][end];
	if (ret != -1) return ret;
	ret = INF;
	int plus = pSum[end + 1] - pSum[start];
	for (int i = start; i <= end - 1; ++i)
		ret = min(ret, dp(start, i) + dp(i + 1, end) + plus);
	return ret;
}
int main()
{
	FASTIO
		cin >> tc;
	while (tc-- > 0)
	{
		init();
		int res = INF;
		for (int i = 0; i < n - 1; ++i)
			res = min(res, dp(0, i) + dp(i + 1, n - 1));
		cout << res << endl;
	}
}
