# 6087 레이저 통신

## 코드

```java
package week.w32.b19952;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static BufferedReader br;
    private static StringTokenizer st;

    private static final int[] dr = {-1, 0, 1, 0};
    private static final int[] dc = {0, 1, 0, -1};

    private static int H;
    private static int W;
    private static int[][] board;

    public static void main(String[] args) {
        int testCae = nextInt();
        for(int t=0; t<testCae; t++){
            H = nextInt(); W = nextInt();
            int objectNum = nextInt(), power = nextInt();
            QNode start = new QNode(nextInt()-1, nextInt()-1, power);
            QNode end = new QNode(nextInt()-1, nextInt()-1, 0);

            board = new int[H][W];
            for(int[] bo : board) Arrays.fill(bo, 0);
            for(int o=0; o<objectNum; o++){
                board[nextInt()-1][nextInt()-1] = nextInt();
            }

            if(bfs(start, end)){
                System.out.println("잘했어!!");
            }else{
                System.out.println("인성 문제있어??");
            }
        }
    }

    private static boolean bfs(QNode start, QNode end){
        boolean[][] visited = new boolean[H][W];
        for(boolean[] varr: visited) Arrays.fill(varr, false);

        Queue<QNode> q = new LinkedList<>();
        q.add(start);

        while(!q.isEmpty()){
            int r = q.peek().row;
            int c = q.peek().col;
            int power = q.poll().power;

            if(r == end.row && c == end.col)
                return true;

            for(int d=0; d<4; d++){
                int nr = r + dr[d];
                int nc = c + dc[d];

                if(canMove(r,c, nr, nc, power, visited)){
                    visited[nr][nc] = true;
                    q.add(new QNode(nr,nc, power-1));
                }
            }
        }

        return false;
    }

    private static boolean canMove(int r, int c, int nr, int nc, int power, boolean[][] visited){
        if(isOutBoundary(nr,nc) || visited[nr][nc] || power <= 0) return false;
        int jump = board[nr][nc] - board[r][c];
        if(jump <= 0 || power >= jump) return true;
        return false;
    }

    private static boolean isOutBoundary(int r, int c){
        return r < 0 || r >= H || c < 0 || c >= W;
    }

    private static class QNode{
        int row;
        int col;
        int power;
        public QNode(int row, int col, int power) {
            this.row = row;
            this.col = col;
            this.power = power;
        }
    }

    private static int nextInt() { return Integer.parseInt(nextToken()); }

    private static String nextToken(){
        if(br == null) br = new BufferedReader(new InputStreamReader(System.in));
        if(st == null || !st.hasMoreTokens()) {
            try {
                st = new StringTokenizer(br.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return st.nextToken();
    }
}
```