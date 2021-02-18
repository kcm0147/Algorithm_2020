# 2306 유전자

## KOI 유전자일 수 있는 조건

1. at와 gc는 KOI이다.

2. 어떤 X가 KOI일 때, aXt와 gXt도 KOI이다.

3. X,Y둘 다 KOI이면, XY도 KOI이다.

## 구해야하는 것

* 한 DNA 서열이 나타났을 때 임의의 위치에 있는 유전자들을 삭제할 수 있다.

* 이 때 최대 길이의 KOI를 구하시오.

## 아이디어

1. KOI가 될 수 있는 조건들을 하나하나 살펴보자 어떤 유전자에 대하여 다음과 같이 나타내보자

    `find(dna)` : dna에서 구할 수 있는 KOI의 최대 길이
   
2. 여기서 길이가 2라면 단순하게 1번조건으로 확인할 수 있다.

3. 2번의 경우도 어렵지 않다. 2번 조건을 만족한다면 다음과 같이 표현할 수 있다.

```
find(dna) = find(subDna) + 2, subDna는 양쪽 두 염기를 제거한 것

여기서 subDna에서 최대 KOI를 찾으면 되는 이유는, 염기들을 삭제할 수 있기 때문이다.

즉 그 안에서 최대한의 KOI만 구하면 나머지는 무시할 수 있다는 의미이다.
```

4. 3번의 조건을 보자, 이 경우에는 현재 DNA를 두 부분으로 나눠 각각에서 최대한의 KOI를 구해 그 크기를 더해야 한다.

   * 어떤 X,Y가 될지 모르므로 mid 값을 left로 두어 right-1까지 진행하며
   
   * X는 (left, mid)
   
   * Y는 (mid+1, right) 구역을 맡는다.

   * 여기서 mid가 left로 두고 끝을 right-1로 두는 이유는 그냥 양 옆 한개만 삭제하는 경우도 포함시키기 위하여서다.
   
5. `find(dna)`는 2번 조건과 3번 조건에 대하여 검사하고, 2번 조건은 기저조건으로 활용한다.

6. 또한 길이가 1 이하인것도 기저조건으로 이용한다.

7. 메모이제이션을 적용하며 dna를 (left,right)로 표현한다.

## 느낀 점

1. 처음에 어떻게 풀어야하나 고민하다가 결국 힌트를 본 문제이다..

2. dp문제가 참 떠오르기가 어려운 것 같다. dp도 많이 연습해야할 것 같다.

## 코드

```java
package daily.day0216.boj2306;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    private static String s;
    private static int[][] cache;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        s = reader.readLine();
        cache = new int[s.length()][s.length()];
        for(int[] c : cache) Arrays.fill(c , -1);

        System.out.println(find(0, s.length()-1));
    }

    private static int find(int left, int right){
        if(left >= right) return 0;

        if(cache[left][right] != -1) return cache[left][right];

        int ret = 0;
        for(int split=left; split<right; split++){
            ret = Math.max(ret, find(left,split) + find(split+1, right));
        }

        if((s.charAt(left) == 'a' && s.charAt(right) == 't')
            || (s.charAt(left) == 'g' && s.charAt(right) == 'c')){
            ret = Math.max(ret, find(left+1, right-1) + 2);
        }

        return cache[left][right] = ret;
    }
}
```
