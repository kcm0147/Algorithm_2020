#include <iostream>
#include <queue>
#include <string>
#include <cstring>
#define FASTIO ios_base::sync_with_stdio(false); cin.tie(NULL); cout.tie(NULL);
#define ll long long
#define pii pair<int, int>
#define pll pair<ll, ll>
#define INF 987654321
using namespace std;
int m, n;
int dx[4] = { -1, 0, 1, 0 }, dy[4] = { 0, 1, 0, -1 }, visit[50][50][50][50];
string board[50], dir[4] = { "N", "E", "S", "W" };
struct pos
{
	pii m1, m2;
	string chunk;
};
void outControl(int* x, int* y)
{
	// 상-우-하-좌
	if (*x < 0) *x += m;
	else if (*y >= n) *y -= n;
	else if (*x >= m) *x -= m;
	else if(*y < 0) *y += n;
}
string play(queue<pos> move)
{
	while (!move.empty())
	{
		pos round = move.front(); move.pop();
		pii pack1 = round.m1, pack2 = round.m2;
		string chunk = round.chunk;
		int x1 = pack1.first, y1 = pack1.second, x2 = pack2.first, y2 = pack2.second;
		visit[x1][y1][x2][y2] = 0;
		for (int i = 0; i < 4; ++i)
		{
			/* b1 이동 */
			int nx = x1 + dx[i], ny = y1 + dy[i];
			outControl(&nx, &ny);
			if (board[nx][ny] == 'G') continue;
			if (board[nx][ny] == 'X')  
			{
				nx = x1;
				ny = y1;
			}

			/* b2 이동 */
			int mx = x2 + dx[i], my = y2 + dy[i];
			outControl(&mx, &my);
			if (board[mx][my] == 'G') continue;
			if (board[mx][my] == 'X')
			{
				mx = x2;
				my = y2;
			}
			if (visit[nx][ny][mx][my] <= visit[x1][y1][x2][y2] + 1)
				continue; // 방문한 곳이면 재방문 X, 벽이 있는 위치는 방문할 수 없으므로 제자리 위치 가능

			/* 방문 체크 */
			visit[nx][ny][mx][my] = visit[x1][y1][x2][y2] + 1;

			/* 합 체 ! */
			if (nx == mx && ny == my)
				return chunk + dir[i];
			
			move.push({ pii(nx, ny), pii(mx, my), chunk + dir[i]});
		}
	}
	return "";
}
int main()
{
	int tc;
	FASTIO
	cin >> tc;
	while (tc-- > 0)
	{
		pii b1 = { 0, 0 }, b2 = { 0, 0 };
		cin >> m >> n;
		for (int i = 0; i < 50; ++i)
			for (int j = 0; j < 50; ++j)
				for (int k = 0; k < 50; ++k)
					for (int l = 0; l < 50; ++l) visit[i][j][k][l] = INF;
		for (int i = 0; i < m; ++i)
		{
			cin >> board[i];
			for (int j=0; j<n; ++j)
			{
				if (board[i][j] == 'P')
				{
					if (b1.first == 0 && b1.second == 0) b1 = pii(i, j);
					else b2 = pii(i, j);
				}
			}
		}
		queue<pos> move;
		move.push({ { b1.first, b1.second }, { b2.first, b2.second }, ""});
		string ans = play(move);
		int len = ans.length();
		if(len) cout << len << ' ' << ans << '\n';
		else cout << "IMPOSSIBLE" << '\n';
	}
}
