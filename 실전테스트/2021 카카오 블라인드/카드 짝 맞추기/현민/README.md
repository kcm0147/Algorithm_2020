# 카드 짝 맞추기

## 코드

```java
package kakao_21.p6;

import java.awt.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

class Solution {
    private static int[] dr = {-1, 0, 1, 0};
    private static int[] dc = {0, 1, 0, -1};

    public int solution(int[][] board, int r, int c) {
        int sizeChar = Arrays.stream(board).mapToInt(arr -> Arrays.stream(arr).max().getAsInt()).max().getAsInt();
        Point[][] characters = new Point[sizeChar][2];

        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board.length; col++) {
                if (board[row][col] == 0) continue;
                int ch = board[row][col] - 1;
                if (characters[ch][0] == null)
                    characters[ch][0] = new Point(col, row);
                else
                    characters[ch][1] = new Point(col, row);
            }
        }

        return dfs(board, characters, new Point(c, r));
    }

    private int dfs(int[][] board, Point[][] characters, Point start) {
        if (isEmpty(board)) return 0;

        int ret = Integer.MAX_VALUE / 2;
        for (int i = 0; i < characters.length; i++) {
            for (int j = 0; j < 2; j++) {
                Point char1 = characters[i][j];
                Point char2 = characters[i][(j + 1) % 2];
                if (board[char1.y][char1.x] == 0) continue;

                int dist = getDist(board, start, char1)
                        + getDist(board, char1, char2) + 2;

                board[char1.y][char1.x] = board[char2.y][char2.x] = 0;
                ret = Math.min(ret, dist + dfs(board, characters, char2));
                board[char1.y][char1.x] = board[char2.y][char2.x] = i + 1;
            }
        }

        return ret;
    }


    private int getDist(int[][] board, Point start, Point end) {
        int[][] dist = new int[board.length][board.length];
        for (int[] d : dist) Arrays.fill(d, -1);
        dist[start.y][start.x] = 0;

        Queue<Point> q = new LinkedList<>();
        q.add(start);
        while (!q.isEmpty()) {
            int r = q.peek().y;
            int c = q.poll().x;

            if (end.y == r && end.x == c) return dist[end.y][end.x];

            for (int d = 0; d < 4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];
                checkAndAdd(q, dist, nr, nc, dist[r][c]);

                for (; !outOfBound(nr, nc) && board[nr][nc] == 0; nr += dr[d], nc += dc[d]) ;
                if (outOfBound(nr, nc)) {
                    nr -= dr[d];
                    nc -= dc[d];
                }

                checkAndAdd(q, dist, nr, nc, dist[r][c]);
            }
        }

        return dist[end.y][end.x];
    }

    private void checkAndAdd(Queue q, int[][] dist, int nr, int nc, int distance) {
        if (outOfBound(nr, nc)) return;
        if (dist[nr][nc] == -1) {
            dist[nr][nc] = distance + 1;
            q.add(new Point(nc, nr));
        }
    }

    private boolean outOfBound(int r, int c) {
        return r < 0 || r >= 4 || c < 0 || c >= 4;
    }

    private boolean isEmpty(int[][] board) {
        for (int[] line : board) {
            for (int space : line) {
                if (space != 0) return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[][] board1 = {{1, 0, 0, 3}, {2, 0, 0, 0}, {0, 0, 0, 2}, {3, 0, 1, 0}};
        int[][] board2 = {{3, 0, 0, 2}, {0, 0, 1, 0}, {0, 1, 0, 0}, {2, 0, 0, 3}};

        System.out.println(new Solution().solution(board1, 1, 0));
        System.out.println(new Solution().solution(board2, 0, 1));
    }
}
```