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

struct Tong
{
	int start, end, height, index;
	Tong() : start(0), end(0), height(0), index(0) {}
	bool operator< (const Tong comp) const
	{
		if (start != comp.start) return start < comp.start;
		return end < comp.end;
	}
};
vector<Tong> tong_tree;
vector<pii> query;

void init()
{
	FASTIO
		cin >> n >> m;
	tong_tree.resize(n + 1, Tong());
	query.resize(m);
	for (int i = 1; i <= n; ++i)
	{
		cin >> tong_tree[i].start >> tong_tree[i].end >> tong_tree[i].height;
		tong_tree[i].index = i;
	}
	for (int i = 0; i < m; ++i)
		cin >> query[i].first >> query[i].second;
	
	sort(tong_tree.begin() + 1, tong_tree.end());
}
void calc()
{
	vector<int> group(n + 1, 0);
	int group_number = 0, pivot = tong_tree[1].end;

	group[tong_tree[1].index] = group_number;
	for (int i = 2; i <= n; ++i)
	{
		if (pivot < tong_tree[i].start)
			group[tong_tree[i].index] = ++group_number;
		else
			group[tong_tree[i].index] = group_number;
		pivot = max(pivot, tong_tree[i].end);
	}

	for (int i = 0; i < m; ++i)
	{
		if (group[query[i].first] == group[query[i].second])
			cout << 1 << endl;
		else cout << 0 << endl;
	}
}
int main()
{
	init();
	calc();
}
