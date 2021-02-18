# 2157 여행

## 조건

1. 도시는 동쪽`1`부터 서쪽`N`까지 순서대로 나열되어 있다.

2. 이 도시에서 1번 도시에서 N번 도시까지 여행하려 한다.

3. 단, 1번 도시와 N번 도시를 포함해 총 M개 이하의 도시만 방문해야 한다.

4. 또한 도시의 숫자가 증가하는 방향으로만 갈 수 있다.

5. 도시에서 도시로 이동하는 비행 항로에는 기내식이 있으며, 기내식 점수가 있다.

## 구해야 하는 것

* M개 이하의 도시를 지나 1부터 N까지 여행한다 할 때, 먹은 기내식들의 합의 최대

## 아이디어

* 일단 여기서보면 아래와 같은 입력 조건들이 있다.
    - `1<=N<=300`
    - `2<=M<=N` 
    - `1<=K<=100,000`  k는 비행 항공로 수
    
* 여기서 그래프 문제로 봐보자.

* k는 edge로 볼 수 있으며 weight는 그 비행 항공로의 기내식의 점수라 볼 수 있다.

* 모든 경우를 탐색하는 dfs의 경우 `O(V+E)`이므로 충분히 가능하다고 볼 수 있으나,

* 이 경우는 인접리스트로 그래프를 표현했을 때이며, 만약 인접행렬이어도 할 수 없는데, 똑같은 도시 쌍이 경로로 있을 수 있기 때문이다.

* 이 문제에서는 도시의 숫자가 증가하는 방식으로 탐색해야하므로, graph를 표현하는 자료구조에 order가 들어가 있어야 한다.

* 허나 인접리스트의 경우 불가능하고, 만약 고쳐준다고 해도 이는 인접리스트를 만들 때 최약의 경우 `100,000 * 100,000`만큼 걸린다.

* 인접리스트를 map으로 표현하는 방법도 있겠으나 더 쉬운 방법을 생각해보자.

* 바로 여기서 중요한 점이 최적 부분 구조가 성립한다는 것이다.

  * 즉 이전의 결과가 현재 결과에 영향을 안 미친다는 것이다.
  
* 또한 a도시에서 c도시로 가는 것과 b도시에서 c도시로 가는 이런 경우가 있을 수 있다.

  * 즉 부분문제를 재활용한다고 볼 수 있다.
  
* 따라서 아래와 같이 부분문제를 정의해보자

  * `travel(i)` : i번 도시에서 여행하여 N까지 도착하는 경우 중 최대 점수

* 단 이 같은 경우는 우리가 원하는 M번 이하의 도시를 제한하지 못하므로 아래와 같이 정의하자

  * `travel(i,rest)` : i번 도시에서 rest만큼의 도시를 더 갈 수 있을 때, 최대 점수
  
* 부분문제를 재활용한다고 볼 수 있으니 위 점화식을 메모이제이션하여 재활용하자.

## 코드

```java
package daily.day0216.boj2157;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    private static int[][] cache;
    private static List<List<Node>> adjList;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        int size = stoi(tokenizer.nextToken());
        int rest = stoi(tokenizer.nextToken());
        int edgeSize = stoi(tokenizer.nextToken());

        cache = new int[size+1][size+1];
        for(int[] c : cache) Arrays.fill(c, -1);
        adjList = new ArrayList<>();
        for(int i=0; i<=size; i++) adjList.add(new ArrayList<>());

        for(int edge=0; edge<edgeSize; edge++){
            tokenizer = new StringTokenizer(reader.readLine());
            int start = stoi(tokenizer.nextToken());
            int end = stoi(tokenizer.nextToken());
            int weight = stoi(tokenizer.nextToken());
            adjList.get(start).add(new Node(end, weight));
        }

        System.out.println(solve(1, rest-1));
    }

    private static int solve(int city, int rest){
        if(rest < 0) return Integer.MIN_VALUE;
        if(city == adjList.size()-1) return 0;

        if(cache[city][rest] != -1) return cache[city][rest];

        int ret = Integer.MIN_VALUE;
        for(Node node : adjList.get(city)) {
            if (city >= node.city) continue; // 오름차순이 아님.
            ret = Math.max(ret, node.weight + solve(node.city, rest-1));
        }

        cache[city][rest] = ret;
        return ret;
    }

    private static class Node{
        int city;
        int weight;

        public Node(int city, int weight) {
            this.city = city;
            this.weight = weight;
        }
    }

    private static int stoi(String s){
        return Integer.parseInt(s);
    }
}
```
