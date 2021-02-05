import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Q4811 {
    static int n;
    static long cache[][];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while(true){
            n = Integer.parseInt(br.readLine());
            if(n == 0) break;
            cache = new long[31][31];
            for (int i = 0; i < 31; i++)
                Arrays.fill(cache[i], -1); // max : 31 * 30
            System.out.println(solution(n, 0));
        }

    }

    private static long solution(int total, int half) {
        if(total == 0 && half == 0) return 1;
        if(cache[total][half] != -1) return cache[total][half];
        long ret = 0;
        if(total > 0)
            ret += solution(total - 1, half + 1);
        if(half > 0)
            ret += solution(total, half - 1);
        return cache[total][half] = ret;
    }
}
