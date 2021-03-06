# 문제
- [19542. 전단지 돌리기](https://www.acmicpc.net/problem/19542)

## 코드

<details><summary> 코드 보기 </summary>

``` java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Q19542 {
    static int n, s, d;
    static List<Integer> adj[] = new List[100001];
    static int depth[] = new int[100001];
    static boolean visited[] = new boolean[100001];
    public static void main(String[] args) throws IOException {
        init();
        solution();
    }

    private static void solution() {
        findDepth(s);
        int ans = calc(s);
        System.out.println(ans);
    }

    private static int calc(int root) {
        if(depth[root] <= d) return 0;
        int ret = 0;
        visited[root] = true;
        for (int i = 0; i < adj[root].size(); i++) {
            int next = adj[root].get(i);
            if(visited[next] || depth[next] < d) continue;
            ret += 2 + calc(next);
        }
        return ret;
    }

    private static int findDepth(int root) {
        depth[root] = 0;
        for (int i = 0; i < adj[root].size(); i++) {
            int next = adj[root].get(i);
            if(depth[next] != -1) continue;
            depth[root] = Math.max(depth[root], 1 + findDepth(next));
        }
        return depth[root];
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = stoi(st.nextToken());
        s = stoi(st.nextToken());
        d = stoi(st.nextToken());

        Arrays.fill(depth, -1);

        for (int i = 0; i <= n; i++)
            adj[i] = new ArrayList<>();

        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int u = stoi(st.nextToken());
            int v = stoi(st.nextToken());
            adj[u].add(v);
            adj[v].add(u);
        }
    }

    private static int stoi(String str) {
        return Integer.parseInt(str);
    }
}
```

</details>

## ⭐️느낀점⭐️
> 내가 너무 어렵게 생각해서 풀려고 하는 것 같다. 기본적인 접근 방법부터 떠올리려고 해보자!!

## 풀이 📣

1️⃣ 트리의 각 노드별로 깊이를 모두 계산해 둔다.

    - 현재 노드가 가지는 자손의 레벨 중 가장 높은 레벨을 그 노드의 깊이로 설정한다.


2️⃣️ DFS로 탐색하면서 현재 노드의 깊이가 d 이하이면 더 이상 탐색할 필요가 없으므로 0을 반환한다.

    - visited 배열을 통해 이미 방문한 노드는 재방문하지 않는다.

3️⃣ 현재 노드의 깊이가 d보다 크다면, 각 자손들 별로 탐색을 시행한다.

    - 자손 노드 중 깊이가 d 이하인 노드는 탐색을 하지 않는다.

    - 그 노드까지 갔다가 돌아와야하므로 비용은 +2 가 소요된다.

    - 자손노드에서 다시 3번 과정을 재귀적으로 반복한다. 


4️⃣ 루트 노드에서부터 모든 노드를 탐색하는데 드는 비용을 모두 더하여 출력한다.

<hr/>

## 실수 😅
- 처음에는 클래스를 만들어서 깊이도 보관하면서 해야하나 하는 고민을 했다가, 깊이를 나타내는 일차원 배열로 분리시키고 단순 DFS 로 구현하였다.

- 너무 어렵게 생각하지 말고 단순한 방법부터 접근하는 방식을 몸에 익혀야겠다 