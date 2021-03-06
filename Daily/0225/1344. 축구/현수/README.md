# 문제
- [1344. 축구](https://www.acmicpc.net/problem/1344)

## 코드

<details><summary> 코드 보기 </summary>

``` java
import java.util.Scanner;

public class Q1344 {
    static double A, B;
    static long binom[][];
    static int counts[] = {0, 1, 4, 6, 8, 9, 10, 12, 14, 15, 16, 18}, n = 18;
    public static void main(String[] args) {
        init();
        solution();
    }

    private static void solution() {
        makeBinom();
        double scoreA = getScore(A);
        double scoreB = getScore(B);
        System.out.println(1 - scoreA * scoreB);
    }

    private static double getScore(double p) {
        double ret = 0;
        for (int r : counts)
            ret += binom[n][r] * Math.pow(p, r) * Math.pow(1 - p, n - r);
        return ret;
    }

    private static void makeBinom() {
        for (int i = 0; i <= 18; i++) {
            binom[i][0] = 1;
        }
        for (int i = 1; i <= 18; i++) {
            for (int j = 1; j <= i; j++) {
                if(i == j) binom[i][j] = 1;
                else
                    binom[i][j] = binom[i-1][j] + binom[i - 1][j - 1];
            }
        }
    }

    private static void init() {
        Scanner sc = new Scanner(System.in);
        A = sc.nextDouble() / 100;
        B = sc.nextDouble() / 100;
        binom = new long[19][19];
    }
}
```

</details>

## ⭐️느낀점⭐️
> 예전에 풀었던 이항계수 풀이를 떠올려서 쉽게 적용할 수 있었다. 다 풀고나서 현민이의 DP 설계를 보고 감탄했다.
>

## 풀이 📣
<hr/>

1️⃣ 이항계수를 미리 다 구해놓는다.

    - binom[N][R] : N 개 중에 R 개를 선택하는 경우의 수 => 현재 경우를 포함하지 않는경우(binom[N-1][R]) + 현재 경우를 포함하는 경우(binom[N-1][R-1])


2️⃣ 확률과 통계 수업에서 배웠던 확률 계산식을 사용하여 답을 구한다.

    - nCr * (r번의 사건이 발생할 확률 p) ^ r * (n - r번의 사건이 발생하지 않을 확률 1 - p) ^ (n - r)


3️⃣ A가 소수 만큼의 득점을 하거나 B가 소수 만큼의 득점을 하는 경우 이므로, `1 - (A와 B가 소수가 아닌 만큼의 득점을 하는 경우)` 가 문제의 조건을 만족하는 확률이 된다.


<hr/>

## 실수 😅
- 처음에 A가 소수만큼의 득점하는 확률 + B가 소수만큼의 득점하는 확률을 구해서 교집합을 제거해주지 못했다.