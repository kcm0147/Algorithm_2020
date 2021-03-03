#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

vector<vector<int>> tree;
int* early_dp;
int* noearly_dp;
bool* visit;

void bfs_circuit(int parents) {
	if (tree[parents].size() == 1 && parents != 1) {
		early_dp[parents] = 1;
		return;
	}

	int child_size = tree[parents].size(), child_num, i;
	visit[parents] = true;

	for (i = 0; i < child_size; i++)
		if(!visit[tree[parents][i]])
			bfs_circuit(tree[parents][i]);
	for (i = 0; i < child_size; i++) {
		child_num = tree[parents][i];
		noearly_dp[parents] += early_dp[child_num];
		early_dp[parents] += min(noearly_dp[child_num], early_dp[child_num]);
	}
	early_dp[parents]++;
}

int main() {
	int size, parents, child;

	cin >> size;
	tree.resize(size + 1);
	early_dp = new int[size + 1]{ 0 };
	noearly_dp = new int[size + 1]{ 0 };
	visit = new bool[size + 1]{ false };
	for (int i = 1; i < size; i++) {
		cin >> parents >> child;
		tree[parents].push_back(child);
		tree[child].push_back(parents);
	}

	bfs_circuit(1);
	cout << min(early_dp[1], noearly_dp[1]) << "\n";

	return 0;
}