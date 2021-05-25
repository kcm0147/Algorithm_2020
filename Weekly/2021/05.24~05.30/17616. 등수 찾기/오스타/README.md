# 17616. 등수 찾기

###### 작성자 : O-star

<br/>

<br/>

### 풀이 : 

순위가 둘 중 어느 학생이 높은지에 관한 정보가 담긴 쌍이 여러 개 주어지는 것을 보고 위상정렬로 접근해야할 것이라고 예상했다.

하지만 해당 학생이 최소 몇 등부터, 최대 몇 등까지 가능한지 범위를 구해줘야 했기 때문에 단순한 위상정렬 알고리즘으로는 해결할 수 없을 것이라 판단했다. 그래서 위상정렬을 방향을 역으로 해서 한 번 더 실행해 총 두번의 위상정렬 탐색(bfs)으로 해답을 구할 수 있는 방법을 구상했다.

<br/>

세부 구현사항 : 

1. 방향성을 가지는 topological sort adj 배열을 두개 준비한다. loweradj의 경우 자신보다 낮은 순위로 방향을 가지는 인접행렬을 구성하고, higheradj의 경우 자신보다 높은 순위로 방향을 가지는 인접행렬을 구성한다.
2. 해당 학생의 최대 순위를 저장하는 highestrank, 최소 순위를 저장하는 lowestrank 변수를 준비해 각각 1, n 으로 초기화한다.
3. 해당 학생보다 낮은 순위를 가지는 학생의 수를 loweradj 인접 벡터의 위상정렬 탐색을 통해 구한 후 lowestrank의 값에 빼준다.
4. 해당 학생보다 높은 순위를 가지는 학생의 수는 higheradj 인접 벡터의 위상정렬 탐색을 통해 구한 후 highestrank의 값에 더해준다.

다음의 방식으로 위상정렬 탐색(bfs) 두 번 으로 순위 범위의 해답을 얻을 수 있다.

<br/>

<br/>

### 코드 : 

```c++
#include <iostream>
#include <vector>
#include <queue>

using namespace std;
vector<vector<int>> loweradj, higheradj;
int n, orgstu;

int bfs(bool isloweradj) {
    vector<vector<int>> adj;
    queue<int> q;
    bool *used = new bool[n + 1]{false};
    int count = 0;

    q.push(orgstu);
    used[orgstu] = true;
    adj = (isloweradj) ? loweradj : higheradj;

    while (!q.empty()) {
        int curidx = q.front();
        q.pop();

        int size = adj[curidx].size();
        for (int k = 0; k < size; k++) {
            int cmpidx = adj[curidx][k];
            if (used[cmpidx]) continue;
            q.push(cmpidx);
            used[cmpidx] = true;
            count++;
        }
    }

    return count;
}

int main() {
    int m, fir, sec;

    cin >> n >> m >> orgstu;
    int highestrank = 1, lowestrank = n;

    loweradj.resize(n + 1);
    higheradj.resize(n + 1);

    while (m--) {
        cin >> fir >> sec;
        loweradj[fir].push_back(sec);
        higheradj[sec].push_back(fir);
    }

    lowestrank -= bfs(true);
    highestrank += bfs(false);

    cout << highestrank << ' ' << lowestrank << '\n';

    return 0;
}
```

