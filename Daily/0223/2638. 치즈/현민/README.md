# 2638 치즈

## 조건

1. 외부로부터 2변 이상이 맞닿은 치즈는 1시간 후에 녹게 된다.

2. 안쪽 공기는 영향을 미치지 않는다.

3. 맨 가장자리는 비어있다.

## 구해야 하는 것

* 치즈가 다 녹기까지 걸리는 시간

## 아이디어

1. 맨 가장자리가 비어있는 것을 유심깊게 보자

2. 맨 가장자리부터 dfs로 맵을 탐색하면 외부로부터 닿은 치즈들을 탐색할 수 있게 된다.

3. 이러면 안쪽 공기는 전혀 신경쓰지 않고 쉽게 해결할 수 있게 된다.

4. 맞닿을 때마다 맞닿은 변의 정보를 갱신한다.

5. 가장가리 dfs가 끝난 후 2개이상의 변이 맞닿은 치즈를 찾아 없애준다.

6. 이 과정을 모든 치즈가 없어질 때까지 반복한다.

## 코드

```java
package daily.day0223.boj2638;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static BufferedReader br;
    private static StringTokenizer st;

    private static int[] dc = {0, 1, 0, -1}; // up right down left
    private static int[] dr = {-1, 0, 1, 0};

    private static int[][] board; // -1 : empty, 0~4 : number of side that contact outside
    private static int rowSize;
    private static int colSize;

    private static final int AIR = -1;
    private static final int OUTSIDE = -2;

    public static void main(String[] args) throws IOException {
        init();
        System.out.println(solve());
    }

    private static void dfs(int row, int col, boolean[][] visited){
        if(visited[row][col]) return;
        visited[row][col] = true;

        for(int d=0; d<4; d++){
            int nr = row + dr[d];
            int nc = col + dc[d];

            if(board[nr][nc] == OUTSIDE) continue;
            else if(!visited[nr][nc] && board[nr][nc] >= 0) board[nr][nc]++;
            else dfs(nr,nc,visited);
        }
    }

    private static int solve() {
        int time=0;

        while (hasCheese()) {
            boolean[][] visited = new boolean[rowSize + 2][colSize + 2];
            for(boolean[] v : visited) Arrays.fill(v, false);
            initCheese();

            dfs(1, 1, visited);
            dfs(rowSize, 1, visited);
            dfs(rowSize, colSize, visited);
            dfs(1, colSize, visited);

            for(int row=1; row<=rowSize; row++){
                for(int col=1; col<=colSize; col++){
                    if(board[row][col] >= 2)
                        board[row][col] = AIR;
                }
            }

            time++;
        }

        return time;
    }

    private static boolean hasCheese() {
        for(int row=1; row<=rowSize; row++){
            for(int col=1; col<=colSize; col++){
                if(board[row][col] >= 0) return true;
            }
        }
        return false;
    }

    private static void init() throws IOException {
        rowSize = nextInt(); colSize = nextInt();
        board = new int[rowSize+2][colSize+2];
        for(int[] b : board) Arrays.fill(b, OUTSIDE);

        for(int row=1; row<=rowSize; row++){
            for(int col=1; col<=colSize; col++){
                board[row][col] = nextInt() -1;
            }
        }

        initCheese();
    }

    private static void initCheese(){
        for(int row=1; row<=rowSize; row++){
            for(int col=1; col<=colSize; col++){
                if(board[row][col] >= 0) board[row][col] = 0;
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