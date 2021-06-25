# 풀이

## 코드

```java
package week.week0524.p16398;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    private static int n;
    private static int[][] edges;

    public static void main(String[] args) throws IOException {
        init();
        long result = solve();
        System.out.println(result);
    }

    private static void init() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(bufferedReader.readLine().split(" ")[0]);

        edges = new int[n][n];
        for(int i=0; i<n; i++){
            String[] s = bufferedReader.readLine().split(" ");
            for(int j=0; j<n; j++){
                edges[i][j]= Integer.parseInt(s[j]);
            }
        }
    }

    private static long solve(){
        long ret =0;
        int[] minweight = new int[n];
        for(int i=0; i<n; i++) minweight[i] = edges[0][i];

        int[] isAdded = new int[n];
        Arrays.fill(isAdded, 0);
        isAdded[0] = 1;

        while(Arrays.stream(isAdded).sum() < n){
            int min = Integer.MAX_VALUE-1, v=-1;
            for(int i=0; i<n; i++){
                if(isAdded[i] == 0 && minweight[i] < min){
                    v = i;
                    min = minweight[i];
                }
            }

            isAdded[v] = 1;
            ret += min;
            for(int i=0; i<n; i++) minweight[i] = Math.min(minweight[i], edges[v][i]);
        }

       return ret;
    }
}
```