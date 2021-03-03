#include <iostream>
#include <vector>
#include <utility>
#include <queue>
#include <cstdlib>

using namespace std;

struct node {
	int level;
	int vertical;	// 0: vertical, 1: horizontal
	pair<int, int> cur_pos;
};

struct cmp {
	bool operator()(node& a, node& b) {
		return a.level > b.level;
	}
};

char input[50][50];
bool visit[2][50][50];	// 3차원 0 index : vertical, 3차원 1 index : horizontal
int di[4] = { 0, 1, 0, -1 }, dj[4] = { 1, 0, -1, 0 };

bool compare_dest(vector<pair<int, int>> cur, vector<pair<int, int>> dest) {
	int i;
	for (i = 0; i < 3; i++)
		if (cur[i].first != dest[i].first || cur[i].second != dest[i].second)
			break;

	if (i < 3)
		return false;
	return true;
}

int main() {
	int i, j, size, answer = -1, level, next_x, next_y, cur[3][2];
	vector<pair<int, int>> start, dest, next;
	node comp;
	priority_queue<node, vector<node>, cmp> pq;
	bool vertical;

	cin >> size;
	for (i = 0; i < size; i++)
		for (j = 0; j < size; j++) {
			cin >> input[i][j];
			if (input[i][j] == 'B')
				start.push_back(make_pair(i, j));
			else if (input[i][j] == 'E')
				dest.push_back(make_pair(i, j));
		}

	comp.level = 0;
	comp.cur_pos = start[1];
	if (start[0].second == start[1].second)
		comp.vertical = 1;
	else
		comp.vertical == 0;
	visit[comp.vertical][start[1].first][start[1].second] = true;
	pq.push(comp);

	while (!pq.empty()) {
		level = pq.top().level;
		vertical = pq.top().vertical;
		cur[1][0] = pq.top().cur_pos.first;
		cur[1][1] = pq.top().cur_pos.second;
		if (pq.top().vertical) {
			cur[0][0] = cur[1][0] - 1;		cur[2][0] = cur[1][0] + 1;
			cur[0][1] = cur[1][1];			cur[2][1] = cur[1][1];
		}
		else {
			cur[0][0] = cur[1][0];			cur[2][0] = cur[1][0];
			cur[0][1] = cur[1][1] - 1;		cur[2][1] = cur[1][1] + 1;
		}
		pq.pop();

		if (0 < answer && answer <= level + 1)
			break;

		for (i = 0; i < 4; i++) {
			next.clear();
			if (visit[vertical][cur[1][0] + di[i]][cur[1][1] + dj[i]])
				continue;
			for (j = 0; j < 3; j++) {
				next_x = cur[j][0] + di[i];
				next_y = cur[j][1] + dj[i];
				if (next_x < 0 || next_x >= size || next_y < 0 || next_y >= size || input[next_x][next_y] == '1')
					break;
				next.push_back(make_pair(next_x, next_y));
			}
			if (j < 3)
				continue;
			if (compare_dest(next, dest)) {
				answer = level + 1;
				continue;
			}
			comp.level = level + 1;
			comp.cur_pos = next[1];
			comp.vertical = vertical;
			visit[vertical][next[1].first][next[1].second] = true;
			pq.push(comp);
		}
		if (cur[0][0] == cur[1][0] && !visit[!vertical][cur[1][0]][cur[1][1]]) {	// 가로 방향 통나무
			for (j = 0; j < 3; j++) {
				next_x = cur[j][0] + 1;
				next_y = cur[j][1];
				if (next_x < 0 || next_x >= size || input[next_x][next_y] == '1')
					break;
				next_x = cur[j][0] - 1;
				if (next_x < 0 || next_x >= size || input[next_x][next_y] == '1')
					break;
			}
			if (j >= 3) {
				next.clear();
				next.push_back(make_pair(cur[0][0] - 1, cur[0][1] + 1));
				next.push_back(make_pair(cur[1][0], cur[1][1]));
				next.push_back(make_pair(cur[2][0] + 1, cur[2][1] - 1));
				if (compare_dest(next, dest))
					answer = level + 1;
				else {
					comp.level = level + 1;
					comp.vertical = !vertical;
					comp.cur_pos = next[1];
					visit[!vertical][next[1].first][next[1].second] = true;
					pq.push(comp);
				}
			}
		}
		else if (!visit[!vertical][cur[1][0]][cur[1][1]]) {	// 세로방향 통나무
			for (j = 0; j < 3; j++) {
				next_x = cur[j][0];
				next_y = cur[j][1] + 1;
				if (next_y < 0 || next_y >= size || input[next_x][next_y] == '1')
					break;
				next_y = cur[j][1] - 1;
				if (next_y < 0 || next_y >= size || input[next_x][next_y] == '1')
					break;
			}
			if (j >= 3) {
				next.clear();
				next.push_back(make_pair(cur[0][0] + 1, cur[0][1] - 1));
				next.push_back(make_pair(cur[1][0], cur[1][1]));
				next.push_back(make_pair(cur[2][0] - 1, cur[2][1] + 1));
				if (compare_dest(next, dest))
					answer = level + 1;
				else {
					comp.level = level + 1;
					comp.vertical = !vertical;
					comp.cur_pos = next[1];
					visit[!vertical][next[1].first][next[1].second] = true;
					pq.push(comp);
				}
			}
		}
	}

	if (answer == -1)
		cout << 0 << "\n";
	else
		cout << answer << "\n";
	return 0;
}