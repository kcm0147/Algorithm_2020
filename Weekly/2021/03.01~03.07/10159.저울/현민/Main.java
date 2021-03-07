package week.w31.boj10159;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static BufferedReader br;
    private static StringTokenizer st;

    private static int[][] bigger; // 1 : true, 0 : false, -1 : i dont know
    private static int n;

    public static void main(String[] args) throws IOException {
        n = nextInt();
        int m = nextInt();

        bigger = new int[n+1][n+1];
        for(int[] b : bigger) Arrays.fill(b, -1);
        for(int i=0; i<m; i++){
            int u = nextInt(), v = nextInt();
            bigger[u][v] = 1;
            bigger[v][u] = 0;
        }

        calcBigger();

        for(int u=1; u<=n; u++){
            int unknown=0;
            for(int v=1; v<=n; v++){
                if(u == v) continue;
                if(bigger[u][v] == -1) unknown++;
            }
            System.out.println(unknown);
        }
    }

    private static void calcBigger(){
        for(int k=1; k<=n; k++){
            for(int i=1; i<=n; i++){
                for(int j=1; j<=n; j++){
                    if(i == j) continue;
                    int value = -1;
                    if(bigger[i][k] == 1 && bigger[k][j] == 1) value = 1;
                    else if(bigger[i][k] == 0 && bigger[k][j] == 0) value = 0;
                    bigger[i][j] = Math.max(bigger[i][j], value);
                }
            }
        }
    }

    private static int nextInt() throws IOException { return Integer.parseInt(nextToken()); }

    private static String nextToken() throws IOException {
        if(br == null) br = new BufferedReader(new InputStreamReader(System.in));
        if(st == null || !st.hasMoreTokens())
            st = new StringTokenizer(br.readLine());
        return st.nextToken();
    }
}
