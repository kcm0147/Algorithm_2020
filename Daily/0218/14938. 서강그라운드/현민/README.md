# 14938

## 조건

* 각 지점에는 아이템의 갯수가 주어진다.

* 예은이는 낙하한 지점에서 m이하의 거리내의 지점들을 탐색할 수 있다.

## 구해야 하는 것

* 예은이가 낙하하여 얻을 수 있는 아이템의 최대 갯수

## 아이디어

* 각 지점에서 갈 수 있는 곳을 구해야하니 각 지점에서 다른 지점으로의 거리를 구해야한다.

* 처음에 문제를 보고 한 지점에서 시작하니 다익스트라로 풀었으나

* 다른 multi source shortest path 알고리즘을 쓰는게 더 효율적이겠다.

* 각 거리를 구한 뒤 시작지점으로부터 m이하의 거리에 있는 지점들의 아이템들 갯수의 합을 구한다.

* 이 합을 최댓값으로 계속 갱신하여 준다.

## 느낀점

1. 문제를 보고 빨리 알고리즘을 파악할 수 있어 크게 어려운 문제는 아니었다.

2. mssp 문제도 한번 풀어봐야겠다.

## 코드
```java
package daily.day0218.boj14928;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.function.ToIntFunction;

public class Main {
    private static int[] distance;
    private static int[][] adj;
    private static int[] items;

    private static ToIntFunction<String> stoi = s -> Integer.parseInt(s);

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] line = reader.readLine().split(" ");
        int n = stoi.applyAsInt(line[0]);
        int m = stoi.applyAsInt(line[1]);
        int r = stoi.applyAsInt(line[2]);

        distance = new int[n+1];
        adj = new int[n+1][n+1];
        items = new int[n+1];

        line = reader.readLine().split(" ");
        for(int i=1; i<=n; i++)
            items[i] = stoi.applyAsInt(line[i-1]);

        for(int edge=0; edge<r; edge++){
            line = reader.readLine().split(" ");
            int u = stoi.applyAsInt(line[0]);
            int v = stoi.applyAsInt(line[1]);
            adj[u][v] = adj[v][u] = stoi.applyAsInt(line[2]);
        }

        int answer = 0;
        for(int start=1; start<=n; start++) {
            calcDistance(start);
            int sum=0;
            for(int i=1; i<=n; i++){
                if(distance[i] > m) continue;
                sum += items[i];
            }
            answer = Math.max(answer, sum);
        }

        System.out.println(answer);
    }

    private static void calcDistance(int start){
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[start] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>((n1,n2) -> Integer.compare(n1.distance, n2.distance));
        pq.add(new Node(start, 0));
        while(!pq.isEmpty()){
            int u = pq.poll().node;

            for(int v=0; v<adj.length; v++){
                if(adj[u][v] <= 0) continue;

                if(distance[v] > distance[u] + adj[u][v]){
                    distance[v] = distance[u] + adj[u][v];
                    pq.add(new Node(v, distance[v]));
                }
            }
        }
    }

    private static class Node{
        int node;
        int distance;
        public Node(int node, int distance) {
            this.node = node;
            this.distance = distance;
        }
    }
}
```