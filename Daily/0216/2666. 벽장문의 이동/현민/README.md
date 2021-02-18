# 2666 벽장문의 이동

## 조건

* 열려있는 문이 2개가 있다.

* 닫혀있는 곳에는 벽장이 있다.

* 열려있는 문의 양쪽은 열려있는 쪽으로 벽장을 밀어 열리게 만들 수 있다.

## 구해야 하는 것

* 열고 싶은 문들이 순서대로 주어진다.

* 주어진 순서대로 문을 연다고 했을 때 최소한의 벽장 밈 횟수

## 아이디어

* 일단 다음과 같이 부분문제를 정의해보자

    - `solve(벽장상태, idx)` : idx번째를 열어야 할 차례이고 현재 벽장상태일때 최소한의 밈 횟수
  
* 일단 벽장상태를 나타내야하는데 벽장의 수는 최대 N이므로 비트마스크는 안된다.

* 여기서 한 가지 중요한 점이 있는데 열려있는 문이 2개라는 것이다.

* 즉 변수 2개를 두어 몇번째 문이 각 각 해당하는지 저장하면 되는 것이다.

    - `solve(left,right,idx)`
  
* 이제 여기서 다음 상태로 넘어가는 것을 생각해보자

* 벽면을 밀어 idx를 열어야한다. 이 때 경우의 수는 2가지가 있다.

```
left쪽으로 벽장들을 밀어 여는 방법

right쪽으로 벽장들을 밀어 여는 방법
```

* 벽장 문을 몇개를 밀었는지는 벽장을 민 방향의 열려있는 문과 idx의 차이이다.

* 즉 다음과 같이 부분문제를 표할 수 있다.

  - `solve(left,right,idx)` = `min(abs(idx-left) + solve(idx, right, idx+1), abs(idx-right) + solve(left, idx, idx+1))       `
  
## 느낀 점

1. 거의 다 접근했으나 아쉽게 풀지 못했던 문제이다. 3차원 dp까지는 생각하였으나 점화식을 세우는게 좀 아쉬웠다.

2. dp 개념도 다시 봐야겠다.

## 코드 

```java
package daily.day0216.boj2666;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.function.ToIntFunction;

public class Main {
    private static int[] orders;
    private static int[][][] cache;

    private static ToIntFunction<String> stoi = s -> Integer.parseInt(s);


    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int size = stoi.applyAsInt(reader.readLine());

        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        int left = stoi.applyAsInt(tokenizer.nextToken()) - 1;
        int right = stoi.applyAsInt(tokenizer.nextToken()) - 1;

        orders = new int[stoi.applyAsInt(reader.readLine())];
        for (int i = 0; i < orders.length; i++)
            orders[i] = stoi.applyAsInt(reader.readLine()) - 1;

        cache = new int[size][size][orders.length];
        for(int[][] ary2d: cache) for(int[] ary1d : ary2d) Arrays.fill(ary1d, Integer.MAX_VALUE);

        System.out.println(solve(left, right, 0));
    }

    // left와 right가 열려 있고, 현재 door[idx]문을 열어야할 때 최소의 벽문 이동 횟수
    private static int solve(int left, int right, int idx){
        if(idx >= orders.length) return 0;

        if(cache[left][right][idx] != Integer.MAX_VALUE) return cache[left][right][idx];
        
        int door = orders[idx];
        int ret = Math.min(Math.abs(door-left) + solve(door, right, idx+1)
                        , Math.abs(door-right) + solve(left, door, idx+1));

        return cache[left][right][idx] = ret;
    }
}
```

