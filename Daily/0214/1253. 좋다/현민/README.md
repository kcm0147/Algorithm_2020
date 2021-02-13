# 1253 좋다

## 조건

* N개의 수 중 두 수를 더해서 어떤 수가 된다면 그 수는 Good라고 한다.

* good의 수를 구하시오

## 아이디어

* 일단 두수를 합치는 방법을 모두 만들어야 한다.

* 따라서 brute force를 통해 모든 조합을 만들어 볼 수 있겠다.

* 이 때 2,000 * 2,000 = 4,000,000이므로 충분하다

* 하지만 이제 이 합친 수를 이 안에서 찾아야하는데 2,000시간이 걸리면 안된다.

* 그렇다면 이진탐색 밖에 없다. 숫자를 정렬한 후 이진탐색해서 찾아낸다!

## 하지만

* 여기서 생각해야 하는 조건이 더 있었다. 합 친 두 수와 고른 수는 서로 다 다른 수여야 한다.

* 또한 숫자가 같은게 여러 개가 있으면 이들은 서로 다른 수로 친다는 것이다.

* 그래서 이진탐색으로 먼저 찾은 뒤 양쪽으로 범위를 넓혀가며 자기와 같은 수가 있는데까지 탐색한다.

* 허나 이는 최악의 경우 N^3까지 갈 수 있다. 예를 들면 0으로만 이루어진 수열

* 따라서 투포인터를 이용하며, 거꾸로 시작하여, 이 수가 good인지 찾는 것으로 순회하기로 한다.

* 이러면 N^2으로 풀 수 있다.

## 느낀점

1. 쉬울줄 알았으나 간과한 조건들이 여러개 있었다.

2. 결국 투포인터를 이용해 풀었으나, 이는 힌트를 얻고 푼거라 다시 비슷한 문제를 접해서 풀어봐야겠다.

3. 문제를 풀면 풀 수록 점점 다시 공부해야할 알고리즘들이 늘고 있다.

## 코드

```java
package daily.day0214.boj1253;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(new StringTokenizer(reader.readLine()).nextToken());
        int[] array = Arrays.stream(reader.readLine().split(" ")).mapToInt(s -> Integer.parseInt(s)).toArray();
        System.out.println(solution(array));
    }

    private static int solution(int[] array){
        Arrays.sort(array);
        int ret = 0;
        for(int i=0; i<array.length; i++){
            int l=0, r=array.length-1;
            while(l < r){
                int sum = array[l] + array[r];
                if(sum == array[i]){
                    if(i != l && i != r){
                        ret++;
                        break;
                    }
                    else if(i == l) l++;
                    else if(i == r) r--;
                }else if(sum < array[i]){
                    l++;
                }else{
                    r--;
                }
            }
        }
        return ret;
    }
}
```