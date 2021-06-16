---
title: '16398. 행성 연결'
metaTitle: '만렙 개발자 키우기'
metaDescription: '알고리즘 문제를 풀고 정리한 곳입니다.'
tags: ['그래프 이론', '최소 스패닝 트리']
date: '2021-06-16'
---

# 문제
- [16398. 행성 연결](https://www.acmicpc.net/problem/16398)

## 코드

<details><summary> 코드 보기 </summary>

``` java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Q16398_2 {
static int n, parent[], arr[][];
static PriorityQueue<Edge> pq = new PriorityQueue<>((a, b)-> (a.cost - b.cost));

    public static void main(String[] args) throws IOException {
        init();
        solution();
    }

    private static void solution() {
        long sum = 0L;
        while(!pq.isEmpty()){
            Edge e = pq.poll();
            int u = e.u, v = e.v, cost = e.cost;
            if(find(u) == find(v)) continue;
            sum += cost;
            union(u, v);
        }
        System.out.println(sum);
    }

    private static int find(int u) {
        if(parent[u] == u) return u;
        return parent[u] = find(parent[u]);
    }

    private static void union(int u, int v){
        int pu = find(u), pv = find(v); // 항상 pu > pv
        if(pu < pv) {
            int temp = pu;
            pu = pv;
            pv = temp;
        }
        parent[pv] = pu;
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = stoi(br.readLine());
        parent = new int[n+1];
        arr = new int[n+1][n+1];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        for (int i = 1; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++) {
                arr[i][j] = stoi(st.nextToken());
            }
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j < i; j++) {
                if(arr[i][j] != 0) {
                    pq.add(new Edge(i, j, arr[i][j]));
                }
            }
        }
    }

    private static int stoi(String str) {
        return Integer.parseInt(str);
    }

    static class Edge{
        int u, v, cost;

        public Edge(int u, int v, int cost) {
            this.u = u;
            this.v = v;
            this.cost = cost;
        }
    }
}

```
</details>

## ⭐️느낀점⭐️
> 간단하게 크루스칼 알고리즘을 사용해서 풀 수 있었다.

## 풀이 📣
<hr/>

1️⃣ 인접 행렬로 주어지는 입력에 대해 어차피 하나의 간선은 가중치가 같으므로 절반만큼만 사용한다.

    - 전체 인접 행렬에서 절만 삼각형 만큼만 우선순위큐에 넣는다.


2️⃣ 우선순위 큐에서 꺼내서 두 정점의 부모를 찾아서 부모가 같지 않으면 해당 간선을 사용한다.


3️⃣ 모든 간선을 확인해보면서 하나의 스패닝 트리로 만들어준다.

    - 그때 간선의 가중치만큼을 더해준다. 


4️⃣ 최종적으로 구한 최소 유지비용을 출력한다.

## 실수 😅
<hr/>

- 출력 시 long 형으로 바꿔줘야 했다.

