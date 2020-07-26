#include <iostream>
#include <queue>
#include <stack>
#include <vector>
#include <list>
#include <functional>
#include <algorithm>
#include <string>
#include <map>
#include <set>
#include <bitset>
#include <unordered_map>
#include <unordered_set>
#include <cstring>
#include <cmath>
#include <cstdio>
#define FASTIO ios_base::sync_with_stdio(false); cin.tie(NULL); cout.tie(NULL);
#define ll long long
#define pii pair<int, int>
#define pll pair<ll, ll>
#define INF 987654321
using namespace std;
int n, m;
char color;
ll res = 0;
string board[2001];
queue<pii> q;
bool touch_board(int x, int y)
{
	if (x < 0 || x >= n || y < 0 || y >= m) return true;
	if (board[x][y] == color) return true;
	return false;
}
bool is_isolated(int x, int y)
{
	if (touch_board(x - 1, y) && touch_board(x + 1, y) &&
		touch_board(x, y - 1) && touch_board(x, y + 1))
		return true;
	return false;
}
void init()
{
	FASTIO
	cin >> n >> m;
	for (int i = 0; i < n; ++i)
		cin >> board[i];
	for (int i = 0; i < n; ++i)
		for (int j = 0; j < m; ++j)
		{
			color = board[i][j];
			if (is_isolated(i, j))
				res++;
		}
}
void calc()
{
	int cnt = 1;
	while (res > 0)
	{
		cnt *= 2;
		cnt %= 1000000007;
		res--;
	}
	cout << cnt << endl;
}
int main()
{
	init();
	calc();
}
