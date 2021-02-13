# 13398 연속 합

## 조건

* n개로 이루어진 정수 수열이 주어진다.

* 연속된 수들을 골라 합이 제일 큰 것을 골라야 한다.

* 단 이 수열에서 숫자 하나를 제거할 수 있다.

## 아이디어

* 일단 연속된 수를 하나하나 골라갈 때 양수라면 무조건 추가해도 된다.

* 하지만 음수라면 지울수도 포함시킬 수도 있다.

    * 포함시킬 수 있는 경우는 뒤에 연결하였을 때 더 큰 이익을 볼 수 있기 때문이다.
    
* 연속된 부분수열의 합의 최댓값 문제로서 메모이제이션을 먼저 떠올려볼 수 있겠다.

* 단 여기서 생각해야 할 것은 한 개를 지울 수 있다는 것이니 다음과 같이 생각해보자

```
cache(i,j) : (i, n)까지 부분수열에서 j개만큼 숫자를 제외 시켰을 때 최댓값

cache(i,0) : (i, n)의 부분 수열에서 한 개도 제외시키지 않았을 때
cache(i,1) : (i, n)의 부분 수열에서 한 개를 제외시켰을 때
```

* 이를 토대로 아래와 같이 접화식을 사용할 수 있겠다.

```
양수라면
  cache(i,0) = max of arr(i) , arr(i) + cache(i+1,0)
  cache(i,1) = max of cache(i+1,0) , arr(i) + cache(i+1,1)
  
음수라면
  cache(i,0) = max of arr(i), arr(i) + cache(i+1, 0)
  cache(i,1) = max of cache(i+1,0), arr(i) + cache(i+1,1)
```

## 검증

* 우리가 여기서 알아야하는 것은 모든 경우의 수를 고려하는 것인지 확인 하는 것이다.

* `cache(i,j)` 의 정의자체가 문제에서 구하는 문제의 부분문제이므로 모든 경우의 수를 고려한다고 볼 수 있다.

* 여기서 각 부분 문제가 모든 경우를 고려해 계산하는 귀납법을 이용해 증명해보자

* `cache(i,0)`
```
cache(i,0) : 여기서부터 시작하는 부분 수열 중 제일 큰 수열을 고르는 것

0이므로 하나도 제거하지 않으므로 무조건 현재 arr(i)는 포함시켜야한다.

그렇다면 아래와 같이 두 경우가 있다.

뒷부분 포함 -> arr(i) + cache(i+1,0)

뒷부분 포함하지 않음 -> arr(i)

이 두가지 경우 중 제일 큰 것을 고려하면 된다.
```

* `cache(i,1)`

```
cache(i,1)의 경우 2가지를 고려해 볼 수 있겠다.

arr(i)를 제거하고 뒤를 붙일 때 -> cache(i+1, 0)

arr(i)를 포함할 때 -> cache(i+1,1) + arr(i)

이 셋 중 제일 큰 값을 고르면 된다.

무조건 한 개는 포함시켜야 하니 0은 되지 않는다.
```

* 양수 음수를 나눴으나 굳이 그렇게 생각할 필요없이 하나로 통일시켜도 된다.

## 코드

```java
package daily.day0212.boj13398;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(new StringTokenizer(reader.readLine()).nextToken());

        int[] arr = new int[size];
        String[] numbers = reader.readLine().split(" ");
        for(int i=0; i<size; i++) arr[i] = Integer.parseInt(numbers[i]);

        System.out.println(new Solution().solution(arr, size));
    }
}

class Solution{
    int solution(int[] arr, int size){
        int[][] cache = new int[size][2];

        cache[size-1][1] = cache[size-1][0] = arr[size-1];

        int answer = cache[size-1][0];
        for(int i= size-2; i>=0; i--){
            cache[i][0] = Math.max(arr[i], arr[i] + cache[i+1][0]);
            cache[i][1] = Math.max(cache[i+1][0], arr[i] + cache[i+1][1]);

            answer = Math.max(answer, Math.max(cache[i][0], cache[i][1]));
        }

        return answer;
    }
}
```