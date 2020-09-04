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
// a^= b^= a^= b; -> swapµÊ.
int n, m, k;
ll binom[101][101];
void make_binom()
{
	int i, j;
	for (int i = 0; i <= 100; ++i)
		binom[0][i] = binom[i][0] = 1;
	binom[1][1] = 2;
	for (i = 1; i <= 100; ++i)
		for (j = 1; j <= 100; ++j)
			if (i == 1 && j == 1) continue;
			else binom[i][j] = min(binom[i - 1][j] + binom[i][j - 1], (ll)(10e9 + 1));
}
int main()
{
	FASTIO
		cin >> n >> m >> k;
	string result;
	make_binom();

	if (k > binom[n][m])
	{
		cout << -1 << '\n';
		return 0;
	}
	else
	{
		while (n >= 1 && m >= 1)
		{
			if (k > binom[n - 1][m])
			{
				result.append("z");
				k -= binom[n - 1][m];
				m -= 1;
			}
			else
			{
				result.append("a");
				n -= 1;
			}
		}
		while(n>=1)
		{
			result.append("a");
			--n;
		}
		while(m>=1)
		{
			result.append("z");
			--m;
		}
	}
	cout << result << '\n';
	return 0;
}
