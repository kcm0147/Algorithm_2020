---
title: '2631. 줄세우기'
metaTitle: '만렙 개발자 키우기'
metaDescription: '알고리즘 문제를 풀고 정리한 곳입니다.'
tags: ['DP', 'LIS']
date: '2021-04-14'
---

# 문제
- [2631. 줄세우기](https://www.acmicpc.net/problem/2631)

## 코드

<details><summary> 코드 보기 </summary>

``` java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Q2631 {
    static int n;
    static int arr[];
    static List<Integer> list = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        init();
        solution();
    }

    private static void solution() {
        lis();
        System.out.println(n - list.size());
    }

    private static void lis() {
        for (int i = 0; i < n; i++) {
            if(list.isEmpty() || list.get(list.size() - 1) < arr[i]) {
                list.add(arr[i]);
                continue;
            }
            int left = 0, right = list.size() - 1;
            int ret = 0;
            while(left <= right){
                int mid = (left + right) / 2;
                if(list.get(mid) >= arr[i]){
                    ret = mid;
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
            list.set(ret, arr[i]);
        }
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = stoi(br.readLine());
        arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = stoi(br.readLine());
        }
    }

    private static int stoi(String str) {
        return Integer.parseInt(str);
    }
}

```

</details>

## ⭐️느낀점⭐️
<hr/>

> 그리디 방식으로 풀 수 있을줄 알고 문자열 연산을 통해 정렬된 상태를 구하려고 했다.
>
> 하지만 N 이 200이고, 최악의 경우엔 수많은 문자열 조합이 발생할 수 있음을 깨닫고 DP를 사용했다. 


## 풀이 📣
<hr/>

1️⃣ 이미 순서를 만족하고 있는 숫자들은 위치를 바꿀 필요 없다.

- 순서를 만족하지 않는 숫자들을 빼서 이동시키면, 이미 순서가 맞춰진 숫자들은 자동으로 순서가 맞아진다.


2️⃣ 증가하는 순서를 이루고 있는 수열을 구한다.

    - 증가하는 수열 = LIS

    - 따라서 LIS를 구한다.


3️⃣ LIS를 구하는 2가지 방법이 있다.

    - 단순히 바텀 업 방식으로 구하는 O(N^2) 의 방법

    - 이분탐색을 통해 순서를 만족하는 적절한 위치에 덮어쓰는 O(NlgN) 방법


4️⃣ 증가하는 순서를 갖는 가장 긴 순서열을 찾고, 그렇지 않은 숫자들의 개수를 출력한다.

    - 증가하는 순서는 가만히 두고, 그렇지 않은 숫자들을 순서에 맞게 이동시키면 정렬이 완료된다.


## 실수 😅
<hr/>

- `문자열` + `브루트 포스` + `가지치기` 를 통해서 순차 탐색으로 가장 마지막 숫자보다 큰 값을 찾을 때 마다 그 앞에 넣어보는 식으로 진행하였다.
  
 
- 그렇게 하면서 중복 상태는 제외하고, 현재까지 찾은 최소값보다 더 많이 진행한 경우는 모두 가지치기를 하면 상태공간을 충분히 줄일 수 있을 것이라 판단했다.


- 하지만 이 방법은 N이 최대 200일 때 수많은 방법이 존재하였다..