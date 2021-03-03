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
using namespace std;


vector<vector<int>> tree;
int n;
int cache[1000010][2];

void init()
{
	FASTIO
		cin >> n;
	tree.resize(n + 1, vector<int>());
	for (int i = 0; i < n - 1; ++i)
	{
		int parent, child;
		cin >> parent >> child;
		tree[parent].push_back(child);
		tree[child].push_back(parent);
	}
	memset(cache, -1, sizeof(cache));
}
int calc(int node, int earlier, int parent)
{
	int& ret = cache[node][earlier];
	if (ret != -1) return ret;
	ret = earlier;

	for (auto& child : tree[node])
	{
		if (child == parent) continue;
		if (earlier) ret += min(calc(child, 0, node), calc(child, 1, node));
		else ret += calc(child, 1, node);
	}
	return ret;
}
int main()
{
	init();
	int ret = min(calc(1, 0, 1), calc(1, 1, 1));
	cout << ret << endl;
}
/*
39
1 2
1 3
1 4
2 5
2 6
3 7
3 8
3 9
4 10
4 11
5 12
5 13
6 14
6 15
6 16
8 17
8 18
10 19
10 20
11 21
11 22
11 23
11 24
12 25
12 26
12 27
13 28
14 29
14 30
16 31
16 32
18 33
19 34
22 35
22 36
22 37
24 38
24 39
=> 15

31
1 2
1 3
1 4
1 5
1 6
2 7
2 8
2 9
3 10
3 11
4 12
4 13
5 14
5 15
6 16
6 17
7 18
7 19
7 20
7 21
8 22
8 23
10 24
13 25
15 26
15 27
15 28
17 29
17 30
17 31
=> 11
*/
