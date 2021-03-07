package week.w31.boj1938;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static final int MAX_NUM = Integer.MAX_VALUE / 10;

    private static int[] upNums;
    private static int[] downNums;
    private static int[][][] cache;

    private static int N;
    private static int validSizeUp;
    private static int validSizeDown;

    public static void main(String[] args) {
        N = nextInt();
        upNums = new int[N];
        int idx=0;
        for(int i=0; i<N; i++){
            int num = nextInt();
            if(num == 0) continue;
            upNums[idx++] = num;
            validSizeUp++;
        }

        downNums = new int[N];
        idx = 0;
        for(int i=0; i<N; i++){
            int num = nextInt();
            if(num ==0) continue;
            downNums[idx++] = num;
            validSizeDown++;
        }

        cache = new int[N][N][N];
        for(int[][] c2d : cache) for(int[] c : c2d) Arrays.fill(c, -MAX_NUM);
        System.out.println(solve(0,0, 0));
    }

    private static int solve(int up, int down, int cur){
        if(up >= validSizeUp || down >= validSizeDown) return 0;

        if(cache[up][down][cur] != -MAX_NUM) return cache[up][down][cur];

        int ret = upNums[up] * downNums[down] + solve(up+1, down+1, cur+1);
        if(validSizeUp-up + cur < N) ret = Math.max(ret, solve(up, down+1, cur+1)); // 위 공간에 빈칸을 끼워넣을 수 있을 때
        if(validSizeDown-down + cur < N) ret = Math.max(ret, solve(up+1, down, cur+1)); // 아래 공간에 빈칸을 끼워넣을 수 있을 때
        return cache[up][down][cur] = ret;
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
