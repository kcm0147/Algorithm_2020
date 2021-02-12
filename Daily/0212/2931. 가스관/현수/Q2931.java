import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;


public class Q2931 {
    static int sx, sy, ansR, ansC, r, c, dx[] = {-1, 0, 1, 0}, dy[] = {0, 1, 0, -1};
    static char board[][], pipe[] = {'|', '-', '+', '1', '2', '3', '4'}, ans = ' ';
    static boolean visited[][];

    public static void main(String[] args) throws IOException {
        init();
        solution(sx, sy);
    }

    private static void solution(int x, int y) {
        for (int d = 0; d < 4; d++) {
            int nx = x + dx[d], ny = y + dy[d];
            if(!isBorder(nx, ny) || board[nx][ny] == '.') continue;
            if(dfs(nx, ny)) break;
        }
    }

    private static boolean dfs(int x, int y) {
        char deli = board[x][y];
        if(!isBorder(x, y) || visited[x][y]) return false;
        if(deli == '.'){
            ansR = x;
            ansC = y;
            ans = findPipe(x, y);
            System.out.println(ansR + " " + ansC + " " + ans);
            return true;
        }
        visited[x][y] = true;
        if(deli == '|'){
            if(dfs( x - 1, y)) return true;
            if(dfs( x + 1, y)) return true;
        }
        else if(deli == '-'){
            if(dfs( x, y - 1)) return true;
            if(dfs( x, y + 1)) return true;
        }
        else if(deli == '+'){
            for (int d = 0; d < 4; d++)
                if(dfs(x + dx[d], y + dy[d]))
                    return true;
        }
        else if(deli == '1'){
            if(dfs( x, y + 1)) return true;
            if(dfs( x + 1, y)) return true;
        }
        else if(deli == '2'){
            if(dfs( x - 1, y)) return true;
            if(dfs( x, y + 1)) return true;
        }
        else if(deli == '3'){
            if(dfs( x, y - 1)) return true;
            if(dfs( x - 1, y)) return true;
        }
        else if(deli == '4'){
            if(dfs(x + 1, y)) return true;
            if(dfs(x, y - 1)) return true;
        }
        return false;
    }

    private static char findPipe(int x, int y) {
        boolean up, right, down, left, flag = true;
        up = right = down = left = false;

        for (int d = 0; d < 4; d++) {
            int nx = x + dx[d], ny = y + dy[d];
            if(!isBorder(nx, ny)) continue;
            char deli = board[nx][ny];
            if(d == 0){
                if(deli == '|' || deli == '+' || deli == '1' || deli == '4')
                    up = true;
            }
            else if(d == 1){
                if(deli == '-' || deli == '+' || deli == '3' || deli == '4')
                    right = true;
            }
            else if(d == 2){
                if(deli == '|' || deli == '+' || deli == '2' || deli == '3')
                    down = true;
            }
            else if(d == 3){
                if(deli == '-' || deli == '+' || deli == '1' || deli == '2')
                    left = true;
            }
        }

        if(up && left && down && right) return '+';
        if(up && down) return '|';
        if(left && right) return '-';
        if(down && right) return '1';
        if(up && right) return '2';
        if(up && left) return '3';
        if(down && left) return '4';
        return 'X';
    }

    private static boolean isBorder(int x, int y) {
        return (x >= 1 && x <= r && y >= 1 && y <= c);
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        board = new char[r + 1][c + 1];
        visited = new boolean[r + 1][c + 1];
        for (int i = 0; i <= r; i++)
            Arrays.fill(board[i], '.');
        for (int i = 1; i <= r; i++) {
            String line = br.readLine();
            for (int j = 1; j <= c; j++) {
                board[i][j] = line.charAt(j-1);
                if(board[i][j] == 'M'){
                    sx = i;
                    sy = j;
                }
            }
        }
    }
}