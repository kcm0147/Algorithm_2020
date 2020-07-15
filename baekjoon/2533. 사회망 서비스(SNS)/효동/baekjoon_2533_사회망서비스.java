import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
   static LinkedList<Integer> list[];
   static boolean[] visited;
   static int[][] D;
   public static void main(String args[]) throws IOException{
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      int n = Integer.parseInt(br.readLine());
      
      list = new LinkedList[n+1];
      
      for(int i=0;i<=n;i++)
         list[i] = new LinkedList<>();

      visited = new boolean[n+1];
      Arrays.fill(visited, false);
      
      D = new int[n+1][2];
      
      for(int i=0;i<n-1;i++) {
        String str = br.readLine();
         StringTokenizer st = new StringTokenizer(str, " ");
         
         int a = Integer.parseInt(st.nextToken());
         int b = Integer.parseInt(st.nextToken());
         
         list[a].add(b);
         list[b].add(a);
         
      }
      //그래프 입력받기
 
      for(int i=0;i<n+1;i++) {
    	  D[i][0]=0;
    	  D[i][1]=1;
      }
      //자신이 얼리어답터인지에 따라 기본 값을 가지고 있어야함.
      
      dfs(1);

      System.out.println(Math.min(D[1][0], D[1][1]));
   }
   
   public static void dfs(int i) {
	  visited[i] = true; 
      
	  for(int j=0;j<list[i].size();j++) {
		  int child = list[i].get(j);
		  if(!visited[child]) {
			  visited[child]=true;
			  dfs(child);//맨밑부터 올라와야해서 dfs를 사용.(bfs는 위부터 전부 순회하고 내려오기 때문에 적합하지 않다.)
			  D[i][0]+=D[child][1];//부모가 얼리어답터가 아닌경우:무조건 자식은 얼리어답터이다.
			  D[i][1]+=Math.min(D[child][0], D[child][1]);//부모가 얼리어답터인 경우: 자식은 뭐든지 상관없다.
		  }
	  }
   }
}