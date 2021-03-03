import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {


  static char[][] map;
  static boolean[][] check;
  static boolean[][] visit;
  static int N, M;
  static int cnt;

  static final int DIVIDE=1000000007;

  public static void main(String argv[]) throws IOException {

    init();
    bfs(0, 0);
    int result=1;

    for(int i=1;i<=cnt;i++){
      result=result*2;
      if(result>DIVIDE){
        result%=DIVIDE;
      }
    }

    System.out.println(result);

  }

  public static void init() throws IOException {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    map = new char[N][M];
    visit = new boolean[N][M];
    check = new boolean[N][M];
    cnt = N*M;

    for (int row = 0; row < N; row++) {
      String temp = br.readLine();
      for (int col = 0; col < M; col++) {
        map[row][col] = temp.charAt(col);
      }
    }

  }

  public static void bfs(int x, int y) {
    Queue<Pair<Integer, Integer>> que = new LinkedList<>();
    que.offer(new Pair<>(x, y)); // que 사이즈 문제.
    visit[x][y]=true;

    int[][] dir = {{0, 1},{1, 0}};

    while (!que.isEmpty()) {
      Pair<Integer, Integer> cur = que.poll();
      char curColor = map[cur.left][cur.right];

      for (int i = 0; i < 2; i++) {
        int nx = cur.left + dir[i][0];
        int ny = cur.right + dir[i][1];

        if (nx < 0 || nx >= N || ny < 0 || ny >= M)
          continue;

        if (curColor != map[nx][ny]) {
          if(check[nx][ny]==false) {
            check[nx][ny]=true;
            cnt--;
          }
          if (check[cur.left][cur.right] == false) {
            check[cur.left][cur.right] = true;
            cnt--;
          }
        }

        if (visit[nx][ny] == false) {
          visit[nx][ny] = true;
          que.offer(new Pair<>(nx, ny));
        }

      }

    }

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
