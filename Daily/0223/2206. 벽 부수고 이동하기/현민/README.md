# 2206 벽 부수고 이동하기

## 조건

1. 맵에는 빈곳(0)과 벽(1)이 있다.

2. 벽은 단 한번만 부술 수 있다. 그 외에는 이동할 수 없다.

3. 빈 곳은 자유롭게 이동가능하다.

## 구해야 하는 것

* (1,1)에서 시작하여 (N,M)으로 가는 최단경로의 길이를 구하시오.

## 아이디어

1. 다음과 같이 부분문제를 표현하자
```
solve(n,m,0) : 한번도 부수지 않았을 때 (n,m)으로 가는 최단경로의 길이
solve(n,m,1) : 한번만 부쉈을 때 (n,m)으로 가는 최단경로의 길이
```

2. 맵을 상하좌우로 이동하는 것에 착안하여 bfs를 통한 기본적인 탐색을 떠올릴 수 있겠다.

3. 여기서는 벽을 부수는 행위를 통해 더 가까운 경로도 만들 수 있으니 다익스트라를 이용해보자

4. 만약 다음 지점이 벽이라면 다음과 같이 점화식을 세울 수 있겠다.
```
distance[nr][nm][1] = min of distance[nr][nm][1], distance[n][m][0] + 1

벽이라면 부숴야하므로 이전에 부수지 않은 결과를 통해 최단경로를 갱신해야 한다.
```

5. 만약 다음 지점이 비어있다면 다음과 같다.
```
distance[nr][nm][0] = min of distance[nr][nm][0], distance[n][m][0] + 1
distance[nr][nm][1] = min of distance[nr][nm][1], distance[n][m][1] + 1
```

6. 새로 갱신해준 결과는 q에 삽입하여 준다.

## 느낀점

1. 다행히 크게 어려움 없이 떠올릴 수 있어 풀 수 있었다.

## 코드

```java
package daily.day0223.boj2206;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    private static BufferedReader br;
    private static StringTokenizer st;

    private static int[][][] distance;
    private static int[][] board;
    private static int row;
    private static int col;

    public static void main(String[] args) throws IOException {
        init();
        System.out.println(solve());
    }

    private static void init() throws IOException{
        br = new BufferedReader(new InputStreamReader(System.in));

        row = nextInt(); col = nextInt();
        board = new int[row+2][col+2];
        for(int[] b: board) Arrays.fill(b, -1);

        for(int r=1; r<=row; r++){
            String s = nextToken();
            for(int c=1; c<=s.length(); c++){
                board[r][c] = s.charAt(c-1) - '0';
            }
        }
    }

    private static int solve(){
        distance = new int[row+2][col+2][2];
        for(int[][] c2d : distance) for(int[] c1d : c2d) Arrays.fill(c1d, Integer.MAX_VALUE/2);
        distance[1][1][0] = 1;

        int[] dc = {0, 1, 0, -1}; // up right down left
        int[] dr = {-1, 0, 1, 0};

        PriorityQueue<Node> pq = new PriorityQueue<>((n1,n2) -> Integer.compare(n1.distance, n2.distance));
        pq.add(new Node(1,1,1));
        while(!pq.isEmpty()){
            int r = pq.peek().row;
            int c = pq.poll().col;

            for(int d=0; d<4; d++){
                int nr = r + dr[d];
                int nc = c + dc[d];
                if(board[nr][nc] == -1) continue; // out of bound

                if(board[nr][nc] == 1 && distance[r][c][0] + 1 < distance[nr][nc][1]){
                    distance[nr][nc][1] = distance[r][c][0] + 1;
                    pq.add(new Node(nr, nc, distance[nr][nc][1]));
                }else if(board[nr][nc] == 0){
                    for(int isBreak=0; isBreak<2; isBreak++){
                        if(distance[r][c][isBreak] + 1 < distance[nr][nc][isBreak]){
                            distance[nr][nc][isBreak] = distance[r][c][isBreak] + 1;
                            pq.add(new Node(nr,nc, distance[nr][nc][isBreak]));
                        }
                    }
                }
            }
        }

        if(distance[row][col][0] == Integer.MAX_VALUE/2 && distance[row][col][1] == Integer.MAX_VALUE/2)
            return -1;
        return Math.min(distance[row][col][0], distance[row][col][1]);
    }

    private static int nextInt() throws IOException {
        return Integer.parseInt(nextToken());
    }

    private static String nextToken() throws IOException {
        if(st == null || !st.hasMoreTokens())
            st = new StringTokenizer(br.readLine());
        return st.nextToken();
    }

    static class Node{
        int row;
        int col;
        int distance;
        Node(int row, int col, int distance) {
            this.row = row;
            this.col = col;
            this.distance = distance;
        }
    }
}
```