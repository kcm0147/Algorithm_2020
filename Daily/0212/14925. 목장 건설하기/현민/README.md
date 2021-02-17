# 14925 목장건설하기

## 조건

1. 목장은 정사각형으로만 만들 수 있다.

2. 목장 안에는 나무`1`나 돌`2`이 없어야 한다.

## 아이디어

1. 일단 여기서 문제를 풀 수 있는 방법은 dp를 통해 부분문제의 중복을 재활용하는 것이다.

2. `cache[i][j]` : (i,j)가 빈들판일 때 지을 수 있는 최대 크기의 목장의 한변 크기

3. `cache[i][j] = min(cache[i-1][j], min(cache[i][j-1],cache[i-1][j-1] + 1`

    - 이렇게 볼 수 있는 이유는 만약 더 큰 들판을 지을 수 있는지를 보려면 이전에 지었던 목장 중에서 살펴봐야하며
    
    - 세 곳 중에서 한 곳이 더 커봤자 나머지 두 곳이 작아서 정사각형을 이룰 수 없다.
    
## 느낀점
1. dp를 이렇게 활용할 수 있구나를 느꼈다.

2. 다른 사람들의 코드를 보니 온라인 알고리즘으로 풀던데 이렇게 짧은 알고리즘이라면 충분히 도입가능할 거 같다.

## 코드

```java
package daily.day0212.boj14925;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
        int rowSize = Integer.parseInt(stringTokenizer.nextToken());
        int colSize = Integer.parseInt(stringTokenizer.nextToken());
        int[][] board = new int[rowSize + 1][colSize + 1];

        for (int r = 1; r <= rowSize; r++) {
            stringTokenizer = new StringTokenizer(reader.readLine());
            for (int c = 1; c <= colSize; c++) {
                board[r][c] = Integer.parseInt(stringTokenizer.nextToken());
            }
        }

        System.out.println(new Solution().solve(board));
    }

    private static class Solution {
        int[][] cache;

        int solve(int[][] board) {
            cache = new int[board.length][board[0].length];
            for (int[] c : cache) Arrays.fill(c, 0);

            int answer = 0;
            for (int i = 1; i < board.length; i++) {
                for (int j = 1; j < board[0].length; j++) {
                    if (board[i][j] != 0) continue;
                    cache[i][j] = Math.min(cache[i - 1][j], Math.min(cache[i][j - 1], cache[i - 1][j - 1])) + 1;
                    answer = Math.max(answer, cache[i][j]);
                }
            }
            return answer;
        }
    }
}
```