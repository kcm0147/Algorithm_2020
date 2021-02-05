package solve1113;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.init();
        System.out.println(solution.solve());
    }
}

class Solution{
    private int[] dr = {-1, 1, 0, 0}; // up, down, left, right
    private int[] dc = {0, 0, -1, 1};

    private int[][] board;
    private int[][] waterBoard;
    private boolean[][] visited;

    private int rowSize;
    private int colSize;

    void init(){
        try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))){
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            rowSize = Integer.parseInt(stringTokenizer.nextToken());
            colSize = Integer.parseInt(stringTokenizer.nextToken());

            board = new int[rowSize][colSize];
            for(int row=0; row<rowSize; row++){
                String line = new StringTokenizer(bufferedReader.readLine()).nextToken();
                for(int col=0; col<colSize; col++){
                    board[row][col] = line.charAt(col) - '0';
                }
            }

            waterBoard = new int[rowSize][colSize];
            for(int[] w: waterBoard) Arrays.fill(w, 0);

            visited = new boolean[rowSize][colSize];
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    int solve(){
        dfsAll();
        int ret=0;
        for(int[] w : waterBoard) ret += Arrays.stream(w).sum();
        return ret;
    }

    private void initWaterBoardIfVisited(){
        for(int row=1; row<rowSize-1; row++){
            for(int col=1; col<colSize-1; col++){
                if(visited[row][col]) waterBoard[row][col] = 0;
            }
        }
    }

    private void dfsAll(){
        for(int row=1; row<rowSize-1; row++){
            for(int col=1; col<colSize-1; col++){
                for(int height=9; height>board[row][col]; height--) {
                    for(boolean[] v: visited) Arrays.fill(v, false);
                    if (dfs(row, col, height)) break;
                    initWaterBoardIfVisited();
                }
            }
        }
    }

    private boolean dfs(int row, int col, int heightAfterFill){
        if(isBoundary(row, col)) return false;
        if(waterBoard[row][col] > 0) return true;

        waterBoard[row][col] = heightAfterFill - board[row][col];
        visited[row][col] = true;

        for(int dir=0; dir<4; dir++){
            int nextRow = row + dr[dir];
            int nextCol = col + dc[dir];

            if(visited[nextRow][nextCol]) continue;
            if(board[nextRow][nextCol] >= heightAfterFill) continue;

            if(waterBoard[nextRow][nextCol] > 0 || dfs(nextRow,nextCol,heightAfterFill) == false){
                waterBoard[row][col] = 0;
                return false;
            }
        }

        return true;
    }

    private boolean isBoundary(int row, int col) {
        return row == 0 || row == rowSize-1 || col == 0 || col == colSize-1;
    }
}