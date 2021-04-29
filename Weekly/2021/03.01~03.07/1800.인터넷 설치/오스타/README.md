# 1800. 인터넷 설치

###### 작성자 : O-star

<br/>

<br/>

#### 풀이 : 

풀이를 보지 않고서는 사실 잘 접근하지 못한 문제였던 것 같다.

처음에는 K개의 인터넷 선에 대해서는 공짜로 연결해주고 가장 높은 가격의 인터넷 선만 가격을 받는다고 하길래, N번 컴퓨터에서 K개의 연결선까지는 가격비교를 하지 않고 그 이후의 연결선 부터 가장 가격이 비싼 인터넷 선을 구해보려고 했다.

본 방식은 테스트케이스는 통과할 수 있었으나, 생각해보니 초반에 연결하는 인터넷 선을 공짜로 한다고 해서 가장 비싼 인터넷 선 가격을 낮출 수 있는 것은 아니였다. -> 가장 비싼 인터넷 선 상위 K개를 제외시켜야 최소 가격이 나올텐데 초반 선들을 무작정 공짜 선으로 선택하게 되면 이 조건이 부합되지 않는 것이다.

풀이를 참고해 본 결과 일반적 다익스트라 알고리즘에서 이분 탐색을 결합한 형태로 문제를 해결해야 했다. 이 풀이과정을 구현하는데 있어서 사실 이분 탐색을 오랜만에 구현해서 그런지 생각보다 까다로운 구현 파트가 많았던 것 같다.

우선 이분 탐색으로 인터넷 선 MAX 범위 가격을 결정하고 해당 가격 이하의 가격 인터넷 선만을 사용해서 1번 컴퓨터에서 N번 컴퓨터로 향할 수 있는지 탐색한다. (단, MAX 범위 가격 초과 가격의 인터넷 선은 K개에 한해 사용할 수 있도록 구현한다.) 

만약 해당 인터넷 선 가격범위로 경로를 만들 수 있다면 right를 mid - 1로, 그렇지 않다면 left를 mid + 1로 바꿔주면서 가격범위를 측정해 준다면 최종적으로 이분탐색과 다익스트라 알고리즘을 결합한 형태로 문제를 해결할 수 있다.

<br/>

<br/>

#### 코드 : 

```c++
#include <iostream>
#include <vector>
#include <utility>
#include <queue>
#include <algorithm>

#define pii pair<int, int>
#define MAX 9999999

using namespace std;
typedef struct Qnode {
    int idx, remainfree;
} Qnode;

struct compare {
    bool operator()(Qnode a, Qnode b) {
        return a.remainfree < b.remainfree;
    }
};

vector<vector<pii>> adj;
vector<int> pricevec;
int n, freenums;

bool searchPath(int stdprice) {
    priority_queue<Qnode, vector<Qnode>, compare> pq;
    bool *visit = new bool[n + 1]{false};
    pq.push(Qnode{1, freenums});

    while (!pq.empty()) {
        int curidx = pq.top().idx, curremain = pq.top().remainfree;
        pq.pop();

        if (visit[curidx]) continue;
        visit[curidx] = true;
        if (curidx == n) return true;

        int size = adj[curidx].size();
        for (int k = 0; k < size; k++) {
            int nextidx = adj[curidx][k].first, nextprice = adj[curidx][k].second;

            if (visit[nextidx]) continue;
            if (stdprice >= nextprice) {
                pq.push(Qnode{nextidx, curremain});
            } else if (curremain) {
                pq.push(Qnode{nextidx, curremain - 1});
            }
        }
    }
    return false;
}

int binarySearch(int left, int right) {
    if (left == right) {
        if (searchPath(pricevec[left])) return left;
        else return left + 1;
    }

    int mid = (left + right) / 2;
    if (searchPath(pricevec[mid])) return binarySearch(left, mid - 1);
    else return binarySearch(mid + 1, right);
}

int main() {
    int cables, fir, sec, price;
    cin >> n >> cables >> freenums;

    adj.resize(n + 1);

    while (cables--) {
        cin >> fir >> sec >> price;
        adj[fir].emplace_back(sec, price);
        adj[sec].emplace_back(fir, price);
        pricevec.push_back(price);
    }

    sort(pricevec.begin(), pricevec.end());

    int ansidx = binarySearch(0, pricevec.size() - 1);
    if (!ansidx) {
        if (searchPath(0)) cout << "0\n";
        else cout << pricevec[ansidx] << '\n';
    } else if (ansidx > pricevec.size() - 1) cout << "-1\n";
    else cout << pricevec[ansidx] << '\n';

    return 0;
}
```

