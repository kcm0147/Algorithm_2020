#include <iostream>
#include <queue>
#include <stack>
#include <vector>
#include <list>
#include <functional>
#include <algorithm>
#include <string>
#include <map>
#define FASTIO ios_base::sync_with_stdio(false); cin.tie(NULL); cout.tie(NULL);
#define INF 987654321
using namespace std;
int n = 0;
typedef struct directory DIR;
struct directory
{
	map<string, DIR> children;
};
map<string, DIR> root;

vector<string> dir_in_path;
vector<string> parsing(string line)
{
	vector<string> ret;
	int idx = line.find("\\");
	if (idx != string::npos)
	{
		ret.push_back(line.substr(0, idx));
		line = line.substr(idx + 1);
		while ((idx = line.find("\\")) != string::npos)
		{
			ret.push_back(line.substr(0, idx));
			line = line.substr(idx + 1);
		}
	}
	ret.push_back(line);
	return ret;
}
void calc(DIR* parent, int idx)
{
	if (idx >= dir_in_path.size()) return;
	DIR child;
	string name = dir_in_path[idx];
	if ((*parent).children.find(name) == (*parent).children.end())
		(*parent).children[name] = child;
	calc(&(*parent).children[name], idx + 1); // 수정 해야함
}
void print_directory(DIR dir, int height)
{
	for (auto iter : dir.children)
	{
		for (int i = 0; i < height; ++i)
			cout << ' ';
		cout << iter.first << endl;
		print_directory(iter.second, height + 1);
	}
}
int main()
{
	FASTIO
		cin >> n;
	for (int i = 0; i < n; ++i)
	{
		string line;
		cin >> line;
		dir_in_path = parsing(line);
		root[dir_in_path[0]];
		if (dir_in_path.size() > 1)
			calc(&(root[dir_in_path[0]]), 1);
	}
	for (auto iter : root)
	{
		cout << iter.first << endl;
		print_directory(iter.second, 1);
	}
}