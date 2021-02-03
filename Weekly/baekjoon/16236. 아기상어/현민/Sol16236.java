import java.util.*;

public class Sol16236 {
    private static Board board;
    private static Shark shark;
    private static boolean[][] visited;

    public static void main(String[] args) {
        init();
        System.out.println(solve());
    }

    private static void init() {
        try (Scanner input = new Scanner(System.in)) {
            int size = input.nextInt();
            board = new Board(size);
            visited = new boolean[size][size];

            for (int y = 0; y < size; y++) {
                for (int x = 0; x < size; x++) {
                    int state = input.nextInt();
                    if (state == 9) shark = new Shark(x, y, 2, 0);
                    else if(state != 0) board.setState(x, y, state);
                }
            }
        }
    }

    private static int solve(){
        int ret=0;
        int time = bfs();
        while(time != -1){
            ret += time;
            time = bfs();
        }
        return ret;
    }

    private static int bfs() {
        PriorityQueue<Pos> pq = new PriorityQueue<>();

        Pos start = new Pos(shark.getX(), shark.getY(), 0);
        pq.add(start);
        for(int x=0; x<visited.length; x++){
            for(int y=0; y<visited[0].length; y++)
                visited[x][y] = false;
        }
        visited[start.x][start.y] = true;

        while (!pq.isEmpty()) {
            Pos u = pq.poll();
            if(board.canEat(u, shark.getSize())){
                shark.moveAndEat(board, u);
                return u.countMove;
            }

            for(int i=0; i<Pos.NUM_DIR; i++){
                Pos v = u.move(i);
                if(board.canSet(v, shark.getSize()) == false) continue;
                if(visited[v.x][v.y]) continue;
                pq.add(v);
                visited[v.x][v.y] = true;
            }
        }

        return -1;
    }
}

class Pos implements Comparable<Pos>{
    private static int[] dx = { 0, -1, 1, 0 }; // UP, LEFT, RIGHT, DOWN
    private static int[] dy = { -1, 0, 0, 1 };
    static final int NUM_DIR = 4;

    final int x;
    final int y;
    final int countMove;

    Pos(int x, int y, int countMove) { this.x = x; this.y = y; this.countMove = countMove; }

    Pos move(int dir){
        return new Pos(x + dx[dir], y + dy[dir], countMove+1);
    }

    @Override public boolean equals(Object obj) {
        Pos pos = (Pos) obj;
        return x == pos.x && y == pos.y;
    }

    @Override public int hashCode() {
        return Integer.hashCode(x) * 31 + Integer.hashCode(y);
    }

    @Override public int compareTo(Pos o) {
        return Comparator.comparingInt((Pos pos) -> pos.countMove).thenComparingInt((Pos pos) -> pos.y)
        .thenComparingInt((Pos pos) -> pos.x).compare(this, o);
    }
}

class Shark {
    private int x;
    private int y;
    private int size;
    private int eatFish;

    Shark(int x, int y, int size, int eatFish) {
        this.x = x;   this.y = y;
        this.size = size;   this.eatFish = eatFish;
    }

    void moveAndEat(Board board, Pos p){
        this.x = p.x; this.y = p.y;
        plusEatFish();
        board.setState(p.x, p.y, 0);
    }

    private void plusEatFish() {
        eatFish++;
        if (eatFish == size) {
            size++;
            eatFish = 0;
        }
    }

    int getX() { return x; }
    int getY() { return y; }
    int getSize() { return size; }
}

class Board {
    private int[][] board;

    boolean canSet(Pos pos, int sharkSize) {
        if (isOutOfBound(pos.x, pos.y)) return false;
        if (sharkSize < board[pos.x][pos.y]) return false;
        return true;
    }

    boolean canEat(Pos pos, int sharkSize){
        return board[pos.x][pos.y] > 0 && sharkSize > board[pos.x][pos.y];
    }

    void setState(int x, int y, int state) { board[x][y] = state; }

    Board(int size) {
        this.board = new int[size][size];
        for (int[] b : board)
            Arrays.fill(b, 0);
    }

    private boolean isOutOfBound(int x, int y) {
        return x < 0 || x >= board.length || y < 0 || y >= board[0].length;
    }
}