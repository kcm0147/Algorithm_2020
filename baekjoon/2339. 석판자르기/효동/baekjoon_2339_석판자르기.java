/*baekjoon_2339_석판자르기*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
   static int n;
   static int[][] map;

   public static void main(String args[]) throws IOException {
      init();
      
      int res = cut(false, new Pos(0, 0), new Pos(n-1, n-1))+cut(true, new Pos(0, 0), new Pos(n-1, n-1));
      //가로시작+세로시작의 경우를 각각 구해서 총합을 구한다
      
      if(res==0) {
         System.out.println(-1);
         return;
      }
      System.out.println(res);
   }

   public static void init() throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      String[] s = br.readLine().split(" ");
      
      n = Integer.parseInt(s[0]);
      
      map = new int[n][n];
      for(int i=0;i<n;i++) {
         s = br.readLine().split(" ");
         for(int j=0;j<n;j++) {
            map[i][j]=Integer.parseInt(s[j]);
         }
      }//map생성
   }
   
   public static int cut(boolean wasVertz, Pos sPos, Pos ePos) {
      Info info = getInfo(sPos, ePos);
      if(info.jewel_cnt==0)return 0;
      else if(info.jewel_cnt==1 && info.trash_cnt==0)return 1;
      else if(info.trash_cnt==0)return 0;//보석은 1개 이상인데 석판을 나눠줄 불순물이 없다
      //그 외 상황은 더 나눠봐야 알 수 있다.
      
      int total=0;
      for(Pos trash : info.trashes) {
         if(!wasVertz) {//이전 단계가 세로였다면 이번에는 가로로함
            //가로로 하려면 가로줄 확인 필요
            Info inf = getInfo(new Pos(trash.y, sPos.x), new Pos(trash.y, ePos.x));
            
            //확인 후, 해당 줄에 보석이 있거나, 자르려는 영역이 줄 차이가 1보다 작을 경우는 제외(차이가 1씩은 나야 두 영역으로 cut이 가능하기 때문)
            if(inf.jewel_cnt>0 || trash.y - sPos.y<1 || ePos.y-trash.y<1) continue;
            total += cut(true, sPos, new Pos(trash.y-1, ePos.x))*cut(true, new Pos(trash.y+1, sPos.x ), ePos);
         }
         else {//반대도 동일한 방식적용
            Info inf = getInfo(new Pos(sPos.y, trash.x), new Pos(ePos.y, trash.x));
            
            if(inf.jewel_cnt>0 || trash.x - sPos.x<1 || ePos.x-trash.x<1) continue;
            total += cut(false, sPos, new Pos(ePos.y, trash.x-1))*cut(false, new Pos(sPos.y, trash.x+1), ePos);
         }
      }

      return total;
   }
  
   public static Info getInfo(Pos sPos, Pos ePos) {
      int jewel_cnt=0;
      int trash_cnt=0;
      ArrayList<Pos> trashes = new ArrayList<>();
      
      for(int i=sPos.y;i<=ePos.y;i++) {
         for(int j=sPos.x;j<=ePos.x;j++) {
            if(map[i][j]==2) jewel_cnt++;
            if(map[i][j]==1) {
               trash_cnt++;   
               trashes.add(new Pos(i, j));         
            }
         }
      }
      return new Info(jewel_cnt, trash_cnt, trashes);
   }
   
   public static class Info {
         int jewel_cnt;
         int trash_cnt;
         ArrayList<Pos> trashes;
         
         public Info(int jewel_cnt, int trash_cnt, ArrayList<Pos> trashes) {
            this.jewel_cnt=jewel_cnt;
            this.trash_cnt=trash_cnt;
            this.trashes = trashes;
         }
      }
   
   public static class Pos{
      int y;
      int x;
      public Pos(int y, int x){
         this.y = y;
         this.x = x;
      }
   }
}