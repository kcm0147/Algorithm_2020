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
struct Point
{
	pii origin;
	vector<pii> sub_point;
	int count, r, c;
};
int n, m, k, result;
Point sticker[101];
int board[41][41];

void init()
{
	FASTIO
		cin >> n >> m >> k;

	for (int i = 0; i < k; ++i)
	{
		int row, col, start = 0;
		cin >> row >> col;
		
		sticker[i].count = 0;
		sticker[i].r = row; sticker[i].c = col;
		for (int j = 0; j < row; ++j)
			for (int h = 0; h < col; ++h)
			{
				int num; 
				cin >> num;
				if (num == 0) continue;
				if (start++ == 0) sticker[i].origin = pii(j, h);

				sticker[i].sub_point.push_back(pii(j - sticker[i].origin.first,
					h - sticker[i].origin.second));
				sticker[i].count++;
			}
	}
}
pii change(int x, int y, int rc)
{
	if (rc == 0) return pii(x, y);
	else if (rc == 1) return pii(y, -x);
	else if (rc == 2) return pii(-x, -y);
	else return pii(-y, x);
}
bool isBorder(int x, int y)
{
	return (x >= 0 && x < n && y >= 0 && y < m);
}
bool rotate_sticker(int x, int y, int idx, int s)
{
	vector<pii> covered;
	bool cont = true;
	for (int i = 0; i < sticker[idx].count; ++i)
	{
		pii trans = change(sticker[idx].sub_point[i].first, sticker[idx].sub_point[i].second, s);
		int nx = x + trans.first, ny = y + trans.second;
		if (!isBorder(nx, ny) || board[nx][ny] == 1)
		{
			cont = false;
			break;
		}
		covered.push_back(pii(nx, ny));
	}
	if (cont == false) return false;
	for (int i = 0; i < covered.size(); ++i)
		board[covered[i].first][covered[i].second] = 1;
	result += sticker[idx].count;
	return true;
}
void setBoard(int idx)
{
	for (int s = 0; s < 4; ++s)
	{
		for (int x = 0; x < n; ++x)
			for (int y = 0; y < m; ++y)
				if (board[x][y] == 0)
				{
					if(rotate_sticker(x, y, idx, s)) 
						return;
				}
	}
}
int main()
{
	init();
	for (int i = 0; i < k; ++i)
			setBoard(i);
	cout << result << endl;
}
