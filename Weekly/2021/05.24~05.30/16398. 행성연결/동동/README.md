<h1> 16398. 행성 연결 </h1>

#### [분류]
- 최소 스패닝 트리
<br><br>

#### [문제링크]
- https://www.acmicpc.net/problem/16398
<br><br>


#### [요구사항]
- 모든 행성을 연결했을 때, 최소 플로우의 관리비용을 출력한다.<br><br> 

#### [풀이]

1. 전형적인 최소 스패닝 트리 문제이다.<br><br>

2. 행렬을 edge로 모두 만들고 cost에 따른 오름차순 정렬을 한다.<br><br>

3. cost가 작은 edge부터 하나씩 꺼내서 그래프를 만들어가면서 사이클이 존재하면 넣지 않는다.<br><br>

4. 그래프가 완성되면 전체 cost의 합을 출력한다.<br><br>


#### [코드]
```java
//baekjoon_16398_행성연결

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main{
    static int[] parent;
    static ArrayList<Edge> edgeList = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());

        parent = new int[n+1];
        for(int i=1;i<=n;i++) parent[i]=i;
        for(int i=0; i<n;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<n;j++){
                long cost = Long.parseLong(st.nextToken());
                if(cost==0) continue;
                edgeList.add(new Edge(i+1, j+1, cost));
            }
        }

        Collections.sort(edgeList);

        long sum = 0;
        for(int i=0;i<edgeList.size();i++){
            Edge edge = edgeList.get(i);

            if(!isSameParent(edge.v1, edge.v2)){
                sum+=edge.cost;
                union(edge.v1, edge.v2);
            }
        }

        System.out.println(sum);
    }

    private static void union(int x, int y){
        x = find(x);
        y = find(y);

        if(x!=y) parent[y] = x;
    }

    private static int find(int x){
        if(x==parent[x]) return x;
        return parent[x] = find(parent[x]);
    }

    private static boolean isSameParent(int x, int y){
        x = find(x);
        y = find(y);

        if(x==y) return true;
        return false;
    }
}

class Edge implements Comparable<Edge>{
    int v1;
    int v2;
    long cost;

    Edge(int v1, int v2, long cost){
        this.v1 = v1;
        this.v2 = v2;
        this.cost = cost;
    }

    @Override
    public int compareTo(Edge o) {
        if(this.cost>o.cost) return 1;
        else if(this.cost==o.cost) return 0;
        else return -1;
    }
}
```
<br><br>

#### [통과여부]
![image](https://user-images.githubusercontent.com/54053016/119688136-aea4e480-be82-11eb-8e98-410556d1f0f4.png)
<br><br>

#### [느낀점]
없다.