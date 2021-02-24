# 16469 소년 점프

## 조건

1. 악당은 총 세명이 있다.

2. 각각 다른 위치에서 시작한다.

3. 맵은 벽과 빈곳으로 이루어진 R*C 크기의 맵이다.

4. 벽은 지날 수 없다.

5. 악당은 각각 상하좌우로 1칸씩 움직일 수 있다.

6. 움직이지 않을 수 있다.

## 구해야하는 것

* 악당들이 서로 한 곳에 모이는 최소 시간과 그런 지점의 수

## 아이디어

1. 여기서 주목할 점은 악당이 움직이지 않고 기다릴 수 있다는 점이다.

2. 즉 어떤 한 지점에 모이는 시간은 마지막에 도착하는 악당의 시간이 된다.

3. 여기서는 한 칸씩 밖에 움직일 수 없으므로, bfs를 통해 탐색하도록 한다.

4. 다음과 같이 거리를 정의하자.
   
`distance[i][r][c]` : i번째 악당이 (r,c)에 도달하는 시간

5. 어떤 한 지점에 모이는 시간은 다음과 같다.

`time(r,c)` = `max(distance[0][r][c], max(distance[1][r][c],distance[2][r][c]))`

6. 모든 (r,c) 중에서 최소가 되는 time(r,c)을 구한다.

7. 모두 도달할 수 없다면 distance의 초깃값이 time이 될때다. 이때 -1를 출력한다.

## 느낀점

1. 쉽게 아이디어가 떠올라 풀 수 있었다.

2. gold 4,5 문제는 아이디어만 떠올린다면 쉽게 풀 수 있는거 같다.

## 코드

```java
package daily.day0225.boj16469;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static BufferedReader br;
    private static StringTokenizer st;

    private static final int[] dr = {-1, 0, 1, 0}; //up right down left
    private static final int[] dc = {0, 1, 0, -1};
    
    private static int[][] board;

    public static void main(String[] args) throws IOException {
        int rowSize = nextInt(), colSize = nextInt();
        board = new int[rowSize+2][colSize+2];
        for(int[] b : board) Arrays.fill(b, 1);

        for(int row=1; row<=rowSize; row++){
            String s = nextToken();
            for(int col=1; col<=colSize; col++){
                board[row][col] = s.charAt(col-1) - '0';
            }
        }

        int[][][] distance = new int[3][rowSize+2][colSize+2];
        for(int i=0; i<3; i++){
            dfs(distance[i], nextInt(), nextInt());
        }

        int minTime=Integer.MAX_VALUE/2, freq=0;
        for(int row=1; row<=rowSize; row++){
            for(int col=1; col<=colSize; col++){
                int time = Math.max(distance[0][row][col], Math.max(distance[1][row][col], distance[2][row][col]));
                if(minTime == time) freq++;
                if(minTime > time){
                    minTime = time;
                    freq = 1;
                }
            }
        }

        if(minTime == Integer.MAX_VALUE/2) System.out.println(-1);
        else {
            System.out.println(minTime);
            System.out.println(freq);
        }
    }

    private static void dfs(int[][] distance, int startRow, int startCol){
        for(int[] d : distance) Arrays.fill(d, Integer.MAX_VALUE/2);
        distance[startRow][startCol] = 0;
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(startCol, startRow));

        while(!q.isEmpty()){
            int row = q.peek().y;
            int col = q.poll().x;

            for(int d=0; d<4; d++){
                int nr = row + dr[d];
                int nc = col + dc[d];
                if(board[nr][nc] != 0 || distance[nr][nc] != Integer.MAX_VALUE/2) continue; // out of bound or wall or visit

                distance[nr][nc] = distance[row][col] + 1;
                q.add(new Point(nc, nr));
            }
        }
    }

    private static int nextInt() throws IOException { return Integer.parseInt(nextToken()); }

    private static String nextToken() throws IOException {
        if(br == null) br = new BufferedReader(new InputStreamReader(System.in));
        if(st == null || !st.hasMoreTokens())
            st = new StringTokenizer(br.readLine());
        return st.nextToken();
    }
}
```