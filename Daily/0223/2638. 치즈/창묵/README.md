개인적으로 문제에서 가장 해결하기 어려웠던 것이 '외부공기와 내부공기를 나누는 것'이 었습니다.

문제에서 가장자리에는 치즈가 없다고 했기 때문에, 0,0은 무조건 치즈의 외부공기가 된다는 것을 캐치해야합니다.

외부공기와 내부공기를 나누는 방법은 0,0에서 BFS를 진행하여 0을 만나게되면 외부공기로 판단을 해주고 que에 넣어주고

1을 만나게되면 치즈이기 떄문에 que에 넣지 않습니다.

```java

if(map[nx][ny]==0&&visit[nx][ny]==0) {
    visit[nx][ny]=1;
    q.add(new Pair(nx,ny));
}

```

외부공기가 BFS를 진행하면서 치즈를 만나게되는데, 이때 치즈를 방문하는 횟수(=치즈와 면적이 맞닿는 횟수)가 2회이상이면 치즈를 제거해줍니다.

```java
if(map[nx][ny]==1) {
    visit[nx][ny]++;
    if(visit[nx][ny]>=2) {
        map[nx][ny]=0;
    }
}

```

이렇게 치즈가 없어질때까지 연산을 반복진행해주면 됩니다.



<br/>



<br/> <br/>

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