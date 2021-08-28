# 스티커 모으기(2) - PROGRAMMERS

###### 작성자 : O-star

<br/>

<br/>

### 풀이 : 

![image](https://user-images.githubusercontent.com/57346455/131207282-9fcab815-ff33-457c-9420-582954293825.png)

**계단 오르기와 같은 대표적은 DP 유형에 속하는 문제라고 처음에 감이 왔다.**

**하지만 원형으로 구성되어 있기 때문에 평범하게 memorization을 설계해나갈 수 없는 구조였다.**

**원형 구조에서는 가장 처음 0번 인덱스를 선택하느냐 마느냐에 따라 마지막 인덱스를 선택할 수 있는지 없는지가 결정된다.**

**따라서 DP memorization 배열을 2차원 배열로 구성하여 0번 인덱스를 선택한 경우와 선택하지 않은 경우를 따로 수행해주어야 한다.**

**또 본 문제에서는 인접한 스티커를 연속해서 선택할 수 없다. 즉 이 조건에 따르면 최대 두 개 이하의 스티커를 뛰어넘고 선택할 수 있다. (3개의 스티커 중에서는 무조건 하나 이상 선택하게 되어 있음)** 

**=> 따라서 이 조건을 사용해서 위 그림의 점화식처럼 `dp[x] = max(dp[x-2], dp[x-3]) + sticker[x-1]` 의 식을 도출할 수 있다.**

<br/>

<br/>

### 코드 : 

```c++
#include <vector>
#include <algorithm>

using namespace std;

int solution(vector<int> sticker) {
    int dp[2][100000]{0}, stickerSize = sticker.size();

    if (stickerSize == 1) return sticker[0];
    dp[0][1] = sticker[0];
    dp[0][2] = sticker[1];
    dp[1][2] = sticker[1];
    for (int k = 3; k <= stickerSize; k++) {
        for (int t = 0; t < 2; t++) {
            dp[t][k] = max(dp[t][k - 2], dp[t][k - 3]) + sticker[k - 1];
        }
    }
    return max(max(dp[0][stickerSize - 2], dp[0][stickerSize - 1]), max(dp[1][stickerSize - 1], dp[1][stickerSize]));
}
```

