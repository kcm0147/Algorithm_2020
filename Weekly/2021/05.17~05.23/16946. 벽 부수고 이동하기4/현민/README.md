# 풀이

## 코드

```java
package week.week0517.solve16946;

import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;

public class Main {
    private static int ROW_SIZE;
    private static int COL_SIZE;
    private static int[][] map;

    private static int[] dr = { -1, 0, 0, 1 }; // up, left, right, down
    private static int[] dc = { 0, -1, 1, 0 };

    public static void main(String[] args) throws IOException {
        init();
        bfsAll();
        printMap();
    }

    private static void printMap() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for(int row=0; row<ROW_SIZE; row++){
            for(int col=0; col<COL_SIZE; col++){
                if(map[row][col] == 0) {
                    bw.write("0");
                }
                else{
                    Set<Integer> p = new HashSet<>();
                    for(int d=0; d<4; d++){
                        int nr=row + dr[d];
                        int nc=col + dc[d];
                        if(outOfBound(nr,nc) || map[nr][nc] == 1) continue;
                        p.add(group[nr][nc]);
                    }
                    int size = p.stream().mapToInt(v -> groupSize.get(v)).sum();
                    p.clear();
                    bw.write("" + ((size+1)%10));
                }
            }
            bw.write("\n");
        }
        bw.flush();
    }

    private static int[][] group;
    private static List<Integer> groupSize;

    private static void bfsAll(){
        int groupNum=0;
        for(int row=0; row<ROW_SIZE; row++){
            for(int col=0; col<COL_SIZE; col++){
                if(map[row][col] == 1 || group[row][col] != -1) continue;
                bfs(row,col, groupNum++);
            }
        }
    }

    private static void bfs(int row, int col, int groupNum){
        Point start = new Point(row, col);
        Queue<Point> q = new LinkedList<>();
        q.add(start);

        group[row][col] = groupNum;
        int size = 1;

        while (!q.isEmpty()){
            Point u = q.poll();

            for(int d=0; d<4; d++){
                int nr = u.x + dr[d];
                int nc = u.y + dc[d];
                Point v = new Point(nr, nc);

                if(outOfBound(nr, nc) || map[nr][nc] == 1 || group[nr][nc] != -1) continue;
                q.add(v);
                group[v.x][v.y] =groupNum;
                size++;
            }
        }

        groupSize.add(size);
    }

    private static boolean outOfBound(int row, int col){
        return row < 0 || row >= ROW_SIZE || col < 0 || col >= COL_SIZE;
    }

    private static void init(){
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        try {
            String[] sizes = bufferedReader.readLine().split(" ");
            ROW_SIZE = Integer.parseInt(sizes[0]);
            COL_SIZE = Integer.parseInt(sizes[1]);
            map = new int[ROW_SIZE][COL_SIZE];

            group = new int[ROW_SIZE][COL_SIZE];
            for(int[] g: group) Arrays.fill(g, -1);
            groupSize = new ArrayList<>();

            for(int row=0; row<ROW_SIZE; row++){
                String line = bufferedReader.readLine();
                for(int col=0; col<COL_SIZE; col++){
                    map[row][col] = line.charAt(col) - '0';
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```