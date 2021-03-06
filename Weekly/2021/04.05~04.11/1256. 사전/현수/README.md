---
title: '1256. 사전'
metaTitle: '만렙 개발자 키우기'
metaDescription: '알고리즘 문제를 풀고 정리한 곳입니다.'
tags: ['DP', '조합론']
date: '2021-04-23'
---

# 문제
- [1256. 사전](https://www.acmicpc.net/problem/1256)

## 코드

<details><summary> 코드 보기 </summary>

``` java
import java.util.Scanner;

public class Q1256 {
    static int n, m, k;
    static long cache[][];

    public static void main(String[] args) {
        init();
        if(k > cache[n][m]) {
            System.out.println(-1);
            return;
        }
        solution(n, m);
    }

    private static void solution(int a, int z) {
        StringBuilder sb = new StringBuilder("");
        while(a >= 1 && z >= 1){
            if(cache[a-1][z] >= k){
                sb.append("a");
                a -= 1;
            } else {
                sb.append("z");
                k -= cache[a-1][z];
                z -= 1;
            }
        }
        while(a-- >= 1){
            sb.append("a");
        }
        while(z-- >= 1){
            sb.append("z");
        }
        System.out.println(sb.toString());
    }

    private static void init() {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        k = sc.nextInt();
        cache = new long[n+1][m+1];

        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m ; j++) {
                if(i == 0 || j == 0){
                    cache[i][j] = 1;
                    continue;
                }
                cache[i][j] = Math.min(cache[i-1][j] + cache[i][j-1], (long)1e9);
            }
        }
    }
}

```
</details>

## ⭐️느낀점⭐️
> 이전에 풀어봤던 문제여서 그 때 했던 삽질은 조금 덜 할 수 있었다.
> 
> 앞에서부터 글자를 놓아보면서 규칙을 찾아서 점화식까지 생각할 수 있었다.

## 풀이 📣
<hr/>

1️⃣ `a` 가 n개, `b` 가 m개 존재할 경우 `k 번째` 문자열을 찾아야한다.


2️⃣ 따라서 `a` 와 `z`의 개수별로 만들 수 있는 조합의 개수를 미리 세어놓는다.

    - a가 n개, z가 m개 있다고 가정한다. => cache[n][m]

    - 그렇다면 a a a ... a a z z ... z z 의 문자열을 생각해볼 수 있다. 이 외에도 가능한 모든 경우의 수를 세어야 한다.

    - 이 때 가장 앞의 글자에 따라서 경우의 수를 나눠볼 수 있다.

    - 가장 앞의 글자가 a 일 경우, 뒤에 오는 문자열에 포함되는 a의 개수는 (n-1)개, z의 개수는 m 개 이다.

    - 가장 앞의 글자가 z 일 경우, 뒤에 오는 문자열에 포함되는 a의 개수는 n개, z의 개수는 (m-1)개 이다.

    - 따라서 a 가 n개, b 가 m개인 경우의 수는 cache[n-1][m] + cache[n][m-1] 이라는 점화식을 얻을 수 있다.


3️⃣ 가장 앞의 글자로 만들 수 있는 경우의 수와 K 를 비교하면서 글자를 하나씩 붙여나간다.

    - a 로 시작하는 문자열을 만들면 cache[n-1][m] 개의 경우의 수가 존재한다.

    - 따라서 cache[n-1][m] >= k 일 경우, k 는 a 로 시작하는 문자열에 포함된다. 정답에 a를 추가한다.

    - 만약 cache[n-1][m] < k 일 경우, k 는 b 로 시작하는 문자열에 포함된다. ( a로 시작하는 문자열로 만들 수 있는 문자열들보다 사전순으로 더 뒤에 있기 때문)

    - 그럴 경우 k 에서 cache[n-1][m] (a 로 시작하는 문자열의 개수) 만큼을 감소시켜주고 정답에 b를 추가한다. 


4️⃣ 최종적으로 k 번째로 만들어지는 문자열을 출력하고 종료한다.

## 실수 😅
<hr/>

- 전에도 실수했던 부분을 또 놓쳤다. => `1e10` 보다 큰 수는 필요없기 때문에 cache를 초기화할 때 해당 값을 처리해줘야한다. 