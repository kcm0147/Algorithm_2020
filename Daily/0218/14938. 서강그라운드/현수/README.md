# 문제
- [14938. 서강그라운드](https://www.acmicpc.net/problem/14938)

## 코드

<details><summary> 플로이드-와샬 풀이 </summary>

![image](https://user-images.githubusercontent.com/51476083/108365802-f6d8b900-723a-11eb-889e-c5e454b6d329.png)


``` java
import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Q14938 {
    static int n, m, r, itemCount[];
    static int adj[][];

    public static void main(String[] args) throws IOException {
        init();
        solution();
    }

    private static void solution() {
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    if(j == k) continue;
                    adj[j][k] = Math.min(adj[j][k], adj[j][i] + adj[i][k]);
                }
            }
        }
        for (int i = 0; i < n; i++) {
            int temp = itemCount[i];
            for (int j = 0; j < n; j++) {
                if(i == j) continue;
                if(adj[i][j] <= m)
                    temp += itemCount[j];
            }
            ans = Math.max(ans, temp);
        }
        System.out.println(ans);
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = stoi(st.nextToken());
        m = stoi(st.nextToken());
        r = stoi(st.nextToken());
        adj = new int[n][n];
        itemCount = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            itemCount[i] = stoi(st.nextToken());
            Arrays.fill(adj[i], 987654321);
            adj[i][i] = 0;
        }

        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());
            int u = stoi(st.nextToken()) - 1;
            int v = stoi(st.nextToken()) - 1;
            int len = stoi(st.nextToken());
            adj[u][v] = adj[v][u] = len;
        }
    }

    static int stoi(String st) {
        return Integer.parseInt(st);
    }
}

```
</details>

<br/>

<details><summary> BFS 풀이 </summary>

![image](https://user-images.githubusercontent.com/51476083/108365710-ddd00800-723a-11eb-979d-00454cb05c54.png)

```java
import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n, m, r, itemCount[];
    static List<Point> adj[];
    static boolean visited[];
    public static void main(String[] args) throws IOException {
        init();
        solution();
    }

    static void solution() {
        int ans = 0;
        for (int i = 0; i < n; i++) {
            visited[i] = true;
            ans = Math.max(ans, bfs(i));
            Arrays.fill(visited, false);
        }
        System.out.println(ans);
    }

    static int bfs(int start) {
        int ret = itemCount[start];
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(start, 0));

        while(!q.isEmpty()){
            Point edge = q.poll();
            int here = edge.x, cost = edge.y;
            for (int i = 0; i < adj[here].size(); i++) {
                Point p = adj[here].get(i);
                int next = p.x, nextCost = p.y;
                if(cost + nextCost <= m){
                    q.add(new Point(next, cost + nextCost));
                    if(!visited[next])
                        ret += itemCount[next];
                    visited[next] = true;
                }
            }
        }
        return ret;
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = stoi(st.nextToken());
        m = stoi(st.nextToken());
        r = stoi(st.nextToken());
        adj = new List[n];
        itemCount = new int[n];
        visited = new boolean[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            itemCount[i] = stoi(st.nextToken());
            adj[i] = new ArrayList<>();
        }
        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());
            int u = stoi(st.nextToken()) - 1;
            int v = stoi(st.nextToken()) - 1;
            int len = stoi(st.nextToken());
            adj[u].add(new Point(v, len));
            adj[v].add(new Point(u, len));
        }
    }

    static int stoi(String st) {
        return Integer.parseInt(st);
    }
}
```

</details>

## ⭐️느낀점⭐️
> 앞선 두 문제보다 간단해서 다행이었다. 단순한 그래프 + 최단경로 사용하는 문제여서 수월했다. 

## 풀이 📣
<hr/>

1️⃣ 인접 리스트(BFS풀이) / 인접 행렬(플로이드-와샬 풀이) 을 저장해서 노드 간 간선의 정보를 저장한다.

    - 양방향 간선이므로 양쪽 노드에서 간선을 추가해준다.

    - 노드의 개수가 100 이하여서 O(N^3) 인 플로이드-와샬 최단경로도 시간적으로 충분하다.


2️⃣ BFS 풀이 - 방문했던 노드에 대해서 더 짧은 경로로 올 수 있으므로 첫 방문시에만 아이템의 개수를 더해주고 재 방문시에는 더 짧은 경로일 경우만 큐에 다시 넣어준다. 

    - 일반적인 방법으로 재방문 시에 재탐색을 하지 않으면, 이미 방문했던 노드의 경로가 단축될 경우 더 많은 노드를 탐색할 기회를 배제하게 된다


3️⃣ 플로이드-와샬 풀이 - 각 노드에서 부터 모든 노드까지의 최단 경로를 미리 구해놓는다.

    - 특정 노드를 경유해서 도달할 수 있는 모든 노드 간에 최단 경로를 구한다.

    - 각 노드에서 다른 모든 노드까지 m 이하의 경로로 도달할 수 있다면 해당 노드의 아이템의 개수를 더해준다.


4️⃣ 최종적으로 구한 최대 아이템의 개수를 출력한다.


## 실수 😅

- BFS 풀이 방식에서 단순히 재방문을 배제하고 제출했다가 틀려서 생각해보니, 이미 방문했던 노드까지 더 짧은 경로가 존재해 다른 노드로 더 이동할 수도 있음을 알아차렸다. 