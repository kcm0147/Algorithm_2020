# 20057. 마법사 상어와 토네이도

###### 작성자 : O-star

<br/>

<br/>

#### 풀이 : 

**최근 삼성 역량 테스트 문제에서 배열을 회전 시키거나 회전하면서 탐색하는 로직을 활용해야 하는 알고리즘 문제들이 많이 출제되고 있다.**

**그 문제들 중에서도 비교적 풀만한 문제였던 것 같다.**

**하지만 조금만 더 어려워지면 생각하기 어려운 로직들일 것 같아 배열 회전, 탐색의 구현에 익숙해지려면 좀 더 연습이 필요할 것 같다.**

**<br/>**

**세부 구현 사항 :** 

- **행, 열 길이 n은 항상 홀수이며 출발위치는 (n/2, n/2)에서 출발한다. 배열을 회전 탐색하면서 (0, 0)에 도달할 때까지 배열 인덱스 밖으로 빠져나온 모래양을 계산한다.**

- **회전 탐색 구현의 경우 일정한 규칙을 발견할 수 있다. 처음 시작 방향은 임의로 정해도 무관하다. 나는 코드에서 왼쪽 방향부터 시작하는 것으로 가정했다. 회전은 상하좌우 4방향으로 진행되는 이동량이 2회 후에 1씩 증가하는 형태를 가지고 있다. 가령 처음 이동량부터 적어보면 1, 1, 2, 2, 3, 3 ...의 형태를 띄고 있다. 따라서 이동량은 2의 배수에서 1씩 증가하면서 이동방향만 바꿔주면 회전 탐색을 구현할 수 있다.**

- **모래가 날려가는 양을 계산할 때에는 아래 그림처럼 12가지 위치의 인덱스를 잡고 계산해 주었다. 이동방향에 따라서 각 인덱스별 모래 이동 비율이 달라지기 때문에 해당 인덱스의 방향별 모래 비율을 저장하는 이차원 배열 `dv[4][12]` 를 만들어 계산에 활용했다.**

  ![image](https://user-images.githubusercontent.com/57346455/118751023-91c54b80-b89b-11eb-9a73-c8f58732c260.png)

<br/>

<br/>

#### 코드 : 

```c++
#include <iostream>

using namespace std;
int n, map[500][500];
int movedi[4] = {0, 1, 0, -1}, movedj[4] = {-1, 0, 1, 0};
int di[12] = {-2, -1, -1, -1, 0, 0, 0, 0, 1, 1, 1, 2}, dj[12] = {0, -1, 0, 1, -2, -1, 1, 2, -1, 0, 1, 0};
int dv[4][12] = {{2, 10, 7,  1,  5, -1, 0,  0, 10, 7,  1,  2},
                 {0, 1,  0,  1,  2, 7,  7,  2, 10, -1, 10, 5},
                 {2, 1,  7,  10, 0, 0,  -1, 5, 1,  7,  10, 2},
                 {5, 10, -1, 10, 2, 7,  7,  2, 1,  0,  1,  0}};

int tornadoProcess() {
    int movelength = 0, movedir = -1, outsum = 0;
    int curx = n / 2, cury = n / 2, alpax, alpay;

    while (1) {
        movelength++;

        for (int i = 0; i < 2; i++) {
            movedir = (movedir + 1) % 4;

            for (int j = 0; j < movelength; j++) {
                curx += movedi[movedir];
                cury += movedj[movedir];
                int curamount = map[curx][cury];

                for (int k = 0; k < 12; k++) {
                    if (!dv[movedir][k]) continue;
                    int cmpx = curx + di[k], cmpy = cury + dj[k], moveamount = curamount * dv[movedir][k] / 100;
                    if (dv[movedir][k] == -1) {
                        alpax = cmpx;
                        alpay = cmpy;
                        continue;
                    }

                    map[curx][cury] -= moveamount;
                    if (cmpx < 0 || cmpx >= n || cmpy < 0 || cmpy >= n) outsum += moveamount;
                    else map[cmpx][cmpy] += moveamount;
                }

                if (alpax < 0 || alpax >= n || alpay < 0 || alpay >= n) outsum += map[curx][cury];
                else map[alpax][alpay] += map[curx][cury];
                map[curx][cury] = 0;

                if (!curx && !cury) return outsum;
            }
        }
    }

}

int main() {
    cin >> n;

    for (int i = 0; i < n; i++)
        for (int j = 0; j < n; j++)
            cin >> map[i][j];

    cout << tornadoProcess() << '\n';

    return 0;
}
```

