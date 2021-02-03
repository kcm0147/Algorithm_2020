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

typedef struct Info Info;
struct Info
{
	pii pos;
	int shape;
};
int n, goal_form, shape; // shape =0 -> ����, shape = 1 -> ����.
pii center; // center : �볪�� �߽�
pii tree_pos[3], goal_pos[3]; // tree_pos : ���� �볪�� ��ġ, goal_pos : ���� ������
string board[51];
int dx[4] = { -1, 0 ,1, 0 };
int dy[4] = { 0, 1, 0, -1 };
int visited[51][51][2]; // �߽��� [][][0/1] ������� [x][y] �� ������ ���� �ִ� �� Ȯ��

void init()
{
	FASTIO
	cin >> n;
	int b_cnt = 0, e_cnt = 0;
	for (int i = 0; i < n; ++i)
	{
		cin >> board[i];
		for (int j = 0; j < n; ++j)
		{
			/* �볪�� ��ǥ ���� */
			if (board[i][j] == 'B')
			{
				b_cnt++;
				if (b_cnt == 2)
				{
					center = pii(i, j);
					tree_pos[1] = pii(i, j);
					if (i - 1 >= 0 && board[i - 1][j] == 'B')
					{
						shape = 1;
						tree_pos[0] = pii(i - 1, j);
						tree_pos[2] = pii(i + 1, j);
					}
					else if (j - 1 >= 0 && board[i][j - 1] == 'B')
					{
						shape = 0;
						tree_pos[0] = pii(i, j - 1);
						tree_pos[2] = pii(i, j + 1);
					}
					break;
				}
			}
			/* ������ ��ǥ ���� */
			if (board[i][j] == 'E')
			{
				e_cnt += 1;
				if (e_cnt == 2)
				{
					goal_pos[1] = pii(i, j);
					if (i - 1 >= 0 && board[i - 1][j] == 'E')
					{
						goal_form = 1;
						goal_pos[0] = pii(i - 1, j);
						goal_pos[2] = pii(i + 1, j);
					}
					else if (j - 1 >= 0 && board[i][j - 1] == 'E')
					{
						goal_form = 0;
						goal_pos[0] = pii(i, j - 1);
						goal_pos[2] = pii(i, j + 1);
					}
					break;
				}
			}
		}
	}
}
bool isBorder(pii t1, pii t3) // ��ܷ� ������� Ȯ��
{
	return (t1.first >= 0 && t3.first < n && t1.second >= 0 && t3.second < n);
}
bool canMove(pii t1, pii t2, pii t3, int shape) // �ش� ��ġ���� �̵� �������� Ȯ��
{��
	if (visited[t2.first][t2.second][shape])
		return false;
	if (isBorder(t1, t3) &&  t2.first >= 0 && t2.first < n && t2.second >= 0 && t2.second < n)
	{
		if (board[t1.first][t1.second] != '1' && board[t2.first][t2.second] != '1' && board[t3.first][t3.second] != '1')
			return true;
	}
	return false;
}
bool canRotate(pii t1, pii t2, pii t3, int form) // �ش� ��ġ���� ȸ�� �������� Ȯ��
{
	int changed_form = abs(form - 1);
	if (visited[t2.first][t2.second][changed_form])
		return false;
	/* �볪���� ���ι��� �̾��ٸ� */
	if (form == 0)
	{
		int x_up = t2.first - 1, x_down = t2.first + 1;
		int y1 = t2.second - 1, y2 = t2.second, y3 = t2.second+1;
		if (isBorder(pii(x_up, y1), pii(x_down, y1)) && isBorder(pii(x_up, y2), pii(x_down, y2)) && isBorder(pii(x_up, y3), pii(x_down, y3)))
			if (board[x_up][y1] != '1' && board[x_down][y1] != '1' && board[x_up][y2] != '1' && board[x_down][y2] != '1' && board[x_up][y3] != '1' && board[x_down][y3] != '1')
				return true;
	}
	/* �볪���� ���ι��� �̾��ٸ� */
	else
	{
		int x_up = t2.first - 1, x = t2.first, x_down = t2.first + 1;
		int y_left = t2.second - 1, y_right = t2.second + 1;
		if (isBorder(pii(x_up, y_left), pii(x_up, y_right)) && isBorder(pii(x, y_left), pii(x, y_right)) && isBorder(pii(x_down, y_left), pii(x_down, y_right)))
			if (board[x_up][y_left] != '1' && board[x_up][y_right] != '1' && board[x][y_left] != '1' && board[x][y_right] != '1' && board[x_down][y_left] != '1' && board[x_down][y_right] != '1')
				return true;
	}
	return false;
}
void set_pos(pii center, pii* t1, pii* t2, pii* t3, int shape, int dir) // �볪�� ��ǥ ����, �� �� �Լ����� ��� ó��
{
	// dir == -1 -> �߽����� �������� �̵����� �ʾ��� ����� ��ǥ ����
	// dir != -1 -> �볪���� �ű�� ���� �߽����� ���� �ش� �������� �ű� ����� ��ǥ ����

	/* ������ ��ġ Ȯ���ϴ� �뵵 */
	if (dir != -1)
	{
		center.first += dx[dir];
		center.second += dy[dir];
	}

	*t2 = center;
	if (shape == 0) // ���ι���
	{
		(*t1).first = (*t3).first = center.first;
		(*t1).second = center.second - 1;
		(*t3).second = center.second + 1;
	}
	else // ���ι���
	{
		(*t1).second = (*t3).second = center.second;
		(*t1).first = center.first - 1;
		(*t3).first = center.first + 1;
	}
}
void calc()
{
	int operation_cnt = 0;
	bool valid_bit = true;
	queue<Info> queue;
	Info info = { center, shape };
	queue.push(info);

	while (!queue.empty() && valid_bit)
	{
		int size = queue.size();

		/* �� ���� �̵��ȿ� ������ ��� ����� �� */
		for (int i = 0; i < size; ++i)
		{
			Info now = queue.front(); queue.pop();
			pii pos = now.pos;
			int form = now.shape;
			pii t1, t2, t3;

			set_pos(pos, &t1, &t2, &t3, form, -1); // ���� ��ǥ ����

			if (pos == goal_pos[1]) 
			{
				if (form == goal_form) // ���������� ������ ���� �� �Լ� ����.
				{
					cout << operation_cnt << endl;
					return;
				}
				else if (canRotate(t1, t2, t3, form))
				{
					cout << operation_cnt + 1 << endl;
					return;
				}
			}
			
			/* ���ڸ� ȸ�� */
			if (canRotate(t1, t2, t3, form))
			{
				int changed_form = abs(form - 1);
				queue.push({ t2, changed_form });
				visited[t2.first][t2.second][changed_form] = 1;
			}
			visited[t2.first][t2.second][form] = 1;

			/* �ϵ����� ������ �̵�*/
			for (int j = 0; j < 4; ++j)
			{
				pii temp1 = t1, temp2 = t2, temp3 = t3;
				set_pos(pos, &temp1, &temp2, &temp3, form, j); // �̵� �� ��ǥ ����
				if (canMove(temp1, temp2, temp3, form))
				{
					queue.push({ temp2, form });
					visited[temp2.first][temp2.second][form] = 1;
				}
			}
		}
		operation_cnt += 1;
	}
	if (valid_bit) cout << 0 << endl;
	else cout << operation_cnt << endl;
}
int main()
{
	init();
	calc();
}

/*
4
BBB1
0E01
0E01
0E00

5
01011
0B011
0B011
0B011
EEE11

4
BBB1
0001
1111
EEE1
*/