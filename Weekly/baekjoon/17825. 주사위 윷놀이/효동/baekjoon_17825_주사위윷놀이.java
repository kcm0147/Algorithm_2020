/*baekjoon_17825_주사위윷놀이*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
   static int[] map = { 0, 2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30, 32, 34, 36, 38, 40, -1, 
		   10, 13, 16, 19, 25, 30, 35, 40, -1,
		   20, 22, 24, 25, 30, 35, 40, -1,
		   30, 28, 27, 26, 25, 30, 35, 40, -1 };
   static int[] dice = new int[10];
   static int[] player = new int[10];// 어떤 말을 움직일지에 대한 결정

   static int max_num = 0;

   public static void main(String args[]) throws IOException {
      init();
      setPlayer(0);
      System.out.println(max_num);
   }

   public static void init() throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      String[] s = br.readLine().split(" ");

      for (int i = 0; i < 10; i++) {
         dice[i] = Integer.parseInt(s[i]);
         player[i] = 0;
      }
   }

   public static void setPlayer(int cnt) {
      if (cnt == 10) {// 9번 player까지 모두 채우면 그 때의 점수 합을 구하기 위해 getSum() 실행
         int sum = 0;
         if (max_num < (sum = getSum()))
            max_num = sum;
         return;
      }

      for (int i = 1; i <= 4; i++) {// 4번 player 모두 해봐야하기때문에 for문 필요
         player[cnt] = i;
         setPlayer(cnt + 1);// 모든 경우를 해봐야하기 때문에 player배열을 모두 일단 채운다
      }
   }

   public static int getSum() {
      int sum = 0;
      int[] pos = new int[5];
      for (int i = 0; i < 5; i++)
         pos[i] = 0;// player를 시작점에 위치
      for (int i = 0; i < 10; i++) {
         int pnum = player[i];
         // player를 차례대로 살펴본다
         // 조건에 따라 실제로 그 player를 움직일 수 있는지 판별하고 움직이거나 바로 종료 시켜버린다

         if (pos[pnum] == 21 || pos[pnum] == 30 || pos[pnum] == 38 || pos[pnum] == 47) {
            return 0;
         } // 움직이고 싶은 말이 이미 도착점일 경우 움직이는 것이 불가능함으로 return

         // pos[pnum]은 해당 player의 자리
         if (pos[pnum] == 5) // 10일경우
            pos[pnum] = 22;

         if (pos[pnum] == 10) // 20일 경우
            pos[pnum] = 31;

         if (pos[pnum] == 15) // 30일 경우
            pos[pnum] = 39;
         // 우선 현재 자리를 판별해서 파란색 동그라미면 해당 위치로 재설정

         //여기서 부터 이제 옮길 자리에 대한 판단
         int before = pos[pnum];
         pos[pnum]+=dice[i];
         
         // player자리에 주사위 값 더해서 새로 결정
         // 방금 자리 변경 해주지 않았을 경우, 즉 끝점에 도달했다고 판단되는 경우
         if ((before < 21 && pos[pnum] >= 21) || (before < 30 && pos[pnum] >= 30) || (before < 38 && pos[pnum] >= 38)
               || (before < 47 && pos[pnum] >= 47)) {
            pos[pnum] = 47;
            continue;
         }
         // 도착점을 넘으면 안되기 때문에 도착점으로 재설정

         if(isFull(pos, pnum)) return 0;
         // 그 자리에 이미 누가 있었다면 불가능

         sum += map[pos[pnum]];
      }
      return sum;
   }
   
   public static boolean isFull(int[] pos, int pnum) {
      if(pos[pnum]==26 || pos[pnum]==34 || pos[pnum]==43) {//공통부분 25
         for(int j=1;j<=4;j++) {
            if(j!=pnum) {
               if(pos[j]==26 || pos[j]==34 || pos[j]==43)
                  return true;
            }
         }
      }
      
      else if(pos[pnum]==27 || pos[pnum]==35 || pos[pnum]==44) {//공통부분 30
         for(int j=1;j<=4;j++) {
            if(j!=pnum) {
               if(pos[j]==27 || pos[j]==35 || pos[j]==44)
                  return true;
            }
         }
      }
      
      else if(pos[pnum]==28 || pos[pnum]==36 || pos[pnum]==45) {//공통부분 35
         for(int j=1;j<=4;j++) {
            if(j!=pnum) {
               if(pos[j]==28 || pos[j]==36 || pos[j]==45)
                  return true;
            }
         }
      }
      
      else if(pos[pnum]==20 || pos[pnum]==29 || pos[pnum]==37 || pos[pnum]==46) {//공통부분 40
         for(int j=1;j<=4;j++) {
            if(j!=pnum) {
               if(pos[j]==20 || pos[j]==29 || pos[j]==37 || pos[j]==46)
                  return true;
            }
         }
      }
      
      for (int j = 1; j <= 4; j++) {
         if (j != pnum) {
        	 if(pos[j] == pos[pnum])
        		 return true;
         }
      }//일반적인 경우
      
      return false;
   }
}