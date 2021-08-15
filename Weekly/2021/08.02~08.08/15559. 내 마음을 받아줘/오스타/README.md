# 15559. 내 마음을 받아줘

###### 작성자 : O-star

<br/>

<br/>

### 풀이 : 

인턴 실습 때문에 알고리즘을 너무 오래 쉰 탓에 다시 매일 많이 풀어봐야 할 것 같다.

다행히도 전에 풀어본 문제여서 다시 실력을 끌어올릴 겸 풀어보았다.

문제는 재귀 방식의 경로 탐색 방법을 사용하면 쉽게 접근할 수 있었다.

<br/>

**[ 세부 구현사항 ]**

- 최대 가로, 세로 1000 사이즈의 배열에서 어느 칸에서든 선물에 접근할 수 있도록 최소 선물 갯수를 도출해야 한다. 즉, 배열 상에서 서로 연결되지 않은 경로 갯수를 도출해내면 되는 문제이다.
- 문제는 크게 현재 인덱스 칸에서 이동해야 하는 방향을 저장해두는 `map[1000][1000]` 배열과 현재 인덱스 칸이 연결되어 있는 경로 번호를 저장하는 `routeIndex[1000][1000]` 배열만을 사용해서 해결한다.
- 재귀 함수를 사용해 현재 칸의 경로 번호를 결정한다. 초반 curidx(다음번 경로 번호)로 초기값을 설정해주고 탐색을 진행하다 다음 칸에 이미 경로 번호가 저장되어있는 칸을 만날 시 해당 번호로 재귀 함수를 빠져나오면서 다시 설정해주는 방식으로 각 칸의 번호를 설정해준다.
- 이렇게 모든 칸에 경로 번호 설정이 완료되면 현재까지의 경로 번호가 최소 선물 갯수가 된다.

<br/>

<br/>

### 코드 : 

```c++
#include <iostream>

using namespace std;
int width, height, routeidx, map[1000][1000], routeIndex[1000][1000];
int di[4] = {0, 1, 0, -1}, dj[4] = {1, 0, -1, 0};

int dirToInt(char dir) {
    switch(dir) {
        case 'E': return 0;
        case 'S': return 1;
        case 'W': return 2;
        default: return 3;
    }
}

int selectRouteIndex(int curidx, int curx, int cury) {
    if(routeIndex[curx][cury]) return routeIndex[curx][cury];
    routeIndex[curx][cury] = curidx;

    return routeIndex[curx][cury] = selectRouteIndex(curidx, curx + di[map[curx][cury]], cury + dj[map[curx][cury]]);
}

int main() {
    char input;
    cin >> height >> width;

    for(int i=0; i<height; i++)
        for(int j=0; j<width; j++){
            cin >> input;
            map[i][j] = dirToInt(input);
        }

    for(int i=0; i<height; i++)
        for(int j=0; j<width; j++)
            if(!routeIndex[i][j]) {
                if(selectRouteIndex(routeidx + 1, i, j) == routeidx + 1)
                    routeidx++;
            }

    cout << routeidx;
    return 0;
}
```

