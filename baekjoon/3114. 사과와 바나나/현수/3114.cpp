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
string country[1500][1500];
int a_Psum[1500][1500], b_Psum[1500][1500], cache[1500][1500];
int dx[3] = { 0, 1, 1 }, dy[3] = { 1, 1, 0 };
int last_aPsum[1500], last_bPsum[1500];
int tree_count(char fruit, string grid)
{
	if (fruit != grid[0]) return 0;
	return stoi(grid.substr(1, grid.length() - 1));
}
bool isBorder(int x, int y)
{
	return (x >= 0 && x < n && y >= 0 && y < m);
}
void init()
{
	string input;
	FASTIO
		cin >> n >> m;
	memset(cache, -1, sizeof(cache));

	/* B 누적합*/
	for (int i = 0; i < n; ++i)
	{
		for (int j = 0; j < m; ++j)
		{
			cin >> country[i][j];
			if (i == 0)
				b_Psum[i][j] = tree_count('B', country[0][j]);
			else
			{
				if (country[i - 1][j][0] == 'A' && country[i][j][0] == 'A') 
					b_Psum[i][j] = last_bPsum[j];
				else 
					b_Psum[i][j] = b_Psum[i-1][j] + tree_count('B', country[i][j]);
			}
			last_bPsum[j] = b_Psum[i][j];
		}
	}
	/* A 누적합*/
	for (int i = n-1; i >=0; --i)
	{
		for (int j = 0; j < m; ++j)
		{
			if (i == n-1) 
				a_Psum[i][j] = tree_count('A', country[i][j]);
			else
			{
				if (country[i + 1][j][0] == 'B' && country[i][j][0] == 'B')
					a_Psum[i][j] = last_aPsum[j];
				else
					a_Psum[i][j] = a_Psum[i + 1][j] + tree_count('A', country[i][j]);
			}
			last_aPsum[j] = a_Psum[i][j];
		}
	}
	// grid 한칸은 해당 열에서 아래쪽 사과나무 개수 + 위쪽 바나나나무 개수
	// 각 grid의 나무 개수를 꺼내려면 ~_Psum[x][y] - tree_count(~, country[x][y])
}
int calc(int x, int y)
{
	if (x == n - 1 && y == m - 1) return b_Psum[x][y] - tree_count('B', country[x][y]);
	int& ret = cache[x][y];
	if (ret != -1) return ret;
	ret = INF;

	int cur_value = (a_Psum[x][y] - tree_count('A', country[x][y])) + (b_Psum[x][y] - tree_count('B', country[x][y]));

	for (int i = 0; i < 3; ++i)
	{
		int nx = x + dx[i], ny = y + dy[i];
		if (isBorder(nx, ny))
		{
			if (i == 2) 
				ret = max(ret, calc(nx, ny) - tree_count('B', country[x][y])); 
			// 아래로 내려오면 바로 위칸의 바나나 나무도 제외해야함.
			else ret = max(ret, cur_value + calc(nx, ny));
		}
	}
	return ret;
}
int main()
{
	init();
	cout << calc(0, 0) << endl;
}
