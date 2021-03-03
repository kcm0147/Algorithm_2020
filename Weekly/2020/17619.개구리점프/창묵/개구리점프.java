import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;



public class Main{

  public static int[] parent;
  public static List<Node> nodeList;
  public static List<Node> tc;
  public static int N,Q;

  public static void main(String[] args) throws IOException {

    init();
    UnionAndFind();


    for (int i = 0; i < tc.size(); i++) {
      if(find(parent[tc.get(i).xleft])==find(parent[tc.get(i).xright]))
        System.out.println(1);
      else
        System.out.println(0);
    }

  }



  public static void init() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    N=Integer.parseInt(st.nextToken());
    Q=Integer.parseInt(st.nextToken());

    parent=new int[N+1];
    nodeList=new ArrayList<>();
    tc = new ArrayList<>();

    for (int i = 1; i <= N; i++) {
      parent[i]=i;
      st=new StringTokenizer(br.readLine());
      int xleft=Integer.parseInt(st.nextToken());
      int xright=Integer.parseInt(st.nextToken());
      int height =Integer.parseInt(st.nextToken());
      nodeList.add(new Node(xleft,xright,i));
    }

    Collections.sort(nodeList, new Comparator<Node>() {
      @Override
      public int compare(Node o1, Node o2) {
        if(o1.xleft>o2.xleft)
          return 1;
        else if(o1.xleft==o2.xleft){
          if(o1.xright>o2.xright)
            return 1;
          else
            return 0;
        }
        else
          return -1;
      }
    });


    for(int i=0;i<Q;i++){
      st = new StringTokenizer(br.readLine());
      int log1=Integer.parseInt(st.nextToken());
      int log2=Integer.parseInt(st.nextToken());
      tc.add(new Node(log1,log2,0));
    }

  }


  public static void UnionAndFind() {

    for (int i = 0; i < nodeList.size();i++) {
      for (int j = i+1; j < nodeList.size();j++) {

        Node log1 = nodeList.get(i);
        Node log2 = nodeList.get(j);

        if (log1.xleft <= log2.xleft && log1.xright >= log2.xleft) {
          union(log1.index, log2.index);
        } else
          break;

      }


    }
  }

  public static int find(int num){
    if(parent[num]==num) return num;
    else
      return find(parent[num]);
  }

  public static void union(int num1,int num2){
    int parent1=find(num1);
    int parent2=find(num2);

    if(parent1>parent2)
      parent[parent2]=parent1;
    else
      parent[parent1]=parent2;
  }

}

class Node {

  int xleft;
  int xright;
  int index;

  Node(int xleft, int xright,int index) {
    this.xleft=xleft;
    this.xright = xright;
    this.index=index;
  }
}
