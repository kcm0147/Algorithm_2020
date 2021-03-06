# 문제
- [1099. 알 수 없는 문장](https://www.acmicpc.net/problem/1099)

## 코드

<details><summary> 코드 보기 </summary>

``` java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Q1099 {
    static final int ERROR = 99999;
    static String line;
    static String[] words;
    static int n, cache[];

    public static void main(String[] args) throws IOException {
        init();
        int ret = ERROR;
        ret = Math.min(ret, solution(0));
        if(ret == ERROR) System.out.println(-1);
        else System.out.println(ret);
    }

    private static int solution(int start) {
        if(start == line.length()) return 0;
        else if(start > line.length()) return ERROR;

        if(cache[start] > -1) return cache[start];

        int ret = ERROR;
        for(String word : words){
            int len = word.length();
            if(start + len - 1 >= line.length()) continue;
            int diff = countDiff(word, start);
            if(diff != -1)
                ret = Math.min(ret, diff + solution(start + len));
        }
        return cache[start] = ret;
    }

    private static int countDiff(String word, int start) {
        int ret = 0, wordFreq[] = new int[26], strFreq[] = new int[26];

        for (int i = 0; i < word.length(); i++) {
            int w = word.charAt(i) - 'a', s = line.charAt(start + i) - 'a';
            if(w != s) ret += 1;
            wordFreq[w] += 1;
            strFreq[s] += 1;
        }
        for (int i = 0; i < 26; i++)
            if(wordFreq[i] != strFreq[i]) return -1;
        return ret;
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        line = br.readLine();
        n = Integer.parseInt(br.readLine());
        cache = new int[51];
        words = new String[n];
        Arrays.fill(cache, -1);
        for (int i = 0; i < n; i++)
            words[i] = br.readLine();
        br.close();
    }
}
```

</details>

## ⭐️느낀점⭐️
> 메모리 초과가 6번이나 발생해서 나는 뇌용량 초과가 발생했다.
>
> 대부분 메모리 초과는 DP 설정이 잘못 되어 발생함을 기억하자.
>
> 문제 읽고 해석하기가 참 어렵다..

## 풀이 📣
<hr/>

1️⃣ 전체 문장에서 각 단어별로 문장을 해석해본다.

    - 시작 인덱스에서 모든 단어를 비교해보고 가장 적은 비용으로 해석할 수 있는 단어를 선택해서 시작 인덱스를 단어의 길이만큼 증가시켜준다.

    - 단어를 선택한 후, 전체 문장에서 해당 단어와 같은 위치의 문자들을 서로 비교해보면서 문자의 위치가 다른 개수를 카운트한다.

    - 동시에 등장했던 문자의 횟수를 저장해서 전체 문장에서 해당 단어의 위치의 부분 문자열이 해당 단어를 구성하는 문자들과 같은지 확인한다.

    - 만약 구성 문자가 다르면 해당 단어로는 문장을 해석할 수 없으므로 -1 을 반환해서 비교에 실패하였음을 알려준다.

    - 비교에 성공하면 다른 문자의 위치 개수만큼 비용을 들이고 다음 시작 인덱스부터 다시 모든 단어와 비교해서 비용을 찾아나간다.

2️⃣ 이전까지의 단어들을 뭐를 선택해서 비교했는지 정보는 앞으로 비교할 과정에서 사용되지 않는다.

    - 현재 시작 인덱스를 캐싱해서 앞으로 선택할 수 있는 단어들로부터 얻는 최소 비용을 저장한다.

3️⃣ 구한 최소 비용을 출력한다.

<hr/>

## 실수 😅

- `cache[]` 배열을 -1로 초기화를 시켜놓았으나 초기화 확인을 하는 코드를 `cache[start]> 0` 으로 해서 0으로 저장해놓은 값을 확인할 수 없었다.
