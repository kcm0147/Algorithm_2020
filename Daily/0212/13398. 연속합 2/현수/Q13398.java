import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Q13398 {
    static int n, arr[], cache[][], pSum[];
    public static void main(String[] args) throws IOException {
        init();
        solution();
    }
    static void solution() {
        cache[n-1][0] = cache[n-1][1] = arr[n-1];
        for (int i = n-2; i >= 0; --i) {
            int stop = arr[i], proceed = arr[i] + cache[i + 1][0];
            cache[i][0] = Math.max(stop, proceed);
            proceed = Math.max(cache[i + 1][0], arr[i] + cache[i + 1][1]);
            cache[i][1] = Math.max(stop, proceed);
        }
        int ans = -987654321;
        for (int i = 0; i < n; i++) {
            int max = Math.max(cache[i][0], cache[i][1]);
            ans = Math.max(ans, max);
        }
        System.out.println(ans);
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n]; cache = new int[n][2];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            Arrays.fill(cache[i], 0);
        }
    }
}
/*
10
1 -1 1 -1 1 -1 1 -1 1 -1
 */