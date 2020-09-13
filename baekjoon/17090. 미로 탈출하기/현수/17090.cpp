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

int n, m, res;
string board[600];
int visit[600][600];
bool isBorder(int x, int y)
{
	return (x >= 0 && x < n && y >= 0 && y < m);
}
pii move(int x, int y)
{
	if (board[x][y] == 'U')	x -= 1;
	else if (board[x][y] == 'D') x += 1;
	else if (board[x][y] == 'L') y -= 1;
	else if (board[x][y] == 'R') y += 1;
	return pii(x, y);
}
int dfs(int x, int y)
{
	if (!isBorder(x, y) || visit[x][y] == 2) return 1;
	if (visit[x][y] == 1) return 0;
	visit[x][y] = 1;

	pii moved = move(x, y);
	int nx = moved.first, ny = moved.second;
	
	if (dfs(nx, ny))
	{
		visit[x][y] = 2;
		return 1;
	}
	return 0;
}
int main()
{
	cin >> n >> m;
	for (int i = 0; i < n; ++i) cin >> board[i];
	for (int i = 0; i < n; ++i)
		for (int j = 0; j < m; ++j)
			res += dfs(i, j);
	cout << res << endl;
}
