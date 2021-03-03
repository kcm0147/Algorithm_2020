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

int n;
char pattern[][6] =
{
	"  *  ",
	" * * ",
	"*****"
};
char m[3500][6500], save[3500][6500];
int main()
{
	FASTIO
	cin >> n;
	for (int i = 0; i < 3; ++i)
		memcpy(m[i], pattern[i], sizeof(pattern[i]));
	int row = 3;
	while (2 * row <= n)
	{
		for (int i = 0; i < row; ++i)
		{
			memset(&(save[i]), 0, row * 2);
			memcpy(&(save[i][row]), m[i], row * 2);
			memcpy(&(save[i + row][0]), m[i], row * 2);
			memcpy(&(save[i + row][2 * row]), m[i], row * 2);
		}
		memcpy(m, save, sizeof(save));
		row *= 2;
	}
	for (int i = 0; i < n; ++i)
	{
		for (int j = 0; j < 2 * n - 1; ++j)
			cout << (m[i][j] == '*' ? '*' : ' ');
		cout << '\n';
	}
}

