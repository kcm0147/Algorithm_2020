이 문제를 보자마자 `BFS`임을 떠올렸으나, visit처리를 하기위해서는 3차원 배열이 필요하다는 것을 떠올렸습니다.

visit의 3차원 배열이 필요한 이유는? 

**쓰레기(더러운 방)가 여러개가 존재하며 청소도구가 (x,y)에 도달을 했을 때 쓰레기를 한 개 치웠는지 두 개 치웠는지 경우의 수가 다르기** 때문입니다.

하지만 visit[][][]배열의 3차원 요소로 치운 쓰레기의 개수로 둬서는 안됩니다.

예로 들어 아래와 같은 예제를 보겠습니다.

<br/>

```
7 5
.......
.o...*.
.......
.*...*.
.......

```

만약 쓰레기의 개수로 visit[] 배열을 처리한다면, visit[x][y][2]의 경우 쓰레기 두개를 치우고 (x,y)에 도달했음을 의미합니다.

하지만 쓰레기 두개를 치우는 경우가 (2,6) (4,2) (4,6) 중 어느 쓰레기 두개를 치우고 x,y에 방문한 것인지 알 수 없습니다.

즉 **쓰레기 두개를 치운 경우의 수는 여러가지가 존재**한다는 것이죠!

문제에서는 쓰레기의 갯수는 최대 10개라고 했습니다.

그러면 우리는 **쓰레기를 치운 상태를 비트마스크로 표현**하여 visit[] 배열의 3차원 요소로 사용할 수 있습니다 !

<br/>

```java

if(map[nx][ny]>='0' && map[nx][ny]<='9') {
    state|=(1<<(map[nx][ny]-'0'));
}

```
map의 쓰레기를 각각 숫자로 나누어서 표시하였습니다.

map의 쓰레기를 만난다면, 쓰레기를 치우는데 그때의 상태를 state의 비트마스크로 표현하였습니다.

<br/>

```java

if(cur.state==((1<<trash)-1)){
    answer=Math.min(answer,cur.cost);
    continue;
}


```

쓰레기를 다 치웠을 때는 비트마스크가 1로 다 채워진 경우를 의미하기 떄문에 위와 같은 종료조건이 충족되면 answer를 cur.cost와 대수 비교하여 update합니다.

<br/>

---


### 또 다른 풀이법 ( BFS + DFS )

다른분들의 문제풀이 방식도 확인하였는데, 대부분 MST를 만드셔서 문제를 해결하였습니다.

즉 BFS+DFS 형식을 섞어서 문제를 해결하였는데 좋은 문제풀이 방식이라고 생각했습니다.

로봇 -> 첫번쨰 쓰레기, 두번째 쓰레기, 세번째 쓰레기

첫번째쓰레기 -> 로봇, 두번째 쓰레기, 세번째 쓰레기

두번째쓰레기 -> 로봇, 첫번째 쓰레기, 세번째 쓰레기 

..

이렇게 두 요소 사이의 이동거리를 BFS를 이용해서 구한후에 2차원 Table을 만들어서 최단거리를 저장합니다.

이후에 DFS를 이용하여 쓰레기들의 방문순서를 순열을 만들어서 전부 **완전탐색**을 진행합니다.

완전탐색을 진행한 후에 나온 최솟값을 답으로 출력하면 문제를 해결할 수 있습니다.

역시 다양한 풀이를 확인해보는게 좋은 경험이 되는 것 같습니다.


<br/>

```java

public class Main {

    static char[][] map;
    static boolean[][][] visit;

    static int N,M,trash;
    static Pair start;

    public static void main(String[] args) throws IOException {

       init();
       calc();
    }


    public static void init() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        while(true){
            StringTokenizer st = new StringTokenizer(br.readLine());
            M=stoi(st.nextToken());
            N=stoi(st.nextToken());

            if(N==0 && M==0) break;

            map=new char[N][M];
            visit=new boolean[N][M][1<<(11)];
            trash=0;

            for(int row=0;row<N;row++){
                String input=br.readLine();

                for(int col=0;col<M;col++){
                    map[row][col]=input.charAt(col);
                    if(map[row][col]=='o'){
                     start=new Pair(row,col,0,0);
                    }
                    else if(map[row][col]=='*'){
                        map[row][col]=(char)('0'+(trash++));
                    }
                }

            }

            
            System.out.println(calc());
        }
    }

    public static int calc(){
        int answer=Integer.MAX_VALUE;

        Queue<Pair> que = new LinkedList<>();
        que.add(start);
        visit[start.x][start.y][0]=true;

        int dir[][] = {{0,1},{1,0},{-1,0},{0,-1}};

        while(!que.isEmpty()){
            Pair cur = que.poll();

            if(cur.state==((1<<trash)-1)){
                answer=Math.min(answer,cur.cost);
                continue;
            }

            for(int i=0;i<4;i++){
                int nx=cur.x+dir[i][0];
                int ny=cur.y+dir[i][1];

                if(!checkBound(nx,ny) || map[nx][ny]=='x') continue;

                int state=cur.state;

                if(map[nx][ny]>='0' && map[nx][ny]<='9') {
                    state|=(1<<(map[nx][ny]-'0'));
                }

                if(!visit[nx][ny][state]){
                    visit[nx][ny][state]=true;
                    que.add(new Pair(nx,ny,state,cur.cost+1));
                }

            }
        }

        if(answer==Integer.MAX_VALUE)
            return -1;

        return answer;
    }

    public static boolean checkBound(int nx,int ny){
        if(nx<0 || nx>=N || ny<0 || ny>=M) return false;
        return true;
    }

    public static int stoi(String input){
        return Integer.parseInt(input);
    }

}

class Pair{
    int x,y,state,cost;

    Pair(int x,int y,int state,int cost){
        this.x=x;
        this.y=y;
        this.state=state;
        this.cost=cost;
    }
}


```