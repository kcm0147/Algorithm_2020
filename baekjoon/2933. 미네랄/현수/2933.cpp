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

int n, m, toss;
int dx[4] = { -1, 0, 1, 0 }, dy[4] = { 0, 1, 0, -1 };
bool visited[101][101];
string board[101];
vector<int> stick;
struct suite
{
	int first, second;
	suite(int a, int b) : first(a), second(b) {}
};
bool cmp(const suite& a, const suite& b)
{
	return make_tuple(-a.first, a.second) < make_tuple(-b.first, b.second);
}
bool isBorder(int x, int y)
{
	return (x >= 0 && x < n && y >= 0 && y < m);
}
void init()
{
	FASTIO
		cin >> n >> m;
	for (int i = 0; i < n; ++i)
		cin >> board[i];
	cin >> toss;
	stick.resize(toss, 0);
	for (int i = 0; i < toss; ++i)
		cin >> stick[i];
}
vector<suite> isSeperate(int x, int y)
{
	queue<suite> bfs;
	vector<suite> ret;
	ret.push_back(suite(x, y));
	bfs.push(suite(x, y));
	visited[x][y] = true;
	while (!bfs.empty())
	{
		suite cur = bfs.front();
		bfs.pop();

		for (int i = 0; i < 4; ++i)
		{
			int nx = cur.first + dx[i], ny = cur.second + dy[i];
			if (isBorder(nx, ny) && visited[nx][ny] == false && board[nx][ny] == 'x')
			{
				visited[nx][ny] = true;
				bfs.push(suite(nx, ny)); ret.push_back(suite(nx, ny));
				if (nx == n - 1)
					return vector<suite>();
			}
		}
	}
	return ret;
}
void sink(vector<suite> cluster)
{
	sort(cluster.begin(), cluster.end(), cmp); // 모양이 유지됨을 생각하지 않고 각 열의 클러스터 조각들이 바닥에 닿을 때 까지 추락시키는걸 구현함(쓸데없이 어렵게 생각..)
	int diff = 12345, h;
	bool check[101];
	fill_n(check, 101, 0);
	for (auto& mineral : cluster)
	{
		int hori = mineral.first, vert = mineral.second;
		if (check[vert] == true) continue;
		check[vert] = true;
		for (h = hori + 1; h < n; ++h)
			if (board[h][vert] == 'x') break;
		diff = min(diff, h - 1 - hori);
	}
	for (auto& mineral : cluster)
	{
		int hori = mineral.first, vert = mineral.second;
		board[hori][vert] = '.';
		board[hori + diff][vert] = 'x';
	}
}
void calc()
{
	for (int i = 0; i < toss; ++i)
	{
		int h = stick[i], x = 0, y = 0;
		if (i % 2 == 0) // 왼쪽
		{
			for(int j=0; j<m; ++j) 
				if (board[n - h][j] == 'x')
				{
					board[n - h][j] = '.';
					x = n - h;
					y = j;
					break;
				}
		}
		else // 오른쪽
		{
			for (int j = m-1; j>=0; --j)
				if (board[n - h][j] == 'x')
				{
					board[n - h][j] = '.';
					x = n - h;
					y = j;
					break;
				}
		}
		for (int j = 0; j < 4; ++j)
		{
			int nx = x + dx[j], ny = y + dy[j];
			
			if (isBorder(nx, ny) && board[nx][ny] == 'x')
			{
				memset(visited, 0, sizeof(visited));
				vector<suite> cluster = isSeperate(nx, ny);
				if (cluster.size() == 0) continue;
				sink(cluster);
			}
		}
	}
	for (int i = 0; i < n; ++i) cout << board[i] << '\n';
}
int main()
{
	init();
	calc();
}
