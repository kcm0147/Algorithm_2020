#include <iostream>
#include <vector>
#include <utility>
#include <algorithm>

using namespace std;

vector<vector<pair<int, int>>> tree;
bool visit[10001];
int answer, max_node;

void dfs(int node, int mid_weight) {
	if (tree[node].size() == 1 && node != 1 && mid_weight) {
		if (answer < mid_weight) {
			answer = mid_weight;
			max_node = node;
		}
		return;
	}

	int n_size = tree[node].size(), next_node;
	for (int i = 0; i < n_size; i++) {
		next_node = tree[node][i].first;
		if (!visit[next_node]) {
			visit[next_node] = true;
			dfs(next_node, mid_weight + tree[node][i].second);
			visit[next_node] = false;
		}
	}
}

int main() {
	int size, num1, num2, weight, i;

	cin >> size;
	tree.resize(size + 1);

	for (i = 1; i < size; i++) {
		cin >> num1 >> num2 >> weight;
		tree[num1].push_back(make_pair(num2, weight));
		tree[num2].push_back(make_pair(num1, weight));
	}

	for (i = 1; i < size; i++)
		if (tree[i].size() == 1)
			dfs(i, 0);
	cout << answer << "\n";

	return 0;
}