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
bool seg[9][10];
string board[9];
vector<pii> zero;
void partition()
{
	int row = 0, col = 0, part = 0;
	while (row < 9 && col < 9)
	{
		for (int i = row; i<row + 3; ++i)
			for (int j = col; j < col + 3; ++j)
				seg[part][board[i][j] - '0'] = true;
		col += 3;
		if (col >= 9)
		{
			row += 3;
			col = 0;
		}
		part += 1;
	}
}
vector<int> getCandidate(int part)
{
	vector<int> ret;
	for (int i = 1; i <= 9; ++i)
		if (seg[part][i] == false) ret.push_back(i);
	return ret;
}
int getSegment(int x, int y)
{
	int row = x / 3, col = y / 3;
	return row * 3 + col;
}
bool isAllComplete()
{
	/* 안채워 진 것이 있는지 */
	for (int i = 0; i < 9; ++i)
		for (int j = 1; j <= 9; ++j)
			if (seg[i][j] == false) return false;
	return true;
}
bool canProceed(int x, int y, int val)
{
	for (int i = 0; i < 9; ++i)
		if (board[x][i] == '0' + val || board[i][y] == '0' + val)
			return false;
	return true;
}
bool complete(int idx)
{
	if (idx == zero.size())
	{
		if (isAllComplete()) return true;
		else return false;
	}
	pii cur = zero[idx];
	int part = getSegment(cur.first, cur.second);
	vector<int> candidate = getCandidate(part);
	for (auto& elem : candidate)
	{
		if (canProceed(cur.first, cur.second, elem) != true) continue;
		board[cur.first][cur.second] = '0' + elem;
		seg[part][elem] = true;
		if (complete(idx + 1)) return true;
		seg[part][elem] = false;
		board[cur.first][cur.second] = '0';
	}
	return false;
}
bool initialCheck()
{
	bool rcheck[10], ccheck[10], rect_check[10];
	int size = zero.size();
	for (int s = 0; s < size; ++s)
	{
		int x = zero[s].first, y = zero[s].second;
		memset(rcheck, 0, sizeof(rcheck));
		memset(ccheck, 0, sizeof(ccheck));
		// row
		for (int i = 0; i < 9; ++i)
		{
			if (rcheck[board[x][i] - '0'])
			{
				if (board[x][i] == '0') continue;
				return false;
			}
			rcheck[board[x][i] - '0'] = true;
		}
		// col
		for (int i = 0; i < 9; ++i)
		{
			if (ccheck[board[i][y] - '0'])
			{
				if (board[i][y] == '0') continue;
				return false;
			}
			ccheck[board[i][y] - '0'] = true;
		}
		//rect
		int row = x / 3 * 3, col = y / 3 * 3;
		memset(rect_check, 0, sizeof(rect_check));
		for(int i=row; i < row + 3; ++i)
			for (int j = col; j < col + 3; ++j)
			{
				if (rect_check[board[i][j] - '0'])
				{
					if (board[i][j] == '0') continue;
					return false;
				}
				else rect_check[board[i][j] - '0'] = true;
			}
	}
	return true;
}
int main()
{
	int tc;
	FASTIO
		cin >> tc;
	while (tc-- > 0)
	{
		memset(seg, 0, sizeof(seg));
		for (int i = 0; i < 9; ++i)
		{
			cin >> board[i];
			for (int j = 0; j < 9; ++j)
				if (board[i][j] == '0') zero.push_back(pii(i, j));
		}
		partition();
		if (initialCheck() == false)
		{
			cout << "Could not complete this grid.\n" << '\n';
			continue;
		}
		if (complete(0))
		{
			for (int i = 0; i < 9; ++i)
				cout << board[i] << '\n';
		}
		else cout << "Could not complete this grid.\n";
		cout << '\n';
	}
}

/*
1
481253697
267948135
539671284
654009712
928004563
173502849
742136958
315897426
896425371

1
123456789
123456789
123456789
123456789
123456789
123456789
123456779
123456779
123456790
*/
