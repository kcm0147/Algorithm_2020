# 13397 구간 나누기2

## 조건

* N개의 수가 순서 상관없이 나열되어 있다.

* M개 이하의 구간으로 나누어야 하며 구간은 다음과 같은 조건을 만족해야한다.

    * 구간에는 하나 이상의 수가 들어 있어야 한다.
    
    * 모든 수는 하나의 구간에 포함되어 있어야 한다.
    
* `각 구간의 점수` : `구간 안의 최댓값` - `구간 안의 최솟값`

## 구해야 하는 것

* 수들을 M개 이하의 구역으로 나누었을 때, 각 구간의 점수 중에서 최댓값의 최솟값

## 아이디어

* 우리가 구해야하는 것은 각 구간의 점수 중 최댓값 의 최솟값이다.

* 여기서 우리가 구해야하는 답을 이진탐색을 통하여 풀어본다.

* 이진탐색을 하며 mid값을 underBonud로 줬을 때 m개 이하로 구간을 나눌 수 있다면 성공이다.

* 각 구간을 underBound를 이하로 나누는 알고리즘은 다음과 같다.

```
for i=0; i<arr.length; i++){
  min = min(min, arr[i])
  max = max(max, arr[i])

  if(max - min > underBound){
    min = max = arr[i];
    group++;
  }
}
```

* 이렇게 구하면 최적으로 구간을 나눌 수 있음은 다음과 같이 증명할 수 있다.

```
만약 i번째 수를 현재 구간에 포함시키지 않는 것이 더 좋은 결과를 가질 수 있다고 하자

더 좋은 결과를 가질 수 있다는 말은 구간의 갯수가 더 줄어드는 것이다.

다음 구간인 (i+1,i+k) 구간에 포함되었다고 치자.

더 좋은 결과를 만드려면 i번째 수가 포함되었을 때 i+k+1도 다음 구간에 포함시킬 수 있어야 한다.

i가 새로운 최솟값이라고 해보자,

i+k+1번째 수가 다음 구간에 포함되기에 큰 수라면 다음 구간에 포함시킬 수 없다.
i+k+1번째 수가 다음 구간에 포함되기에 작은 수라면, i가 어차피 영향을 미치지 않으므로 다음 구간에 포함시킬 수 없다.

i가 새로운 최댓값이라고 해보자

위와 비슷하게 이 또한 불가능하다.

i가 중간에 있는 값이라면 변화를 주도 할 수 없으므로

i번째 수를 다음 구간에 포함시킨다고 해도 더 좋은 결과를 만들 수 없다.

따라서 underBound에 성립되는 한 순차적으로 모든 수를 현재 구간에 포함시키는 것이

최적의 값을 포함한다.
```

## 느낀점

1. 이번에 내가 고른 문제여서 겨우 풀 수 있지 않았나 싶다. 이진탐색이라는 태그를 보고 골랐는데 역시나 굉장하다.

2. 이진탐색을 항상 머릿속에서 같이 고려하고 있어야 할 거 같다.

## 코드

```java
package daily.day0218.boj13397;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.function.ToIntFunction;

public class Main {
    private static int[] arr;
    private static int N;
    private static int M;

    private static ToIntFunction<String> stoi = s -> Integer.parseInt(s);

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        N = stoi.applyAsInt(tokenizer.nextToken());
        M = stoi.applyAsInt(tokenizer.nextToken());

        String[] number = reader.readLine().split(" ");
        arr = new int[N];
        for(int i=0; i<N; i++) arr[i] = stoi.applyAsInt(number[i]);

        System.out.println(solve());
    }

    private static int solve(){
        int left=0, right = Integer.MAX_VALUE;
        int answer = Integer.MAX_VALUE;

        while(left <= right){
            int mid = (left+right) / 2;
            if(canDivide(mid)){
                answer = Math.min(answer, mid);
                right = mid-1;
            }else
                left = mid+1;
        }

        return answer;
    }

    private static boolean canDivide(int upperBound){
        int groupSize = 1;
        int min = arr[0];
        int max = arr[0];
        for(int i=1; i<arr.length; i++){
            min = Math.min(arr[i], min);
            max = Math.max(arr[i], max);

            if(max - min > upperBound){
                min = max = arr[i];
                groupSize++;
            }

            if(groupSize > M) return false;
        }

        return true;
    }
}
```