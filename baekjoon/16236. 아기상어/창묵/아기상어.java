import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;



public class Main {


  static int[][] map;
  static int N;
  static int fishCnt;

  static List<Pair<Integer,Integer>> fishIndex=new ArrayList<>();

  static sharkState shark;

  public static void main(String argv[]) throws IOException {

    init();

    while(fishCnt!=0) {
      if(!bfs())
        break;
    }

    System.out.println(shark.depth);


  }

  public static void init() throws IOException {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    N=Integer.parseInt(br.readLine());
    map=new int[N][N];

    for(int row=0;row<N;row++) {
      st=new StringTokenizer(br.readLine());
      for (int col = 0; col < N; col++) {
        map[row][col]=Integer.parseInt(st.nextToken());
        if(map[row][col]==9){
          map[row][col]=0;
          shark=new sharkState(new Pair<>(row,col),2,0,0);
        }
        else if(map[row][col]!=0){
          fishIndex.add(new Pair<>(row,col));
          fishCnt++;
        }
      }
    }

  }
  // 더이상 잡을 수 있는 물고기가 존재하지 않음을 확인하는 방법...

  public static boolean bfs(){

    int[][] dir= {{-1,0},{0,-1},{1,0},{0,1}};
    boolean[][] visit=new boolean[N][N];


    PriorityQueue<sharkState> que = new PriorityQueue<>(new myComparator());

    que.offer(shark);
    visit[shark.pos.left][shark.pos.right]=true;

    while(!que.isEmpty()){ // 고기잡는거 탐색해야한다...
      sharkState cur=que.poll();

      if(map[cur.pos.left][cur.pos.right]>0 && cur.weight>map[cur.pos.left][cur.pos.right]){ // 맵에 물고기가 존재하면 물고기를 제거
        fishCnt--;
        cur.eatFish++;
        map[cur.pos.left][cur.pos.right]=0;

        if(cur.eatFish==cur.weight){
          cur.weight++;
          cur.eatFish=0;
        }
        shark=new sharkState(new Pair<>(cur.pos.left,cur.pos.right),cur.weight,cur.depth,cur.eatFish);
        return true;
      }

      for(int i=0;i<4;i++){

        int nx=cur.pos.left+dir[i][0];
        int ny=cur.pos.right+dir[i][1];

        if(nx<0 || nx>=N || ny<0 || ny>=N || map[nx][ny]>cur.weight || visit[nx][ny]==true)
          continue;

        if(map[nx][ny]<=cur.weight){ // 움직일 수 있으면 큐에 등록
          visit[nx][ny]=true;
          que.offer(new sharkState(new Pair<>(nx,ny),cur.weight,cur.depth+1,cur.eatFish));
        }

      }

    }

    return false;
  }

}

class sharkState {

  Pair<Integer, Integer> pos;
  int weight;
  int depth;
  int eatFish;

  sharkState(Pair<Integer, Integer> pos, int weight, int depth, int eatFish) {
    this.pos = pos;
    this.weight = weight;
    this.depth = depth;
    this.eatFish = eatFish;
  }


}

class Pair<L,R> {

  final L left;
  final R right;

  public Pair(L left, R right) {
    this.left = left;
    this.right = right;
  }

  static <L, R> Pair<L, R> of(L left, R right) {
    return new Pair<L, R>(left, right);
  }
}

class myComparator implements Comparator<sharkState> {
  public int compare(sharkState i1, sharkState i2) { // 음수나 0은 자리를 그대로 양수는 자리를 바꾼다.
    if (i1.depth < i2.depth)
      return -1; // i1이 i2보다 depth가 작으면 그대로 두고
    else if (i1.depth == i2.depth) {

      if (i1.pos.left < i2.pos.left) { // x좌표가 작으면 그대로 둔다.
        return -1;
      }
      else if (i1.pos.left == i2.pos.left) {
        if (i1.pos.right < i2.pos.right) {
          return -1;
        }
        else if (i1.pos.right == i2.pos.right) {
          return -1;
        }
        return 1;
      }
      else {
        return 1;
      }

    }
    else
      return 1; //i1 이 i2보다 depth가 크면 자리를 바꾼다.
  }
}