# 문제
- [2306. 유전자](https://www.acmicpc.net/problem/2306)

## 코드

<details><summary> 코드 보기 </summary>

``` java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Q2306 {
    static String line;
    static int[][] cache;

    public static void main(String[] args) throws IOException {
        init();
        System.out.println(solution(0, line.length() - 1));
    }

    static int solution(int x, int y) {
        if(x < 0 || y >= line.length()) return 0;
        if(x >= y) return 0;
        if(cache[x][y] != -1) return cache[x][y];
        int ret = 0;

        for (int i = x; i < y; i++) {
            if(line.charAt(i) == 'a'){
                for (int j = y; j > i; j--) {
                    if(line.charAt(j) == 't'){
                        ret = Math.max(ret,
                                2 + solution(x, i - 1) +
                                solution(i + 1, j - 1) +
                                solution(j + 1, y)
                        );
                        break;
                    }
                }
            }
        }

        for (int i = x; i < y; i++) {
            if(line.charAt(i) == 'g'){
                for (int j = y; j > i; j--) {
                    if(line.charAt(j) == 'c'){
                        ret = Math.max(ret,
                                2 + solution(x, i - 1) +
                                        solution(i + 1, j - 1) +
                                        solution(j + 1, y)
                        );
                        break;
                    }
                }
            }
        }

        return cache[x][y] = ret;
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        line = br. readLine();
        cache = new int[line.length()][line.length()];
        for (int i = 0; i < line.length(); i++) {
            Arrays.fill(cache[i], -1);
        }
    }
}
/*
static int solution(int x, int y) {
        if(x >= y) return 0;
        if(cache[x][y] != -1) return cache[x][y];

        char left = line.charAt(x), right = line.charAt(y);
        int ret = 0;

        if((left == 'a' && right == 't') || (left == 'g' && right == 'c'))
            ret = cache[x][y] = 2 + solution(x + 1, y - 1);

        ret = Math.max(ret, solution(x + 1, y));
        ret = Math.max(ret, solution(x, y - 1));

        int mid = (x + y) / 2;
        ret = Math.max(ret, solution(x, mid) + solution(mid + 1, y));
        return cache[x][y] = ret;
    }
 */
/*
agact
4

aattgc
6

atat
4
 */
```

</details>

## ⭐️느낀점⭐️
> DP 문제라는걸 알고 양 끝 포인터를 인덱스로하는 2차원 배열로 접근하는것까지는 성공했다.
>
> 하지만 이후 재귀 진입을 잘못해서 모든 TC 를 통과시키지 못하는 코드를 작성해버렸다.

<hr/>

## 풀이 📣

1️⃣ 시작점부터 'a' 또는 'g' 를 찾는다.

    - a 에서 시작해서 t로 끝나거나 g 에서 시작해서 c 로 끝날 경우 KOI 유전자가 될 수 있다.

    - 왼쪽부터해서 a 를 찾으면 오른쪽 끝부터 t 를 찾고, 왼쪽부터 g 를 찾으면 오른쪽 끝부터 c를 찾는 2중 for문을 2개 사용한다.


2️⃣ 양 끝 지점을 확인한 후 해당 범위에서 왼쪽, 안쪽, 오른쪽 으로 재귀적으로 진입해서 최대 개수를 찾아낸다.

    - 이미 양 끝 지점에서 KOI 유전자를 하나 찾았으므로 길이를 + 2 해주고 세 개의 범위에서 구할 수 있는 최대 길이를 더해준다.

    - 해당 양 끝 지점으로 잘랐을 경우의 최대 길이와 이전에 이미 구해둔 최대 길이 중 더 큰 값을 cache[왼쪽 끝 인덱스][오른쪽 끝 인덱스]에 저장한다


3️⃣ 모든 KOI 유전자의 길이 중 최대값을 출력하고 종료한다.

    - lis 배열을 통해 교차하지 않고 추가할 수 있었던 전구의 정보를 찾아낸다.

    - 해당 전구의 인덱스로부터 매핑되어 있는 스위치를 찾아낸다.

<hr/>

## 실수 😅

<details><summary> 틀렸던 코드 </summary>

```java
static int solution(int x, int y) {
        if(x >= y) return 0;
        if(cache[x][y] != -1) return cache[x][y];

        char left = line.charAt(x), right = line.charAt(y);
        int ret = 0;

        if((left == 'a' && right == 't') || (left == 'g' && right == 'c'))
            ret = cache[x][y] = 2 + solution(x + 1, y - 1);

        ret = Math.max(ret, solution(x + 1, y));
        ret = Math.max(ret, solution(x, y - 1));

        int mid = (x + y) / 2;
        ret = Math.max(ret, solution(x, mid) + solution(mid + 1, y));
        return cache[x][y] = ret;
}
```

</details>

<br/>

- 양 끝 지점에서부터 1개씩 줄역나가는 방법으로 DP를 설계했었다. -> 중간에 문자를 제거하는 경우를 판단하지 못함.


- 양 끝이 KOI 유전자를 만족할 경우에만 길이 + 2 를 해주었었다.


- 이렇게 짠 코드는 메모이제이션도 제대로 되지 않는다. -> 서로 다른 크기의 2개의 KOI 유전자로 나뉘는 경우 각각의 경우의 합을 구하지 못한다.


- 2개의 KOI 유전자를 이어붙이는 경우를 고려하기 위해서 다른 방법으로 접근할 필요가 있었다.


- 따라서 위에 풀이에 정리한 것 처럼 최대값을 구할 수 있는 경우를 찾아서 `a or g 를 앞에서부터 찾고, t or c 를 뒤에서부터 찾는 방법` 왼쪽, 중간, 오른쪽 3가지 범위를 모두 탐색해주는 방법이 가장 합리적이다.