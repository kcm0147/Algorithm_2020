# 느낀 점

1. 당황스러웠다. 다익스트라 몇 번 쓰면 될거 같아서 그렇게 하니 정말 풀렸다.

# 풀이

```java
public class Main {
    private static BufferedReader br;
    private static StringTokenizer st;

    private static List<List<Node>> adjList;
    private static int N;
    private static int v1;
    private static int v2;

    public static void main(String[] args) throws IOException {
        init();
        System.out.println(solution());
    }

    private static int solution(){
        int path1to2 = dijkstra(1, v1) + dijkstra(v1, v2) + dijkstra(v2, N);
        int path2to1 = dijkstra(1, v2) + dijkstra(v2, v1) + dijkstra(v1, N);

        if(path1to2 < 0 && path2to1 < 0) return -1;
        return Math.min(path1to2, path2to1);
    }

    private static int dijkstra(int start, int end){
        int[] dist = new int[N+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(Node::getDistance));
        pq.add(new Node(start, 0));

        while(!pq.isEmpty()){
            Node u = pq.poll();
            if(u.num == end) return dist[u.num];

            for(Node v : adjList.get(u.num)){
                if(dist[u.num] + v.distance < dist[v.num]){
                    dist[v.num] = dist[u.num] + v.distance;
                    pq.add(new Node(v.num, dist[v.num]));
                }
            }
        }

        return Integer.MIN_VALUE / 4;
    }

    private static void init() throws IOException {
        N = nextInt();
        adjList = new ArrayList<>();
        for(int i=0; i<N+1; i++) adjList.add(new ArrayList<>());

        int edgeSize = nextInt();
        for(int i=0; i<edgeSize; i++){
            int u = nextInt(), v = nextInt(), d = nextInt();
            adjList.get(u).add(new Node(v, d));
            adjList.get(v).add(new Node(u, d));
        }

        v1 = nextInt(); v2 = nextInt();
    }

    private static int nextInt() throws IOException {
        if(br == null) br = new BufferedReader(new InputStreamReader(System.in));
        if(st == null || !st.hasMoreTokens()) st = new StringTokenizer(br.readLine());
        return Integer.parseInt(st.nextToken());
    }

    private static class Node{
        private int num;
        private int distance;

        public Node(int vertex, int distance) {
            this.num = vertex;
            this.distance = distance;
        }

        public int getNum() {return num;}
        public int getDistance() {return distance;}
    }
}
```

1. (1,V1) + (V1, V2) + (V2, N) 과 (1, V2) + (V2, V1) + (V1, N) 중에서 작은 값을 선택한다.

2. 길이 없을 수도 있다는 조건이 있으므로, 다익스트라에서 길을 발견 못할시 int의 최솟값 / 4를 반환한다.