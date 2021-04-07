# 1256. 사전

###### 작성자 : O-star

<br/>

<br/>

#### 풀이 : 

**지난 번에 풀어본 문제였지만 다시 풀어봐도 어려웠다!.. DP가 좀 감이 오는 것 같았는데, 다시 어려워지는 것 같다.**

**문제를 해결하고 나서 풀이를 정리하자면 key point는 두가지인 것 같다.**

1. **전형적인 memorization, 하지만 memorization을 활용해서 K번째 문자열을 재탐색해나가는 과정이 꽤나 까다로웠다.**
2. **memorization 배열의 크기를 long long 형으로 잡더라도 해당 범위를 넘어선 큰 수를 저장해야 한다. 여기서 포인트는 K가 1,000,000,000이하이기 때문에 최대값을 1,000,000,000보다 큰 값으로 맞춰주면 문제를 해결할 수 있다.**

**Memorization의 경우 행을 a의 갯수 열을 z의 갯수로 간주해서 dp[i] [j] = a i개와 z j개로 만들 수 있는 조합 갯수를 저장한다.**

**여기서 2번에서 말한 것과 같이 long long형이 담을 수 없는 범위까지 저장되어 overflow가 일어나는데 그래서 최댓값을 정해둬야 한다.**

**문자열 재탐색의 경우 재귀 방식을 이용해 curidx + dp[i-1] [j] ≥ findidx(찾으려는 문자열 인덱스)일 경우 'a' 문자를 더해주고 그 외의 경우 'z' 글자를 더해주는 방식으로 최종 문자열을 완성해 나갈 수 있다.**

<br/>

<br/>

#### 코드 : 

```c++
#include <iostream>
#include <string>
#include <algorithm>

#define MAX 1234567890
#define ll long long

using namespace std;
int findidx;
ll dp[101][101];

string findString(string curstr, int anums, int znums, int curidx) {
    if (!anums && !znums) return curstr;

    if (anums > 0 && curidx + dp[anums - 1][znums] >= findidx)
        return findString(curstr + 'a', anums - 1, znums, curidx);
    else
        return findString(curstr + 'z', anums, znums - 1, curidx + dp[anums - 1][znums]);
}

int main() {
    int n, m;
    cin >> n >> m >> findidx;

    dp[0][0] = 1;
    for (int i = 1; i <= 100; i++) {
        dp[i][0] = 1;
        dp[0][i] = 1;
    }

    for (int i = 1; i <= 100; i++)
        for (int j = 1; j <= 100; j++)
            dp[i][j] = min((ll) MAX, dp[i - 1][j] + dp[i][j - 1]);
    if (dp[n][m] < findidx) cout << "-1\n";
    else cout << findString("", n, m, 0) << '\n';
    return 0;
}
```

