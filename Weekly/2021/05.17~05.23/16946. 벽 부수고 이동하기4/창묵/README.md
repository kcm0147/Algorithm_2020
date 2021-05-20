* BFS

<br/>

벽 부수고 이동하기4 문제는 벽을 부셔서 이동하는 문제이기 보단

문제에서 요구하는 것처럼 벽마다 부셨을 때 이동할 수 있는 칸수를 구하는 것입니다.

모든 벽을 하나씩 부술 때마다 BFS를 사용한다면 시간초과가 발생할 것이라고 생각을 했습니다.

그래서 다음과 같은 중복을 제거하는 방법을 생각했습니다.

<br/>

```
1. 일단 0을 그룹화 지어서 0끼리 이동할 수 있는 횟수를 기록해놓았습니다.

2. 그리고 맵 전체를 탐색하면서 벽인것을 찾습니다.

3. 벽을 찾았을 때 벽과 인접한 0은 이동을 할 수 있는 곳이기 때문에 1에서 구한 이동할 수 있는 횟수를 더해줍니다.

4. 하지만 여기서 주의해야될 점은 이때 인접한 0이 같은 그룹의 0일 수도 있기 때문에 이를 확인해주어야 합니다.

```

**같은그룹의 0 이란, 0에서 다른 0으로 이동할 수 있는 지점을 의미**합니다. 

예로 들어, 다음과 같은 경우 입니다.

<br/>

0 0 1
0 1 0 
0 0 1 

<br/>

이와 같은 맵은 1번을 거치면 다음과 같이 기록이 될 것 입니다

5 5 1
5 1 1
5 5 1

이때 (1,1)에 있는 1이 그룹을 신경쓰지않고 인접한 0의 횟수들을 다 더한다면 16이라는 결과가 나올 것입니다.

이를 방지하기 위해서 **그룹화를 하여 같은 그룹의 0은 한번만 더 하도록 해야**합니다.



<br/>



---

<br/>

```java

public class Main {

    static int N,M,cnt,groupId;

    static boolean[] checkGroup;
    static int[][] ary,visit,group;
    static int[][] dir = {{0,1},{1,0},{-1,0},{0,-1}};
    static Queue<Pair> insertQue;
    static Queue<Pair> que;


    public static void main(String[] args) throws IOException
    {
        init();
        findZero();

        checkGroup=new boolean[groupId+1];
        findWall();
        print();
    }

    public static void print() throws IOException {
        BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(System.out));

        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                if (ary[i][j] != 0)
                    wr.write(visit[i][j]%10+'0');
                else {
                    wr.write(ary[i][j]+'0');
                }
            }
            wr.newLine();
        }

        wr.flush();
        wr.close();
    }

    public static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N=stoi(st.nextToken()); M=stoi(st.nextToken());

        ary=new int[N][M];
        visit=new int[N][M];
        group=new int[N][M];
        que=new LinkedList<>();
        insertQue=new LinkedList<>();

        for(int i=0;i<N;i++){
            String cur = br.readLine();
            for(int j=0;j<M;j++){
                ary[i][j]=stoi(Character.toString(cur.charAt(j)));
            }
        }
    }

    public static int stoi(String input){
        return Integer.parseInt(input);
    }

    public static void findZero(){

        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                if(ary[i][j]==0 && visit[i][j]==0){
                    bfs(i,j);
                    fillValue();
                }
            }
        }
    }

    public static void findWall(){

        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                if(ary[i][j]==1 && visit[i][j]==0){
                    crashWall(i,j);
                }
            }
        }
    }


    public static void fillValue(){

        groupId++;
        while(!insertQue.isEmpty()){
            Pair cur = insertQue.poll();
            visit[cur.x][cur.y]=cnt;
            group[cur.x][cur.y]=groupId;
        }
    }

    public static void crashWall(int x,int y) {

        Arrays.fill(checkGroup,false);
        visit[x][y] = 1;

        for (int i = 0; i < 4; i++) {
            int nx = x + dir[i][0];
            int ny = y + dir[i][1];
            if (!checkBound(nx, ny) || ary[nx][ny]!=0 || checkGroup[group[nx][ny]]) continue;

            checkGroup[group[nx][ny]]=true;
            visit[x][y] += visit[nx][ny];
        }
    }


    public static void bfs(int x,int y){

        que.add(new Pair(x,y));
        insertQue.add(new Pair(x,y));
        visit[x][y]=1;
        cnt=1;

        while(!que.isEmpty()){
            Pair cur = que.poll();

            for(int i=0;i<4;i++){
                int nx=cur.x+dir[i][0];
                int ny=cur.y+dir[i][1];
                if(!checkBound(nx,ny) || ary[nx][ny]!=0 || visit[nx][ny]!=0) continue;

                visit[nx][ny]=1;
                que.add(new Pair(nx,ny));
                insertQue.add(new Pair(nx,ny));
                cnt++;
            }
        }

    }

    public static boolean checkBound(int x,int y){
        if(x<0 || x>=N || y<0 || y>=M) return false;
        return true;
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
