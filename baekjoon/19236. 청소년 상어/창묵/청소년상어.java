import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main{

    static fish[][] map;
    static fish shark;
    static fish[] fishAry;
    static int[][] dirAry={{-1,0},{-1,-1},{0,-1},{1,-1},{1,0},{1,1},{0,1},{-1,1}};
    static int answer;

    public static void main(String[] argv) throws IOException {

        init();
        dfs(0,0,answer,map,fishAry);
        System.out.println(answer);
    }

    public static void init() throws IOException {
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        map=new fish[4][4];
        fishAry=new fish[16];
        shark=new fish(0,0,0,-2);


        for(int i=0;i<4;i++){
            st = new StringTokenizer(read.readLine());
            for(int j=0;j<4;j++) {
                int number = Integer.parseInt(st.nextToken()) - 1;
                int dir = Integer.parseInt(st.nextToken()) - 1;
                fishAry[number] = new fish(i, j, dir,number);
                map[i][j]=fishAry[number];
            }
        }

        shark.dir=map[0][0].dir;
        map[0][0].dir=-1;
        answer=map[0][0].number+1;
        map[0][0].number=-1;
        shark.x=0; shark.y=0;
    }

    public static void dfs(int x,int y,int result,fish[][] curmap,fish[] curFish){

        fish[][] maptemp=new fish[4][4];
        fish[] fishtemp=new fish[16];

        for(int i=0;i<4;i++) {
            System.arraycopy(curmap[i],0,maptemp[i],0,curmap[i].length);
        }

        System.arraycopy(curFish,0,fishtemp,0,curFish.length);


        for(int i=0;i<fishtemp.length;i++) {
            if(fishtemp[i].dir!=-1)
                fishMove(x,y,maptemp,fishtemp[i],fishtemp);
        }


        int length=1;

        while(true){
            int sharkNx=x+dirAry[shark.dir][0]*length;
            int sharkNy=y+dirAry[shark.dir][1]*length;
            int oldDir;

            if (sharkNx < 0 || sharkNx >= 4 || sharkNy < 0 || sharkNy>=4) {
                break;
            }

            if(maptemp[sharkNx][sharkNy].dir==-1) { // 물고기가 x
                length++;
                continue;
            }

            else if(maptemp[sharkNx][sharkNy].dir!=-1){
                oldDir=shark.dir;
                shark.dir=maptemp[sharkNx][sharkNy].dir;
                int catchFish=maptemp[sharkNx][sharkNy].number;
                fishtemp[maptemp[sharkNx][sharkNy].number]=new fish(sharkNx,sharkNy,-1,-1);
                maptemp[sharkNx][sharkNy]=new fish(sharkNx,sharkNy,-1,-1);
                dfs(sharkNx,sharkNy,result+catchFish+1,maptemp,fishtemp); // 탐
                fishtemp[catchFish]=new fish(sharkNx,sharkNy,shark.dir,catchFish);
                maptemp[sharkNx][sharkNy]=new fish(sharkNx,sharkNy,shark.dir,catchFish);
                shark.dir=oldDir;
            }
            length++;
        }

        answer=Math.max(result,answer);

    }


    public static void fishMove(int sharkX,int sharkY,fish[][] curmap,fish curFish,fish[] curfishAry){

        int oldDir=curFish.dir;
        int nDir;
        int nx,ny;
        int tempNumber,tempDir;

        for(int i=0;i<=8;i++){
            nDir=(oldDir+i)%8;
            nx=curFish.x+dirAry[nDir][0];
            ny=curFish.y+dirAry[nDir][1];

            if((nx==sharkX&& ny==sharkY) || nx<0 || nx>=4 || ny<0 || ny>=4){ // 다른 방향
               continue;
            }

            if(curmap[nx][ny].dir==-1){ // 물고기가 없을
                curmap[nx][ny]=new fish(nx,ny,nDir,curFish.number);
                curmap[curFish.x][curFish.y]=new fish(curFish.x,curFish.y,-1,-1);
                curfishAry[curFish.number]=new fish(nx,ny,nDir,curFish.number);
            }
            else{
                tempNumber=curmap[nx][ny].number;
                tempDir=curmap[nx][ny].dir;
                curmap[nx][ny]=new fish(nx,ny,nDir,curFish.number);
                curmap[curFish.x][curFish.y]=new fish(curFish.x,curFish.y,tempDir,tempNumber);

                curfishAry[tempNumber]=new fish(curFish.x,curFish.y,tempDir,tempNumber);
                curfishAry[curFish.number]=new fish(nx,ny,nDir,curFish.number);
            }
            break;
        }
    }
}



class fish{
    int x;
    int y;
    int dir;
    int number;
    fish(int x,int y,int dir,int number){
        this.x=x;
        this.y=y;
        this.dir=dir;
        this.number=number;
    }
}
