# 2342. Dance Dance Revolution

###### 작성자 : O-star

<br/>

<br/>

## 풀이 : 

**DP를 활용해야 문제를 해결할 수 있었다. 어떤 데이터를 메모이제이션할지 잘 정해서 점화식만 세우면 문제를 해결할 수 있었으나 점화식을 설계하는데까지 쉽지는 않은 문제인 듯 하다.**

<br/>

#### **[세부 구현사항]**

- **문제는 한번 발을 옮기는 경우의 수가 왼발, 오른발 두가지 경우의 수가 있기 때문에 최대 100,000 길이의 수열이 들어오는 문제에서 완전 탐색 방식으로 문제를 해결할 수는 없다.**
- **문제를 해결하기 위해선 `dp[i][j][k]`(i: 수열 인덱스, i번째 밟아야 하는 버튼, j:왼쪽발의 위치, k:오른쪽발의 위치)를 활용해서 현재 발 위치에서 최소의 힘 값을 사용해 다음 번 버튼을 밟을 경우의 최소 힘 값을 구한다.**
- **발을 옮길 수 있는 경우는 같은 지점을 다시 누를 경우(+1), 중앙 위치에서 다른 버튼을 누를 경우(+2), 인접 지점으로 이동해서 누를 경우(+3), 반대편으로 이동해 누를 경우(+4) 총 4가지 경우가 있다. => 왼발, 오른발은 무시해도 좋다. 여기서 무시해도 된다는 말은 왼쪽 발을 기준으로 발을 옮길 수 있는 경우를 모두 고려할 경우 오른발을 이동하는 경우도 같은 최소 힘이 동일하다는 의미이다. (`dp[i][j][k] == dp[i][k][j]`)**
- **점화식 : `dp[i][k][nextbtn] = min(dp[i - 1][k][nextbtn] + 1, dp[i - 1][k][0] + 2, dp[i - 1][k][nextbtn == 4 ? 1 : nextbtn + 1] + 3, dp[i - 1][k][nextbtn == 1 ? 4 : nextbtn - 1] + 3, dp[i - 1][k][(nextbtn + 2) % 4 == 0 ? 4 : (nextbtn + 2) % 4] + 4)`**

<br/>

<br/>

#### 코드 : 

```c++
#include <iostream>
#include <vector>
#include <algorithm>

#define MAX 987654321

using namespace std;
int dp[100000][5][5];
vector<int> btnvec;

void dpProcess() {
    int vecsize = btnvec.size();


    for (int i = 0; i < vecsize; i++)
        for (int j = 0; j < 5; j++)
            fill_n(dp[i][j], 5, MAX);
    dp[0][0][0] = 0;

    for (int i = 1; i < vecsize; i++) {
        int nextbtn = btnvec[i];

        for (int k = 0; k < 5; k++) {
            if (k == nextbtn) continue;
            dp[i][k][nextbtn] = min(
                    {MAX, dp[i - 1][k][nextbtn] + 1, dp[i - 1][k][0] + 2,
                     dp[i - 1][k][nextbtn == 4 ? 1 : nextbtn + 1] + 3,
                     dp[i - 1][k][nextbtn == 1 ? 4 : nextbtn - 1] + 3,
                     dp[i - 1][k][(nextbtn + 2) % 4 == 0 ? 4 : (nextbtn + 2) % 4] + 4});
            dp[i][nextbtn][k] = dp[i][k][nextbtn];
        }
    }
}

int main() {
    int btn, minans = MAX;

    btnvec.push_back(0);
    while (true) {
        cin >> btn;
        if (!btn) break;
        btnvec.push_back(btn);
    }
    dpProcess();

    int lastidx = btnvec.size() - 1;
    for (int i = 0; i < 5; i++)
        for (int j = 0; j < 5; j++)
            minans = min(minans, dp[lastidx][i][j]);
    cout << minans << '\n';
    return 0;
}
```

