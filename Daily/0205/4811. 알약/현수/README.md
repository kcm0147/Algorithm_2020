# 문제
- [4811. 알약](https://www.acmicpc.net/problem/4811)

## 코드

<details><summary> 코드 보기 </summary>

``` java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Q4811 {
    static int n;
    static long cache[][];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while(true){
            n = Integer.parseInt(br.readLine());
            if(n == 0) break;
            cache = new long[31][31];
            for (int i = 0; i < 31; i++)
                Arrays.fill(cache[i], -1); // max : 31 * 30
            System.out.println(solution(n, 0));
        }

    }

    private static long solution(int total, int half) {
        if(total == 0 && half == 0) return 1;
        if(cache[total][half] != -1) return cache[total][half];
        long ret = 0;
        if(total > 0)
            ret += solution(total - 1, half + 1);
        if(half > 0)
            ret += solution(total, half - 1);
        return cache[total][half] = ret;
    }
}

```

</details>

## ⭐️느낀점⭐️
> 이제 메모리초과를 방지하기 위해 DP 에 캐싱할 때 문자열을 키값으로 절대 하지 않는다. 

## 풀이 📣
<hr/>

1️⃣ 온전한 알약의 개수 `total` 과 반 쪽 짜리 알약의 개수 `half` 를 저장한다.

    - 온전한 알약을 먹으면 total 은 1만큼 감소하고, half 는 1만큼 증가한다. 

    - 반 쪽 짜리 알약을 먹으면 half 는 1만큼 감소한다.

2️⃣ 현재 남아있는 알약의 상태들로 문자열을 만들어나가기 때문에 따로 문자열을 보관하고 관리할 필요가 없다.


3️⃣ 앞으로 남은 알약들의 개수를 캐시에 저장해서 해당 상태일 때 구한 서로 다른 문자열의 개수를 저장해둔다.

    - cache[total][half] 를 재사용한다.


4️⃣ 모든 형태의 알약을 소진할 때 까지 반복해서 구할 수 있는 서로 다른 문자열의 총 개수를 출력한다.


<hr/>

## 실수 😅
- 처음에 문자열을 어떻게 캐싱할까 고민하다가 다행히 오래 지나지않아 지난번의 교훈을 상기시켜 인덱스로 접근해서 풀 수 있었다!

- 다음에는 문자열 보자마자 바로 인덱스로 바꿔서 생각해 접근해야겠다.