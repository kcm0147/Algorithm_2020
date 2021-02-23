# 1043

## 조건

1. 지민이는 파티에서 거짓말 혹은 진실을 이야기할 수 있다.

2. 하지만 진실을 아는 자가 참여하는 파티에서는 진실만을 이야기해야한다.

3. 또한 어떤 사람이 파티에서 들었던 내용(거짓,진실)과 다른 파티에서 들은 내용이 달라도 안된다.

## 구해야하는 것

1. 위 조건을 지키면서 최대한 많이 거짓말 할 수 있는 파티의 수

## 아이디어

1. 이 문제에서는 2,3번 조건을 유의해서 보자.

2. 3번부터 보자, 어떤 사람이 어떤 어떤 파티에 참여했는지 알아야하며 그 파티들에서는 항상 일관성있게 대답해야한다.

3. 이를 위해 disjoint-set을 이용하여 파티들을 그룹지을 수 있겠다.

4. 만약 일관성을 지켜야 하는 파티들이라면 모두 공통 부모(파티)를 가지게 될 것이다.

5. 그 다음 2번 조건을 이용해보자! 2번 조건의 경우 진실을 아는 자가 참여하는 파티에서 진실만을 이야기해야 한다.

6. 따라서 그 파티와 일관성을 지켜야하는 파티들 모두 진실만을 이야기 해야한다.

7. 이를 제외한 나머지 파티들에서는 거짓말을 할 수 있을 것이다.

## 느낀점

1. 어떻게 풀어야하지 하다가 힌트로 disjoint set인걸 알고 푼 문제이다.. 굉장히 아쉽다. 

## 코드

```java
package daily.day0223.boj1043;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

public class Main {
    private static int[] parent;
    private static int[] rank;
    private static int[] size;

    private static BufferedReader br;
    private static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));

        int N = nextInt(), M = nextInt();

        int numKnowTruth = nextInt();
        Set<Integer> knowTruth = new HashSet<>();
        for(int i=0; i<numKnowTruth; i++) knowTruth.add(nextInt());

        int[] visited = IntStream.iterate(-1, i->-1).limit(N+1).toArray();

        parent = IntStream.iterate(0, i->i+1).limit(M).toArray();
        rank = IntStream.iterate(0, i->0).limit(M).toArray();
        size = IntStream.iterate(1, i->1).limit(M).toArray();
        int[] onlyTrue = IntStream.iterate(0, i->0).limit(M).toArray();

        for(int p=0; p<M; p++){
            int num = nextInt();
            for(int i=0; i<num; i++){
                int person = nextInt();
                if(knowTruth.contains(person)) onlyTrue[p] = 1;
                if(visited[person] != -1){
                    union(p, visited[person]);
                }
                visited[person] = p;
            }
        }

        for(int p=0; p<M; p++){
            if(onlyTrue[p] == 1) onlyTrue[find(p)] = 1;
        }

        Set<Integer> separated = new HashSet<>();
        for(int p=0; p<M; p++){
            int root = find(p);
            if(onlyTrue[root] == 1) continue;
            separated.add(root);
        }

        int answer=0;
        for(int p : separated){
            answer += size[p];
        }
        System.out.println(answer);
    }

    private static int find(int u){
        if(parent[u] == u) return u;
        return parent[u] = find(parent[u]);
    }

    private static void union(int u, int v){
        u = find(u); v = find(v);
        if(u == v) return;

        if(rank[u] < rank[v]){
            int temp = u;
            u = v;
            v = temp;
        }

        parent[v] = u;
        if(rank[u] == rank[v]) rank[u]++;
        size[u] += size[v];
        size[v] = 0;
    }

    private static int nextInt() throws IOException { return Integer.parseInt(nextToken()); }

    private static String nextToken() throws IOException {
        if (st == null || !st.hasMoreTokens())
                st = new StringTokenizer(br.readLine());
        return st.nextToken();
    }
}
```