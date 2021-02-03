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
int dice[11], max_score = 0;
int board[5][22] = {
	{ 0, 2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30, 32, 34, 36, 38, 40, 0 },
	{ 10, 13, 16, 19, 25 },
	{ 20, 22, 24, 25 },
	{ 30, 28, 27, 26, 25 },
	{ 25, 30, 35, 40, 0}
};
int board_size[5] = { 22, 5, 4, 5, 5 };
int marker[4][2]; // [path, pos]
int markerSum[4];
bool isPreempted(int path, int pos)
{
	for (int i = 0; i < 4; ++i)
		if (marker[i][0] == path && marker[i][1] == pos)
			return true;
	return false;
}
void init()
{
	for (int i = 1; i <= 10; ++i)
		cin >> dice[i];
}
void calc(int dice_idx, int sum)
{
	if (dice_idx > 10)
	{
		max_score = max(max_score, sum);
		return;
	}

	for (int i = 0; i < 4; ++i)
	{
		int cur_pos = marker[i][1], cur_path = marker[i][0]; 

		if (cur_pos == board_size[cur_path] - 1) continue;

		int next_path = cur_path, next_pos = cur_pos + dice[dice_idx];

		if (cur_path == 0)
		{
			if (next_pos == 5)
			{
				next_path = 1;
				next_pos = 0;
			}
			else if (next_pos == 10)
			{
				next_path = 2;
				next_pos = 0;
			}
			else if (next_pos == 15)
			{
				next_path = 3;
				next_pos = 0;
			}
			else if (next_pos == 20)
			{
				next_path = 4;
				next_pos = 3;
			}
		}
		else if (cur_path < 4)
		{
			if (next_pos >= board_size[cur_path] - 1)
			{
				next_path = 4;
				next_pos -= board_size[cur_path] - 1;
			}
		}
		// 도착지를 넘어가면 도착지에서 끝남.
		if (next_pos >= board_size[next_path])
			next_pos = board_size[next_path] - 1;

		// 시작, 끝점이 아니고, 다음 위치에 말판이 없을 때만 계속 진행.
		if (board[next_path][next_pos] != 0 && isPreempted(next_path, next_pos)) continue;

		marker[i][0] = next_path;
		marker[i][1] = next_pos;

		calc(dice_idx + 1, sum + board[next_path][next_pos]);

		/* Roll Back */
		marker[i][0] = cur_path;
		marker[i][1] = cur_pos;
	}
	return;
}
int main()
{
	init();
	calc(1, 0);
	cout << max_score << endl;
}
