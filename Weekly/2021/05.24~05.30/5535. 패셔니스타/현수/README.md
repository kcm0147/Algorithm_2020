---
title: '5535. 패셔니스타'
metaTitle: '만렙 개발자 키우기'
metaDescription: '알고리즘 문제를 풀고 정리한 곳입니다.'
tags: ['DP']
date: '2021-06-19'
---

# 문제
- [5535. 패셔니스타](https://www.acmicpc.net/problem/5535)

## 코드

<details><summary> 코드 보기 </summary>

``` java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Info{
    int low, high, cost;

    public Info(int low, int high, int cost) {
        this.low = low;
        this.high = high;
        this.cost = cost;
    }
}

public class Q5535 {
    static int d, n, ans;
    static int days[], dp[][];
    static Info info[];

    public static void main(String[] args) throws IOException {
        init();
        for (int i = 0; i < n; i++) {
            if(days[0] >= info[i].low && days[0] <= info[i].high)
                ans = Math.max(ans, solution(1, i));
        }
        System.out.println(ans);
    }

    private static int solution(int idx, int pre) {
        if(idx == d){
            return 0;
        }
        if(dp[idx][pre] != -1) return dp[idx][pre];
        dp[idx][pre] = 0;
        int ret = 0;

        for (int i = 0; i < n; i++) {
            if(days[idx] >= info[i].low && days[idx] <= info[i].high){
                int diff = Math.abs(info[pre].cost - info[i].cost);
                ret = Math.max(ret, diff + solution(idx + 1, i));
            }
        }
        return dp[idx][pre] = ret;
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        d = stoi(st.nextToken());
        n = stoi(st.nextToken());
        days = new int[d];
        info = new Info[n];
        dp = new int[d][n];
        for (int i = 0; i < d; i++) {
            days[i] = stoi(br.readLine());
        }
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int low = stoi(st.nextToken()), high = stoi(st.nextToken()), cost = stoi(st.nextToken());
            info[i] = new Info(low, high, cost);
        }
        for (int i = 0; i < d; i++) {
            Arrays.fill(dp[i], -1);
        }
    }

    private static int stoi(String str) {
        return Integer.parseInt(str);
    }
}

```
</details>

## ⭐️느낀점⭐️
> 정당성을 입증하지 못한 그리디는 위험하다는 것을 느꼈다. 

## 풀이 📣
<hr/>

1️⃣ `dp[날짜][그 날에 입은 옷]` 을 설정하여 부분 문제를 해결한다.

    - 해당 날짜에 특정 옷을 선택하였을 때, 이후에 가능한 경우들은 해당 날짜의 선택에 영향을 받는 부분 문제가 성립한다.

    - 따라서 DP 로 접근하되, 인덱스는 위와 같이 설정해줄 수 있다.

    - 그렇지 않으면 D번째 날에 N 가지 옷을 입을 수 있는 상황이 존재할 수 있고, 그럼 시간 복잡도는 O(D^N) 이므로 최대 200^200 이라는 말도 안되는 시간이 된다.

    - 따라서 dp[D][N] 으로 값을 저장해두면 O(DN) 으로 가능하게 되는 것이다. -> 최대 O(200 * 200)


2️⃣ 인덱스를 증가시켜 가며 해당 인덱스에 가능한 모든 옷을 입어본다.

    - (전날 선택한 옷과 당일 선택한 옷의 차이) + solution(다음 날, 당일 선택한 옷) 의 최대값을 구해준다.


3️⃣ 이후 2번에서 구한 `dp[당일][이전에 선택한 옷]` 을 재계산 할 필요 없어 계산 속도가 빨라진다.


4️⃣ 가능한 모든 경우에 대해 계산해본 후, 가장 차이값의 합이 큰 경우를 출력하고 종료한다.

## 실수 😅
<hr/>

- 그리디 + 완전탐색으로 먼저 시간초과를 띄워서 로직을 검증하려고 했는데, 완전탐색부터 틀려서 쓸데없이 시간을 많이 썼다.


- (해당 날짜에 선택한 옷 - 전날 선택한 옷)의 최대값만을 누적시켜나가며 풀었는데, 이 방법은 부분 최적해만 구할 수 있어서 정답을 못 찾았다.