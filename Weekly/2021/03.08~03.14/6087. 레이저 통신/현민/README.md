# 6087 레이저 통신

## 코드

```java
package week.w32.b6087;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static BufferedReader br;
    private static StringTokenizer st;

    private static int[][] board;
    private static int H;
    private static int W;
    private static final int[] dr = {-1, 0, 1, 0}; // up right down left
    private static final int[] dc = {0, 1, 0, -1};

    public static void main(String[] args) {
        W = nextInt();
        H = nextInt();
        board = new int[H + 2][W + 2];
        for (int[] line : board) Arrays.fill(line, 1);

        ArrayList<Point> raser = new ArrayList<>();
        for (int row = 1; row <= H; row++) {
            String line = nextToken();
            for (int col = 1; col <= W; col++) {
                switch (line.charAt(col - 1)) {
                    case '*':
                        board[row][col] = 1;
                        break;
                    case 'C':
                        raser.add(new Point(col, row));
                    case '.':
                        board[row][col] = 0;
                        break;
                }
            }
        }

        System.out.println(djkstra(raser.get(0), raser.get(1)).getAsInt());
    }

    private static OptionalInt djkstra(Point start, Point end) {
        int[][][] dist = new int[H + 2][W + 2][4];
        for (int[][] d2d : dist) for (int[] d1d : d2d) Arrays.fill(d1d, Integer.MAX_VALUE / 2);
        for (int d = 0; d < 4; d++) dist[start.y][start.x][d] = 0;

        PriorityQueue<QNode> pq = new PriorityQueue<>((n1, n2) -> Integer.compare(n1.distance, n2.distance));
        for (int d = 0; d < 4; d++)
            pq.add(new QNode(start.y, start.x, d, 0));
        while (!pq.isEmpty()) {
            QNode u = pq.poll();

            for (int i = -1; i <= 1; i++) {
                int dir = (u.dir + 4 + i) % 4;
                int nr = u.row + dr[dir];
                int nc = u.col + dc[dir];

                if (board[nr][nc] != 0) continue;

                int usedMirror = i != 0 ? 1 : 0;
                if (dist[nr][nc][dir] > u.distance + usedMirror) {
                    dist[nr][nc][dir] = u.distance + usedMirror;
                    pq.add(new QNode(nr, nc, dir, dist[nr][nc][dir]));
                }
            }
        }

        return Arrays.stream(dist[end.y][end.x]).min();
    }

    private static class QNode {
        int row;
        int col;
        int dir;
        int distance;

        public QNode(int row, int col, int dir, int distance) {
            this.row = row;
            this.col = col;
            this.dir = dir;
            this.distance = distance;
        }
    }

    private static int nextInt() {
        return Integer.parseInt(nextToken());
    }

    private static String nextToken() {
        if (br == null) br = new BufferedReader(new InputStreamReader(System.in));
        if (st == null || !st.hasMoreTokens()) {
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