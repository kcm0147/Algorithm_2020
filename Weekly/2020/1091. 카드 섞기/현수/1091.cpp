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

int n, s[50], res = INF, p[50];
void Shuffle()
{
	int temp[50];
	for (int i = 0; i < n; ++i)
		temp[s[i]] = p[i];
	for (int i = 0; i < n; ++i)
		p[i] = temp[i];
}
bool check()
{
	for (int i = 0; i < n; ++i)
		if (p[i] != i % 3)
			return false;
	return true;
}
void search()
{
	int res = INF, cnt = 0, init[50];
	for (int i = 0; i < n; ++i) init[i] = p[i];
	while (1)
	{
		bool flag = true;
		if (check())
		{
			res = cnt;
			break;
		}
		Shuffle();
		cnt++;
		for (int i = 0; i < n; ++i)
			if (init[i] != p[i])
			{
				flag = false;
				break;
			}
		if (flag) break;
	}
	cout << (res == INF ? -1 : res) << endl;
}
void init()
{
	cin >> n;
	for (int i = 0; i < n; ++i)
		cin >> p[i];
	for (int i = 0; i < n; ++i)
		cin >> s[i];
}
int main()
{
	init();
	search();
}
