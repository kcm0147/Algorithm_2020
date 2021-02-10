import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Q3584 {
    static int n, parent[];
    static boolean cache[];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int tc = Integer.parseInt(st.nextToken());
        while(tc-- >0){
            n = Integer.parseInt(br.readLine());
            parent = new int[n + 1]; Arrays.fill(parent, -1);
            cache = new boolean[n + 1]; Arrays.fill(cache, false);
            for (int i = 0; i < n - 1; i++) {
                st = new StringTokenizer(br.readLine());
                int p = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                parent[c] = p;
            }
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            System.out.println(getRoot(a, b));
        }
    }

    private static int getRoot(int a, int b) {
        cache[a] = true;
        while(parent[a] != -1) {
            a = parent[a];
            cache[a] = true;
        }
        while(parent[b] != -1){
            if(cache[b]) return b;
            b = parent[b];
        }
        return a;
    }
}
/*
1
3
1 2
3 1
2 1
 */