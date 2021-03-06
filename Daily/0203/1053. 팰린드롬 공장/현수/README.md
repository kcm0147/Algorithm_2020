# 문제
- [1053. 팰린드롬 공장](https://www.acmicpc.net/problem/1053)

## 코드

<details><summary> 코드 보기 </summary>

``` java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Q1053 {
    static String line, ori;
    static int cache[][] = new int[51][51];
    public static void main(String[] args) throws IOException {
        init();
        int len = line.length(), ans = 99999, right = isPalindrome(line), left = line.length() - 1 - right;
        if(right == -1) {
            System.out.println(0);
            return;
        }
        for (int l = 0; l < 51; l++) Arrays.fill(cache[l], -1);
        ans = Math.min(ans, solution(left, right));
        for (int i = 0; i < len - 1; i++) {
            for (int j = i + 1; j < len; j++) {
                for (int l = 0; l < 51; l++) Arrays.fill(cache[l], -1);
                swap(i, j, ori);
                right = isPalindrome(line); left = line.length() - 1 - right;
                if (right == -1) {
                    System.out.println(1);
                    return;
                }
                ans = Math.min(ans, 1 + solution(left, right));
            }
        }
        System.out.println(ans);
    }
    static int solution(int left, int right) {
        if(left >= right) return 0;
        if(line.charAt(left) == line.charAt(right)) return solution(left + 1, right - 1);
        int ret = Integer.MAX_VALUE;
        if(cache[left][right] != -1) return cache[left][right];

        // 1. 삽입, 삭제
        ret = Math.min(ret, 1 + solution(left + 1, right));
        ret = Math.min(ret, 1 + solution(left, right - 1));

        // 2. 다른 문자로
        ret = Math.min(ret, 1 + solution(left + 1, right - 1));
        return cache[left][right] = ret;
    }
    static void swap(int right, int i, String temp) {
        StringBuilder sb = new StringBuilder(temp);
        char t = sb.charAt(i);
        sb.setCharAt(i, sb.charAt(right));
        sb.setCharAt(right, t);
        line = sb.toString();
    }
    static int isPalindrome(String str){ // OK : -1, NO : right index
        for (int left = 0; left < str.length(); left++) {
            int right = str.length() - 1 - left;
            if(left > right) break;
            if(str.charAt(left) != str.charAt(right)) return right;
        }
        return -1;
    }
    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        line = br.readLine(); ori = line;
    }
}
```

</details>

## ⭐️느낀점⭐️
> 갓현민의 도움으로 마지막에 성공! 조건을 놓치지 말고 다 따져주자
>
> `Map` 은 메모리초과가 자주 발생하는 것 같으니, DP를 구현할 때는 다른 형태로 저장하도록 해봐야겠다. 
>
> 문제의 조건을 이해하기 어려웠다. 3번, 4번 조건 ...

## 풀이 📣
<hr/>

1️⃣ 문자열 안에서 교환은 한 번만 가능하기 때문에 시작부터 각 문자끼리 교환해본다.


2️⃣ 문자열을 변형시킬 필요 없이 인덱스로 탐색이 가능하므로 팰린드롬을 깨뜨리는 인덱스 `left` 와 `right` 를 찾아서 탐색을 실시한다.

    - right 를 기준으로 left와 같은 문자를 삽입하였다고 가정 -> 다음 차례는 left + 1 과 right 비교

    - right 를 삭제하였다고 가정 -> 다음 차례는 left 와 right - 1 비교

    - left 와 right 를 같도록 right를 바꿨다고 가정 -> 다음 차례는 left + 1 과 right - 1 비교


3️⃣ 더 이상 비교할 수 없다면 (`left >= right`) 0을 반환한다.


4️⃣ 최소 연산으로 팰린드롬을 만드는 횟수를 출력한다.


<hr/>

## 실수 😅

- 조건 3번과 4번을 이해하지 못했다. 나만 모호하게 이해한건가..


- dp 저장 시 `Map` 을 사용하는 것은 신중히 선택해야 하는 것을 알았다.