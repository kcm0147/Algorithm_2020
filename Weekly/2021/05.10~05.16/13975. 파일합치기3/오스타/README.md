# 13975. 파일합치기3

###### 작성자 : O-star

<br/>

<br/>

### 풀이 : 

**문제를 너무 어렵게 생각한 탓에 오히려 로직을 생각해내지 못한 문제였다...**

**처음에는 서로 인접해 있는 파일끼리만 합칠 수 있는 줄 알고 행렬 최소 곱셈 횟수를 구하는 문제와 같이 dp로 풀어야겠구나 라는 생각을 했다.**

**하지만 입력 범위가 백만개나 되는 양이었기에 dp memorization을 해내기에는 너무 많은 공간을 잡아먹어 이 방법이 아니겠구나 싶었다.**

**그래서 중간에 세그먼트 트리나 분할정복까지 생각해봤지만 문제는 쉽게 풀릴 것 같지 않았다.**

**그런데 알고보니 본 문제는 인접해 있는 파일끼리 합쳐나가는 문제가 아니라 그냥 작은 파일들을 줄세워서 계속 합쳐나가 그리디 방식으로 해결할 수 있는 문제였다.**

**우선 우선순위 큐를 구현하여 입력 숫자들을 모두 넣은 후 작은 숫자 두 개씩을 빼내면서 합쳐준 후 그 합의 값을 다시 큐에 넣어주고 반복해주는 방식으로 문제를 해결한다. 큐에 숫자가 하나만 남을 때까지 반복문을 돌려주면 문제를 해결할 수 있다.**

<br/>

<br/>

#### 코드 : 

```c++
#include <iostream>
#include <queue>

#define ll long long

using namespace std;

void testCase() {
    priority_queue<ll, vector<ll>, greater<>> pq;
    int n;
    ll sum = 0, input;

    cin >> n;
    while (n--) {
        cin >> input;
        pq.push(input);
    }

    while (pq.size() > 1) {
        ll num1 = pq.top();
        pq.pop();
        ll num2 = pq.top();
        pq.pop();

        pq.push(num1 + num2);
        sum += num1 + num2;
    }
    cout << sum << '\n';
}

int main() {
    int tc;
    cin >> tc;
    while (tc--)
        testCase();
    return 0;
}
```

