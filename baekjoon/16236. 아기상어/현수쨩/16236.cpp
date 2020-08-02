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
#define INF 987654321
#define endl '\n'
using namespace std;
int n, shark_size = 2, shark_eat, dist_sum;
int board[21][21], fishes[7];
bool visited[21][21], eaten[21][21];
int dx[4] = { -1, 0 , 0, 1 }, dy[4] = { 0, -1, 1, 0 };
pii shark_pos;
struct info
{
	int dist, x, y;
	info(int d, int _x, int _y) : dist(d), x(_x), y(_y) {}
};

bool comp (const info& a, const info& b)
{
	return make_tuple(a.dist, a.x, a.y) < make_tuple(b.dist, b.x, b.y);
}
bool isBorder(int x, int y)
{
	return (x >= 0 && x < n && y >= 0 && y < n);
} 
void init()
{
	FASTIO
		cin >> n;
	memset(board, 0, sizeof(board));
	memset(visited, 0, sizeof(visited));
	memset(eaten, 0, sizeof(eaten));

	for (int i = 0; i < n; ++i)
		for (int j = 0; j < n; ++j)
		{
			cin >> board[i][j];
			if (board[i][j] == 9)
			{
				shark_pos = pii(i, j);
				visited[i][j] = true;
			}
			else
				fishes[board[i][j]] += 1;
		}
}
info can_shark_survive()
{
	vector<info> canEat; // (°Å¸®, xÁÂÇ¥, yÁÂÇ¥)
	queue<pii> shark;
	shark.push(shark_pos);
	int dist = 1;
	
	while (!shark.empty())
	{
		int size = shark.size();
		for (int j = 0; j < size; ++j)
		{
			pii cur_shark = shark.front(); shark.pop();
			for (int i = 0; i < 4; ++i)
			{
				int nx = cur_shark.first + dx[i],
					ny = cur_shark.second + dy[i];
				int fish_size = board[nx][ny];

				if (!isBorder(nx, ny) || visited[nx][ny] || fish_size > shark_size) continue;
				
				if (fish_size > 0 && fish_size < shark_size && !eaten[nx][ny])
				{
					canEat.push_back(info(dist, nx, ny));
					continue;
				}
				// ±Í¿©¿î ¶Ñ·ç·ç¶Ñ¶Ñ ¹Ù´å¼Ó ¶Ñ·ç¶Ñ·ç·ç ¾Æ±â»ó¾îº¸´Ù ÀÛÀº °í±â°¡ ÀÖ´Ù¸é
				// À§, ¿Þ, ¾Æ·¡, ¿À¸¥ ¼øÀ¸·Î °¡Àå ¸ÕÀú ¹ß°ßÇÑ ³à¼® ¸Ô¾î¹ö¸²!

				if (!visited[nx][ny])
				{
					visited[nx][ny] = true;
					shark.push(pii(nx, ny));
				}
			}
		}
		dist++;
	}
	if (canEat.size() > 0)
	{
		sort(canEat.begin(), canEat.end(), comp);
		return canEat[0];
	}
	return info(0, 0, 0); // ¸ÔÀÌ°¡ ¾øÀ¸¸é ¾Æ±â »ó¾î´Â ±¾¾î Á×´Â´Ù.
}
void calc()
{
	int start_idx = 1;
	board[shark_pos.first][shark_pos.second] = 0;
	while (1)
	{
		info result = can_shark_survive();
		memset(visited, 0, sizeof(visited));
		if (result.dist)
		{
			int x = result.x, y = result.y;

			board[result.x][result.y] = 0;
			eaten[x][y] = true;
			int pre_sum = dist_sum;
			dist_sum += result.dist;
			pii pre_pos = shark_pos;
			shark_pos = pii(x, y);
			shark_eat += 1;

			if (shark_eat >= shark_size)
			{
				shark_size += 1;
				shark_eat = 0;
			}
		}
		else break;
	}
	cout << dist_sum << endl;
}
int main()
{
	init();
	calc();
}
/*
4
4 3 2 1
0 0 0 0
0 0 9 0
1 1 3 2
*/
