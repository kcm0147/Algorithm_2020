import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Edge{
    int dst, cost;

    public Edge(int dst, int cost) {
        this.dst = dst;
        this.cost = cost;
    }
}
public class Q10282 {
    static final int ERROR = 987654321;
    static int n, d, c, cost[];
    static List<Edge> adj[];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int tc = Integer.parseInt(st.nextToken());
        while(tc-- >0){
            init(br);
            solution(c);
        }
    }

    private static void solution(int start) {
        cost[start] = 0;
        Queue<Integer> pq = new LinkedList<>();
        pq.add(start);
        while(!pq.isEmpty()){
            int here = pq.poll();
            for (int i = 0; i < adj[here].size(); i++) {
                Edge next = adj[here].get(i);
                if(cost[next.dst] > cost[here] + next.cost) {
                    cost[next.dst] = cost[here] + next.cost;
                    pq.add(next.dst);
                }
            }
        }
        int count = 0, time = -1;
        for (int i = 1; i <= n; i++) {
            if(cost[i] != ERROR){
                count += 1;
                time = Math.max(time, cost[i]);
            }
        }
        System.out.println(count + " " + time);
    }

    private static void init(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        adj = new List[n + 1]; cost = new int[n + 1];
        Arrays.fill(cost, ERROR);

        for (int i = 1; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }

        int src = 0, dst = 0, co = 0;
        for (int i = 0; i < d; i++) {
            st = new StringTokenizer(br.readLine());
            dst = Integer.parseInt(st.nextToken());
            src = Integer.parseInt(st.nextToken());
            co = Integer.parseInt(st.nextToken());
            adj[src].add(new Edge(dst, co));
        }
    }
}