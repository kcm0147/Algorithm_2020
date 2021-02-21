# 1613 역사

## 조건

* 사건의 전후관계는 다음과 같이 나타낸다.

    `a b` : a가 b보다 먼저 일어남.
  
* 이런 사건의 전후관계가 여러개 주어진다.

## 풀어야하는 것

* 주어진 전후관계들을 이용하여, 다른 사건 2개가 주어질 때 전후관계를 판단하시오.

## 아이디어

1. 먼저 사건의 전후관계를 떠올리고 위상정렬을 떠올렸다.

2. 하지만 위상정렬의 경우 전후관계가 없는 사건들에 대해 판별할 수 없으므로 불가능하다.

3. 만약 solve(a,b)가 사건의 전후관계를 판단하는 기준이라고 하자.

4. 최단거리를 통해 solve(a,b)를 distance(a,b)로 바꾸어 다음과 같이 표현할 수 있겠다.

```
solve(a,b) = 
    distance[a][b] == MAX_VALUE -> 0
    distance[a][b] > 0 -> -1
    distance[b][a] > 0 -> 1 
```

5. N이 400이하이므로 플로이드 알고리즘을 이용해 모든 경로의 distance를 구할 수 있겠다.

## 느낀점

1. 플로이드 와샬 알고리즘을 공부하기 아주 좋은 문제였다.

2. 특히 위상정렬인가 하는 함정을 파놓은거 같아 굉장했다. 더더욱 검증에 노력해야겠다.

## 코드

```java
package daily.day0220.boj1613;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.function.ToIntFunction;

public class Main {
    private static int N;
    private static int[][] dist;

    private static ToIntFunction<String> stoi = s -> Integer.parseInt(s);

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] line = reader.readLine().split(" ");
        N = stoi.applyAsInt(line[0]);
        int K = stoi.applyAsInt(line[1]);

        dist = new int[N+1][N+1];
        for(int[] d : dist) Arrays.fill(d, (Integer.MAX_VALUE)/2);
        for(int i=0; i<K; i++){
            line = reader.readLine().split(" ");
            int u = stoi.applyAsInt(line[0]);
            int v = stoi.applyAsInt(line[1]);
            dist[u][v] = 1;
        }

        calcDistance();

        K = stoi.applyAsInt(reader.readLine());
        for(int i=0; i<K; i++){
            line = reader.readLine().split(" ");
            int u = stoi.applyAsInt(line[0]);
            int v = stoi.applyAsInt(line[1]);

            if(dist[u][v] < (Integer.MAX_VALUE)/2) System.out.println("-1");
            else if(dist[v][u] < (Integer.MAX_VALUE)/2) System.out.println("1");
            else System.out.println("0");
        }
    }

    private static void calcDistance(){
        for(int k=1; k<=N; k++){
            for(int i=1; i<=N; i++){
                for(int j=1; j<=N; j++){
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }
    }
}
```