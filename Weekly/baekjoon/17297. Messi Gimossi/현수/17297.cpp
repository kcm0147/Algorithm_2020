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

int main()
{
	string ans = "Messi Gimossi";
	int n, idx = 3, arr[46], comp; cin >> n;
	memset(arr, 0, sizeof(arr));
	arr[0] = 0;  arr[1] = 5; arr[2] = 13;
	
	/* Fibonacci */
	while (1)
	{
		arr[idx] = arr[idx - 1] + arr[idx - 2] + 1;
		if (arr[idx] > n) break;
		idx++;
	}
	comp = arr[idx]; 
	while (n > 13)
	{
		while (n < comp) comp = arr[--idx] + 1;
		n -= comp;
	}

	if (n == 0 || n == 6)
		cout << "Messi Messi Gimossi" << endl;
	else cout << ans[n-1] << endl;
}
