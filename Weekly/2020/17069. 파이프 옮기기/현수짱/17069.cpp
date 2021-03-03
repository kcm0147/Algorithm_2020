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
#define endl '\n'
using namespace std;
int n, m;
int board[33][33];
ll cache[33][33][3];  // row, col, type

// type[0] = �� , type[1] = ��, type[2] = \;
void init()
{
	FASTIO
		cin >> n;
	for (int i = 0; i < n; ++i)
		for (int j = 0; j < n; ++j)
			cin >> board[i][j];
	memset(cache, -1, sizeof(cache));
}
bool isBorder(int x, int y)
{
	return (x >= 0 && x < n && y >= 0 && y < n);
}
bool canMove(int r, int c, int t)
{
	if (t == 0 || t == 1)
	{
		if (!isBorder(r, c) || board[r][c])
			return false;
	}
	else
	{
		if ((!isBorder(r, c) || board[r][c]) || (!isBorder(r, c - 1) || board[r][c - 1]) ||
			(!isBorder(r - 1, c) || board[r - 1][c]))
			return false;
	}
	return true;
}
ll calc(int row, int col, int type)
{
	if (canMove(row, col, type) == false) 
		return 0;
	if (row == n - 1 && col == n - 1) return 1;

	ll& ret = cache[row][col][type];
	if (ret != -1) return ret;
	ret = 0;

	if (type == 0) // ���� ����
	{
		ret += calc(row, col + 1, 0);  // ���η� �б�
		ret += calc(row + 1, col + 1, 2); // �밢�� �б�
	}
	else if (type == 1) // ���� ����
	{
		ret += calc(row + 1, col, 1); // ���η� �б�
		ret += calc(row + 1, col + 1, 2); // �밢�� �б�
	}
	else if (type == 2) // �밢�� ����
	{
		ret += calc(row, col + 1, 0);  // ���η� �б�
		ret += calc(row + 1, col, 1); // ���η� �б�
		ret += calc(row + 1, col + 1, 2); // �밢�� �б�
	}
	return ret;
}
int main()
{
	init();
	cout << calc(0, 1, 0) << endl;
}
