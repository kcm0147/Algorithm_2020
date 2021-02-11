# 10282 해킹

## 조건

* yum3 해커가 하나의 컴퓨터 a를 해킹했다.

* a 컴퓨터에 의존하는 컴퓨터는 일정시간(time(a,b)) 후에 감염된다.

## 구해야 하는 것

* 이런 식으로 바이러스가 퍼져나갈 때 최대한 퍼져나가는데 걸리는 시간

## 아이디어

* 기본적으로 자기 주변부 부터 퍼져나가는 것이 꼭 bfs와 비슷하다.

* 입력의 크기는 다음과 같으므로 bfs를 충분히 활용할 수 있다.

```
1 <= n(컴퓨터의 갯수) <= 10,000
1 <= d(의존성의 갯수) <= 100,000
```

* 보통 bfs의 경우에는 한 점의 상태를 3가지로 나눈다.

```
1. 발견하지 않은 상태
2. 발견하였으나, 방문하지 않은 상태
3. 방문한 상태
```

* 이 문제의 경우 시간이라는 요소가 있으니 다음과 같이 상태를 나타낼 필요가 있다.

```
1. 발견하지 않은 상태
2. 발견하였으나, 방문하지 않았으며, 현재 방문할 수 있는 상태
3. 발견하였으나, 방문하지 않았으며, 현재 방문할 수 없는 상태
4. 방문한 상태
```

* 단 bfs를 고려해보았을 때, 만약 3번의 상태의 점이 발견되면, 
  큐에 다시 넣어야 하는데 bfs의 경우 단일 큐를 사용하므로 이는 매우 어려운 일이다.
  
* 그렇다면, 시간을 key로 하는 heap을 사용하면 이와 같은 문제를 해결할 수 있어 보인다.

```
heap을 통해 제일 빠른 시간의 점을 pop을 하면 
2-3번에 해당하는 점을 자동적으로 시간순서대로 pop 할 수 있다.

만약 이미 v가 heap에 있으나, v로 갈 수 있는 더 짧은 경로가 있다면 이를 heap에 넣어줄 수 있다.
```

* bfs와 비슷한 틀을 가진 알고리즘들이 많으나 이 형태는 전형적인 다익스트라 알고리즘이다.
  다익스트라 알고리즘으로 풀 수 있는지 살펴보자
  
```
다익스트라는 single shortest path problem으로, 
a지점에서 갈 수 있는 모든 점들의 최단경로를 구할 수 있다.
```

* 최단경로를 통해 우리가 이 문제를 해결할 수 있는지 보자
```
우리가 구해야하는 것은 마지막으로 감염되었을 때 걸리는 시간이다.
이는 바꾸어 생각하면 제일 긴 경로를 찾는 것과 마찬가지의 의미이다.

또한 몇 대의 컴퓨터가 감염되었는지 또한 쉽게 구할 수 있는데, 
distance 배열의 초기값을 MAX_INT로 두면 만약 방문할 수 없다면 그래로이기 때문에
-1이 아닌 것들을 수를 카운트하면 된다.
```

## 느낀 점

1. 정말 좋은 문제였다. 단순 bfs문제처럼 보일 수 있으나 다익스트라 문제로 오랜만에 해보니 새록새록 좋았다.

## 코드

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            int testCase = Integer.parseInt(new StringTokenizer(reader.readLine()).nextToken());
            for(int t=0; t<testCase; t++){
                StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
                int sizeComputer = Integer.parseInt(tokenizer.nextToken());
                int sizeEdges = Integer.parseInt(tokenizer.nextToken());
                int start = Integer.parseInt(tokenizer.nextToken());

                int[][] edges = new int[sizeEdges][3];
                for(int i=0; i<sizeEdges; i++){
                    tokenizer = new StringTokenizer(reader.readLine());
                    for(int j=0; j<3; j++){
                        edges[i][j] = Integer.parseInt(tokenizer.nextToken());
                    }
                }

                Solution.Node reuslt = new Solution().solution(sizeComputer, start, edges);
                System.out.println(reuslt.num + " " + reuslt.dist);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

class Solution{
    private List<List<Node>> getAdjList(int sizeComputer, int[][] edges){
        List<List<Node>> ret = new ArrayList<>();
        IntStream.range(0, sizeComputer+1).forEach(i -> ret.add(new ArrayList<>()));
        for(int[] edge : edges)
            ret.get(edge[1]).add(new Node(edge[0], edge[2]));
        return ret;
    }

    Node solution(int sizeComputer, int start, int[][] edges){
        int[] distance = IntStream.iterate(Integer.MAX_VALUE, i -> i == start ? 0 : Integer.MAX_VALUE)
                            .limit(sizeComputer+1).toArray();
        List<List<Node>> adjList = getAdjList(sizeComputer, edges);

        PriorityQueue<Node> pq = new PriorityQueue<>((u, v) -> Integer.compare(u.dist, v.dist));
        Node node = new Node(start, 0);
        pq.add(new Node(start, 0));

        while(!pq.isEmpty()){
            Node u = pq.poll();
            for(Node v : adjList.get(u.num)){
                if(distance[v.num] > distance[u.num] + v.dist){
                    distance[v.num] = distance[u.num] + v.dist;
                    pq.add(new Node(v.num, distance[v.num]));
                }
            }
        }

        return new Node((int)Arrays.stream(distance).filter(dist -> dist < Integer.MAX_VALUE).count()
                ,Arrays.stream(distance).filter(dist -> dist < Integer.MAX_VALUE).max().getAsInt());
    }

    static class Node{
        int num;
        int dist;

        Node(int v, int d) {
            this.num = v;
            this.dist = d;
        }
    }
}
```
