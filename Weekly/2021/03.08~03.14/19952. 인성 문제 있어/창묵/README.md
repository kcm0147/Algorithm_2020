```java
public class Main {

    static int N,M,O,F;
    static Node start,end;

    static int[][] map;
    static boolean[][] visit;

    public static void main(String[] args) throws IOException {

        init();
       
    }


    public static void init() throws IOException{
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));

        int tc=stoi(br.readLine());

        for(int j=0;j<tc;j++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = stoi(st.nextToken());
            M = stoi(st.nextToken());
            O = stoi(st.nextToken());
            F = stoi(st.nextToken());
            start = new Node(stoi(st.nextToken()), stoi(st.nextToken()), F);
            end = new Node(stoi(st.nextToken()), stoi(st.nextToken()), F);

            map = new int[N + 1][M + 1];
            visit = new boolean[N + 1][M + 1];

            for (int i = 0; i < O; i++) {
                st = new StringTokenizer(br.readLine());
                map[stoi(st.nextToken())][stoi(st.nextToken())] = stoi(st.nextToken());
            }

            if (calc()) {
                System.out.println("잘했어!!");
            } else {
                System.out.println("인성 문제있어??");
            }

        }

    }

    public static boolean calc(){

        int[][] dir={{0,1},{1,0},{-1,0},{0,-1}};

        Queue<Node> que = new LinkedList<>();
        que.add(start);
        visit[start.x][start.y]=true;

        while(!que.isEmpty()){
            Node cur = que.poll();

            if(cur.force==0){
                continue;
            }

            for(int i=0;i<4;i++){
                int nx=cur.x+dir[i][0];
                int ny=cur.y+dir[i][1];

                if(checkBound(nx,ny) || visit[nx][ny]) continue;

                if(map[nx][ny]-map[cur.x][cur.y]<=cur.force){

                    if(nx==end.x && ny==end.y){
                        return true;
                    }

                    que.add(new Node(nx,ny,cur.force-1));
                    visit[nx][ny]=true;
                }
            }

        }

        return false;
    }


    public static boolean checkBound(int x,int y){
        if(x<1 || x>N || y<1 || y>M) return true;

        return false;
    }

    public static int stoi(String input){
        return Integer.parseInt(input);
    }


}

class Node{
    int x,y,force;

    Node(int x,int y,int force){
        this.x=x;
        this.y=y;
        this.force=force;
    }
}


```