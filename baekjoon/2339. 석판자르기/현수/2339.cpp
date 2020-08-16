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

int n, board[22][22];
int jPsum[22][22];
vector<pii> dust;
void init()
{
	cin >> n;
	for (int i = 0; i <n; i++)
		for (int j = 0; j < n; j++)
			cin >> board[i][j];
}
int calc(int x1, int x2, int y1, int y2, int dir)
{
	int j_count = 0;
	vector<pii> dust;
	for (int i = x1; i <= x2; ++i)
		for (int j = y1; j <= y2; ++j)
			if (board[i][j] == 1) dust.push_back(pii(i, j));
			else if (board[i][j] == 2) j_count += 1;
	
	if (j_count == 0 || (j_count == 1 && dust.size() > 0) || 
		(j_count >= 2 && dust.size() == 0))
		return 0;
	if (j_count == 1 && dust.size() == 0) return 1;
	
	int ret = 0;
	// dir : 0 -> 초기 dummy 값, 1 -> 세로, 2 -> 가로.
	for (auto& d : dust)
	{
		int x = d.first, y = d.second;
		if (dir != 1) // 가로
		{
			bool flag = true;
			for (int i = y1; i <= y2; ++i)
				if (board[x][i] == 2) flag = false;
			if (flag)
				ret += calc(x1, x - 1, y1, y2, 1) * calc(x + 1, x2, y1, y2, 1);
		}
		if (dir != 2) // 세로
		{
			bool flag = true;
			for (int i = x1; i <= x2; ++i)
				if (board[i][y] == 2) flag = false;
			if (flag)
				ret += calc(x1, x2, y1, y - 1, 2) * calc(x1, x2, y + 1, y2, 2);
		}
	}
	return ret;
}
int main()
{
	init();
	int result = calc(0, n - 1, 0, n - 1, 0);
	cout << (result == 0 ? -1 : result) << endl;
}
