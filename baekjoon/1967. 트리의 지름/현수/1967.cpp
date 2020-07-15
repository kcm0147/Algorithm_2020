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

struct node_info
{
	int max_path_length;
	priority_queue<int> large_ordered_rad;

	node_info() : max_path_length(0) {}
};
int n, max_rad;
vector<int> parent;
vector<int> weight;
vector<node_info> node; // (two_path_sum, max_path,length);

void init()
{
	FASTIO
		cin >> n;
	parent.resize(n + 1, 0);
	weight.resize(n + 1, 0);
	node.resize(n + 1, node_info()); // (child_sum, max_weight)

	for (int i = 0; i < n - 1; ++i)
	{
		int root, child, w;
		cin >> root >> child >> w;
		parent[child] = root;
		weight[child] = w;
	}
}
void calc()
{
	for (int i = n; i > 0; --i)
	{
		node_info& p = node[parent[i]];
		node_info cur = node[i];

		int rad_sum = 0, cnt =0;
		while (!(cur.large_ordered_rad).empty() && cnt < 2)
		{
			rad_sum += (cur.large_ordered_rad).top();
			cur.large_ordered_rad.pop();
			cnt++;
		}
		max_rad = max(rad_sum, max_rad);

		p.large_ordered_rad.push(weight[i] + cur.max_path_length);
		p.max_path_length = max(p.max_path_length, weight[i] + cur.max_path_length);
	}
	cout << max_rad << endl;
}
int main()
{
	init();
	calc();
}
