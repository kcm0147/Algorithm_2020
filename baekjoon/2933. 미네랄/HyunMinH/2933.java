import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try(Scanner input = new Scanner(System.in)){
            int rowSize = input.nextInt();
            int colSize = input.nextInt();
            Solution sol = new Solution(rowSize, colSize);
            
            for(int row=rowSize-1; row >= 0; row--){
                String line = input.next();
                for(int col=0; col < colSize; col++){
                    sol.add(row, col, line.charAt(col));
                }
            }
     
            int numOfThrow = input.nextInt();
            for(int i=0; i<numOfThrow; i++){
                int row = input.nextInt();
                
                boolean left = true;
                if(i % 2 == 1) left = false;
                
                sol.throwStick(row-1, left);
            }
            
            sol.printBoard();
        }
    }
}

class Solution{
    private int [][] board;
    private int rowSize;
    private int colSize;
    private int[][] visited; // 노드가 없어진 후에 같은 클러스터끼리 같은 숫자를 갖게된다.
    private int numOfCluster;
    
    
    Solution(int rowSize, int colSize){
        this.rowSize = rowSize;
        this.colSize = colSize;
        this.board = new int[rowSize][colSize];
        this.visited = new int[rowSize][colSize];
    }
   
    void add(int row, int col, char value){
        if(value == 'X' || value == 'x') board[row][col] = 1;
        else board[row][col] = 0;
    }
    
    void throwStick(int row, boolean left){
        int col = findCol(row, left);
        if(col == -1) return;
        
        for(int[] v : visited) Arrays.fill(v, -1);
        numOfCluster = 0;
        
        dfs(row, col, 0);
        
        rePosition(row, col);
    }
    
    private int findCol(int row, boolean left){
        int startCol = (left == true ? 0 : colSize-1);
        int dCol = (left == true ? 1 : -1);
        int endCol = (left == true ? colSize : -1);
        
        int col = startCol;
        for(; col != endCol; col += dCol){
            if(board[row][col] == 1) break;
        }
        
        return col == endCol ? -1 : col;
    }
    
    private final int[] dr = {-1, 0, 0, 1};
    private final int[] dc = {0, -1, 1, 0};
    
    // 또한 해당 지점이 어느 클러스터에 해당하는지를 계산
    private void dfs(int row, int col, int cluster){
        visited[row][col] = cluster;
        
        for(int d=0; d<4; d++){
            int nr = row + dr[d];
            int nc = col + dc[d];
            
            if(nr < 0 || nr >= rowSize || nc < 0 || nc >= colSize) continue;
            if(board[nr][nc] == 0 || visited[nr][nc] >= 0) continue;
            
            if(cluster == 0) // root node
                dfs(nr, nc, ++numOfCluster);
            else // cluster
                dfs(nr, nc, cluster);
        }
    }
    
    private void rePosition(int row, int col){
        board[row][col] = 0;
        for(int cluster=1; cluster <= numOfCluster; cluster++){
            ifAttachedThenDown(cluster);
        }
    }
    
    private void ifAttachedThenDown(int cluster){
        while(true){
            for(int row=0; row<rowSize; row++){
                for(int col=0; col<colSize; col++){
                    if(visited[row][col] != cluster) continue;
                    if(row == 0 /*|| row == rowSize-1*/) return; // 바닥, 천장에 붙어있는 놈을 발견!
                    if((visited[row-1][col] != cluster && board[row-1][col] == 1)
                       /* || (visited[row+1][col] != cluster && board[row+1][col] == 1)*/)return; // 클러스터에 붙어있는 놈을 발견!
                }
            }

            for(int row=1; row<rowSize; row++){
                for(int col=0; col<colSize; col++){
                    if(visited[row][col] != cluster) continue;
                    board[row][col] = 0;
                    board[row-1][col] = 1;
                    visited[row][col] = -1;
                    visited[row-1][col] = cluster;
                }
            }
        }
    }
    
    void printBoard(){
        for(int row=rowSize-1; row >= 0; row--){
            for(int col=0; col < colSize; col++){
                if(board[row][col] == 0) System.out.print(".");
                else System.out.print("X");
            }
            System.out.println();
        }
    }
}