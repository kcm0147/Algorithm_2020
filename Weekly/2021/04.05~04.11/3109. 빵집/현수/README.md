---
title: '3109. 빵집'
metaTitle: '만렙 개발자 키우기'
metaDescription: '알고리즘 문제를 풀고 정리한 곳입니다.'
tags: ['그래프 이론', '그리디', '그래프 탐색', 'DFS']
date: '2021-04-21'
---

# 문제
- [3109. 빵집](https://www.acmicpc.net/problem/3109)

## 코드

<details><summary> 코드 보기 </summary>

``` java
import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Q3109 {

    static int r, c;
    static int dx[] = {-1, 0, 1}, dy[] = {1, 1, 1};
    static char board[][];
    static boolean visited[][], blocked[][];
    static List<Point> trace;
    public static void main(String[] args) throws IOException {
        init();
        solution();
    }

    private static void solution() {
        int ans = 0;W
        for (int i = 0; i < r; i++) {
            trace = new ArrayList<>();
            if(dfs(i, 0)){
                ans += 1;
                for(Point p : trace) {
                    blocked[p.x][p.y] = true;
                }
            }
        }
        System.out.println(ans);
    }

    private static boolean dfs(int x, int y) {
        if(y == c - 1) {
            trace.add(new Point(x, y));
            return true;
        }

        visited[x][y] = true;
        for (int d = 0; d < 3; d++) {
            int nx = x + dx[d], ny = y + dy[d];
            if(!isBorder(nx ,ny) || board[nx][ny] == 'x' || visited[nx][ny] || blocked[nx][ny])
                continue;

            if(dfs(nx, ny)){
                trace.add(new Point(x, y));
                return true;
            }
        }
        return false;
    }

    private static boolean isBorder(int x, int y) {
        return (x >= 0 && x < r && y >= 0 && y < c);
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        r = stoi(input[0]);
        c = stoi(input[1]);
        board = new char[r][c];
        visited = new boolean[r][c];
        blocked = new boolean[r][c];
        for (int i = 0; i < r; i++) {
            String line = br.readLine();
            for (int j = 0; j < c; j++) {
                board[i][j] = line.charAt(j);
            }
        }
    }

    private static int stoi(String str) {
        return Integer.parseInt(str);
    }
}

```
</details>

## ⭐️느낀점⭐️
> 시험 기간동안 잠시 알고리즘 문제 풀이를 쉬었다가 했지만, 간단한 그래프 탐색 문제여서 어렵지않게 풀 수 있었다. 

## 풀이 📣
<hr/>

1️⃣ 각 칸은 교차될 수 없고, 이동방향은 "오른쪽 위, 오른쪽, 오른쪽 아래" 로 고정되어 있다. 

    - 따라서 가스관의 가장 위쪽부터 차례대로 탐색하며 위,오른쪽, 아래 순으로 탐색을 할 경우 아래쪽의 가스관과 겹치지 않게 연결할 수 있다. 


2️⃣ 각 가스관부터 정해 놓은 순서대로 연결을 해보면서 만약 빵집까지 연결이 가능하다면 해당 경로를 체크한다. 

    - 탐색에 성공하면 경로 리스트에 좌표를 저장한 후 true를 반환하여 모든 재귀에서 탈출시킨다.


3️⃣ `dfs` 메소드를 처음 호출하는 위치에서 탐색 성공여부에 따라 다음 로직들을 수행한다.

    - 만약 탐색에 성공할 경우, 이미 가스관이 연결된 지점을 마킹하는 배열 blocked[][] 에 경로 리스트에 저장된 좌표들을 표시하고 연결 개수를 증가시켜준다.

    - 만약 탐색에 실패할 경우, 동일한 위치에서 다음 방향으로 탐색을 시도한다.


4️⃣ 성공적으로 연결한 가스관의 개수를 출력하고 종료한다.