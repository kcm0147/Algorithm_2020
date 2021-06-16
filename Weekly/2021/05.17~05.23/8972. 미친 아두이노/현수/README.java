import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Q8972 {
    static int r, c, x, y;
    static int dx[] = {0, 1, 1, 1, 0, 0, 0, -1, -1, -1}, dy[] = {0, -1, 0, 1, -1, 0, 1, -1, 0, 1};
    static final int JONGSU = -54321, MAD = 1;
    static int board[][];
    static char[] command;
    static Queue<Point> arduino = new LinkedList<>();
    public static void main(String[] args) throws IOException {
        init();
        solution();
    }

    private static void solution() {
        int moveCnt = 0;
        boolean winFlag = true;
        for (int i = 0; i < command.length; i++) {
            // 이동 횟수 증가
            moveCnt += 1;
            // 더 이상 이동 불가능 - 미친 아두이노 만나는 경우
            if(!jongsuMove(command[i])){
                winFlag = false;
                break;
            }
            // 미친 아두이노와 종수의 아두이노가 만나는 경우
            if(!madArduinoMove()){
                winFlag = false;
                break;
            }
        }
        // 주어진 입력을 모두 수행했으면 성공
        if(winFlag){
            printBoard();
            return;
        }
        System.out.println("kraj " + moveCnt);
    }

    private static void printBoard() {
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (board[i][j] == 0) System.out.print('.');
                else if (board[i][j] == MAD)
                    System.out.print('R');
                else {
                    System.out.print('I');
                }
            }
            System.out.println();
        }
    }

    private static boolean madArduinoMove() {
        while(!arduino.isEmpty()){
            Point p = arduino.poll();
            int d = findShortest(p.x, p.y);
            int nx = p.x + dx[d], ny = p.y + dy[d];

            if(board[nx][ny] == JONGSU){
                return false;
            }
            board[nx][ny] += MAD;
            board[p.x][p.y] -= MAD;
        }

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if(board[i][j] == MAD) {
                    arduino.add(new Point(i, j));
                } else if(board[i][j] > MAD){
                    board[i][j] = 0;
                }
            }
        }
        return true;
    }

    private static int findShortest(int ax, int ay) {
        int minCost = 987654321, minDir = -1;
        for (int d = 1; d <= 9; d++) {
            int nx = ax + dx[d], ny = ay + dy[d];
            if(!isBorder(nx, ny)) continue;
            int nextCost = getDist(nx, ny, x, y);
            if(minCost > nextCost){
                minCost = nextCost;
                minDir = d;
            }
        }
        return minDir;
    }

    private static boolean jongsuMove(char c) {
        int d = c - '0';
        int nx = x + dx[d], ny = y + dy[d];

        if(board[nx][ny] == MAD){
            return false;
        }
        board[x][y] = 0;
        board[nx][ny] = JONGSU;
        x = nx; y = ny;
        return true;
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        board = new int[r][c];

        for(int i=0; i<r; ++i){
            char[] line = br.readLine().toCharArray();
            for (int j = 0; j < c; j++) {
                if(line[j] == 'I'){
                    x = i;
                    y = j;
                    board[i][j] = JONGSU;
                } else if(line[j] == 'R'){
                    board[i][j] = MAD;
                    arduino.add(new Point(i, j));
                } else {
                  board[i][j] = 0;
                }
            }
        }
        command = br.readLine().toCharArray();
    }

    static boolean isBorder(int x, int y){
        return (x >= 0 && x < r && y >=0 && y < c);
    }

    static int getDist(int x, int y, int a, int b){
        return Math.abs(x - a) + Math.abs(y - b);
    }
}