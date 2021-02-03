import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main{

    static int[][] map;
    static boolean[][][][] packVisit;
    static final int packman =9;
    static final int ghost = -1;
    static Pack pack1,pack2;
    static int row,col;


    public static void main(String[] argv) throws IOException{
        init();
    }

    public static void init() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tc=Integer.parseInt(br.readLine());

        for(int i=0;i<tc;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            row=Integer.parseInt(st.nextToken());
            col=Integer.parseInt(st.nextToken());

            map=new int[row][col];
            packVisit=new boolean[row][col][row][col];
            pack1=null;
            pack2=null;

            for(int j=0;j<row;j++){ // create map
                String cur=br.readLine();
                for(int k=0;k<col;k++){
                    if(cur.charAt(k)=='X'){
                        map[j][k]=1;
                    }
                    else if(cur.charAt(k)=='G'){
                        map[j][k]=ghost;
                    }
                    else if(cur.charAt(k)=='P'){
                        if(pack1==null){
                            pack1=new Pack(j,k);
                        }
                        else {
                            pack2 = new Pack(j, k);
                        }
                    }
                }
            }

            String returnValue=bfs();

            if(returnValue.equals("fail")){
                System.out.println("IMPOSSIBLE");
            }
            else{
                System.out.printf("%d %s\n",returnValue.length(),returnValue);
            }
        }
    }

    public static String bfs(){

        PackPair curPair;
        int[][] dir = {{0,1},{0,-1},{1,0},{-1,0}};
        char[] where={'E','W','S','N'};

        Queue<PackPair> que = new LinkedList<>();
        que.add(new PackPair(pack1,pack2,""));
        //     packVisit[pack1.x][pack1.y][pack2.x][pack2.y]=true;

        while(!que.isEmpty()){
            curPair=que.poll();
            Pack leftPack= curPair.pack1;
            Pack rightPack=curPair.pack2;

            if(leftPack.x==rightPack.x && leftPack.y==rightPack.y){
                return curPair.answer;
            }

            for(int i=0;i<4;i++){
                int nx1= (leftPack.x+dir[i][0]+row)%row;
                int ny1= (leftPack.y+dir[i][1]+col)%col;
                int nx2= (rightPack.x+dir[i][0]+row)%row;
                int ny2= (rightPack.y+dir[i][1]+col)%col;

                if(map[nx1][ny1]==1){ // 벽을 만날
                    nx1=leftPack.x;
                    ny1=leftPack.y;
                }
                if(map[nx2][ny2]==1){
                    nx2=rightPack.x;
                    ny2=rightPack.y;
                }



                if(map[nx1][ny1]==0 && map[nx2][ny2]==0 && packVisit[nx1][ny1][nx2][ny2]==false){
                    Pack nleftPack=new Pack(nx1,ny1);
                    Pack nrightPack=new Pack(nx2,ny2);
                    packVisit[nx1][ny1][nx2][ny2]=true;

                    que.add(new PackPair(nleftPack,nrightPack,curPair.answer+where[i]));
                }
            }

        }

        return "fail";
    }


}

class Pack{
    int x;
    int y;

    Pack(int x,int y)
    {
        this.x=x;
        this.y=y;
    }
}

class PackPair{
    Pack pack1;
    Pack pack2;
    String answer;

    PackPair(Pack pack1,Pack pack2,String answer){
        this.pack1=pack1;
        this.pack2=pack2;
        this.answer=answer;
    }
}
