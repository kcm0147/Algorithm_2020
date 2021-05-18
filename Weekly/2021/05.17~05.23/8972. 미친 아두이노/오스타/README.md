# 8972. 미친 아두이노

###### 작성자 : O-star

<br/>

<br/>

#### 풀이 : 

**최근 푼 구현 문제와 비슷한 형태였기에 차근차근 로직을 짰기는 하나 중간에 빠져먹은 케이스들이 많아서 한참을 헤맸던 문제였다.**

**세부로직과 잊고 넘어가기 쉬운 케이스를 정리해보았다.**

**<br/>**

**세부 구현사항 :**

1. **정수형 배열 `map[100][100]` 을 준비한다. 종수의 위치일 경우 -1, 미친 아두이노 위치일 경우 1 이상(해당 위치의 아두이노 개수 저장용), 빈 칸일 경우 0을 저장한다.**
2. **기본적으로 미친 아두이노들의 인덱스 정보는 큐에 저장한다.**
3. **종수 위치 이동을 차례대로 진행한다.**
4. **종수 위치를 이동할 시, 이동한 위치의 map 값이 0 이상일 경우 미친 아두이노가 있는 곳이기 때문에 현재 이동 횟수를 반환한다.**
5. **종수 위치 이동 후에는 현재 미친 아두이노들 중 중복 위치한 아두이노들은 제외하기 위해 큐에 인덱스 정보들을 꺼내가며 map 배열을 0으로 전환하면서 중복 아두이노들을 거른다. map 배열을 초기화해주는 이유는 아두이노들을 하나씩 움직일 때 기존에 위치하고 있는 아두이노들이 개입하지 않기 위해서이다.**
6. **5번 과정이 끝나고 나면, 본격적으로 미친 아두이노들을 정수와 가장 가까운 위치로 한 칸씩 이동시킨다. 각 미친 아두이노들을 차례로 큐에서 꺼내가며 8방향 중 가장 가까운 위치를 찾아내고 가장 가까운 위치의 map 값이 1 이상일 경우에는 중복된 위치기 때문에 큐에 넣어주지 않는다. -1일 경우는 정수의 위치기 때문에 현재 이동 횟수를 반환한다.**
7. **3 ~ 6과정을 종수 이동 순서대로 진행해주고 종수 아두이노와 미친 아두이노가 끝까지 맞닥뜨리지 않을 경우 배열을 출력한다.**

**<br/>**

**에러 케이스 :** 

- **미친 아두이노를 이동할 때에는 단순히 차례로 이동시키기만 해서는 안된다. 우선 기존의 아두이노 위치들의 map 값을 초기화 시킨 후 이동시켜 주어야 한다. 이런 과정이 없다면 기존 위치의 아두이노를 이동 후의 아두이노로 인식하여 에러를 발생시킨다.**
- **마지막에 배열을 출력할 때 map 배열의 값이 1인 인덱스에만 미친 아두이노를 출력해주어야 한다. 내가 짠 로직의 경우 중복된 위치가 마지막에 생겨 1 초과 값이 들어있을 수 있기 때문에 본 인덱스에서는 아두이노가 모두 사라지기 때문에 이를 주의한다.**
- **또 내가 실수한 부분은 아두이노 이동 횟수가 (문자열 인덱스 번호) + 1이라는 것이다. 기본 적인 부분이지만 단순히 인덱스 번호를 출력하면 안된다.. 주의 !**

<br/>

<br/>

#### 코드 : 

```c++
#include <iostream>
#include <queue>
#include <utility>
#include <string>
#include <cmath>

#define pii pair<int, int>

using namespace std;
queue<pii > q;
string moveorder;
int rows, cols, jongsux, jongsuy;
int di[10] = {0, 1, 1, 1, 0, 0, 0, -1, -1, -1}, dj[10] = {0, -1, 0, 1, -1, 0, 1, -1, 0, 1};
int map[100][100];

int moveProcess() {
    int orderlength = moveorder.length();

    for (int i = 0; i < orderlength; i++) {
        int movedir = moveorder[i] - '0';
        map[jongsux][jongsuy] = 0;
        jongsux += di[movedir];
        jongsuy += dj[movedir];
        // 종수 위치 이동

        if (map[jongsux][jongsuy] > 0) return i;
        map[jongsux][jongsuy] = -1;
        // 종수 아두이노 미친 아두이노와 맞닥뜨렸는지 확인

        int size = q.size();
        while (size--) {
            int curx = q.front().first, cury = q.front().second;
            q.pop();

            if (map[curx][cury] > 1) {
                map[curx][cury] = 0;
                continue;
            }
            map[curx][cury] = 0;
            q.push(pii(curx, cury));
        }   // 미친 아두이노 위치 map value 모두 초기화 후 중복 위치된 아두이노 제외 큐에 다시 삽입

        size = q.size();
        while (size--) {
            int curx = q.front().first, cury = q.front().second;
            q.pop();

            int minx, miny, mindiff = 987654321;
            for (int k = 1; k < 10; k++) {
                if (k == 5) continue;
                int cmpx = curx + di[k], cmpy = cury + dj[k], cmpdiff = abs(jongsux - cmpx) + abs(jongsuy - cmpy);
                if (cmpx < 0 || cmpx >= rows || cmpy < 0 || cmpy >= cols) continue;

                if (cmpdiff < mindiff) {
                    mindiff = cmpdiff;
                    minx = cmpx;
                    miny = cmpy;
                }
            }   // 8방향 중 가장 가까운 위치 탐색

            if (map[minx][miny] == -1) return i;
            map[minx][miny]++;
            if (map[minx][miny] > 1) continue;
            // 이동된 미친 아두이노 위치가 변수 상황인 경우

            q.push(pii(minx, miny));
        }
    }

    return orderlength;
}

int main() {
    char input;
    cin >> rows >> cols;

    for (int i = 0; i < rows; i++)
        for (int j = 0; j < cols; j++) {
            cin >> input;
            if (input == 'I') {
                jongsux = i;
                jongsuy = j;
            } else if (input == 'R') {
                q.push(pii(i, j));
                map[i][j] = 1;
            }
        }

    cin >> moveorder;
    int moveresult = moveProcess();
    if (moveresult < moveorder.length()) cout << "kraj " << moveresult + 1 << '\n';
    else {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (map[i][j] == -1) cout << 'I';
                else if (map[i][j] == 1) cout << 'R';
                else cout << '.';
            }
            cout << '\n';
        }
    }

    return 0;
}
```

