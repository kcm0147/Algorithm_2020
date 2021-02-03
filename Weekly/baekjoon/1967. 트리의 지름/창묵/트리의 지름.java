import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;



public class Main{


  public static List<Node>[] nodeList;

  public static void main(String[] argv) throws IOException {

    int answer=Integer.MIN_VALUE;

    makeTree();
    for(int i=1;i<nodeList.length;i++){
      if(nodeList[i].size()>=1){
        answer=Math.max(answer,BFS(i));
      }
    }

    System.out.println(answer);

  }

  public static void makeTree() throws IOException {

    StringTokenizer st;
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int NodeCnt=Integer.parseInt(br.readLine());
    int parentIndex,childIndex,weight;

    nodeList=new ArrayList[NodeCnt+1];

    for(int i=0;i<=NodeCnt;i++){
      nodeList[i]=new ArrayList<>();
    }



    for(int i=1;i<NodeCnt;i++){
      st=new StringTokenizer(br.readLine());

      parentIndex=Integer.parseInt(st.nextToken());
      childIndex=Integer.parseInt(st.nextToken());
      weight=Integer.parseInt(st.nextToken());

      nodeList[parentIndex].add(new Node(childIndex,weight,0));
    }

  }

  public static int BFS(int rootIndex){
    Queue<Node> que = new LinkedList<>();
    Integer[] sum=new Integer[nodeList[rootIndex].size()];
    Node temp;

    for(int i=0;i<nodeList[rootIndex].size();i++){
      Node child=nodeList[rootIndex].get(i);
      que.add(new Node(child.childIndex,child.sum,i));
      sum[i]=new Integer(0);
    }

    while(!que.isEmpty()){
      temp=que.poll();

      if(nodeList[temp.childIndex].size()==0){
        sum[temp.dir]=Math.max(sum[temp.dir],temp.sum);
        continue;
      }

      for(int i = 0; i < nodeList[temp.childIndex].size(); i ++)
      {
        Node insertNode=nodeList[temp.childIndex].get(i);
        que.offer(new Node(insertNode.childIndex,insertNode.sum+temp.sum,temp.dir));
      }

    }

    Arrays.sort(sum,Comparator.reverseOrder());
    if(nodeList[rootIndex].size()>1){
      return sum[0]+sum[1];
    }
    else{
      return sum[0];
    }
  }
}


class Node {

  int childIndex;
  int sum;
  int dir;

  Node(int childIndex, int sum, int dir) {
    this.childIndex = childIndex;
    this.sum = sum;
    this.dir = dir;
  }
}