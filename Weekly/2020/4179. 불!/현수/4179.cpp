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
#include <tuple>
#define FASTIO ios_base::sync_with_stdio(false); cin.tie(NULL); cout.tie(NULL);
#define ll long long
#define pii pair<int, int>
#define pll pair<ll, ll>
#define INF -987654321
#define endl '\n'
using namespace std;

int n, m;
string board[1001];
bool visited[1001][1001];
int dx[4] = { -1, 0, 1, 0 };
int dy[4] = { 0, 1, 0, -1 };
pii jihun;
queue<pii> fire;
bool isExit(int x, int y)
{
	return (x < 0 || x >= n || y < 0 || y >= m);
}
bool canBurn(int x, int y)
{
	if (board[x][y] == '#' || board[x][y] == 'F') return false;
	return true;
}
bool canGo(int x, int y)
{
	if (visited[x][y] == false && board[x][y] == '.') return true;
	return false;
}
void init()
{
	cin >> n >> m;
	for (int i = 0; i < n; ++i)
	{
		cin >> board[i];
		for (int j = 0; j < m; ++j)
			if (board[i][j] == 'J') jihun = pii(i, j);
			else if (board[i][j] == 'F') fire.push(pii(i, j));
	}
}
void calc()
{
	int golden_time = 0;
	queue<pii> p;
	pii f;
	p.push(jihun);
	while (!p.empty())
	{
		int f_cnt = fire.size();
		for (int i = 0; i < f_cnt; ++i)
		{
			f = fire.front(); fire.pop();
			for (int j = 0; j < 4; ++j)
			{
				int nx = f.first + dx[j], ny = f.second + dy[j];
				if (!isExit(nx, ny) && canBurn(nx, ny))
				{
					board[nx][ny] = 'F';
					fire.push(pii(nx, ny));
				}
			}
		}

		int j_cnt = p.size();
		//vector<pii> jihun_can_go;
		for (int i = 0; i < j_cnt; ++i)
		{
			jihun = p.front(); p.pop();
			visited[jihun.first][jihun.second] = true;
			for (int j = 0; j < 4; ++j)
			{
				int nx = jihun.first + dx[j], ny = jihun.second + dy[j];
				if (isExit(nx, ny))
				{
					cout << golden_time + 1 << endl;
					return;
				}
				if (canGo(nx, ny))
				{
					visited[nx][ny] = true;
					p.push(pii(nx, ny));
				}
			}
		}
		golden_time++;
	}
	cout << "IMPOSSIBLE" << endl;
	return;
}
int main()
{
	init();
	calc();
}
