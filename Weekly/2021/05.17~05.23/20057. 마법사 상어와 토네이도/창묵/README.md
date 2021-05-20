* 시뮬레이션

<br/>

문제 자체는 까다롭지는 않지만, 이동하는 방향에 따라서 dx,dy를 어떻게 손을 대야할지 고민을 하게 만든 문제입니다.

저 같은 경우에는 그냥 dx,dy를 하드코딩하여 설정해주었습니다.

<br/>

이 문제에서 핵심적인 것 또 하나는 토네이도가 얼마나 움직이는지를 파악하는 것 입니다.

토네이도가 움직이는 방향과 횟수는 규칙을 찾아보니 **1,1,2,2,3,3,4,4,5,5 ...** 

이렇게 움직이는 방향은 `서 남 동 북` 방향으로 움직이되, `움직이는 방향의 횟수는 2번씩 움직일 때마다 늘어나는 것을 확인`할 수 있었습니다.

<br/>

그러다가 저 같은 경우에는 토네이도가 **0,0에 도착을 하게 된다면 토네이도가 그만 움직이도록 구현**하였습니다.

한 칸 움직일 때마다 현재 움직이는 방향에 맞는 dx,dy를 선택하여 모래를 다른 칸으로 이동하도록 구현하였는데,

이때 다른 칸이 격자 밖을 의미한다면 answer에 모래의 양을 더하도록 하였습니다.


<br/>



---

<br/>

```java

public class Main {


    static int N,answer;
    static int[][] map;
    static int[][] xdx = {{-1,1,-1,1, -1, 1, -2, 2, 0, 0},{-1, 1, -1, 1, -1, 1, -2, 2, 0, 0 },
        { 0, 0, 1, 1, 2, 2, 1, 1, 3, 2}, { 0, 0, -1, -1, -2, -2, -1, -1, -3, -2}};
    static int[][] ydy = {{ 0, 0, 1, 1, 2, 2, 1, 1, 3, 2}, { 0, 0, -1, -1, -2, -2, -1, -1, -3, -2},
        { -1, 1, -1, 1, -1, 1, -2, 2, 0, 0}, {-1, 1, -1, 1, -1, 1, -2, 2, 0, 0} };
    static int[] percent= { 1, 1, 7, 7, 10, 10, 2, 2, 5 };
    static int[] dx = { 0, 0, 1, -1 };
    static int[] dy = { 1, -1, 0, 0 };


    public static void main(String[] args) throws IOException
    {
        init();
        calc();
        System.out.println(answer);
    }



    public static void init() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N=stoi(st.nextToken());

        map=new int[N][N];

        for(int i=0;i<N;i++){
            st=new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++){
                map[i][j]=stoi(st.nextToken());
            }
        }
    }


    public static int changeDir(int dir) {

        if (dir == 0) return 3;
        if (dir == 1) return 2;
        if (dir == 2) return 0;
        else return 1;
    }



    public static void calc(){
        int x = N/2;
        int y = N/2;
        int dir = 1;
        int moveCnt = 1;

        while (true)
        {
            for (int i = 0; i < 2; i++)
            {
                for (int j = 0; j < moveCnt; j++)
                {
                    spreadSand(x, y, dir);
                    x += dx[dir];
                    y += dy[dir];

                    if(x==0 && y==0) return;

                }
                dir=changeDir(dir);
            }

            moveCnt++;

        }
    }

    public static void spreadSand(int x,int y,int dir){
        int nx=x+dx[dir];
        int ny=y+dy[dir];

        if(map[nx][ny]==0) return;

        int temp = map[nx][ny];
        int sand=temp;

        for(int i=0;i<9;i++){
            int xx = x + xdx[dir][i];
            int yy = y + ydy[dir][i];

            int cost = (temp * percent[i]) / 100;

            if (!checkBound(xx,yy)) answer+=cost;
            else map[xx][yy] += cost;

            sand-=cost;
        }

        int xx=x+xdx[dir][9];
        int yy=y+ydy[dir][9];

        if(!checkBound(xx,yy)) answer+=sand;
        else map[xx][yy]+=sand;

        map[nx][ny]=0;

    }

    public static boolean checkBound(int x,int y){
        if(x<0 || x>=N || y<0 || y>=N) return false;
        return true;
    }


    public static int stoi(String input){
        return Integer.parseInt(input);
    }

}
```
