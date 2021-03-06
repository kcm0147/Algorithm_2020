# 문제
- [1508. 레이스](https://www.acmicpc.net/problem/1508)

## 코드

<details><summary> 코드 보기 </summary>

``` java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q1508 {
    static int n, m, k, arr[];
    public static void main(String[] args) throws IOException {
        init();
        solution();
    }

    private static void solution() {
        int left = 0;
        int right = n;
        String ans = "";
        while(left <= right){
            int mid = (left + right) / 2;
            int count = 1, lo = arr[0], hi = 0;
            StringBuilder sb = new StringBuilder("1");
            for (int i = 1; i < k; i++) {
                if(count == m) break;
                hi = arr[i];
                if(hi - lo >= mid){
                    count += 1;
                    lo = hi;
                    sb.append(1);
                    continue;
                }
                sb.append(0);
            }
            while(sb.length() < k) sb.append(0);
            if(count == m){
                left = mid + 1;
                ans = sb.toString();
            }
            else right = mid - 1;
        }
        System.out.println(ans);
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = stoi(st.nextToken());
        m = stoi(st.nextToken());
        k = stoi(st.nextToken());
        arr = new int[k];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < k; i++)
            arr[i] = stoi(st.nextToken());
    }

    private static int stoi(String str) {
        return Integer.parseInt(str);
    }
}

```

</details>

## ⭐️느낀점⭐️
> 공유기 설치 문제에서 이분탐색을 완벽히 이해했다고 생각했는데 그 문제에서 가능했던 풀이 방식이었다.
> 
> 전체적인 흐름은 크게 다르지 않지만 조건 판별하는 부분이 문제에 따라 달라짐을 깨달았다. 이젠 진짜 원리를 이해했다!!

<hr/>

## 풀이 📣


1️⃣ 심판이 위치할 수 있는 장소를 입력받는다.

    - 이분탐색을 위해서 값들이 정렬되어 있다.


2️⃣ 전체 길이의 절반씩 나눠가며 구간 간격의 기준값을 구해 심판의 수가 m 명이 되도록 앞에서부터 위치시킨다.

    - 이전 인덱스 (lo) 와 현재 인덱스(hi) 의 원소값의 차이가 현재 기준값보다 커지면 현재 인덱스에 심판을 위치시킨다.

    - 이전 인덱스를 현재 인덱스값으로 저장시키고, 현재 인덱스 값은 +1 해준다.

    - 이렇게 앞에서부터 심판을 m 명 위치시키면 탐색을 종료한다.

    - 심판을 세운 상황을 스트링 빌더로 저장한다. 심판을 세우는 위치에는 1, 세우지 않는 위치에는 0을 붙여준다.


3️⃣ m 명을 선택한 후, 뒤의 지점에는 심판을 더 이상 세울 수 없다.

    - 스트링 빌더 길이가 k 가 될 때 까지 남는 공간에 0을 더해준다.


4️⃣ 선택된 심판의 수가 m 명이라면 기준값을 더 크게 잡고, 그렇지 않다면 기준값을 더 작게 잡아 위의 과정을 반복한다.

    - 기준값을 더 크게하는 경우 -> 가장 가까운 심판과 심판 사이의 거리가 더 커진다

    - 기준값을 더 작게하는 경우 -> 가장 가까운 심판과 심판 사이의 거리가 더 좁아진다


5️⃣ 가장 가까운 심판끼리의 거리를 최대로 하는 경우에 사전순으로 가장 나중에 오는 상황을 나타내는 스트링을 출력한다. 

<hr/>

## 실수 😅
- 이전 공유기 설치 문제에서는 기준값으로 나눈 구간의 개수가 공유기 설치개수보다 많아질 경우 기준값을 키우는 방식으로 구현했다.

- 하지만 이렇게되면 설치 가능한 공유기보다 더 많은 개수의 공유기를 설치할 때도 기준값을 키우게 된다.

- 반복문을 돌며 전체 카운트해야할 값과 같아질 경우 반복문을 탈출시키고, 값과 같을 경우에만 기준값을 키우도록 구현해야 한다는 것을 알았다.