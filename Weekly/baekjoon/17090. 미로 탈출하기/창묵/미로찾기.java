import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main{

    static int N,M;
    static char[][] map;
    static int[][] check;
    static int result;

    public static void main(String[] args) throws IOException{
        init();

        for(int i=0;i<N;i++){

            for(int j=0;j<M;j++){
                if(check[i][j]==0)
                    moveRoad(i,j);
            }
        }

        System.out.println(result);
    }

    public static void init() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N=Integer.parseInt(st.nextToken());
        M=Integer.parseInt(st.nextToken());

        map=new char[N][M];
        check=new int[N][M];
        for(int i=0;i<N;i++){
            String temp = br.readLine();
            for (int j=0;j<M;j++){
                map[i][j]=temp.charAt(j);
            }
        }
    }

    public static int moveRoad(int nx,int ny){

        if(nx<0 || nx>=N || ny<0 || ny>=M) return 2; // 미로 탈출

        if(check[nx][ny]==2){ // 이 칸은 미로를 탈출 할 수 있
            return 2;
        }

        if(check[nx][ny]==-1){ // 이 칸은 미로를 탈출 할 수 없다
            return -1;
        }

        if(check[nx][ny]==1){ // 이 칸은 이미 탐색하고 있는 도중에 왔던곳을 돌아왔
            return -1;
        }


        check[nx][ny]=1;

        // move Road
        if(map[nx][ny]=='D'){
            check[nx][ny]=moveRoad(nx+1,ny);
        }
        else if(map[nx][ny]=='R'){
            check[nx][ny]=moveRoad(nx,ny+1);
        }
        else if(map[nx][ny]=='L'){
            check[nx][ny]=moveRoad(nx,ny-1);
        }
        else if(map[nx][ny]=='U'){
            check[nx][ny]=moveRoad(nx-1,ny);
        }

        if(check[nx][ny]==2)
            result++;

        return check[nx][ny];
    }

}
