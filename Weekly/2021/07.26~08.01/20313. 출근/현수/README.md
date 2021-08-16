# 문제
- [20313. 출퇴근](https://www.acmicpc.net/problem/20313)

## 코드

<details><summary> 코드 보기 </summary>

``` java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Q20313 {
    static int n, m, a, b, k, useMagic[][];
    static long dist[][];
    static List<Node> adj[];
    public static void main(String[] args) throws IOException {
        init();
        System.out.println(solution());
    }

    private static long solution() {
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingLong(Node::getDist));
        pq.add(new Node(a, 0, 0, 0));
        dist[a][0] = 0;
        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            int v = cur.v, magic = cur.magic, o = cur.order;
            long d = cur.dist;
            if(v == b) return d;
            if(dist[v][magic] < d) continue;

            for (Node node : adj[v]) {
                for (int mg = magic; mg <= k; mg++) {
                    if(dist[node.v][mg] > d + useMagic[mg][node.order]){
                        dist[node.v][mg] = d + useMagic[mg][node.order];
                        pq.add(new Node(node.v, d + useMagic[mg][node.order], mg, o));
                    }
                }
            }
        }
        return -1;
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = stoi(st.nextToken());
        m = stoi(st.nextToken());
        a = stoi(st.nextToken());
        b = stoi(st.nextToken());
        adj = new List[n+1];
        for (int i = 0; i < n + 1; i++) {
            adj[i] = new ArrayList<>();
        }
        useMagic = new int[101][2001];
        dist = new long[1001][101];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int u = stoi(st.nextToken()), v = stoi(st.nextToken());
            int c = stoi(st.nextToken());
            adj[u].add(new Node(v, c, 0, i));
            adj[v].add(new Node(u, c, 0, i));
            useMagic[0][i] = c; // 마법을 쓰지 않은 상태의 각 간선의 비용
        }
        k = stoi(br.readLine());
        for (long[] d : dist) {
            Arrays.fill(d, Long.MAX_VALUE);
        }
        // 마법을 i 번 썼을 때의 각 간선의 비용 입력
        for (int i = 1; i <= k; i++) {
            useMagic[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
    }

    private static int stoi(String str) {
        return Integer.parseInt(str);
    }

    static class Node{
        int v, magic, order;
        long dist;

        public Node(int v, long dist, int magic, int order) {
            this.v = v;
            this.dist = dist;
            this.magic = magic;
            this.order = order;
        }

        public long getDist(){
            return this.dist;
        }
    }
}

```
</details>

## ⭐️느낀점⭐️
> 다익스트라 문제를 변형하여 조금 더 어려워진 문제였다.
> 
> 좌표 정보에 추가적인 `마법 수` 라는 정보를 포함시키는 색다른 문제였다.

## 풀이 📣
<hr/>

1️⃣ 각 간선을 입력받을 때 마다 인접 리스트를 만들어주고, 해당 간선의 순서에 따라 번호를 부여한다.

    - 처음으로 입력받는 간선의 번호는 0, 그다음은 1, ...

    - 인접 리스트에 저장되는 객체는 Node

    - Node :: 다음 정점 v, 간선의 비용 dist, 마법을 사용한 횟수 magic, 간선의 번호 order 


2️⃣ 마법을 사용했을 때와 사용하지 않았을 때의 경로의 비용을 2차원 배열 `useMagic` 에 저장시켜 둔다.

    - useMagic[마법 사용 횟수][간선의 번호]


3️⃣ 사용 가능한 마법 횟수를 고려하여 다익스트라를 모두 돌려본다.

    - 특정 정점에서 다음 정점으로 이동할 때, 남은 사용 가능한 마법의 수를 고려하여 이동할 수 있다.

    - 즉, 마법을 사용하지 않고 이동하였을 때의 정보를 우선순위 큐에 넣을 수 있고, 마법을 K번까지 사용해서 이동 전 이동 가능한 간선의 비용을 바꿀 수 있다.

    - 이때, 마법을 사용한다면 현재 이동 가능한 간선의 정보만 영향을 받을 수 있도록 for 문을 구성한다.

    - 만약 마법을 사용한다면, 현재까지 마법을 사용했던 정보 Node :: magic 에 체크를 하고 우선순위 큐에 넣어준다.

    - 이렇게 되면 최대 K번 (K <= 100) 다익스트라를 수행하게 된다.

## 실수 😅
<hr/>

- 고민을 해봤었지만 노드 정보에 추가적인 정보를 넣을 생각을 못했다. 

- 덕분에 그래프 문제에서 새로운 접근법을 배울 수 있었다.