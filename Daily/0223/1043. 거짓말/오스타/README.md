# 1043. 거짓말

###### 작성자 : O-star

<br/>

<br/>

#### 풀이 : 

**문제를 좀 어렵게 푼 감이 없지 않아 있다.**

- **우선 문제를 정리해보자면 각 파티원들은 이야기의 진실을 알고 있는지 모르는지에 대한 여부가 주어진다. => 이 여부를 vector<bool> truevec에 저장한다.**

- **이 진실 여부는 변동된다. 변동된다는 말은 진실을 모르는 사람도 어느 파티에 참여하느냐에 따라 진실을 저절로 알게될 수 있다는 것이다.**
- **이 문제에서 주의해야할 점은 단순히 파티들을 순서대로 살펴보면 진실을 아는 사람이 포함된 파티는 진실을, 진실을 모르는 사람들로 형성된 파티는 거짓말을 해서는 안된다. => 거짓말을 들은 파티원이 다른 파티에서 진실을 들을 수도 있기 때문이다.**
- **즉 본 문제는 거짓말을 할 수 있는 파티를 살펴보기 전에 진실을 알 수 있는 모든 파티원을 파악해두어야 한다.**
- **나는 진실을 알 수 있는 모든 파티원을 파악하기 위해 adj 벡터를 생성해 각 파티의 파티원들을 연결해두고 한명이라도 진실을 알고 있는 파티원이 포함된 파티 참여원들은 모두 진실을 알 수 있는 인원으로 바꾸고 adj를 계속 탐색해가며 진실여부를 변환시켜주었다. => 이 부분을 사실 disjoint set으로 구현했다면 더 코드가 깔끔했을 것 같긴한데,,, 머 결론적으로는 내가 한 과정 또한 disjoint set과 일맥상통하는 부분이 있어 굳이 코드를 어렵게 바꾸진 않았다.**
- **모든 진실 여부가 파악된 후에 파티를 차례로 확인해보며 거짓말을 할 수 있는 파티를 확인한다.**

<br/>

<br/>

#### 코드 : 

```c++
#include <iostream>
#include <vector>
#include <queue>

using namespace std;
int people, partynum, truenum, fakepartynum;
vector<bool> truevec, visit;
vector<vector<int>> adj, partyvec;

void bfs(int curidx) {
    queue<int> q;
    q.push(curidx);
    visit[curidx] = true;

    while (!q.empty()) {
        curidx = q.front();
        q.pop();

        int size = adj[curidx].size();
        for (int k = 0; k < size; k++) {
            int cmpidx = adj[curidx][k];
            if (visit[cmpidx]) continue;
            truevec[cmpidx] = true;
            visit[cmpidx] = true;
            q.push(cmpidx);
        }
    }
}

int main() {
    int input, nums;
    cin >> people >> partynum >> truenum;

    truevec.resize(people + 1);
    visit.resize(people + 1);
    adj.resize(people + 1);
    partyvec.resize(partynum);

    for (int i = 0; i < truenum; i++) {
        cin >> input;
        truevec[input] = true;
    }

    for (int i = 0; i < partynum; i++) {
        int compidx, nextidx;
        cin >> nums;
        if (nums--) {
            cin >> compidx;
            partyvec[i].push_back(compidx);
        }
        while (nums--) {
            cin >> nextidx;
            partyvec[i].push_back(nextidx);
            adj[compidx].push_back(nextidx);
            adj[nextidx].push_back(compidx);
        }
    }

    for (int i = 1; i <= people; i++)
        if (!visit[i] && truevec[i]) bfs(i);

    for (int i = 0; i < partynum; i++) {
        int j, size = partyvec[i].size();
        for (j = 0; j < size; j++)
            if (truevec[partyvec[i][j]]) break;

        if (j >= size) fakepartynum++;
    }

    cout << fakepartynum << '\n';
    return 0;
}
```

