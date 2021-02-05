import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.List;

public class Q1113 {
    static int n, m, board[][], dx[] = {-1, 0, 1, 0}, dy[] = {0, 1, 0, -1};
    static boolean existHeight[], existWater[][], visited[][];
    static List<Point> testPool = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        init();
        solution();
    }

    private static void solution() {
        // 물 채우기 :: 둘러싸고 있는 벽 중 가장 낮은 벽이 최대.
        // 각 칸에 채울 수 있는 물의 양 = (최대 양 - 칸의 높이)
        int ans = 0;
        for (int max = 9; max > 1; --max) {
            if(!existHeight[max]) continue;
            int ret = 0;

            for (int x = 1; x < n - 1; x++) {
                for (int y = 1; y < m - 1; y++) {
                    if(existWater[x][y] || board[x][y] >= max)
                        continue;
                    for(int i=0; i<n; ++i)
                        Arrays.fill(visited[i], false);
                    visited[x][y] = true;

                    if(dfs(x, y, max))
                        ret += getDiff(max);
                    testPool = new ArrayList<>();
                }
            }
            ans += ret;
        }
        System.out.println(ans);
    }

    static boolean dfs(int x, int y, int max){
        for (int d = 0; d < 4; d++) {
            int nx = x + dx[d], ny = y + dy[d];
            if(!isBorder(nx, ny)) return false;

            if(existWater[nx][ny] || visited[nx][ny] || board[nx][ny] >= max)
                continue;
            visited[nx][ny] = true;
            if(!dfs(nx, ny, max))
                return false;
        }
        testPool.add(new Point(x, y));
        return true;
    }

    private static int getDiff(int max) {
        int diff = 0;
        for (Point p : testPool) {
            if(existWater[p.x][p.y]) continue;
            existWater[p.x][p.y] = true;
            diff += max - board[p.x][p.y];
        }
        return diff;
    }

    private static boolean isBorder(int x, int y) {
        return (x >= 0 && x < n && y >= 0 && y < m);
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        board = new int[n][m]; existHeight = new boolean[10];
        existWater = new boolean[n][m];
        visited = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < m; j++) {
                board[i][j] = line.charAt(j) - '0';
                existHeight[board[i][j]] = true;
            }
        }
    }
}
/*
4 5
14231
23323
32123
43333
5

5 5
55515
53121
54445
51115
55555
10

5 5
16666
63636
66146
13236
66666
7

48 40
1666116661166611666116661166611666116661
6111661116611166111661116611166111661116
1666116661166611666116661166611666116661
1666116661166611666116661166611666116661
6111661116611166111661116611166111661116
1666116661166611666116661166611666116661
1666116661166611666116661166611666116661
6111661116611166111661116611166111661116
1666116661166611666116661166611666116661
1666116661166611666116661166611666116661
6111661116611166111661116611166111661116
1666116661166611666116661166611666116661
1666116661166611666116661166611666116661
6111661116611166111661116611166111661116
1666116661166611666116661166611666116661
1666116661166611666116661166611666116661
6111661116611166111661116611166111661116
1666116661166611666116661166611666116661
1666116661166611666116661166611666116661
6111661116611166111661116611166111661116
1666116661166611666116661166611666116661
1666116661166611666116661166611666116661
6111661116611166111661116611166111661116
1666116661166611666116661166611666116661
1666116661166611666116661166611666116661
6111661116611166111661116611166111661116
1666116661166611666116661166611666116661
1666116661166611666116661166611666116661
6111661116611166111661116611166111661116
1666116661166611666116661166611666116661
1666116661166611666116661166611666116661
6111661116611166111661116611166111661116
1666116661166611666116661166611666116661
1666116661166611666116661166611666116661
6111661116611166111661116611166111661116
1666116661166611666116661166611666116661
1666116661166611666116661166611666116661
6111661116611166111661116611166111661116
1666116661166611666116661166611666116661
1666116661166611666116661166611666116661
6111661116611166111661116611166111661116
1666116661166611666116661166611666116661
1666116661166611666116661166611666116661
6111661116611166111661116611166111661116
1666116661166611666116661166611666116661
1666116661166611666116661166611666116661
6111661116611166111661116611166111661116
1666116661166611666116661166611666116661

 */