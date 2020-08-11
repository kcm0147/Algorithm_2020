import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main{

  static final int parallel = 0;
  static final int vertical = 1;
  static int size;
  static int[][] map;

  public static void main(String[] args) throws IOException {
    init();
    int result=solve(0,0,size-1,size-1,parallel)+solve(0,0,size-1,size-1,vertical);

    if(result==0)
      result=-1;
    System.out.println(result);
  }


  public static void init() throws IOException {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    size=Integer.parseInt(br.readLine());
    map=new int[size][size];
    for(int i=0;i<size;i++){
      StringTokenizer st = new StringTokenizer(br.readLine());
      for(int j=0;j<size;j++){
        map[i][j]=Integer.parseInt(st.nextToken());
      }
    }
  }

  public static int solve(int x1,int y1,int x2,int y2,int dir){


    int rock=0;
    int jewelry=0;


    for(int i=x1;i<=x2;i++){
      for(int j=y1;j<=y2;j++){
        if(map[i][j]==1)
          rock++;
        if(map[i][j]==2)
          jewelry++;
      }
    }

    if(rock==0 && jewelry==1) return 1;
    if(jewelry==0) return 0;

    int result=0;

    for(int i=x1;i<=x2;i++){

      for(int j=y1;j<=y2;j++){

        if(map[i][j]==1) { // 불순물
          if (checkDiv(y1, y2,i,parallel) && dir == parallel) {
              result+=solve(x1,y1,i-1,y2,vertical)*solve(i+1,y1,x2,y2,vertical);
          } else if (checkDiv(x1, x2,j,vertical) && dir == vertical) {
              result+=solve(x1,y1,x2,j-1,parallel)*solve(x1,j+1,x2,y2,parallel);
          }
        }
      }
    }

    return result;

  }


  public static boolean checkDiv(int startPoint,int endPoint,int cur,int dir){
    if(dir==parallel){
      for(int i=startPoint;i<=endPoint;i++){
        if(map[cur][i]==2) // 보석
        {
          return false;
        }
      }
      return true;
    }
    else if(dir==vertical){

      for(int i=startPoint;i<=endPoint;i++){
        if(map[i][cur]==2){
          return false;
        }
      }
      return true;
    }

    return false;

  }

}