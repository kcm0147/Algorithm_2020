import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main{

    static int N,M,S;
    static int row,col,cnt,result;
    static int[][] map;
    static int[][] sticker;




    public static void main(String[] args) throws IOException {
        init();
        System.out.println(result);
    }

    public static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N=Integer.parseInt(st.nextToken());
        M=Integer.parseInt(st.nextToken());
        S=Integer.parseInt(st.nextToken());
        map=new int[N][M];

        for(int i=0;i<S;i++){
            st = new StringTokenizer(br.readLine());
            row=Integer.parseInt(st.nextToken());
            col=Integer.parseInt(st.nextToken());
            sticker = new int[row][col];
            cnt=0;
            for(int j=0;j<row;j++)
            {
                st=new StringTokenizer(br.readLine());
                for(int k=0;k<col;k++) {
                    sticker[j][k] = Integer.parseInt(st.nextToken());
                    if(sticker[j][k]==1)
                        cnt++;
                }
            }

            for(int rotateCnt=0;rotateCnt<4;rotateCnt++){ // put check
                if(solve()){
                    result+=cnt;
                    break;
                }
                sticker=rotate(); // 0-> 0 1 -> 90 2-> 180 3-> 270
            }
        }
    }

    public static int[][] rotate(){
        int[][] temp=new int[col][row];

        for(int i=0;i<col;i++){
            for(int j=0;j<row;j++){
                temp[i][j]=sticker[row-1-j][i];
            }
        }

        int coltemp=col;
        col=row;
        row=coltemp;

        return temp;
    }

    public static boolean solve(){
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                if(putcheck(i,j)){
                    put(i,j);
                    return true;
                }
            }
        }

        return false;
    }

    public static boolean putcheck(int x,int y){
        for(int i=0;i<row;i++){ // stikcer size row col
            for(int j=0;j<col;j++){
                if(i+x>=N || j+y >=M) return false;
                if(map[i+x][j+y]==1 && sticker[i][j]==1) return false;
            }
        }
        return true;
    }

    public static void put(int x,int y){
        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                if(sticker[i][j]==1 && map[i+x][j+y]==0)
                    map[i+x][j+y]=1;
            }
        }
    }
}
