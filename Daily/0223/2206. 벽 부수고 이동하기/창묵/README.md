```java

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N,M;
    static int[][] map;
    static int[][][] dp;
    static final int INF = 9999999;
    static int[][] dir={{0,1},{1,0},{0,-1},{-1,0}};


    public static void main(String[] args) throws IOException{
        init();
        int answer=calc(0,0,0);

        if (answer == INF) {
            System.out.println(-1);
        } else {
            System.out.println(answer);
        }
    }

    public static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N=stoi(st.nextToken());
        M=stoi(st.nextToken());
        map=new int[N][M];
        dp=new int[N][M][2];

        for(int i=0;i<N;i++){
            String cur =br.readLine();
            for(int j=0;j<M;j++){
                map[i][j]=stoi(Character.toString(cur.charAt(j)));
            }
        }

        for(int i=0;i<N;i++) {
            for(int j=0;j<M;j++)
                Arrays.fill(dp[i][j], -1);
        }

    }

    public static int calc(int x,int y,int crash){
        if(x==N-1 && y==M-1)
            return 1;
        if(dp[x][y][crash]!=-1) return dp[x][y][crash];

        dp[x][y][crash]=INF;

        for(int i=0;i<4;i++){
            int nx=x+dir[i][0];
            int ny=y+dir[i][1];

            if(checkBound(nx,ny)) continue;

            if(crash==0){
                if(map[nx][ny]==1) {
                    dp[x][y][crash] = Math.min(dp[x][y][crash], calc(nx, ny,1)+1);
                }
                else{
                    dp[x][y][crash] = Math.min(dp[x][y][crash], calc(nx, ny,0)+1);
                }
            }
            else{
                if(map[nx][ny]==0){
                    dp[x][y][crash]=Math.min(dp[x][y][crash],calc(nx,ny,1)+1);
                }
            }
        }

        return dp[x][y][crash];
    }


    public static boolean checkBound(int x,int y){

        if(x<0 || x>=N || y<0 || y>=M)
            return true;
        return false;
    }

    public static int stoi(String input){
        return Integer.parseInt(input);
    }
}
```


```java

BFS로 풀수도 있는데, BFS의 브루트앤포스나 백트랙킹을 사용하면 더 좋다. ( 탐색할려는곳에 그 전보다 이미 count가 더 크면 굳이 탐색필요 x )

for (int dir = 0; dir < 4; dir++) {
                int ny = p.y + dy[dir];
                int nx = p.x + dx[dir];

                if (ny < 0 || nx < 0 || ny >= n || nx >= m) continue;

                if (visited[ny][nx] <= p.count) continue;

                if (map[ny][nx] == 0) {
                    visited[ny][nx] = p.count;
                    q.offer(new Place(ny, nx, p.dist + 1, p.count));

                } else {
                    if (p.count == 0) {
                        visited[ny][nx] = p.count + 1;
                        q.offer(new Place(ny, nx, p.dist + 1, p.count + 1));
                    }
                }

            }
        }

```
