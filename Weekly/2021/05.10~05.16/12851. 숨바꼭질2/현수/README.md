---
title: '12851. 숨바꼭질2'
metaTitle: '만렙 개발자 키우기'
metaDescription: '알고리즘 문제를 풀고 정리한 곳입니다.'
tags: ['그래프 이론', 'BFS', '그래프 탐색']
date: '2021-05-13'
---


# 문제
- [12851. 숨바꼭질2](https://www.acmicpc.net/problem/12851)

## 코드
``` java
import java.awt.Point;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Q12851 {
    static int n, k, minTime = 987654321, minTimeCnt;
    static int visited[];
    public static void main(String[] args) {
        init();
        solution();
    }

    private static void init() {
        Scanner sc = new Scanner(new InputStreamReader(System.in));
        n = sc.nextInt();
        k = sc.nextInt();
        visited = new int[100001];
        Arrays.fill(visited, 987654321);
        visited[n] = 0;
    }

    private static void solution() {
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(n, 0));

        if(k <= n){
            System.out.println(n - k);
            System.out.println(1);
            return;
        }

        while (!q.isEmpty()) {

            Point here = q.poll();
            int pos = here.x, time = here.y;
            if(time > minTime) continue;

            if(pos == k){
                if(time < minTime) {
                    minTime = time;
                    minTimeCnt = 1;
                }
                else if(time == minTime)
                    minTimeCnt += 1;

                continue;
            }

            if(pos - 1 >= 0 && time + 1<= visited[pos - 1]){
                q.add(new Point(pos - 1, time + 1));
                visited[pos - 1] = time + 1;
            }
            if(pos + 1 <= 100000 && time + 1<= visited[pos + 1]){
                q.add(new Point(pos + 1, time + 1));
                visited[pos + 1] = time + 1;
            }
            if(pos * 2 <= 100000 && time + 1<= visited[pos * 2]){
                q.add(new Point(pos * 2, time + 1));
                visited[pos * 2] = time + 1;
            }
        }
        System.out.println(minTime);
        System.out.println(minTimeCnt);
    }
}

```


## ⭐️느낀점⭐️
> 금방 풀 줄 알았는데, 방문 체크 부분에서 생각을 늦게 떠올렸다! 예전엔 이렇게도 금방 생각했었는데 지금은 천천히 생각하고 나서야 떠오르는걸 보면서 감이란게 정말 중요하다는걸 다시 한번 느꼈다. 


## 풀이 📣

<hr/>

1️⃣ 현재 위치에서 -1, +1, *2 만큼 이동하는 경우를 판단한다.


2️⃣ 다음 위치에 도달한 적이 있는지 확인한다.

    - 만약 지금 이동했을 때까지 걸린 시간보다, 이전에 더 먼저 도착한 적이 있다면 큐에 다시 넣지 않는다.

    - 이전에 도착한 적이 없다면, 해당 인덱스에 방문 시점을 저장해두고 큐에 다시 삽입한다.


3️⃣ 목적지에 도달하였을 때, 걸린 시간을 비교한다.

    - 이전에 도달했을 때보다 적은 시간으로 도달하였으면 최소 도달 시간을 갱신한다.

    - 그때 방법의 수를 1로 초기화한다.

    - 만약 이전과 동일한 시간 내에 도달하였으면 방법의 수를 +1 해준다.


## 실수 😅

<hr/>

-  방문 체크를 `boolean` 값으로 해서 중복 경우를 세어주지 못했다.