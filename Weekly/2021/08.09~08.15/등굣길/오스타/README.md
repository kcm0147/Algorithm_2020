# 등굣길 [programmers]

###### 작성자 : O-star

<br/>

<br/>

### 풀이 : 

전형적인 DP 문제이다.

DP 경로 횟수를 저장하는 dpMap 배열을 활용하여 각 인덱스의 칸에 도달하는 최소 경로 갯수를 누적으로 저장해가면서 값을 계산한다.

puddleMap 배열을 활용하여 해당 인덱스 칸이 웅덩이인지 아닌지를 판별하도록 구현하였다.

이 외에는 경로 횟수를 1,000,000,007로 나눈 나머지를 저장해야한다는 조건 외에는 크게 어렵지 않게 구현할 수 있는 문제이다.

<br/>

<br/>

### 코드 : 

```c++
#include <vector>

#define DVD 1000000007

using namespace std;

int dpMap[101][101];
bool puddleMap[101][101];

int solution(int m, int n, vector<vector<int>> puddles) {

    unsigned puddleSize = puddles.size();
    for (int i = 0; i < puddleSize; i++)
        puddleMap[puddles[i][1]][puddles[i][0]] = true;
    dpMap[1][0] = 1;

    for (int i = 1; i <= n; i++)
        for (int j = 1; j <= m; j++) {
            if (puddleMap[i][j]) {
                dpMap[i][j] = 0;
                continue;
            }
            dpMap[i][j] = (dpMap[i - 1][j] + dpMap[i][j - 1]) % DVD;
        }

    return dpMap[n][m];
}
```

