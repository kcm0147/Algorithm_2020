import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import javafx.util.Pair;



public class Main{

  public static void main(String[] argv) throws IOException {

    Solution sol = new Solution();
    sol.init();
    sol.bfs();
    System.out.println(sol.getResult());
  }

}


class Solution{

  private int mapSize;
  private int result;

  private char[][] map;
  private boolean[][][] visit;

  private Node start;
  private Node endPoint;

  private final int vertical = 1;
  private final int horizon = 0;

  public int getResult() {
    return result;
  }

  public void init() throws IOException { // init startPoint and Map

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    mapSize = Integer.parseInt(br.readLine());
    map=new char[mapSize][mapSize];
    visit= new boolean[mapSize][mapSize][2];

    List<Pair<Integer,Integer>> pointS = new ArrayList<>();
    List<Pair<Integer,Integer>> pointE = new ArrayList<>();

    for(int row=0;row<mapSize;row++){ // set Map.
      String st=br.readLine();

      for(int col=0;col<mapSize;col++){
        map[row][col]=st.charAt(col);

        if(map[row][col]=='B'){
          pointS.add(new Pair<>(row,col));
        }
        else if(map[row][col]=='E'){
          pointE.add(new Pair<>(row,col));
        }

      }
    }

    if(pointS.get(0).getKey()==pointS.get(1).getKey()) { // equal row at Start Point
      this.start = new Node(horizon,pointS.get(1),0);
    }
    else{ // eqaul col
      this.start = new Node(vertical,pointS.get(1),0);
    }

    if(pointE.get(0).getKey()==pointE.get(1).getKey()){ // equal row at End point
      this.endPoint=new Node(horizon,pointE.get(1),0);
    }
    else{
      this.endPoint = new Node(vertical,pointE.get(1),0);
    }


  }

  public void bfs(){

    Queue<Node> que = new LinkedList<>();
    int[][] dir={{0,1},{0,-1},{1,0},{-1,0}};
    int x,y,nx,ny;

    que.offer(start);
    visit[start.middlePoint.getKey()][start.middlePoint.getValue()][start.dir]= true;

    while(!que.isEmpty()){
      Node cur=que.poll();
      x=cur.middlePoint.getKey();
      y=cur.middlePoint.getValue();

      if(x==endPoint.middlePoint.getKey() && y==endPoint.middlePoint.getValue()){ // endPoint의 middle point와 같으면 그떄 return
        this.result=cur.depth;
        break;
      }

      for(int i=0;i<dir.length;i++){
        nx=x+dir[i][0];
        ny=y+dir[i][1];

        if(boundCheck(nx,ny,cur.dir)){ // 상 하 좌 우 통나무 이동이 가능한지 체크
          que.offer(new Node(cur.dir,new Pair<>(nx,ny),cur.depth+1));
        }
      }

      if(roateCheck(x,y,cur.dir)){ // 회전을 했을 때 이동이 가능한지 체크
        if(cur.dir==horizon) // dir 변경해야하니깐 change
          cur.dir=vertical;
        else
          cur.dir=horizon;
        que.offer(new Node(cur.dir,new Pair<>(x,y),cur.depth+1)); //
      }

    }

  }

  public boolean boundCheck(int x2,int y2,int dir){
    int x1,y1,x3,y3;

    if(dir==horizon){
      x1=x2; y1=y2-1;
      x3=x2; y3=y2+1;
    }
    else{
      x1=x2-1; y1=y2;
      x3=x2+1; y3=y2;
    }


    if(x1<0|| (x3>=mapSize) || y1<0 || (y3>=mapSize) ) // x1은 무조건 위 아니면 왼쪽으로 bound out 이고 x3 y3는 오른쪽으로 bound out
      return false;


    if(map[x1][y1]=='1' || map[x2][y2]=='1' || map[x3][y3]=='1') // 통나무 있으면 체크 X
      return false;

    if(visit[x2][y2][dir]){
      return false;
    }
    else{
      visit[x2][y2][dir]=true;
    }

    return true;

  }

  public boolean roateCheck(int x2,int y2,int dir){
    int[][] htov={{-1,-1},{-1,0},{-1,1},{1,-1},{1,0},{1,1}}; // horizon to vertical check
    int[][] vtoh={{-1,-1},{0,-1},{1,-1},{-1,1},{0,1},{1,1}}; // vertical to horizon check
    int nx,ny;

    if(dir==horizon){

      for(int i=0;i<htov.length;i++) {
        nx = x2 + htov[i][0];
        ny = y2 + htov[i][1];

        if (nx < 0 || nx >= mapSize || ny < 0 || ny >= mapSize || map[nx][ny] == '1') { // bound out or 통나무 존재
          return false;
        }
        dir=vertical;
      }

    }
    else if(dir==vertical){
      for(int i=0;i<vtoh.length;i++){
        nx=x2+vtoh[i][0];
        ny=y2+vtoh[i][1];

        if(nx<0 || nx>=mapSize || ny<0 || ny>=mapSize || map[nx][ny]=='1') {
          return false;
        }
      }
      dir=horizon;
    }

    if(visit[x2][y2][dir]){ // visit check.
      return false;
    }
    else{
      visit[x2][y2][dir]=true;
    }

    return true;
  }


  class Node{
    int dir;
    Pair<Integer,Integer> middlePoint;
    int depth;

    Node(int dir,Pair<Integer,Integer> point,int depth){
      this.dir=dir;
      this.middlePoint=point;
      this.depth=depth;
    }
  }

}