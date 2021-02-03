import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Q12869 {
    static int n, arr[], cache[][][], dmg[] = {0, 9, 3, 1};
    public static void main(String[] args) throws IOException {
        init();
        System.out.println(solution(arr[0], arr[1], arr[2]));
    }

    private static int solution(int one, int two, int three) {
        if(one <= 0 && two <= 0 && three <= 0) return 0;
        one = (one < 0) ? 0 : one; two = (two < 0) ? 0 : two; three = (three < 0) ? 0 : three;
        if(cache[one][two][three] > 0) return cache[one][two][three];
        int ans = 99999;
        for (int i = 1; i <= 3; i++) {
            for (int j = 1; j <= 3; j++) {
                if(i == j) continue;
                for (int k = 1; k <= 3; k++) {
                    if(k != i && k != j) ans = Math.min(ans, 1 + solution(one - dmg[i], two - dmg[j], three - dmg[k]));
                }
            }
        }
        return cache[one][two][three] = ans;
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        arr = new int[3]; cache = new int[61][61][61];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) arr[i] = Integer.parseInt(st.nextToken());
        for (int i = 0; i < 61; i++) {
            for (int j = 0; j < 61; j++) {
                Arrays.fill(cache[i][j], -1);
            }
        }
    }
}
