# 1504. 용액

###### 작성자 : O-star

<br/>

<br/>

### 풀이 : 

**그리 어렵지 않은 문제였으나 꽤나 헤맨 문제였다..**

**우선 처음에는 두 노드를 지나는 최단 경로(경로, 노드는 중복되어도 됌)를 구하기 위해서 두 노드의 방문 유무를 판단하는 2차원 visited 배열을 활용한 경로 탐색 방법을 구현하려 했다.**

**하지만 이 방법에서 난 visited 배열에서 지나가야 하는 a, b 노드의 방문 유무를 (axbx, aobx, axbo, aobo) 이렇게 4가지 경우만 고려해주었는데 a와 b를 모두 지나온 경로의 경우 a를 먼저 지나온 경로와 b를 먼저 지나온 경로의 방문 유무가 겹쳐서 저장되기 때문에 에러가 발생했다.**

**그래서 이 방법 보다 1->a->b->n 경로와 1->b->a->n 경로 이렇게 두 가지 경로만을 다익스트라 알고리즘을 통해 계산해주면 되기 때문에 다시 코드를 설계했다.**

**다익스트라 알고리즘은 크게 3번 실행해서 최단 경로를 도출할 수 있다. => 1 -> (a or b) , a -> b , n -> (a or b) 양방향 경로인 것을 감안하면 이렇게 3번의 다익스트라 알고리즘 실행과 조합만으로 최단 경로를 도출할 수 있다.**

1. **dist(1 -> a) + dist(a -> b) + dist(n -> b)**
2. **dist(1 -> b) + dist(a -> b) + dist(n -> a)**

**[ dist(x -> y) == dist(y -> x) ]**

<br/>

<br/>

### 코드 : 

```c++
#include <iostream>
#include <vector>
#include <utility>
#include <queue>
#include <algorithm>

#define pii pair<int, int>

using namespace std;
int n, e, mid1, mid2;
vector<vector<pii>> adj;

pii dijkstra(int start) {
    bool visited[801]{false};
    int toMid1 = -1, toMid2 = -1, cnt = 0;
    priority_queue<pii, vector<pii >, greater<>> pq;

    pq.push(pii(0, start));
    while (!pq.empty()) {
        int curidx = pq.top().second, curdist = pq.top().first;
        pq.pop();
        if (visited[curidx]) continue;

        if (curidx == mid1) {
            toMid1 = curdist;
            if (++cnt == 2) break;
        } else if (curidx == mid2) {
            toMid2 = curdist;
            if (++cnt == 2) break;
        }

        visited[curidx] = true;
        int cursize = adj[curidx].size();

        for (int k = 0; k < cursize; k++) {
            int cmpidx = adj[curidx][k].first, cmpdist = adj[curidx][k].second;
            pq.push(pii(curdist + cmpdist, cmpidx));
        }
    }
    return pii(toMid1, toMid2);
}

int calculMinDist() {
    int distSum1 = 0, distSum2 = 0;

    pii startToMid = dijkstra(1);
    pii mid1ToMid2 = dijkstra(mid1);
    pii destToMid = dijkstra(n);

    if (mid1ToMid2.second == -1) return -1;

    if (startToMid.first != -1 && destToMid.second != -1) {
        distSum1 = startToMid.first + mid1ToMid2.second + destToMid.second;
    }
    if (startToMid.second != -1 && destToMid.first != -1) {
        distSum2 = startToMid.second + mid1ToMid2.second + destToMid.first;
    }

    if (!distSum1 && !distSum2) return -1;
    return min(distSum1, distSum2);
}

int main() {
    int fir, sec, val;
    cin >> n >> e;
    adj.resize(n + 1);

    while (e--) {
        cin >> fir >> sec >> val;
        adj[fir].emplace_back(sec, val);
        adj[sec].emplace_back(fir, val);
    }
    cin >> mid1 >> mid2;

    cout << calculMinDist() << '\n';
    return 0;
}
```

