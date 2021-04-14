# 1256. 사전

###### 작성자 : O-star

<br/>

<br/>

#### 풀이 : 

**BFS 탐색으로 경우의 수를 따라가면 문제를 해결할 수 있는 것 같다.**

**단 1)한 자리 숫자 보수 바꾸기, 2)현재 수에 1 더하기, 3)현재 수에 1 빼기 라는 조건을 정확하게 인식하고 코딩해야 실수 없이 문제를 해결할 수 있다.**

**나 같은 경우에는 한 자리 숫자 보수 바꾸기 부분에서 맨 앞 숫자 전까지만 숫자를 바꿔주어야 하는데 이 부분에서 범위를 잘 못 측정해서 꽤나 에러가 난 문제였다.**

1. **난 가장 먼저 입력받은 2진수 숫자를 10진수로 바꾸어주었다. => 비트 연산자를 효과적으로 사용하기 위해**
2. **다음은 탐색을 기다리고 있는 숫자들이 대기하는 큐와 탐색 이력 유무를 체크할 set을 하나씩 만들어 주었다. -> 처음에는 탐색 이력 유무 체크를 bool 배열로 체크해 주려고 했으나 10자리 이진수라고 생각하고 1024 근처의 범위로 배열을 만드니 계속 런타임 에러가 발생했다. 두 배 이상의 크기를 잡으니 런타임에러는 해결되었으나 set을 사용해 이력 유무를 체크하면 범위에 대한 걱정을 하지 않아도 된다고 생각해 그냥 set을 사용했던 것 같다.**
3. **그 이후부터는 비트 연산자를 사용해 3가지 조건으로 변경된 숫자를 큐에 차례로 저장해주면 문제를 쉽게 해결할 수 있었던 것 같다.**

<br/>

<br/>

#### 코드 : 

```c++
#include <iostream>
#include <cmath>
#include <queue>
#include <utility>
#include <set>

#define pii pair<int, int>

using namespace std;
int initnum, goalnum;

int binaryToDec(string strnum) {
    int size = strnum.length(), decnum = 0;

    for (int i = 0; i < size; i++) {
        if (strnum[size - i - 1] == '1')
            decnum += pow(2, i);
    }

    return decnum;
}

int main() {
    queue<pii > q;
    set<int> set;
    string initinput, goalinput;
    cin >> initinput >> goalinput;

    initnum = binaryToDec(initinput);
    goalnum = binaryToDec(goalinput);
    q.push(pii(initnum, 0));
    set.insert(initnum);

    while (!q.empty()) {
        int curnum = q.front().first, curcnt = q.front().second, cmpnum;
        q.pop();
        if (curnum == goalnum) {
            cout << curcnt << '\n';
            break;
        }

        for (int t = 1; t < 11; t++) {
            if (pow(2, t) > curnum) break;
            int val = (int) pow(2, t - 1);
            cmpnum = curnum + (((val & curnum) == 0) ? val : -val);
            if (set.find(cmpnum) == set.end()) {
                q.push(pii(cmpnum, curcnt + 1));
                set.insert(cmpnum);
            }
        }

        cmpnum = curnum + 1;
        if (set.find(cmpnum) == set.end()) {
            q.push(pii(cmpnum, curcnt + 1));
            set.insert(cmpnum);
        }

        if (!curnum) continue;
        cmpnum = curnum - 1;
        if (set.find(cmpnum) == set.end()) {
            q.push(pii(cmpnum, curcnt + 1));
            set.insert(cmpnum);
        }
    }

    return 0;
}
```

