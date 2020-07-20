import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;



public class Main {

  public static final int early=1;
  public static final int notEarly=0;
  public static List<Integer>[] edge;

  public static int[][] dp;

  public static void main(String[] argv) throws IOException {

    int answer;

    makeList();
    answer=Math.min(Dp(1,early,1),Dp(1,notEarly,1));

    System.out.println(answer);

  }


  public static int Dp(int index,int state,int parentIndex){

    int result=0;

    if(dp[index][state]!=-1) return dp[index][state];

    if(edge[index].size()==0 && state==notEarly) return dp[index][state]=0;
    if(edge[index].size()==0 && state==early) return dp[index][state]=1;

    if(state==early) {

      for (int i = 0; i < edge[index].size(); i++) {
        int nextIndex = edge[index].get(i);
        if(parentIndex==nextIndex) continue;
        result += Math.min(Dp(nextIndex, early,index), Dp(nextIndex, notEarly,index));
      }

      return dp[index][state]=result+1;

    }
    else{

      for (int i = 0; i < edge[index].size(); i++) {

        int nextIndex = edge[index].get(i);
        if(parentIndex==nextIndex) continue;
        result += Dp(nextIndex, early,index);

      }

      return dp[index][state]=result;


    }

  }

  public static void makeList() throws IOException {

    StringTokenizer st;
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int NodeCnt = Integer.parseInt(br.readLine());
    edge=new LinkedList[NodeCnt+1];

    dp=new int[NodeCnt+1][2];
    int parentIndex, childIndex;


    for(int i=0;i<=NodeCnt;i++) {
      edge[i] = new LinkedList<>();
      Arrays.fill(dp[i],-1);
    }

    for (int i = 2; i <= NodeCnt; i++) {
      st = new StringTokenizer(br.readLine());
      parentIndex = Integer.parseInt(st.nextToken());
      childIndex = Integer.parseInt(st.nextToken());

      edge[parentIndex].add(childIndex);
      edge[childIndex].add(parentIndex);
      }

    }


}