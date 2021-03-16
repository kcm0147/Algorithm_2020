이 문제는 미로찾기와 같은 형태 문제에서, 최단거리를 구하는 문제가 아닌

`방향을 최대한 적게 바꾸어 목적지 까지 도착하는 것이 문제의 목적`입니다.

<br/>

visit[][] 배열 을 일반적인 true / false로 둬서는 안됩니다. 

이 문제에서 visit[x][y] 에서 x,y를 방문하는 경우의 수는 서로 다른 경우의 수로 분류되어지기 떄문입니다.

<br/>

즉 x,y 목적지로 도착을 하는데 `동쪽에서 x,y로 올 수도 있고` '북쪽에서 x,y로 올 수도 있습니다`

다시 말해 x,y로 오는데 동쪽에서 올떄와 북쪽에서 올때의 진행방향이 다르기 때문에 서로 다른 경우의수로 봐야만 합니다.

<br/>

그럼 visit를 어떻게 처리하는게 좋을까요?

visit가 없다면 계속해서 탐색을 하기 때문에, 중복적으로 연산하는 경우를 막아야만 합니다.

이를 위해서 visit에는 x,y에 방문했을때 몇개의 거울을 사용했는지를 표시하였습니다.

```java

if(visit[nx][ny]<cur.cnt+(cur.dir!=i?1:0)) continue;

```

만약에 한 경우의 수에서 x,y에 도착을 했을 때, `visit[x][y]에 저장되어있는 거울의 수 보다 지금 현재 거울을 더 많이 사용했다면?` 이는 더이상 탐색을 할 가치가 없습니다.

위와 같은 사실을 이용하여 저희는 중복적으로 연산이 진행되는 것을 해결할 수 있습니다.

*cur.dir!=i?1:0* 은 cur.dir은 현재 레이저의 진행방향입니다. 

nx,ny에 도착을 할 때 *레이저의 진행방향(cur.dir)과 다른 진행방향으로 움직였다면(i) 이는 거울을 사용한 것이*기 때문에 cur.cnt에 +1을 해주어야 합니다.

<br/>

저는 문제를 좀 더 쉽게 접근하기 위해서, 

처음 Start에서 4방향으로 일단 visit[][]를 0으로 초기화하고 que에 노드들을 넣은 후에 BFS연산을 진행하였습니다.

이때 사용되는 함수가 `initInsert()` 함수입니다.


<br/>

재미있는 BFS문제 였다고 생각합니다.


<br/>



```java

public class Main {

    static int N,M,answer;
    static Node start,end;
    
    static char[][] map;
    static int[][] visit;
    static int[][] dir={{0,1},{1,0},{0,-1},{-1,0}};
    
    static Queue<Node> que = new LinkedList<>();
    

    public static void main(String[] args) throws IOException {

        init();
        calc();
        System.out.println(answer);
    }


    public static void init() throws IOException{
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st =new StringTokenizer(br.readLine());
        M=stoi(st.nextToken());
        N=stoi(st.nextToken());

        map=new char[N][M];
        visit=new int[N][M];

        for(int i=0;i<N;i++)
            Arrays.fill(visit[i],Integer.MAX_VALUE);

        for(int i=0;i<N;i++){
            String cur=br.readLine();
            for(int j=0;j<M;j++){
                map[i][j]=cur.charAt(j);

                if(map[i][j]=='C'){
                    if(start==null){
                        start=new Node(i,j,0,0);
                    }
                    else
                        end=new Node(i,j,0,0);
                }
            }
        }

        initInsert();
        answer=Integer.MAX_VALUE;
    }

    public static void initInsert(){
        for(int i=0;i<4;i++){
            int value=1;
            while(true){
                int nx=start.x+dir[i][0]*value;
                int ny=start.y+dir[i][1]*value;

                if(checkBound(nx,ny)) break;
                if(map[nx][ny]=='*') break;

                visit[nx][ny]=0;
                que.add(new Node(nx,ny,0,i));
                value++;
            }
        }
    }

    public static void calc(){



        while(!que.isEmpty()){
            Node cur = que.poll();
            if(cur.x==end.x && cur.y==end.y){
                answer=Math.min(answer,cur.cnt);
                continue;
            }

            for(int i=0;i<4;i++){
                int nx=cur.x+dir[i][0];
                int ny=cur.y+dir[i][1];

                if(checkBound(nx,ny) || map[nx][ny]=='*') continue;

                if(visit[nx][ny]<cur.cnt+(cur.dir!=i?1:0)) continue;
                else{
                    visit[nx][ny]=cur.cnt+(cur.dir!=i?1:0);
                    que.add(new Node(nx,ny,visit[nx][ny],i));
                }

            }
        }

    }

    public static boolean checkBound(int x,int y){
        if(x<0 || x>=N || y<0 || y>=M) return true;
        return false;
    }



    public static int stoi(String input){
        return Integer.parseInt(input);
    }


}


class Node{
    int x,y,cnt,dir;
    

    Node(int x,int y,int cnt,int dir){
        this.x=x;
        this.y=y;
        this.cnt=cnt;
        this.dir=dir;
    }
}

```
