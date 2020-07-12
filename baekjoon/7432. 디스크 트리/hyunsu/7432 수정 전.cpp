#include <iostream>
#include <queue>
#include <stack>
#include <vector>
#include <list>
#include <functional>
#include <algorithm>
#include <string>
#include <string.h>
#include <cmath>
#include <math.h>
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
int n = 0, depth = 0;

/*
multimap<string, string> mmap[501];
vector<string> directory(501, "");

void umap_set()
{
for (int i = 0; i < n; ++i)
{
string line = directory[i];
int idx = line.find("\\");
if (idx != string::npos)
{
int dir_rank = 0;
string root = line.substr(0, idx);
line = line.substr(idx + 1);
if(mmap[0].find(root) == mmap[0].end())
mmap[0].insert({ root, "" });
while ((idx = line.find("\\")) != string::npos)
{
string substr = line.substr(0, idx);
mmap[++dir_rank].insert({ root, substr });
root = substr;
line = line.substr(idx + 1);
}
mmap[++dir_rank].insert({ root , line });
depth = max(depth, dir_rank);
}
else
mmap[0].insert({ line, "" });
}
}
void init()
{
FASTIO
cin >> n;
for (int i = 0; i < n; ++i)
{
string line;
cin >> line;
directory[i] = line;
}
umap_set();
}
void tree(int height, string root)
{
set<string> sub_dir;
for (auto iter = mmap[height].lower_bound(root); iter != mmap[height].upper_bound(root); ++iter)
sub_dir.insert(iter->second);
for (auto iter : sub_dir)
{
for (int i = 0; i < height; ++i) cout << ' ';
cout << iter << endl;
if(mmap[height+1].find(iter) != mmap[height+1].end())
tree(height + 1, iter);
}
}
int main()
{
init();
for (auto iter : mmap[0])
{
cout << iter.first << endl;
tree(1, iter.first);
}
}
*/

class tree
{
public:
	string name, parent, super;
	tree(string _name, string _parent, string _super) : name(_name), parent(_parent), super(_super) {};

	bool operator< (tree temp) const
	{
		return name < temp.name;
	}
};
vector<string> directory(501);
vector<tree> dirs[501];

bool check_same_path(int height, string name, string parent, string root)
{
	vector<tree> compare = dirs[height];
	for (auto comp : compare)
	{
		if (comp.name == name && comp.parent == parent && comp.super == root)
			return true;
	}
	return false;
}
void tree_set()
{
	for (int i = 0; i < n; ++i)
	{
		string line = directory[i];
		int idx = line.find('\\');

		if (idx != string::npos)
		{
			int dir_rank = 0;
			string root = line.substr(0, idx);
			string parent = root;
			line = line.substr(idx + 1);

			// 최상위 디렉토리에 존재하지 않는 새로운 디렉토리의 경우 추가 
			if (check_same_path(0, root, "", root) == false)
				dirs[0].push_back(tree(root, "", root));

			while ((idx = line.find('\\')) != string::npos)
			{
				string substr = line.substr(0, idx);
				++dir_rank;
				if (check_same_path(dir_rank, substr, parent, root) == false)
					dirs[dir_rank].push_back(tree(substr, parent, root));
				parent = substr;
				line = line.substr(idx + 1);
			}
			++dir_rank;
			if (check_same_path(dir_rank, line, parent, root) == false) // 최상위 root 정보도 저장해야함.
				dirs[dir_rank].push_back(tree(line, parent, root));
			depth = max(depth, dir_rank);
		}
		else
			if (check_same_path(0, line, "", line) == false)
				dirs[0].push_back(tree(line, "", line));
	}
}
void dfs(string parent, string root, int height)
{
	for (auto iter : dirs[height])
	{
		if (iter.super == root && iter.parent == parent)
		{
			for (int i = 0; i < height; ++i) cout << ' ';
			cout << iter.name << endl;
			dfs(iter.name, root, height + 1);
		}
	}
}
void init()
{
	FASTIO
		cin >> n;
	for (int i = 0; i < n; ++i)
	{
		string line;
		cin >> line;
		directory[i] = line;
	}
	tree_set();
}
void calc()
{
	for (int i = 0; i <= depth; ++i)
		sort(dirs[i].begin(), dirs[i].end());

	for (auto iter : dirs[0])
	{
		cout << iter.name << endl;
		dfs(iter.name, iter.super, 1);
	}
}
int main()
{
	init();
	calc();
}

/*
4
A\AA\BB\CC
A\AA\CC
B\AA\CC
AA\B\A\CC

5
A\AA\BB\CC
A\AA\A
B\BB\B\A
A\A\AA\BB\CC
B\BB\B\A\AA\BB\CC

5
A\B\C\D
B\B\C\D\A
A\B\C\B\A
B\BB\C\D\A
A\B\B\C\D

2
B\B\C\D\A
B\BB\C\D\A
*/