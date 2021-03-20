# 6087 레이저 통신

## 코드

```java
package week.w32.b18427;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static BufferedReader br;
    private static StringTokenizer st;

    private static int cache[][];
    private static int blocks[][];

    private static final int MOD = 10007;

    public static void main(String[] args) {
        int numofStudent = nextInt(), numOfCanBlock = nextInt(), height = nextInt();

        blocks = new int[numofStudent][numOfCanBlock];
        for (int i = 0; i < numofStudent; i++) {
            String[] numbers = nextLine().split(" ");
            for (int j = 0; j < numOfCanBlock; j++)
                if (j < numbers.length) blocks[i][j] = Integer.parseInt(numbers[j]);
                else blocks[i][j] = -1;
        }

        cache = new int[numofStudent][height + 1];
        for (int[] c : cache) Arrays.fill(c, -1);

        System.out.println(solve(0, height));
    }

    private static int solve(int student, int height) {
        if (height == 0) return 1;
        if (student >= cache.length || height < 0) return 0;


        if (cache[student][height] != -1) return cache[student][height];

        int ret = solve(student + 1, height); // no use block
        for (int block : blocks[student]) {
            if (block == -1) break;
            ret += solve(student + 1, height - block);
            ret %= MOD;
        }

        return cache[student][height] = ret % MOD;
    }

    private static int nextInt() {
        return Integer.parseInt(nextToken());
    }

    private static String nextToken() {
        if (br == null) br = new BufferedReader(new InputStreamReader(System.in));
        if (st == null || !st.hasMoreTokens()) {
            try {
                st = new StringTokenizer(br.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return st.nextToken();
    }

    private static String nextLine() {
        if (br == null) br = new BufferedReader(new InputStreamReader(System.in));
        try {
            return br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
```