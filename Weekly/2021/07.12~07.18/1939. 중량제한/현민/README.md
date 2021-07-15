# 중량제한

## 느낀점 

하하핫... 새로운 걸 써보다가 시간초과가 나서 당황스러웠다.

알고리즘이 잘못 된거 같아서 다른 풀이를 보고 이분탐색+bfs로 풀었는데 이것도 시간초과가 났다.

결국 혹시나 하고 했던걸 바꿨더니 해결되었다.

1번째 알고리즘도 문제가 아니었었다. 그래서 2개로 풀게 되었다.

하지만 이분탐색 + bfs를 오랫만에 풀어보게 되어 굉장히 좋은 경험이었다.

`Collections.ncopy()` 이거는 쓰지 말자. 매우 시간이 오래 걸린다.

## 코드

### 1번째 풀이

```java
public class Main {
    private static BufferedReader br;
    private static StringTokenizer st;

    private static int N;
    private static int start;
    private static int end;
    private static List<List<Node>> adjList;

    public static void main(String[] args) throws IOException {
        init();
        System.out.println(solve());
    }

    private static int solve(){
        boolean[] visited = new boolean[N+1];
        Arrays.fill(visited,false);
        int[] capacity = IntStream.iterate(-1, i->i).limit(N+1).toArray();

        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(Node::getDist).reversed());
        pq.add(new Node(start, Integer.MAX_VALUE-1));
        while(!pq.isEmpty()){
            Node u = pq.poll();
            visited[u.node] = true;
            if(u.node == end) return u.dist;

            for(Node v : adjList.get(u.node)){
                int c = Math.min(v.dist, u.dist);
                if(visited[v.node] || capacity[v.node] >= c) continue;
                capacity[v.node] = c;
                pq.add(new Node(v.node,c));
            }
        }

        return -1;
    }

    private static void init() throws IOException {
        N = nextInt(); int E = nextInt();
        adjList = new ArrayList<>();
        for(int i=0; i<N+1; i++) adjList.add(new ArrayList<>());

        for(int i=0; i<E; i++){
            int u = nextInt(), v = nextInt(), d = nextInt();
            adjList.get(u).add(new Node(v,d));
            adjList.get(v).add(new Node(u,d));
        }
        start = nextInt(); end = nextInt();
    }

    private static int nextInt() throws IOException {
        if(br == null) br = new BufferedReader(new InputStreamReader(System.in));
        if(st == null || !st.hasMoreTokens()) st = new StringTokenizer(br.readLine());
        return Integer.parseInt(st.nextToken());
    }

    static class Node{
        private int node;
        private int dist;

        public Node(int vertex, int dist) {
            this.node = vertex;
            this.dist = dist;
        }
        public int getDist() { return dist; }
    }
}
```

1. 다익스트라를 변형한 알고리즘이다.

2. 우선순위 큐를 capacity가 큰 순서대로 반환하도록 하였다.

3. capacity[i] 는 start에서 i번까지 오는 가능한 제일 큰 용량이다.

4. u에서 v로 갈 때, capacity[v] 보다 u.dist(capacity)가 크면 갱신해주고, 이를 큐에 넣는다.

5. end를 찾으면 용량을 반환하고 끝난다.

### 2번째 풀이
```java
public class Main {
    private static BufferedReader br;
    private static StringTokenizer st;

    private static int N;
    private static int start;
    private static int end;
    private static int maxCapacity=-1;
    private static List<List<Node>> adjList;

    public static void main(String[] args) throws IOException {
        init();
        System.out.println(solve());
    }

    private static int solve(){
        int left=1, right=maxCapacity;
        int ret=-1;
        while(left<=right){
            int mid = (left+right) / 2;
            if(bfs(mid)){
                left = mid + 1;
                ret=Math.max(mid, ret);
            }else{
                right = mid -1;
            }
        }
        return ret;
    }

    private static boolean bfs(int capacity){
        Queue<Integer> q = new LinkedList<>(){{add(start);}};
        boolean[] visited = new boolean[N+1];
        visited[start] = true;
        while(!q.isEmpty()){
            int u = q.poll();

            if(u == end) return true;
            for(Node v : adjList.get(u)){
                if(visited[v.node] || v.dist < capacity) continue;
                visited[v.node] = true;
                q.add(v.node);
            }
        }
        return false;
    }

    private static void init() throws IOException {
        N = nextInt(); int E = nextInt();
        adjList = new ArrayList<>();
        for(int i=0; i<N+1; i++) adjList.add(new ArrayList<>());

        for(int i=0; i<E; i++){
            int u = nextInt(), v = nextInt(), d = nextInt();
            adjList.get(u).add(new Node(v,d));
            adjList.get(v).add(new Node(u,d));
            maxCapacity = Math.max(maxCapacity, d);
        }
        start = nextInt(); end = nextInt();
    }

    private static int nextInt() throws IOException {
        if(br == null) br = new BufferedReader(new InputStreamReader(System.in));
        if(st == null || !st.hasMoreTokens()) st = new StringTokenizer(br.readLine());
        return Integer.parseInt(st.nextToken());
    }

    static class Node{
        private int node;
        private int dist;

        public Node(int vertex, int dist) {
            this.node = vertex;
            this.dist = dist;
        }
    }
}
```

1. bfs는 어떤 capacity로 start에서 end로 통과할 수 있는 path가 있으면 true 아니면 false를 반환한다.

2. binary search를 통해서 1부터 edge 중 제일 큰 용량값 사이를 탐색하며, bfs값이 true인 제일 큰 값을 찾는다. 