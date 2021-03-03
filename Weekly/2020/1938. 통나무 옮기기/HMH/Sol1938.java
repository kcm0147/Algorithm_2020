import java.io.IOException;
import java.util.*;

public class Sol1938{
    private static Board board;
    private static Status start;
    private static Status end;
    public static void main(String[] args) throws IOException{
        input();
        System.out.println(bfs());
    }

    private static void input(){
        try(Scanner in = new Scanner(System.in)){
            int size = in.nextInt();
            board = new Board(size);
            start = new Status();  end = new Status();

            for(int y=0; y<size; y++){
                String line = in.next();
                for(int x=0; x<size; x++){
                    if(line.charAt(x) == 'B') start.addPos(new Pos(x,y));
                    else if(line.charAt(x) == 'E') end.addPos(new Pos(x,y));
                    else if(line.charAt(x) == '1') board.addTree(new Pos(x,y));
                }
            }
        }
    }

    private static int bfs(){
        Map<Status,Integer> distance = new HashMap<Status,Integer>();
        distance.put(start, Integer.valueOf(0));

        Queue<Status> q = new LinkedList<>();
        q.add(start);
        while(!q.isEmpty()){
            Status cur = q.poll(); int d = distance.get(cur);
            if(cur.equals(end)) return d;

            for(Status next : cur.getAdjList(board)){
                if(distance.putIfAbsent(next, Integer.valueOf(d+1)) != null) continue;
                q.add(next);
            } 
        }

        return 0;
    }
}


class Status{
    private final List<Pos> positions = new ArrayList<>();
    private final static Status emptyStatus = new Status();
    
    public void addPos(Pos pos) { this.positions.add(pos); } 

    List<Status> getAdjList(Board board){
        List<Status> result = new ArrayList<>();
        for(Dir dir: Dir.values()){
            Status next = dir.apply(this, board);
            if(next != emptyStatus) result.add(next);
        }
        return result;
    }

    @Override public boolean equals(Object o){
        if(o == this) return true;
        if(!(o instanceof Status)) return false;
        Status status = (Status) o;
        return positions.equals(status.positions);
    }

    @Override public int hashCode(){
        return positions.hashCode();
    }

    enum Dir{
        UP("up", 0,-1), DOWN("down", 0,1), LEFT("left", -1,0), RIGHT("right", 1,0), TURN("turn", 0,0);
        private String dir;
        int dx;
        int dy;
        private Dir(String dir, int dx, int dy) {this.dir = dir; this.dx = dx; this.dy = dy;}

        Status apply(Status status, Board board){
            Status next;
            if(dir.equals("turn")){
                if(!board.canTurn(status.positions.get(1))) return Status.emptyStatus;
                next = turn(status);
            }else{
                next = push(status);
            }
            
            if(board.canSet(next.positions)) return next;
            return Status.emptyStatus;
        }

        private Status push(Status status){
            Status next = new Status();
            for(Pos pos : status.positions){
                next.addPos(new Pos(pos.x+dx, pos.y+dy));
            }
            return next;
        }

        private Status turn(Status status){
            Status next = new Status();

            int mx = status.positions.get(1).x; 
            int my = status.positions.get(1).y;
            if(status.positions.get(0).x == status.positions.get(1).x){
                // 세로 -> 가로
                next.addPos(new Pos(mx-1, my));
                next.addPos(new Pos(mx, my));
                next.addPos(new Pos(mx+1, my));
            }else{
                next.addPos(new Pos(mx, my-1));
                next.addPos(new Pos(mx, my));
                next.addPos(new Pos(mx, my+1));
            }
            return next;
        }
    }
}

class Board{
    private Set<Pos> trees = new HashSet<>();
    private int size;

    Board(int size) { this.size = size; }

    void addTree(Pos tree) { trees.add(tree); }

    boolean canSet(List<Pos> positions){
        for(Pos pos:positions){
            if(checkBoundary(pos.x, pos.y) == false) return false;
            if(trees.contains(new Pos(pos.x, pos.y))) return false;
        }
        return true;
    }

    boolean canTurn(Pos middle){
        for(int x=middle.x-1; x<=middle.x+1; x++){
            for(int y=middle.y-1; y<=middle.y+1; y++){
                if(checkBoundary(x, y) && trees.contains(new Pos(x,y)))
                    return false;
            }
        }
        return true;
    }
    
    private boolean checkBoundary(int x, int y) {
        return (x >= 0 && x < size) && (y >= 0 && y < size);
    }
}

class Pos{
    int x;
    int y;

    Pos(int x, int y) {this.x = x; this.y = y;}

    @Override public boolean equals(Object o){
        if(o == this) return true;
        if(!(o instanceof Pos)) return false;
        Pos p = (Pos)o;
        return x == p.x && y == p.y;
    }

    @Override public int hashCode(){
        return 31 * Integer.hashCode(x) + Integer.hashCode(y);
    }
}