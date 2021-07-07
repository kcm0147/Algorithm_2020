# Dance Dance Revolution

## 느낀 점

dance dance revolution! 

## 풀이

```java
public class Main {
    private static int[] seq;
    private static int seqSize;
    private static int[][][] cache;

    // start, up, left, down, right -> 0, 1, 2, 3, 4
    private static int[][] cost = {
            //  u, r, d, l
            {-1, 2, 2, 2, 2}, // 0
            {-1, 1, 3, 4, 3}, // up
            {-1, 3, 1, 3, 4}, // left
            {-1, 4, 3, 1, 3}, // down
            {-1, 3, 4, 3, 1} // right
    };

    public static void main(String[] args) throws IOException {
        init();
        System.out.println(solve(0, 0,0));
    }

    private static int solve(int i, int left, int right){
        if(i >= seqSize) return 0;
        if(cache[i][left][right] != -1) return cache[i][left][right];

        int next = seq[i];
        return cache[i][left][right] = Math.min(cost[left][next] + solve(i+1, next, right)
                , cost[right][next] + solve(i+1, left, next));
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        seq = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        seqSize = seq.length-1; // 마지막 0 취급 안함

        cache = new int[seqSize][5][5];
        for(int[][] arr2d : cache) for(int[] arr : arr2d) Arrays.fill(arr, -1);
    }
}
```

1. dp(cost)를 활용해 문제를 풀었다. `dp[i][left][right]`는 i번째 밟아야하고, 왼발은 left, 오른발은 right에 위치할 때 최솟값이다.
   
2. `dp[i][left][right] = min of (cost[left][next] + dp[i+1][next][right], cost[right][next] + dp[i+1][left][next])`
