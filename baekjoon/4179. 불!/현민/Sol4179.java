import java.util.*;

public class Sol4179 {
    private static Board board;
    private static Board nextBoard;
    private static Pos start;
    private static boolean[][] visited;

    public static void main(String[] args){
        try(Scanner input = new Scanner(System.in)){
            int row = input.nextInt(); int col = input.nextInt();
            board = new Board(col, row);

            for(int y=0; y<row; y++){
                String line = input.next();
                for(int x=0; x<col; x++){
                    board.addState(x, y, line.charAt(x));
                    if(line.charAt(x) == 'J') start = new Pos(x,y,0);
                }
            }
            visited = new boolean[col][row];
            nextBoard = new Board(board).next();
        }

        int result = bfs();
        if(result == -1) 
            System.out.println("IMPOSSIBLE");
        else 
            System.out.println(result);
    }

    private static int bfs(){
        Queue<Pos> q = new LinkedList<>();
        q.add(start);
        
        for(boolean[] v : visited) Arrays.fill(v, false);
        visited[start.x][start.y] = true;

        while(!q.isEmpty()){
            Pos u = q.poll();

            if(board.getTime() != u.time){
                board = board.next();
                nextBoard = nextBoard.next();
            }
            
            for(Pos v : u.getAdjList(nextBoard)){
                if(board.isFinished(v.x, v.y)) return v.time;
                if(visited[v.x][v.y]) continue;
                q.add(v);
                visited[v.x][v.y] = true;
            }
        }

        return -1;
    }
}

class Pos{
    private static int[] dy = { -1, 0, 1, 0 };
    private static int[] dx = { 0, 1, 0, -1 };

    final int x;
    final int y;
    final int time;

    Pos(int x, int y, int time) { this.x = x; this.y = y; this.time = time; }

    List<Pos> getAdjList(Board board){
        List<Pos> ret = new ArrayList<>();

        for(int d=0; d<dx.length; d++){
            int nx = x + dx[d]; int ny = y + dy[d];
            if(board.canMove(nx, ny)){
                ret.add(new Pos(nx, ny, time + 1));
            }
        }

        return ret;
    }

    int getTime() { return this.time; }
}

class Board{
    private int time = 0;
    private State[][] board;
    private Queue<Pos> boundaryFires = new LinkedList<>();

    private enum State { EMPTY, BLOCK, FIRE }

    Board(int colSize, int rowSize){
        board = new State[colSize][rowSize];
    }

    Board(Board b){
        board = b.board.clone();
        boundaryFires.addAll(b.boundaryFires);
        time = b.time;
    }

    void addState(int x, int y, char state){
        switch(state){
            case '#' : board[x][y] = State.BLOCK; break;
            case 'F' : board[x][y] = State.FIRE; 
                boundaryFires.add(new Pos(x,y,time)); break;
            default : board[x][y] = State.EMPTY; break;
        }
    }

    Board next(){
        while(!boundaryFires.isEmpty()){
            Pos fire = boundaryFires.peek();
            if(time != fire.getTime()) break; // boundary만 남으면 끝낸다.
            boundaryFires.poll();

            for(Pos next : fire.getAdjList(this)){
                if(isFinished(next.x, next.y)) continue;
                board[next.x][next.y] = State.FIRE;
                boundaryFires.add(next);
            }
        }

        time++;
        return this;
    }

    boolean canMove(int x, int y){
        return isFinished(x, y) ? true : board[x][y] == State.EMPTY;
    }

    boolean isFinished(int x, int y){
        return x < 0 || x >= board.length || y < 0 || y >= board[0].length;
    }

    int getTime() { return this.time; }
}