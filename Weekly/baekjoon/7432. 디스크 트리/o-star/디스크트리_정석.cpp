#include <iostream>
#include <algorithm>
#include <string>
#include <vector>
#include <map>

using namespace std;

struct dir_table {
	string sub_name;
	vector<dir_table> sub_table;
};

bool cmp(dir_table a, dir_table b) {
	return a.sub_name < b.sub_name;
}

void print_directory(vector<dir_table> cur_dir, int level) {
	sort(cur_dir.begin(), cur_dir.end(), cmp);
	int size = cur_dir.size();

	for (int i = 0; i < size; i++) {
		for (int j = 0; j < level; j++)
			cout << " ";
		cout << cur_dir[i].sub_name << "\n";
		if (cur_dir[i].sub_table.size() != 0)
			print_directory(cur_dir[i].sub_table, level + 1);
	}

}

void make_directory(vector<dir_table>* cur_dir, string input, int pos) {

	int length = input.size(), dir_size, i, j;
	string substring;

	for (i = pos; i < length; i++) {
		if (input[i] == '\\' || i == length - 1) {
			if (i == length - 1)
				i++;
			substring = input.substr(pos, i - pos);
			dir_size = (*cur_dir).size();
			for (j = 0; j < dir_size; j++)
				if (substring == (*cur_dir)[j].sub_name)
					break;
			if (j >= dir_size) {
				dir_table sub_dir;
				sub_dir.sub_name = substring;
				(*cur_dir).push_back(sub_dir);
			}
			make_directory(&(*cur_dir)[j].sub_table, input, i + 1);
			break;
		}
	}
}

int main() {
	int size, i;
	string input;
	vector<dir_table> root;

	cin >> size;
	for (i = 0; i < size; i++) {
		cin >> input;
		make_directory(&root, input, 0);
	}

	print_directory(root, 0);

	return 0;
}