import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;



public class Main{


  static int[][] map;
  static long[][][] length;
  static int N;

  static final int horizon = 0;
  static final int vertical = 1;
  static final int diagonal = 2;


  public static void main(String argv[]) throws IOException {

    init();

    System.out.println(dfs(0,1,horizon));
  }

  public static void init() throws IOException {


    BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;
    N=Integer.parseInt(br.readLine());
    map=new int[N][N];
    length=new long[N][N][3];

    for(long[][] arr2 : length){
      for(long[] arr : arr2){
        Arrays.fill(arr,-1);
      }
    }

    for(int i=0;i<N;i++){
      st=new StringTokenizer(br.readLine());
      for(int j=0;j<N;j++){
        map[i][j]=Integer.parseInt(st.nextToken());
      }
    }

  }

  public static long dfs(int nx,int ny,int dir){

    if(length[nx][ny][dir]!=-1){ // 값이 존재할때
      return length[nx][ny][dir];
    }

    if(nx==N-1 && ny==N-1){ // perfect serch end
      return 1;
    }

    length[nx][ny][dir]=0;

    for(int i=0;i<3;i++){ // horizon, vertical,diagonal
      if(dir==horizon && i==vertical)
        continue;
      if(dir==vertical && i==horizon)
        continue;

      if(mapCheck(nx,ny,i)){
        if(i==vertical){
          length[nx][ny][dir]+=dfs(nx+1,ny,vertical);
        }
        else if(i==horizon){
          length[nx][ny][dir]+=dfs(nx,ny+1,horizon);
        }
        else if(i==diagonal){
          length[nx][ny][dir]+=dfs(nx+1,ny+1,diagonal);
        }
      }
    }

    return length[nx][ny][dir];

  }

  public static boolean mapCheck(int nx,int ny,int moveDir){

    if(moveDir==horizon){
      ny++;
      if(!rangeOver(nx,ny) && map[nx][ny]!=1){
        return true;
      }
      return false;
    }
    else if(moveDir==vertical){
      nx++;
      if(!rangeOver(nx,ny) && map[nx][ny]!=1){
        return true;
      }
      return false;
    }
    else if(moveDir==diagonal){
      if(!rangeOver(nx+1,ny+1)){
        if(map[nx+1][ny]!=1 && map[nx][ny+1]!=1 && map[nx+1][ny+1]!=1)
          return true;
        return false;
      }
    }

    return false;
  }

  public static boolean rangeOver(int nx,int ny){
    if(nx >= N || ny >= N)
      return true;

    return false;
  }

}
