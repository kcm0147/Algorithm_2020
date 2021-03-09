# 19542 전단지 돌리기

## 문제 해석

1. 트리 모양의 길이 주어져있다.

2. 케니소프트라는 시작지점에서 시작하여 원래대로 돌아와야한다.

3. 길이 D만큼 전단지를 던질 수 있다.

4. 이어져있는 지점간의 길이는 1이다.

## 풀어야하는 것

* 모든 지점에 전단지를 전달 할 수 있는, 최소 이동거리를 구하시오.

## 아이디어

1. 트리 형태이므로, 어느 지점을 루트로 잡아도 트리형태가 된다.

2. 한 지점에서 제일 긴 자식 leaf 노드간의 거리를 height라고 하자.

3. 만약 height가 D보다 작다면 그 지점은 더 이상 내려갈 필요가 없다.

4. 모든 지점의 height를 dfs를 통해 구한다.

5. 케니소프트로부터 시작하여 dfs 탐색을 실행한다.

6. 만약 방문할 지점의 height가 D보다 작다면 방문하지 않는다.

7. 방문한다면 1만큼 거리를 더한다.

8. 최종적으로 나온 거리의 2배를 해야 왕복 거리가 나온다.

## 코드

```java
package daily.day0227.boj19542;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    private static BufferedReader br;
    private static StringTokenizer st;

    private static List<List<Integer>> adjList;
    private static int[] height;

    public static void main(String[] args) throws IOException {
        int n = nextInt(), start = nextInt(), power = nextInt();

        adjList = new ArrayList<>();
        for(int i=0; i<=n; i++) adjList.add(new ArrayList<>());

        for(int i=0; i<n-1; i++){
            int u = nextInt(), v = nextInt();
            adjList.get(u).add(v);
            adjList.get(v).add(u);
        }

        boolean[] visited = new boolean[n+1];
        Arrays.fill(visited,false);

        height = new int[n+1];
        calcHeight(start, visited);

        Arrays.fill(visited, false);
        System.out.println(2 * dfs(start, power, visited));
    }

    private static int calcHeight(int u, boolean[] visited){
        visited[u] = true;

        int ret=0;
        for(int v : adjList.get(u)){
            if (visited[v]) continue;
            int dist = 1 + calcHeight(v, visited);
            ret = Math.max(ret, dist);
        }
        return height[u] = ret;
    }

    private static int dfs(int u, final int power, boolean[] visited){
        visited[u] = true;

        int ret=0;
        for(int v : adjList.get(u)){
            if (visited[v] || height[v] < power) continue;
            ret += 1 + dfs(v, power, visited);
        }

        return ret;
    }

    private static int nextInt() throws IOException { return Integer.parseInt(nextToken()); }

    private static String nextToken() throws IOException {
        if(br == null) br = new BufferedReader(new InputStreamReader(System.in));
        if(st == null || !st.hasMoreTokens())
            st = new StringTokenizer(br.readLine());
        return st.nextToken();
    }
}
```