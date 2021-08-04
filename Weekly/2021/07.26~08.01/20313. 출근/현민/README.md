# 느낀 점

1. 어려운 문제였다... 처음에 어떻게 해야하나 고민하다가 다익스트라의 dist를 2차원 배열로 두고 계산하면 될 거 같다는 생각을 했다.

2. 10^9 이 꽤 큰 숫자였다. 유심히 보지 않았다..

3. visited로 구현해보았는데 이 때 visited에 노드 번호와 매직 사용횟수를 둘 다 적어줘야한다

4. 물론 안써도 된다.

5. 노드안에 distance를 배열로 두었는데, 다른 풀이를 보니 u,v 만 저장하고 배열은 따로 빼놨던걸 보았다. 
인덱스로 배열에 접근한 걸 보고 놀랐다.

6. 골 3 문제가 아닌거 같다... 이런 유형의 문제를 더 풀어봐야겠다.


# 풀이

```java
public class Main {
   private static BufferedReader br;
   private static StringTokenizer st;

   private static List<List<Node>> adjList;
   private static int start;
   private static int end;
   private static int N;
   private static int K;

   public static void main(String[] args) throws IOException {
      init();
      System.out.println(solution());
   }

   private static long solution() {
      long[][] dist = new long[N + 1][K + 1];
      for (long[] d : dist) Arrays.fill(d, Long.MAX_VALUE);
      dist[start][0] = 0;

      PriorityQueue<PQNode> pq = new PriorityQueue<>(Comparator.comparingLong(PQNode::getDistance));
      pq.add(new PQNode(start, 0, 0));
      while (!pq.isEmpty()) {
         PQNode u = pq.poll();
         if (u.node == end) return u.distance;

         for (Node v : adjList.get(u.node)) {
            for (int magic = u.usedMagic; magic <= K; magic++) {
               long d = v.getDistance(magic);
               if (dist[u.node][u.usedMagic] + d < dist[v.node][magic]) {
                  dist[v.node][magic] = dist[u.node][u.usedMagic] + d;
                  pq.add(new PQNode(v.node, dist[v.node][magic], magic));
               }
            }
         }
      }

      return -1;
   }

   private static void init() throws IOException {
      N = nextInt();
      int M = nextInt();
      start = nextInt();
      end = nextInt();

      adjList = new ArrayList<>();
      for (int i = 0; i < N + 1; i++) adjList.add(new ArrayList<>());

      Point[] point = new Point[M]; // u, v를 기록하기 위함
      for (int i = 0; i < M; i++) {
         int u = nextInt(), v = nextInt(), d = nextInt();
         adjList.get(u).add(new Node(v, d));
         adjList.get(v).add(new Node(u, d));
         point[i] = new Point(u, v);
      }

      K = nextInt();
      for (int i = 0; i < K; i++) {
         for (int j = 0; j < M; j++) {
            int u = point[j].x, v = point[j].y;
            int d = nextInt();

            for (Node node : adjList.get(u)) {
               if (node.node == v) {
                  node.addDistance(d);
                  break;
               }
            }
            for (Node node : adjList.get(v)) {
               if (node.node == u) {
                  node.addDistance(d);
                  break;
               }
            }
         }
      }
   }

   private static int nextInt() throws IOException {
      if (br == null) br = new BufferedReader(new InputStreamReader(System.in));
      if (st == null || !st.hasMoreTokens()) st = new StringTokenizer(br.readLine());
      return Integer.parseInt(st.nextToken());
   }

   private static class Node {
      int node;
      List<Long> distList;

      public Node(int node, long distance) {
         this.node = node;
         distList = new ArrayList<>();
         addDistance(distance);
      }

      public void addDistance(long distance) {
         distList.add(distance);
      }

      public long getDistance(int i) {
         return distList.get(i);
      }
   }

   private static class PQNode {
      int node;
      long distance;
      int usedMagic;

      public PQNode(int node, long distance, int usedMagic) {
         this.node = node;
         this.distance = distance;
         this.usedMagic = usedMagic;
      }

      public long getDistance() {
         return distance;
      }
   }
}
```

1. dist 배열을 2차원으로 둔다. `dist[u][k]` 는 `마법을 k번 사용하고 u에 도달했을 때 최단거리` 이다.

2. 다익스트라에서 현재 노드에서 다음 노드로 갈때, edge를 한개만 넣는 것이 아니라 (현재 노드까지 사용한 마법 갯수 ~ K개) 개의 edge를 PQ에 넣는다.
