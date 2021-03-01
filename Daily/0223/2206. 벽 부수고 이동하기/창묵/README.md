[요구사항]

(1,1)에서 (N,M)의 위치까지 이동을 하려고하는데 벽을 최대 한개까지 깰 수 있다. 

이동할 수 있는 모든 경우 중 최단시간을 구해야합니다.


<br/>

저는 이 문제를 1000 * 1000 이라서 순간 BFS가 아닌 DP로 접근을 하였습니다.

알고보니 BFS를 이용하여 문제를 푸는 것이 조금 더 시간이 적게 걸릴 수 있었습니다.

일단 DP로 문제를 해결하였기 때문에 DP를 `dp[x][y][crash] = x,y에서 N,M까지 이동하는데 걸리는 최단시간`으로 정의를 하였습니다.

다만 벽을 한개까지 부술 수 있기 때문에 Crash의 값을 0 or 1로 정의하였습니다. 

crash가 0이면 벽을 아직 한개도 안 부순 상황이고 1이면 벽을 한개 부순 상황입니다.

이렇게 `dp[1][1][0]`과 `dp[1][1][1]` 중 작은 값을 답으로 출력하면 답이 나옵니다.

---

만약 BFS로 문제를 접근한다면 visit[] 배열을 visit[x][y][crash]를 만들어 놓고, bfs를 진행하여야 합니다.

1) map[x][y]가 벽이면 현재 Que에서 벽을 부술 수 있는 능력이 있으면 이동을 할 수 있습니다.

```java

if (map[x][y]== 1 && queNode.iscrash) {
            
}

```

2) map[x][y]가 벽이아니면 현재 Que에서 벽을 부술 수 있는 능력에따라 방문 여부를 확인하고 이동을 할 수 있습니다.

```java
if( map[x][y]==0 &&!visit[x][y][crash]){
    ...
}

```


bfs 연습하기에 정말 좋은 문제였다고 생각합니다.


<br/> <br/>

```java
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