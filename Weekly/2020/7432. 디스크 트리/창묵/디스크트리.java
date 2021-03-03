import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main{


  public static void main(String[] argv) throws IOException {

    Solution sol = new Solution();
    sol.init();
    sol.printResult(sol.getRoot());

  }

}

class Solution{

  private int caseCnt;
  private Node root;

  public Node getRoot() {
    return root;
  }

  public void init() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    this.caseCnt=Integer.parseInt(br.readLine());
    root=new Node(new TreeMap<>(),-1);

    for(int i=0;i<caseCnt;i++){
      StringTokenizer st = new StringTokenizer(br.readLine());
      parsing(st.nextToken());
    }
  }

  public void parsing(String cur){

    StringTokenizer st = new StringTokenizer(cur);
    Node scan=root;

    while(st.hasMoreTokens()) {

      String curDirName = st.nextToken("\\");

      if (!scan.dirTable.containsKey(curDirName)) {
        scan.dirTable.put(curDirName,new Node(new TreeMap<>(),scan.depth+1));
      }
      scan=scan.dirTable.get(curDirName);

    }


  }

  public void printResult(Node cur){ // 출력부분
    TreeMap<String,Node> curMap = cur.dirTable;
    Iterator<String> iteratorKey = curMap.keySet().iterator();

    while(iteratorKey.hasNext()){
      String curString = iteratorKey.next();

      int depth = curMap.get(curString).depth;

      for(int i=0;i<depth;i++){
        System.out.printf(" ");
      }
      System.out.println(curString);

      printResult(curMap.get(curString));

    }

  }

  class Node{
    TreeMap<String, Node> dirTable;
    int depth;

    Node(TreeMap<String, Node> dir, int depth){
      this.dirTable=dir;
      this.depth=depth;
    }

  }
}

