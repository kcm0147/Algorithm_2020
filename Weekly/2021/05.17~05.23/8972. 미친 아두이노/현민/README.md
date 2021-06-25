# 풀이

## 코드

```java
package week.week0517.solve8972;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {
    private static Map<Aduino, Integer> michin = new HashMap();
    private static Aduino myAduino;
    private static int ROW_SIZE;
    private static int COL_SIZE;
    private static String command;

    public static void main(String[] args) {
        init();
        int result = game();
        if(result != -1) System.out.println("kraj " + result);
        else printMap();
    }

    private static int game(){
        for(int i=0; i<command.length(); i++){
            int dir = command.charAt(i) - '0' - 1;

            myAduino.move(dir);

            if(contactMichin()) return i+1;

            Map<Aduino, Integer> newKeyMap = new HashMap<>();
            michin.keySet().stream().forEach(m -> m.moveToward(myAduino.row, myAduino.col, newKeyMap));
            michin = newKeyMap;

            if(contactMichin()) return i+1;

            michin.values().removeIf(v -> v > 1);
        }

        return -1;
    }

    private static boolean contactMichin(){
        return michin.keySet().stream().filter(m -> myAduino.equals(m)).count() > 0;
    }

    private static void printMap(){
        for(int row=1; row<=ROW_SIZE; row++){
            for(int col=1; col<=COL_SIZE; col++){
                int finalRow = row, finalCol = col;

                if(myAduino.equals(finalRow, finalCol)) System.out.print("I");
                else if(michin.keySet().stream().filter(m -> m.equals(finalRow,finalCol)).count() > 0) System.out.print("R");
                else System.out.print(".");
            }
            if(row < ROW_SIZE) System.out.println();
        }
    }

    private static void init(){
        try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))){
            String[] sizes = bufferedReader.readLine().split(" ");
            ROW_SIZE = Integer.parseInt(sizes[0]);
            COL_SIZE = Integer.parseInt(sizes[1]);

            for(int r=0; r<ROW_SIZE; r++){
                String line = bufferedReader.readLine();
                for(int c=0; c<COL_SIZE; c++){
                    if(line.charAt(c) == 'R') michin.put(new Aduino(r+1, c+1), 1);
                    else if(line.charAt(c) == 'I') myAduino = new Aduino(r+1, c+1);
                }
            }
            command = bufferedReader.readLine();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}

class Aduino {
    private static final int dr[] = {1, 1, 1, 0, 0, 0, -1, -1, -1};
    private static final int dc[] = {-1, 0, 1, -1, 0, 1, -1, 0 ,1};

    int row;
    int col;

    void move(int dir){
        row = row + dr[dir];
        col = col + dc[dir];
    }

    void moveToward(int row, int col, Map<Aduino, Integer> newAduionos){
        int dir = getCloserDir(row, col);
        move(dir);

        newAduionos.putIfAbsent(this, 0);
        newAduionos.compute(this, (k,v) -> v+1);
    }

    private int getCloserDir(int row, int col){
        int minDistance = Integer.MAX_VALUE / 2;
        int minDir = -1;
        for(int d=0; d<dr.length; d++){
            if(d == 4) continue; // 움직여야함!
            int nr = this.row + dr[d];
            int nc = this.col + dc[d];
            int dist = Math.abs(row - nr) + Math.abs(col - nc);
            if(minDistance > dist){
                minDistance = dist;
                minDir = d;
            }
        }

        return minDir;
    }

    public Aduino(int row, int col) {
        this.row = row;
        this.col = col;
    }

    @Override
    public boolean equals(Object obj) {
        Aduino other = (Aduino) obj;
        return (this.row == other.row) && (this.col == other.col);
    }

    @Override
    public int hashCode() {
        return 31 * Integer.hashCode(row) + Integer.hashCode(col);
    }

    public boolean equals(final int row, final int col){
        return (this.row == row) && (this.col == col);
    }
}
```