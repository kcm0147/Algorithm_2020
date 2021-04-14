# 문제풀이

```java
package week.w41.boj1256_사전;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    private static int[][] count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] numbers = Arrays.stream(br.readLine().split(" ")).mapToInt(s -> Integer.parseInt(s)).toArray();
        count = countCombination(numbers[0], numbers[1]);

        if(count[numbers[0]+numbers[1]][numbers[0]] < numbers[2])
            System.out.println(-1);
        else
            System.out.println(solve(numbers[0], numbers[1], numbers[2]-1));
    }

    private static String solve(int n, int m, final int skip){
        if(n == 0 || m == 0){
            String appended = n == 0 ? "z" : "a";
            String ret = "";
            while(n > 0 || m > 0){
                ret += appended;
                n--; m--;
            }
            return ret;
        }

        int count = Main.count[n+m-1][n-1]; // 현재 a를 뽑았을 때 만들 수 있는 경우의 수

        if(skip < count)
            return "a" + solve(n-1, m, skip);

        return "z" + solve(n, m-1, skip - count); // 현재자리에서 a를 뽑는 경우의 수들을 건너뜀
    }

    private static int[][] countCombination(final int n, final int m){
        int[][] ret = new int[n+m+1][n+1]; // n+m개의 중에서 n개의 a를 뽑는 경우의 수

        ret[1][0] = 1; // 한 개중에 0개를 뽑음
        ret[1][1] = 1; // 한 개중에 1개를 뽑음
        for(int i=2; i<=n+m; i++){
            ret[i][0] = 1;
            for(int j=1; j<=i && j<=n; j++){
                ret[i][j] = Integer.min(1000000000+1, ret[i-1][j-1] + ret[i-1][j]); // 현재 뽑았을 때와 안 뽑았을 때, 경우의 수를 더
            }
        }

        return ret;
    }
}
```

## 문제 해결법

1. a, z 단 두 단어만 있다, 또 단어들은 사전순으로 정리되어 있다.

2. 현재, n개의 a, m개의 z가 있을 때, 현재 자리에서 z를 선택하는 것은 (n+m-1, n-1)개를 뛰어넘는 것이다.
    
   - 문자가 a, z 두 개가 있으므로 이 문자들로 만들 수 있는 경우의 수는 조합이다.
    
    - 현재 자리에 a를 선택하는 경우의 수는 (n+m-1, n-1)이다. 

3. 즉 만약 skip해야할 숫자가 a를 선택했을 때 나오는 경우의 수보다 크거나 같은 경우 스킵한다.

   * 반대라면 스킵하지 않는다. -> a를 선택한다 -> a의 갯수를 줄인다.
   
4. 조합의 갯수인 `count[n+m][n]`를 미리 캐싱해놓는다.

5. 매우 중요한 점 K는 1,000,000,000보다 작거나 같다.

   * 여기서는 comination의 경우가 기하급수적으로 늘어난다.
   
   * 즉 어느순간 오버플로우가 일어날 수 있다.
   
   * 따라서, k는 어쨌든 1,000,000,000보다 작으니 1,000,000,000보다 큰 경우의 수들은 이보다 1크게 잡는다.
   

## 느낀점

1. 1,000,000,000 이거를 생각 못했다.

2. 오버플로우를 명심해야겠다.