import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q14925 {
    static int m, n, board[][], cache[][];
    public static void main(String[] args) throws IOException {
        init();
        solution();
    }

    private static void solution() {
        int ans = 0;
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if(board[i][j] == 0){
                    if(board[i][j-1] == 0 && board[i - 1][j - 1] == 0 && board[i-1][j] == 0){
                        int value = Math.min(cache[i-1][j-1], cache[i-1][j]);
                        value = Math.min(value, cache[i][j-1]);
                        cache[i][j] = value + 1;
                        ans = Math.max(ans ,cache[i][j]);
                    }
                }
            }
        }
        System.out.println(ans);
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        board = new int[m + 1][n + 1]; cache = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                /* 캐시 초기화 */
                if(board[i][j] == 0)
                    cache[i][j] = 1;
            }
        }
    }
}
/*
5 6
0 1 0 0 1 0
1 0 0 0 0 0
0 0 0 0 0 0
1 0 0 0 0 1
0 0 0 0 0 1
 */