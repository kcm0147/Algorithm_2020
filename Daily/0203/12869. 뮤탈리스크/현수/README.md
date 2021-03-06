# 문제
- [12869. 뮤탈리스크](https://www.acmicpc.net/problem/12869)

## 코드

<details><summary> 코드 보기 </summary>

``` java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Q12869 {
    static int n, arr[], cache[][][], dmg[] = {0, 9, 3, 1};
    public static void main(String[] args) throws IOException {
        init();
        System.out.println(solution(arr[0], arr[1], arr[2]));
    }

    private static int solution(int one, int two, int three) {
        if(one <= 0 && two <= 0 && three <= 0) return 0;
        one = (one < 0) ? 0 : one; two = (two < 0) ? 0 : two; three = (three < 0) ? 0 : three;
        if(cache[one][two][three] > 0) return cache[one][two][three];
        int ans = 99999;
        for (int i = 1; i <= 3; i++) {
            for (int j = 1; j <= 3; j++) {
                if(i == j) continue;
                for (int k = 1; k <= 3; k++) {
                    if(k != i && k != j) ans = Math.min(ans, 1 + solution(one - dmg[i], two - dmg[j], three - dmg[k]));
                }
            }
        }
        return cache[one][two][three] = ans;
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        arr = new int[3]; cache = new int[61][61][61];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) arr[i] = Integer.parseInt(st.nextToken());
        for (int i = 0; i < 61; i++) {
            for (int j = 0; j < 61; j++) {
                Arrays.fill(cache[i][j], -1);
            }
        }
    }
}

```

</details>

## ⭐️느낀점⭐️
> N 이 최대 3이고 뮤탈이 한마리여서 별로 깊게 생각할 거 없이 바로 풀었다.

## 풀이 📣
<hr/>

1️⃣ scv의 체력을 입력받은 후 뮤탈리스크의 공격을 몇번째로 받을 지 조합탐색을 통해 저장한다.

    - 9, 3, 1의 순열을 만들어서 차례대로 적용해본다


2️⃣ `cache[첫 번째 scv 체력][두 번째 scv 체력][세 번째 scv 체력]` 를 통해 캐싱을 해두고 재사용한다.

    - 체력이 0 이하가 되면 0으로 계산한다.


3️⃣ 모든 scv 의 체력이 0 이하가 될 때 까지의 연산을 반복하고, 최소 연산 회수를 찾아 출력한다.

    

<hr/>

## 실수 😅
- scv 가 최대 3마리밖에 없어서 그냥 싹 다 하드코딩으로 풀어볼라다가 이것도 좀 귀찮을것같아서 그냥 반복문을 사용했다.