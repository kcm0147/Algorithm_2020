[요구사항]

M->Z로가는 가스관 경로 중 하나의 블록이 사라졌는데, 그 블록이 어떠한 가스관의 모양을 가지는지 찾아야합니다.


<br/>

일단 문제를 보면 M->Z로 가는 경로 중 하나의 블럭이 사라졌을 때 그 블럭이 어떠한 모양을 가지고 있는지 찾아야합니다.

일반적인 경로와는 다르게 가스관들은 모든 가스관들끼리 전부 연결이 되어 있어야 합니다.

즉 순환경로 또한 전부다 온전한 경로로 M까지 가도록 해야합니다.

다행히 문제에서는 블록하나만을 찾으라고 했기 때문에, 하나의 블록을 제외하고는 `모든 경로들이 전부 연결되어있다고 가정`하고 문제를 해결 할 수 있습니다!

결국 저희는 사라진 블록의 위치를 찾고, 그 블록이 어떠한 가스관을 가지는지만 확인하면 되는 거죠.


1. 출발지로부터 연결되어있는 가스관을 따라 이동하다가, 도중에 `.`인 부분이 나오면 가스관이 끊어져있다는 의미이기 떄문에 사라진 블록의 위치를 찾을 수 있습니다.

2. 블록을 중심으로 4 방면을 탐색한 후, 왼쪽,오른쪽,위쪽,아래쪽에 가스관통로가 있는지 판단을 해야합니다.

3. 2에서 구한 방향을 기준으로 어떠한 가스관을 놓아야할지 출력합니다.

=> 예를들어 4방면으로 다 가스관이 연결이 되어야한다면 `+`
왼쪽 오른쪽의 가스관만 연결이 되어있다면 `-`만 연결해야 할 것입니다.

---


<br/> <br/>

```java

public class Main {

    static char[][] map;
    static boolean[][] visit;
    static int startX,startY,row,col;
    static int[][] dir ={{1,0},{-1,0},{0,-1},{0,1}};


    public static void main(String[] args) throws IOException {
        init();
        calc();


    }

    public static void init() throws IOException{
            BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine());
            row=stoi(st.nextToken());
            col=stoi(st.nextToken());
            map=new char[row][col];
            visit=new boolean[row][col];

            for(int i=0;i<row;i++){
                String cur = br.readLine();
                for(int j=0;j<col;j++){
                    map[i][j]=cur.charAt(j);
                    if(map[i][j]=='M'){
                        startX=i;
                        startY=j;
                    }
                }
            }
    }

    public static void calc(){

        for(int i=0;i<4;i++){
            int nx=startX+dir[i][0];
            int ny=startY+dir[i][1];

            if(checkBound(nx,ny) || map[nx][ny]=='.') continue;

            dfs(nx,ny);
        }
    }

    public static void dfs(int x,int y){
        if(checkBound(x,y) || visit[x][y] || map[x][y]=='Z') return ;

        visit[x][y]=true;

        if(map[x][y]=='.'){
            find(x,y);
            return ;
        }
        else if(map[x][y]=='+'){
            dfs(x+1,y);
            dfs(x-1,y);
            dfs(x,y+1);
            dfs(x,y-1);
        }
        else if(map[x][y]=='-'){
            dfs(x,y-1);
            dfs(x,y+1);
        }
        else if(map[x][y]=='|'){
            dfs(x+1,y);
            dfs(x-1,y);
        }
        else if(map[x][y]=='1'){
            dfs(x+1,y);
            dfs(x,y+1);
        }
        else if(map[x][y]=='2'){
            dfs(x-1,y);
            dfs(x,y+1);
        }
        else if(map[x][y]=='3'){
            dfs(x-1,y);
            dfs(x,y-1);
        }
        else if(map[x][y]=='4'){
            dfs(x+1,y);
            dfs(x,y-1);
        }
    }

    public static void find(int x,int y){
        boolean up,down,left,right;

        up=down=left=right=false;

        for(int i=0;i<4;i++){
            int nx=x+dir[i][0];
            int ny=y+dir[i][1];

            if(checkBound(nx,ny)) continue;

            if(i==0 && (map[nx][ny]=='|' || map[nx][ny]=='+'
                    || map[nx][ny]=='2'
                    || map[nx][ny]=='3')){
                    up=true;
            }

            if(i==1 && (map[nx][ny]=='|' || map[nx][ny]=='+'
                    || map[nx][ny]=='1'
                    || map[nx][ny]=='4')){
                    down=true;
            }

            if(i==2 && (map[nx][ny]=='-' || map[nx][ny]=='+'
                    || map[nx][ny]=='1'
                    || map[nx][ny]=='2')){
                left=true;
            }

            if(i==3 && (map[nx][ny]=='-' || map[nx][ny]=='+'
                    || map[nx][ny]=='3'
                    || map[nx][ny]=='4')){
                right=true;
            }
        }

        System.out.printf((x+1)+" "+(y+1)+" ");

        if(up && down && right && left){
            System.out.printf("+");
        }
        else if(up && down){
            System.out.printf("|");
        }
        else if(up && left){ // 위, 왼쪽으로 이동해야함
            System.out.printf("4");
        }
        else if(down && left){
            System.out.printf("3");
        }
        else if(up && right){
            System.out.printf("1");
        }
        else if(down && right){
            System.out.printf("2");
        }
        else if(left && right){
            System.out.printf("-");
        }

        System.exit(0);
    }

    public static boolean checkBound(int nx,int ny){
        if(nx<0 || nx>=row || ny<0 || ny>=col) return true;
        return false;
    }

    public static int stoi(String input){
        return Integer.parseInt(input);
    }


}

```