

```java

public class Main {


    static int N,M,cnt,minValue;
    static int[][] pos;
    static int[][] map;
    static int[][][] cost;

    static int[][] dir={{0,1},{1,0},{-1,0},{0,-1}};

    static boolean[][] visit;

    static final int INF = 100000;


    public static void main(String[] args) throws IOException {

        init();
        if (calc()) {
            System.out.println(minValue);
            System.out.println(cnt);
        } else {
            System.out.println(-1);
        }

    }

    public static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N=stoi(st.nextToken());
        M=stoi(st.nextToken());

        map=new int[N][M];
        cost=new int[N][M][3];
        pos=new int[3][2];


        for(int i=0;i<N;i++){
            String cur=br.readLine();
            for(int j=0;j<M;j++){
                map[i][j]=stoi(Character.toString(cur.charAt(j)));
            }
        }

        for(int i=0;i<3;i++){
            st=new StringTokenizer(br.readLine());
            for(int j=0;j<2;j++){
                pos[i][j]=stoi(st.nextToken())-1;
            }
        }

        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                Arrays.fill(cost[i][j],INF);
            }
        }

    }

    public static boolean calc(){

        cnt=0;
        minValue= Integer.MAX_VALUE;
        for(int i=0;i<3;i++){
            visit=new boolean[N][M];
            bfs(pos[i][0],pos[i][1],i);
        }

        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                if(cost[i][j][0]==INF || cost[i][j][1]==INF || cost[i][j][2]==INF) continue;

                int max=Math.max(Math.max(cost[i][j][0],cost[i][j][1]),cost[i][j][2]);

                if(minValue>max){
                    minValue=max;
                    cnt=1;
                }
                else if(minValue==max){
                    cnt++;
                }
            }
        }

        if(minValue==Integer.MAX_VALUE){
            return false;
        }
        else
            return true;
    }

    public static void bfs(int x,int y,int who){
        Queue<Pair> que = new LinkedList<>();
        que.add(new Pair(x,y));
        visit[x][y]=true;
        cost[x][y][who]=0;

        while(!que.isEmpty()){
            Pair cur =que.poll();

            for(int i=0;i<4;i++){
                int nx=cur.x+dir[i][0];
                int ny=cur.y+dir[i][1];

                if(!checkBound(nx,ny)) continue;

                if(!visit[nx][ny]){
                    visit[nx][ny]=true;
                    cost[nx][ny][who]=Math.min(cost[nx][ny][who],cost[cur.x][cur.y][who]+1);
                    que.add(new Pair(nx,ny));
                }
            }
        }


    }

    public static boolean checkBound(int nx,int ny){
        if(nx<0 || nx>=N || ny<0 || ny>=M || map[nx][ny]==1) return false;

        return true;
    }





    public static int stoi(String input) {
        return Integer.parseInt(input);
    }
}

class Pair{
    int x,y;

    Pair(int x,int y){
        this.x=x;
        this.y=y;
    }
}





```