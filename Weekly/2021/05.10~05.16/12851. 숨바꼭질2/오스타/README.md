# 12851. 숨바꼭질2

###### 작성자 : O-star

<br/>

<br/>

### 풀이 : 

**음... 우선 사실 굉장히 쉬운 문제라고 생각하고 풀었는데 중간에 몇가지 착오사항이 많아서 시간이 꽤나 소비한 문제였다.**

**최근 코테를 직접 쳐보면서 가능한 정확하게 로직을 설계해두고 짜야된다는 생각이 들었기에 코딩을 시작하기 전에 에러가 생길만한 부분들을 모두 최대한 설계해두고 시작하는 버릇을 들여야 겠다.**

**본 문제는 수빈이가 술래이고 수빈의 동생은 숨어있는 역할이기 때문에 동생의 위치(sisteridx)는 항상 고정이다. 그리고 수빈의 위치 이동시에도 완전 탐색으로 풀게되면 중복 탐색이 굉장히 다발적으로 일어나기 때문에 중복적인 탐색과 가능성 없는 위치 탐색은 최대한 줄여야 한다.**

#### 중복 탐색 인덱스와 가능성 없는 인덱스인 경우

- **수빈의 위치 인덱스가 동생의 위치 인덱스보다 큰 경우 => 수빈 인덱스에서 +1 혹은 *2의 위치를 탐색할 필요가 없다 해당 위치에서 -1 해가는 횟수가 무조건 더 적은 시간이 소요된다.**
- **수빈의 위치가 0 미만이거나 100000 초과인 경우 => 범위 초과로 더 이상 탐색할 필요가 없다.**
- **탐색한 위치인데 전에 탐색한 시간보다 더 많은 시간을 소요하고 위치에 도달했을 경우 => 이미 탐색 큐에 들어있는 위치인데 해당 위치를 전에 방문한 시간보다 더 많은 시간을 소요한 경우에는 무조건 더 많은 시간을 소요해서 동생을 찾아가기 때문에 더 이상 탐색할 필요 없다. 단, 주의할 점은 같은 시간을 소요하고 중복적인 위치에 도달한 경우는 경우의 수를 탐색해주어야 한다. 동일한 시간을 소요하고 동생을 찾는 경우의 수가 존재할 수 있기 때문**

**이처럼 완전 탐색에서 효과적으로 필요없는 경우의 수를 가지치기한다면 문제를 해결할 수 있다.**

<br/>

<br/>

#### 코드 : 

```c++
#include <iostream>
#include <queue>
#include <utility>

#define pii pair<int, int>

using namespace std;
int visited[100001];

int main() {
    int subinidx, sisteridx, mintime = 1000000, mincnt = 0;
    queue<pii > q;

    fill_n(visited, 100001, 1000000);
    cin >> subinidx >> sisteridx;
    q.push(pii(subinidx, 0));

    while (!q.empty()) {
        int curidx = q.front().first, curtime = q.front().second, cmpidx;
        q.pop();

        if (mintime >= curtime && curidx == sisteridx) {
            mintime = curtime;
            mincnt++;
            continue;
        }

        if (visited[curidx] < curtime) continue;
        visited[curidx] = curtime;

        cmpidx = curidx - 1;
        if (cmpidx >= 0) q.push(pii(cmpidx, curtime + 1));
        cmpidx = curidx + 1;
        if (cmpidx <= sisteridx) q.push(pii(cmpidx, curtime + 1));
        cmpidx = curidx * 2;
        if (curidx <= sisteridx && cmpidx <= 100000) q.push(pii(cmpidx, curtime + 1));
    }

    cout << mintime << '\n' << mincnt << '\n';
    return 0;
}
```

