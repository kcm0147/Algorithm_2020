# 2467. 용액

###### 작성자 : O-star

<br/>

<br/>

### 풀이 : 

투 포인터를 활용하면 쉽게 해결할 수 있는 문제이다.

두 포인터를 어느 경우에서 이동시켜야 하는지 잘 설계한다면 입력되는 모든 용액이 양수이든 음수이든 상관없이 짧은 코드로 문제를 해결할 수 있다.

**left index 이동 경우 : 현재 왼쪽, 오른쪽 포인터 용액의 합이 0보다 작을 경우 두 합이 더 커져야 하기 때문에 left index를 1 증가시킨다.**

**right index 이동 경우 : 현재 왼쪽, 오른쪽 포인터 용액의 합이 0보다 클 경우 두 합이 더 작아져야 하기 때문에 right index를 1 감소시킨다.**

<br/>

<br/>

### 코드 : 

```c++
#include <iostream>
#include <vector>
#include <cmath>

#define MAXVAL 9000000000
#define ll long long

using namespace std;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);
    vector<int> liquidVec;
    int n, input, selectLiquid1, selectLiquid2;
    ll minDiff = MAXVAL;
    cin >> n;

    for (int i = 0; i < n; i++) {
        cin >> input;
        liquidVec.push_back(input);
    }

    int leftIdx = 0, rightIdx = n - 1;
    while (leftIdx < rightIdx) {
        ll compDiff = liquidVec[leftIdx] + liquidVec[rightIdx];
        if (minDiff > abs(compDiff)) {
            minDiff = abs(compDiff);
            selectLiquid1 = liquidVec[leftIdx];
            selectLiquid2 = liquidVec[rightIdx];
        }
        if (compDiff > 0) rightIdx--;
        else leftIdx++;
    }
    cout << selectLiquid1 << ' ' << selectLiquid2 << '\n';
    return 0;
}
```

