# 1344 축구

## 조건

1. 축구 경기 시간은 90분이다.

2. 5분마다 한 간격으로 한다.

3. 한 간격마다 A팀, B팀 각각 최대 1점씩 득점할 수 있다.

4. 한 간격마다 각 팀이 점수를 얻을 수 있는 확률이 주어진다.

## 구해야 하는 것

* 둘 중 한팀이라도 소수 점수로 득점할 수 있는 확률

## 아이디어

1. 다음과 같이 부분 문제를 정의한다.

`solve(round,a,b)` : round 만큼 지났을 때, A팀이 a점을 얻고, B팀이 b점을 얻을 확률

2. 그러면 다음과 같이 점화식을 세울 수 있다.

```
perA : A팀이 점수를 얻을 확률.
preB : B팀이 점수를 얻을 확률.

solve(round,a,b) = solve(round-1,a,b) * (1-perA) * (1-perB)
                + solve(round-1,a-1,b) * perA * (1-perB)
                + solve(round-1,a,b-1) * (1-perA) * perB
                + solve(round-1,a,b) * perA * perB
```

3. 빠지는 경우는 없는지 확인해보자
```
현재 간격에서 일어날 수 있는 경우는 총 4가지 밖에 없다.

1. A, B팀 모두 득점하지 못한 경우.

2. A팀이 득점하고, B팀이 득점하지 못한 경우

3. A팀이 득점하지 못하고, B팀이 득점한 경우

4. A, B팀 모두 득점하는 경우

따라서 위 점화식은 모든 경우를 포함한다고 볼 수 있다.

다만 a가 0이거나 b가 0인 경우는 각각 제외해주면 된다.
```

4. 참조적 투명을 만족할 수 있으므로 메모이제이션이 가능하다.

5. 한 번 계산하는데 1만큼 시간이 든다. 

6. 만들어지는 모든 점화식의 갯수는 19*19*19 이다.

7. 따라서 충분히 문제 해결이 가능하다.

8. a또는 b가 소수일 때, solve[18][a][b] 값들의 합을 구하면 된다. 

## 느낀점

1. 크게 어려움 없이 풀 수 있었다. 

2. 마지막에 소수인 것만 고를 때, a와 b 시작을 0부터도 해줬어야했다. 실수해서 아쉬웠다.

## 코드

```java
package daily.day0225.boj1344;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    private static BufferedReader br;
    private static StringTokenizer st;

    private static double goalA;
    private static double noGoalA;
    private static double goalB;
    private static double noGoalB;

    private static int MAX_ROUND = 18;

    public static void main(String[] args) throws IOException {
        goalA = nextInt() / 100.0; goalB = nextInt() / 100.0;
        noGoalA = 1 - goalA; noGoalB = 1 - goalB;
//        System.out.printf("%.lf", solve());
        System.out.println(solve());
    }

    private static double solve(){
        double[][][] cache = new double[MAX_ROUND+1][MAX_ROUND+1][MAX_ROUND+1];
        cache[0][0][0] = 1.0;

        for(int round=1; round<=MAX_ROUND; round++){
            cache[round][0][0] = cache[round-1][0][0] * noGoalA * noGoalB;
            for(int a=0; a<=round; a++){
                for(int b=0; b<=round; b++){
                    cache[round][a][b] = cache[round-1][a][b] * noGoalA * noGoalB;
                    if(a >= 1) cache[round][a][b] += cache[round-1][a-1][b] * goalA * noGoalB;
                    if(b >= 1) cache[round][a][b] += cache[round-1][a][b-1] * noGoalA * goalB;
                    if(a >= 1 && b >=1) cache[round][a][b] += cache[round-1][a-1][b-1] * goalA * goalB;
                }
            }
        }

        Set<Integer> prime = new HashSet<>(List.of(2,3,5,7,11,13,17));
        double answer=0;
        for(int a=0; a<=MAX_ROUND; a++){
            for(int b=0; b<=MAX_ROUND; b++){
                if(prime.contains(a) || prime.contains(b)){
                    answer += cache[MAX_ROUND][a][b];
                }
            }
        }

        return answer;
    }

    private static int nextInt() throws IOException { return Integer.parseInt(nextToken()); }

    private static String nextToken() throws IOException {
        if(br == null) br = new BufferedReader(new InputStreamReader(System.in));
        if(st == null || !st.hasMoreTokens())
            st = new StringTokenizer(br.readLine());
        return st.nextToken();
    }
}
```
