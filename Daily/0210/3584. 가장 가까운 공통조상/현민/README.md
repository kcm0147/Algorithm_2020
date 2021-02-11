# 3584 가장 가까운 공통 조상

## 조건

* 루트가 있는 트리

## 구해야 하는 것

* 두 정점이 주어질 때 가장 가까운 공통 조상을 찾아라

## 아이디어

* parent를 따라 root로 가면서 orders에 뒤에서부터 차례대로 parent를 추가한다.

* 위와 같은 과정을 두 정점 모두 해준다. 
    
    * orders 배열은 각각 따로 계산한다.
  
* orders는 가까운 parent 순서대로 저장되어 있으므로 
  
* 두 orders를 처음부터 순차적으로 비교하며 제일 가까운 공통 조상을 찾는다.
    
## 제한

```
이 문제에서 정점의 갯수는 N(최대 10,000)개이다.

orders를 구하는 과정은 O(N)이며,

두 orders에 있는 공통 조상을 찾는 과정은 O(N * N)이므로

이 알고리즘으로 충분히 해결할 수 있다. 
```

## 느낀 점

1. 오랜만에 느낀 자료구조 수업 때 느낌이었다.

2. 재밌는 문제였다.

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.parseInt(new StringTokenizer(reader.readLine()).nextToken());

        for(int t=0; t<testCase; t++){
            int size = Integer.parseInt(new StringTokenizer(reader.readLine()).nextToken());
            Solution solution = new Solution(size);

            for(int i=0; i<size-1; i++) {
                String[] line = reader.readLine().split(" ");
                solution.link(Integer.parseInt(line[0]), Integer.parseInt(line[1]));
            }

            String[] line = reader.readLine().split(" ");
            int u = Integer.parseInt(line[0]), v = Integer.parseInt(line[1]);
            System.out.println(solution.solution(u,v));
        }
    }
}

class Solution{
    int[] parents;

    Solution(int size) {
        parents = IntStream.iterate(0, i -> i+1).limit(size+1).toArray();
    }

    void link(int parent, int child){
        parents[child] = parent;
    }

    int solution(int u, int v){
        List<Integer> orders1 = new ArrayList<>();
        List<Integer> orders2 = new ArrayList<>();

        findRoot(u, orders1);
        findRoot(v, orders2);

        for(int p : orders1){
            for(int q : orders2){
                if(p == q) return p;
            }
        }

        return -1; // cannot reach
    }

    void findRoot(int node, List<Integer> orders){
        orders.add(node);
        if(parents[node] == node) return;
        findRoot(parents[node], orders);
    }
}
```
