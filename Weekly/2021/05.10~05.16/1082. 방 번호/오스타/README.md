# 1082. 방 번호

###### 작성자 : O-star

<br/>

<br/>

#### 풀이 : 

**배낭 문제와 비슷한 유형의 문제라는 생각을 읽자마자 했던 것 같다.**

**다만 조금 다른 점이 있다면 내가 살수 있는 문자는 0~9의 숫자인데 이 숫자들의 가격이 모두 1이라고 가정하게 되면 최대 50개의 숫자를 살 수 있기 때문에 정수형, 소수형 변수에는 숫자를 담을 수 없기 때문에 string으로 표현해가면서 문제를 풀어야 한다는 점이다.**

**우선 숫자는 0~9까지의 숫자를 사용할 수 있고 최대 내가 가질 수 있는 돈은 50이기 때문에 완전탐색으로는 10^50 시간 복잡도를 가진다고 판단하였고 백트래킹 방식으로 접근하려 했지만 사실 이 방법은 현재까지 가장 크게 만들 수 있는 숫자 문자열과 남은 돈으로 효율적으로 가지치기를 해야하는데 이 방법보다 dp로 푸는 것이 더 쉬워보여 dp를 활용하기로 결정했다.**

- **dp는 0~50까지 인덱스를 가지는 배열로 계산을 이어간다. 즉 dpary[i] = (i 비용으로 만들 수 있는 최대 숫자 문자열)을 저장해두는 것이다.**
- **needary 배열은 해당 숫자의 가격을 저장하는 배열이다.**
- **그래서 dpary[i] = max(dpary[i], dp[i - needary[j]] + 'j') 형식으로 진행된다. needary의 길이만큼 max를 진행해주면 dpary[i], 즉 i 비용으로 만들 수 있는 최대 숫자 문자열을 구할 수 있다.**
- **아무래도 숫자를 문자열로 저장하고 계속 진행하기 때문에 숫자를 문자열로 비교하는 함수를 따로 구현해주어야 한다. isBigNumber() 함수가 그 기능을 하는데 두 문자열의 길이가 차이가 나면 더 긴 문자열을 반환하고, 길이가 같을 경우 첫 인덱스부터 문자를 비교해 나가면서 더 큰 문자를 가진 문자열을 반환한다.**

<br/>

<br/>

#### 코드 : 

```c++
#include <iostream>
#include <string>

using namespace std;

string isBigNumber(string a, string b) {
    int lenA = a.length(), lenB = b.length();
    if (lenA > lenB) return a;
    if (lenA < lenB) return b;

    for (int i = 0; i < lenA; i++) {
        if (a[i] == b[i]) continue;
        return ((a[i] > b[i]) ? a : b);
    }
    return a;
}

int main() {
    int n, mymoney;
    cin >> n;
    int *needary = new int[n];

    for (int i = 0; i < n; i++)
        cin >> needary[i];

    cin >> mymoney;
    string *dpary = new string[51];
    for (int i = 0; i <= mymoney; i++)
        dpary[i] = "";
    for (int i = 0; i < n; i++) {
        string cmpstr = "";
        cmpstr += '0' + i;
        dpary[needary[i]] = isBigNumber(dpary[needary[i]], cmpstr);
    }

    for (int i = 1; i <= mymoney; i++) {
        for (int j = 0; j < n; j++) {
            if (i <= needary[j]) continue;
            char ch = '0' + j;
            string cmpstr = "";
            if (dpary[i - needary[j]] != "0") cmpstr = dpary[i - needary[j]];
            cmpstr += ch;
            dpary[i] = isBigNumber(dpary[i], cmpstr);
        }
    }

    cout << dpary[mymoney] << '\n';
    return 0;
}
```

