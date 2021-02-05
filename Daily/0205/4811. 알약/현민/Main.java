package solve4811;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main10836(String[] args) throws IOException{
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))){
            Solution solution = new Solution();
            for(int aCase=0; aCase < 1000; aCase++){
                StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
                int input = Integer.parseInt(stringTokenizer.nextToken());
                if(input == 0) break;
                System.out.println(solution.solve(input));
            }
        }
    }

    private static class Solution{
        private static final int MAX_MEDICINE = 30;
        private long[][] cache = new long[MAX_MEDICINE+1][MAX_MEDICINE+1];

        private void initCache(){
            for(long[] c : cache)
                Arrays.fill(c, -1);
        }


        private long solve(int w, int h){
            if(w <= 0) return 1;

            if(cache[w][h] != -1) return cache[w][h];

            long ret =0;
            if(w < h) ret = solve(w-1, h) + solve(w, h-1);
            else ret = solve(w-1, h);

            cache[w][h] = ret;
            return ret;
        }

        long solve(int n){
            initCache();
            return solve(n,n);
        }
    }
}
