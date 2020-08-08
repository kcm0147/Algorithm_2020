import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {


    static int[][] map;
    static boolean[][] check;
    static int row,col;
    static int startX,startY;
    static int result=Integer.MAX_VALUE;
    static final int wall=-1;
    static final int fire=-2;
    static Queue<fireNode> fireQue = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        init();
        BFS();
        if(result==Integer.MAX_VALUE)
            System.out.println("IMPOSSIBILE");
        else
            System.out.println(result);

    }


    public static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        row=Integer.parseInt(st.nextToken());
        col=Integer.parseInt(st.nextToken());
        map=new int[row][col];
        check=new boolean[row][col];

        for(int i=0;i<row;i++){
            String curLine=br.readLine();
            for(int j=0;j<col;j++){
                char input=curLine.charAt(j);

                if(input=='J'){
                    startX=i;
                    startY=j;
                }
                else if(input=='F'){
                    fireQue.add(new fireNode(i,j));
                    map[i][j]=fire;
                }
                else if(input=='#')
                    map[i][j]=wall;
                else if(input=='.')
                    map[i][j]=0;
            }
        }

    }

    public static void BFS(){
        PriorityQueue<queNode> que = new PriorityQueue<>(new Comparator<queNode>(){
            @Override
            public int compare(queNode queNode, queNode t1) {
                if(queNode.depth>t1.depth)
                    return 1;
                else
                    return 0;
            }
        });
        

        int[][] dir={{0,1},{0,-1},{1,0},{-1,0}};
        int previousDepth=0;
        que.add(new queNode(startX,startY,1));
        check[startX][startY]=true;

        while(!que.isEmpty()){
            queNode cur=que.poll();

            if(cur.depth>result)
                break;

            if(cur.x==row-1 || cur.x==0 || cur.y==0 || cur.y ==col-1){
                if(map[cur.x][cur.y]!=wall || map[cur.x][cur.y]!=fire) {
                    result = Math.min(result, cur.depth);
                    continue;
                }
            }
            if(previousDepth!=cur.depth) {
                burn();
                previousDepth=cur.depth;
            }

            for(int i=0;i<4;i++){
                int nx=cur.x+dir[i][0];
                int ny=cur.y+dir[i][1];

                if(nx<0 || nx>=row || ny<0 || ny>=col || map[nx][ny]==fire || map[nx][ny]==wall)
                    continue;
                if(check[nx][ny]==false) {
                    que.add(new queNode(nx, ny, cur.depth + 1));
                    check[nx][ny]=true;
                }
            }

        }

    }

    public static void burn(){
        fireNode curFire;
        Queue<fireNode> nextQue = new LinkedList<>();
        int[][] dir={{0,1},{0,-1},{1,0},{-1,0}};

        while(!fireQue.isEmpty()){
            curFire=fireQue.poll();

            for(int i=0;i<4;i++){
                int nx=curFire.x+dir[i][0];
                int ny=curFire.y+dir[i][1];

                if(nx<0 || nx>=row || ny<0 || ny>=col || map[nx][ny]==fire || map[nx][ny]==wall)
                    continue;
                nextQue.add(new fireNode(nx,ny));
                map[nx][ny]=fire;
            }
        }
        fireQue=nextQue;
    }


}

class queNode{
    int x;
    int y;
    int depth;

    queNode(int x,int y,int depth){
        this.x=x;
        this.y=y;
        this.depth=depth;
    }
}

class fireNode{
    int x;
    int y;

    fireNode(int x,int y){
        this.x=x;
        this.y=y;
    }
}