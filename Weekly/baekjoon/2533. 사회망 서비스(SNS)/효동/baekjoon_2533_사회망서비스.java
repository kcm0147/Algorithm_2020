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
      //�׷��� �Է¹ޱ�
 
      for(int i=0;i<n+1;i++) {
    	  D[i][0]=0;
    	  D[i][1]=1;
      }
      //�ڽ��� �󸮾���������� ���� �⺻ ���� ������ �־����.
      
      dfs(1);

      System.out.println(Math.min(D[1][0], D[1][1]));
   }
   
   public static void dfs(int i) {
	  visited[i] = true; 
      
	  for(int j=0;j<list[i].size();j++) {
		  int child = list[i].get(j);
		  if(!visited[child]) {
			  visited[child]=true;
			  dfs(child);//�ǹغ��� �ö�;��ؼ� dfs�� ���.(bfs�� ������ ���� ��ȸ�ϰ� �������� ������ �������� �ʴ�.)
			  D[i][0]+=D[child][1];//�θ� �󸮾���Ͱ� �ƴѰ��:������ �ڽ��� �󸮾�����̴�.
			  D[i][1]+=Math.min(D[child][0], D[child][1]);//�θ� �󸮾������ ���: �ڽ��� ������ �������.
		  }
	  }
   }
}