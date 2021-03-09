package week.w31.boj2718;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static BufferedReader br;
    private static StringTokenizer st;

    private static int[][] cache;

    public static void main(String[] args) {
        int T = nextInt();
        cache = new int[500][13];
        for(int[] c : cache) Arrays.fill(c, -1);
        for(int test=0; test<T; test++){
            System.out.println(calc(nextInt(),0));
        }
    }

    private static int calc(int n, int bit){
        if(n < 0) return 0; // 경우로 만들 수 없다.
        if(n == 0) return bit == 0 ? 1 : 0; //반드시 비어있어야 한다.

        if(cache[n][bit] != -1) return cache[n][bit];

        int ret = 0;
        switch (bit){
            case 0: ret = calc(n-1, 0) + calc(n-1,3) + calc(n-1,9) + calc(n-1, 12) + calc(n-2, 0);
                    break;
            case 3: ret = calc(n-1, 0) + calc(n-1, 12);
                    break;
            case 6: ret = calc(n-1, 9);
                    break;
            case 9: ret = calc(n-1, 0) + calc(n-1, 6);
                    break;
            case 12: ret = calc(n-1, 0) + calc(n-1, 3);
                    break;
        }

        return cache[n][bit] = ret;
    }


    private static int nextInt() { return Integer.parseInt(nextToken()); }

    private static String nextToken(){
        if(br == null) br = new BufferedReader(new InputStreamReader(System.in));
        if(st == null || !st.hasMoreTokens()) {
            try {
                st = new StringTokenizer(br.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return st.nextToken();
    }
}
