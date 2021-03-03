import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N,M,K;
    static int[][] map;
    static boolean[][] visit;
    static boolean[][] mineral;

    public static void main(String[] args) throws IOException {

        init();
        print();
    }

    public static void breaking(int height,int dir){

        boolean isBreak=false;
        visit=new boolean[N][M];
        mineral=new boolean[N][M];

        if(dir==0) {
            for (int j = 0; j < M; j++) {
                if (map[height][j] != 0) {
                    map[height][j] = 0;
                    isBreak = true;
                    break;
                }
            }
        }
        else if(dir==1){
            for (int j = M-1; j >= 0; j--) {
                if (map[height][j] != 0) {
                    map[height][j] = 0;
                    isBreak = true;
                    break;
                }
            }
        }

        if(isBreak) {

            for (int j = 0; j < M; j++) { // serach floor.
                if (map[N-1][j] != 0 && visit[N-1][j] == false) {
                    bfs(N-1, j);
                }
            }


            int moveHeight=Integer.MAX_VALUE;
            int result;
            for (int i = N - 1; i >= 0; i--) { // move mineral
                for (int j = M - 1; j >= 0; j--) {
                    if (map[i][j] != 0 && visit[i][j] == false) {
                        mineral[i][j]=true;
                        result=findMinHeight(i,j);
                        moveHeight=Math.min(moveHeight,result);
                    }
                }
            }

            for (int i = N - 1; i >= 0; i--) { // move mineral
                for (int j = M - 1; j >= 0; j--) {
                    if (map[i][j] != 0 && visit[i][j] == false) {
                        map[i][j]=0;
                        visit[i][j]=true;
                        if(i+moveHeight<=N-1) {
                            map[i + moveHeight][j] = 1;
                            visit[i + moveHeight][j] = true;
                        }
                    }
                }
            }


        }

    }

    public static Integer findMinHeight(int x,int y){
        int moveHeight=0;
        for(int i=x+1;i<N;i++){
            if(map[i][y]==1 && mineral[i][y]==false)
                return moveHeight;
            moveHeight++;
        }

        return moveHeight;
    }

    public static void bfs(int x,int y){
        Queue<Node> que =new LinkedList<>();
        que.add(new Node(x,y));

        int[][] dir={{0,-1},{0,1},{1,0},{-1,0}};
        while(!que.isEmpty()){
            Node cur=que.poll();

            for(int i=0;i<4;i++){
                int nx=cur.x+dir[i][0];
                int ny=cur.y+dir[i][1];

                if(nx<0 || ny<0 || nx>=N || ny>=M || map[nx][ny]!=1) continue;

                if(visit[nx][ny]==false){
                    visit[nx][ny]=true;
                    que.add(new Node(nx,ny));
                }
            }
        }

    }

    public static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map=new int[N][M];
        visit=new boolean[N][M];

        for(int i=0;i<N;i++){
            String cur = br.readLine();
            for(int j=0;j<M;j++){
                if(cur.charAt(j)=='.'){
                    map[i][j]=0; // land
                }
                else if(cur.charAt(j)=='x'){
                    map[i][j]=1; // mineral
                }
            }
        }

        K=Integer.parseInt(br.readLine());

        st=new StringTokenizer(br.readLine());
        for(int i=0;i<K;i++){
            int height=Integer.parseInt(st.nextToken());
            breaking(M-height,i%2);
        }
    }

    public static void print(){

        for(int i=0;i<N;i++){
            for (int j=0;j<M;j++){
                if(map[i][j]!=0)
                    System.out.print("x");
                else if(map[i][j]==0)
                    System.out.print(".");
            }
            System.out.println();
        }
    }



}

class Node{
    int x;
    int y;


    Node(int x,int y){
        this.x=x;
        this.y=y;
    }
}


