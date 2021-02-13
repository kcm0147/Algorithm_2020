# 20040 사이클 게임

## 조건

* 어느 세 점도 일직선상에 놓여있지 않다.

* 플레이어는 2명이며, 번갈아 가며 두 점을 선으로 잇는다.

* 만약 한 플레이어가 선으로 이은 후 그 플레이어가 이은 선들이 사이클을 이룬다면 게임이 끝나게 된다.

## 구해야 하는 것

* 플레이어들이 잇는 점 2개가 차례대로 주어질 때, 사이클이 처음으로 만들어지는지를 차례의 번호를 출력

* 단, 모든 차례가 지난 후에도 사이클이 없다면 0 을 출력

## 제한 조건

* `점의 갯수 n` : 500,000

* `차례의 수 m` : 1,000,000

* 절대 `m * n` 알고리즘은 안된다.

* `m * logN` 이내여야 한다.

## 아이디어

`사이클을 형성한다` -> 무슨 의미로 재해석할 수 있는가?

```
현재 차례에서 두 점을 이엇을 때 다른 한 점을 통해 자기 자신으로 되돌아 올 수 있는가?

즉, 차례에 주어진 두 점 중 하나의 점을 통해 다른 하나의 점에 도달 할 수 있는가를 판별해야 한다.
```

그렇다면 이를 어떻게 판단해야 하는가?

* 기본적으로 dfs를 통해 탐색할 수 있으나 이 경우 최악의 경우 한 차례당 n타임이 걸리므로 안된다.

* 그렇다면, 같은 집합에 포함되어 있는가 여부를 판단하는 Disjoint Set을 사용해보자!

## 코드

```java
package daily.day0214.boj20040;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
            int n = Integer.parseInt(stringTokenizer.nextToken());
            int m = Integer.parseInt(stringTokenizer.nextToken());

            int[][] points = new int[m][2];
            for(int i=0; i<m; i++){
                stringTokenizer = new StringTokenizer(reader.readLine());
                points[i][0] = Integer.parseInt(stringTokenizer.nextToken());
                points[i][1] = Integer.parseInt(stringTokenizer.nextToken());
            }

            System.out.println(new Solution().solution(n, m, points));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

class Solution{
    private static final int MAX_POINTS = 500000;
    int[] parent = new int[MAX_POINTS];
    private int[] rank = new int[MAX_POINTS];

    int solution(int sizeOfPoint, int sizeOfGames, int[][] pointsToLink){
        for(int i=0; i<sizeOfPoint; i++) parent[i] = i;

        int answer = 0;
        for(int i=0; i<sizeOfGames; i++){
            int u = pointsToLink[i][0], v = pointsToLink[i][1];
            if(find(u) == find(v)){
                answer = i + 1;
                break;
            }
            merge(u, v);
        }
        return answer;
    }

    private int find(int u){
        if(u == parent[u]) return u;
        return parent[u] = find(parent[u]);
    }

    private void merge(int u, int v){
        u = find(u); v = find(v);
        if(u == v) return;

        if(rank[u] > rank[v]){
            int temp = u;
            u = v;
            v = temp;
        }

        parent[u] = v;
        if(rank[u] == rank[v]) ++rank[v];
    }
}
```