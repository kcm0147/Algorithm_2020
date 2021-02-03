import java.util.*;

public class Sol17069{
    private static Board board;
    private static Solve sol;
    public static void main(String[] args){
        try(Scanner in = new Scanner(System.in)){
            int size = in.nextInt();
            board = new Board(size);
            for(int y=0; y<size; y++)
                for(int x=0; x<size; x++)
                    board.addState(x, y, in.nextInt());
        }
        
        sol = Solve.createInstance(board);
        System.out.println(sol.countReachEnd());
    }
}

class Solve{
    private int cache[][][];
    private final Board board;
    private static Solve INSTANCE = null;

    private final int dx[] = { 0, -1, -1 };
    private final int dy[] = { -1, -1, 0 };

    int countReachEnd(){
        int x = board.getSize() - 1; int y = board.getSize() - 1;
        return countReachAble(x, y, 0) + countReachAble(x, y, 1) + countReachAble(x, y, 2);
    }

    private int countReachAble(int x, int y, int angle){
        if(board.canSet(x, y, x+dx[angle], y+dy[angle]) == false) return 0;
        if(x == 1 && y == 0 && x+dx[angle] == 0 && y+dy[angle] == 0) return 1;

        if(cache[x][y][angle] != -1) return cache[x][y][angle];
        
        // push
        int nx = x + dx[angle]; int ny = y + dy[angle];
        int result = countReachAble(nx, ny, angle);

        // push and turn
        if(angle != 1)
            result += countReachAble(nx, ny, 1);
        else
            result += countReachAble(nx, ny, 0) + countReachAble(nx, ny, 2);

        cache[x][y][angle] = result;
        return result;
    }

    private Solve(Board board) { 
        this.board = board; 
        this.cache = new int[board.getSize()][board.getSize()][dx.length];
        for(int[][] c : cache)
            for(int[] c2 : c) Arrays.fill(c2, -1);
    }

    static Solve createInstance(Board board){
        if(INSTANCE == null) INSTANCE = new Solve(board);
        return INSTANCE;
    }
}


class Board{
    private int board[][];

    int getSize() { return this.board.length; }

    Board(int size) { this.board = new int[size][size]; }
    
    void addState(int x, int y, int s) { this.board[x][y] = s; }

    boolean canSet(int x1, int y1, int x2, int y2){
        int startX = Integer.min(x1,x2); int startY = Integer.min(y1, y2);
        int endX = Integer.max(x1, x2); int endY = Integer.max(y1,y2);

        for(int x = startX; x <= endX; x++){
            for(int y = startY; y <= endY; y++){
                if(checkBoundary(x, y) == false) return false;
                if(board[x][y] == 1) return false;
            }
        }
        return true;
    }

    private boolean checkBoundary(int x, int y){
        return (x >= 0 && x < board.length) && (y >= 0 && y < board.length);
    }
} 