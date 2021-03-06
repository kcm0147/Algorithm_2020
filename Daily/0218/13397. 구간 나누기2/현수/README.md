# 문제
- [13397. 구간 나누기2](https://www.acmicpc.net/problem/13397)

## 코드

<details><summary> 코드 보기 </summary>

``` java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Q13397 {
    static int n, m, arr[], cache[][], maxSeg[], minSeg[];
    public static void main(String[] args) throws IOException {
        init();
        solution();
    }

    static void solution() {
        int lo = 0, hi = 10000, ans = Integer.MAX_VALUE;
        while(lo <= hi){
            int mid = (lo + hi) / 2;
            if(parametricSearch(mid)){
                hi = mid - 1;
                ans = Math.min(ans, mid);
            }
            else lo = mid + 1;
        }
        System.out.println(ans);
    }

    static boolean parametricSearch(int mid) {
        int max = arr[0], min = arr[0], diff = max - min, group = 1;
        for (int i = 1; i < n; i++) {
            max = Math.max(max, arr[i]);
            min = Math.min(min, arr[i]);
            diff = max - min;
            if(diff > mid) {
                group += 1;
                max = min = arr[i];
            }
        }
        if(group <= m) return true;
        return false;
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        arr = new int[n];
        for (int i = 0; i < n; i++)
            arr[i] = Integer.parseInt(st.nextToken());
    }
}
```

</details>

## ⭐️느낀점⭐️
> 파라메트릭 서치는 유형이 딱 정해져있는데 떠올리기 어려운 것 같다. 더 연습해봐야겠다!!

## 풀이 📣
<hr/>

1️⃣ 구간 점수들의 최대값의 최소값을 찾는 문제이므로 파라메트릭 서치를 통해 이분탐색을 진행한다.

    - 임의의 구간이 가질 수 있는 (최대값-최소값) 을 구간 최대값 파라미터로 사용한다.

    - 일단은 구간의 차이가 최대로 될 수 있는 값을 먼저 생각한다. 그리고나서 그 최대값이 가능하다면 가능한 최대값 중 최소값을 저장하는 ans 와 비교한다.

2️⃣ N 개의 원소로 이루어진 배열에서 최대-최소값이 파라미터값보다 커지는 경우를 확인한다. 

    - 반복문을 통해 차례대로 원소값을 비교하면서 최소값, 최대값을 갱신한다.

    - 갱신된 값으로 차이를 구해서, 차이값이 파라미터값보다 크다면 해당 구간을 하나의 가능한 독립 구간으로 세어준다.

    - 최대값과 최소값을 다시 현재 인덱스의 값으로 초기화해주고 위의 과정을 반복한다.


3️⃣ 현재 최대값 파라미터로 나눌 수 있는 구간의 수가 m 개 이하라면 분할이 가능한 경우이다.

    - 최대값 파라미터가 가능하다면 hi 를 mid - 1 로 바꿔서 다시 중간값을 최대값 파라미터로 사용한다. -> 최대값이 낮아짐

    - 최대값 파라미터가 불가능하다면 lo 를 mid + 1 로 바꿔서 다시 중간값을 최대값 파라미터로 사용한다. -> 최대값이 커짐.


4️⃣ 위의 과정을 반복해서 가능한 최대값의 최소값을 찾아내어 출력한다.

<hr/>

## 실수 😅
- 구간의 점수를 파라미터로 이분탐색을 하는 것이 이해가 되지 않았었다. 왜냐하면 모든 구간이 mid 값보다 작은 점수를 가지고, 커지는 경우 새로운 구간으로 분할하는 식으로 되어 있는데, <br/>
그렇게 모든 구간을 나누면, 구간의 점수가 모두 mid 보다 작은 경우는 구간점수의 최대값이 mid보다 작아지는데 어떻게 이런 경우를 가능하다고 판단하고 hi = mid - 1로 하는지 이해가 안되었다.
  
- 하지만 이분탐색으로 가능한 최대값 중 가장 작은값이 존재한다는 것은, 구간을 나누면서 그 때의 최대값을 구간 점수로 가지는 구간이 나올 때 까지 최대값을 계속해서 감소시켜 왔을 것이다.

- 따라서 반드시 구간 점수의 최대값들의 최소값을 이분 탐색으로 찾아낼 수 있는 것이다.

