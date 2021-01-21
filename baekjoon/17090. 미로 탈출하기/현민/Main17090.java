import java.util.Scanner;

public class Main17090 {
    public static void main(String[] args){
        Solution sol = new Solution();
        sol.init();
        System.out.println(sol.numberOfCanEscape());
    }
}

class Solution{
    private final int MAX_LINE_SIZE = 500;
    private int rowSize;
    private int colSize;
    private int[][] cache = new int[MAX_LINE_SIZE][MAX_LINE_SIZE];
    private int[][] dir = new int[MAX_LINE_SIZE][MAX_LINE_SIZE];
    private final int[] dr = {-1, 0, 1, 0}; // UP RIGHT DOWN LEFT
    private final int[] dc = {0, 1, 0, -1};

    void init(){
        try(Scanner scanner = new Scanner(System.in)){
            rowSize = scanner.nextInt();
            colSize = scanner.nextInt();
            for(int r=0; r<rowSize; r++){
                String line = scanner.next();
                for(int c=0; c< colSize; c++){
                    dir[r][c] = toInt(line.charAt(c));
                    cache[r][c] = -1;
                }
            }
        }
    }

    private int toInt(char dir){
        switch(dir){
            case 'U' : return 0;
            case 'R' : return 1;
            case 'D' : return 2;
            case 'L' : return 3;
            default : throw new AssertionError("cannot match");
        }
    }

    /*
        성공 시 : 1
        실패 시 : 0
        방문 하지 않았았으면 -1
    */
    private int canEscape(int row, int col){
        if(outOfBoundary(row, col)) return 1;
       
        if(cache[row][col] != -1) return cache[row][col];

        cache[row][col] = 0;
        int d = dir[row][col];
        int nr = row + dr[d];
        int nc = col + dc[d];

        cache[row][col] = canEscape(nr, nc);
        return cache[row][col];
    }

    private boolean outOfBoundary(int row, int col){
        return row < 0 || row >= rowSize || col < 0 || col >= colSize;
    }

    int numberOfCanEscape(){
        int ret = 0;
        for(int r=0; r<rowSize; r++){
            for(int c=0; c<colSize; c++){
                ret += canEscape(r, c);
            }
        }
        return ret;
    }
}