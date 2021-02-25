

```java

public class Main {

    static int map[][];
    static int N,M,result;
    static int visit[][];
    static int dir[][] ={{0,1},{1,0},{-1,0},{0,-1}};
    static Queue<Pair> q;
    public static void main(String[] args) throws IOException{

        init();
        result=0;
        q=new LinkedList<Pair>();
        while(true) {
            q.add(new Pair(0,0));
            visit=new int[N][M];
            BFS();
            result++;
            if(!findCheeze()){
                break;
            }
        }

        System.out.println(result);
    }

    public static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N=stoi(st.nextToken());
        M=stoi(st.nextToken());

        visit=new int[N][M];
        map=new int[N][M];


        for(int i=0;i<N;i++){
            st=new StringTokenizer(br.readLine());
            for(int j=0;j<M;j++){
                map[i][j]=stoi(st.nextToken());
            }
        }
    }

    public static boolean findCheeze(){

        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                if(map[i][j]!=0){
                    return true;
                }
            }
        }

        return false;
    }

    public static void BFS() {
        while(!q.isEmpty()) {
            Pair temp = q.poll();
            for(int i=0;i<4;i++) {
                int nx = temp.x+dir[i][0];
                int ny = temp.y+dir[i][1];

                if(nx<0||ny<0||nx>=N||ny>=M) {
                    continue;
                }

                if(map[nx][ny]==0&&visit[nx][ny]==0) {
                    visit[nx][ny]=1;
                    q.add(new Pair(nx,ny));
                }
                if(map[nx][ny]==1) {
                    visit[nx][ny]++;
                    if(visit[nx][ny]>=2) {
                        map[nx][ny]=0;
                    }
                }
            }

        }
    }

    public static int stoi(String input){
        return Integer.parseInt(input);
    }

}

class Pair{
    int x;
    int y;
    Pair(int x, int y){
        this.x=x;
        this.y=y;
    }
}


```