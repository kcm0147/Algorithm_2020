import java.util.*;

public class Sol15999 {
    private static final int DIVIDE_NUM = 1000000007;

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        int rowSize = input.nextInt();
        int colSize = input.nextInt();

        boolean[][] visited = new boolean[rowSize][colSize];
        for (boolean[] v : visited)
            Arrays.fill(v, false);

        char[][] board = new char[rowSize][colSize];
        for (int r = 0; r < rowSize; r++) {
            String line = input.next();
            for (int c = 0; c < colSize; c++) {
                board[r][c] = line.charAt(c);
            }
        }

        int numOfBoundary = 0;
        for(int r=0; r<rowSize; r++){
            for(int c=0; c<colSize; c++){
                if(visited[r][c]) continue;
                if(isBoundary(board, r, c)){
                    numOfBoundary++;
                    visited[r][c] = true;
                }
            }
        }

        int result = 1;
        for(int i=rowSize * colSize - numOfBoundary; i>0; i--)
            result = (result * 2) % DIVIDE_NUM;

        System.out.println(result);
    }

    private static boolean isBoundary(char[][] board, int r, int c){
        int[] dr = { -1, 0, 1, 0};  int[] dc = { 0, 1, 0, -1}; 

        for(int d=0; d<dr.length; d++){
            int nr = r + dr[d]; int nc = c + dc[d];
            if(outOfBoard(nr, nc, board.length, board[0].length)) continue;
            if(board[r][c] != board[nr][nc]) return true;
        }
        return false;
    }

    private static boolean outOfBoard(int r, int c, int rowSize, int colSize){
        return r < 0 || r >= rowSize || c < 0 || c >= colSize;
    }
}