# 20313. 출퇴근

### 작성자 : O-star

<br/>

<br/>

### 풀이 : 

탐색 문제는 대체로 접근을 잘 하는 편이라 생각했지만, 본 문제는 특히 까다로운 문제였던 것 같다.

우선 마법 횟수로 인해 경로 탐색 시 전체 경로의 소요 시간을 변경시킬 수 있기 때문에 이러한 점을 고려하여 경로 탐색을 진행해야 한다.

마법을 부릴 경우 전체 경로들의 소요 시간이 변경되기 때문에 기존 BFS 풀이 방식처럼 adj 벡터를 통해 고정 소요시간을 저장해두면 문제에 접근 할 수 없다.

세부 구현사항은 다음과 같다.

<br/>

#### **[ 세부 구현사항 ]**

- 문제는 BFS 방식으로 해결한다. 단순한 BFS 방식처럼 풀이해서는 접근할 수 없었고 각 건물에 도달했을 시 현재 사용한 마법 횟수의 경우에서 최소 시간을 저장해두는 `costMap[101][1001]` 을 활용하여 탐색 경우의 수를 가지치기해야 더 효율적으로 탐색할 수 있다.
- 여기서 costMap 배열에 값을 저장할 때 반드시 각 도시에서 **마법 횟수별** 최소 시간을 저장해두어야 한다. 마법 횟수를 고려하지 않고 도시의 최소 시간을 저장해둘 시 마법 횟수를 다르게 사용하고 해당 도시를 지나쳐서 더 적은 소요시간을 가지는 경우의 수를 고려할 수 없기 때문에 이 점이 key point라고 볼 수 있다.
- 각 경로별 소요 시간은 adj 벡터에는 해당 경로의 인덱스를 저장해두고 현재 경로 소요시간과 각 마법 횟수 별 경로 소요 시간을 인덱스별로 저장해둔 2차원 벡터 costVec을 사용하여 소요시간 값에 접근한다.

마법 횟수가 최대 100이기 때문에 사실 본 코드도 더 효율적으로 탐색하지 않으면 시간초과가 발생하지 않을까 생각했지만 costMap을 활용한 백트래킹 방식으로 문제를 해결할 수 있었던 것 같다.

<br/>

<br/>

### 코드 : 

```c++
#include <iostream>
#include <vector>
#include <utility>
#include <queue>

#define MAXVAL 10000000000000
#define ll long long
#define pii pair<int, int>

using namespace std;

struct Node {
    ll costsum;
    int magiccnt, curidx;
};

struct compare {
    bool operator()(Node a, Node b) {
        return a.costsum > b.costsum;
    }
};

int n, startPos, endPos, magicNum;
ll costMap[101][1001];
vector<vector<pii>> adjVec;
vector<vector<int>> costVec(1);

ll bfs() {
    priority_queue<Node, vector<Node>, compare> pq;

    costMap[0][startPos] = 0;
    pq.push(Node{0, 0, startPos});

    while (!pq.empty()) {
        ll curcost = pq.top().costsum;
        int curmagiccnt = pq.top().magiccnt, curidx = pq.top().curidx;
        pq.pop();
        if (costMap[curmagiccnt][curidx] != curcost) continue;
        if (curidx == endPos) return curcost;

        int size = adjVec[curidx].size();
        for (; curmagiccnt <= magicNum; curmagiccnt++)
            for (int k = 0; k < size; k++) {
                int nextidx = adjVec[curidx][k].first;
                ll nextcost = curcost + costVec[curmagiccnt][adjVec[curidx][k].second];
                if (costMap[curmagiccnt][nextidx] > nextcost) {
                    costMap[curmagiccnt][nextidx] = nextcost;
                    pq.push(Node{nextcost, curmagiccnt, nextidx});
                }
            }

    }
}

int main() {
    int m, fir, sec, cost;

    cin >> n >> m >> startPos >> endPos;
    adjVec.resize(n + 1);

    for (int i = 0; i <= 100; i++)
        fill_n(costMap[i], n + 1, MAXVAL);

    for (int i = 0; i < m; i++) {
        cin >> fir >> sec >> cost;
        adjVec[fir].push_back(pii(sec, i));
        adjVec[sec].push_back(pii(fir, i));
        costVec[0].push_back(cost);
    }

    cin >> magicNum;
    costVec.resize(magicNum + 1);

    for (int i = 1; i <= magicNum; i++)
        for (int j = 0; j < m; j++) {
            cin >> cost;
            costVec[i].push_back(cost);
        }

    cout << bfs() << '\n';

    return 0;
}
```

