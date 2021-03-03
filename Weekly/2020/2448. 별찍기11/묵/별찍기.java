import java.io.*;
import java.util.Arrays;


public class Main{

    static int N;
    static char[][] map;


    public static void main(String[] args) throws IOException{
        init();
        makeStar(N,0,N-1);
        printStar();
    }

    public static void init() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N=Integer.parseInt(br.readLine());
        map=new char[N][2*N-1];

        for(int i=0;i<N;i++)
            Arrays.fill(map[i],' ');

    }

    public static void printStar() throws IOException {

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        for(int i=0;i<N;i++){
            String cur=String.valueOf(map[i]);
            bw.write(cur+"\n");
        }
            bw.flush();
    }

    public static void makeStar(int height,int x,int y){

        if(height==3){
            map[x][y]='*';
            map[x+1][y-1]='*';
            map[x+1][y+1]='*';
            map[x+2][y-1]='*';
            map[x+2][y-2]='*';
            map[x+2][y]='*';
            map[x+2][y+1]='*';
            map[x+2][y+2]='*';
            return;
        }

        makeStar(height/2,x,y); // 높이 2 나누기
        makeStar(height/2,x+(height/2),y-(height/2)); // 왼쪽 삼각형
        makeStar(height/2,x+(height/2),y+(height/2)); // 오른쪽 삼각
    }



}