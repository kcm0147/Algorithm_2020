# 디딤돌

## 조건

* 시작지점(0)에서 디딤돌들(1~n)을 건너 도착지점(n+1)에 도달해야함.

* 최대 K만큼의 점프를 할 수 있으나, 그 중간에 디딤돌이 있다면 제일 앞에 있는 디딤돌을 밟아야 함.

* 디딤돌에는 숫자가 주어지며, 한번 디딤돌을 건너면 1씩 줄어듬

* 디딤돌이 0이되면 그 디딤돌을 건널 수 없다.

## 입력 크기

* stones 배열 : 1 ~ 200,000

* 각 stone의 값 : 1 ~ 2,000,000,000

* k : 1 ~ stones.size

## 로직

* 몇명의 친구들이 건널 수 있을지를 이분탐색한다.

    * 이분탐색의 범위는 (0, stone들 중 제일 큰 값)이다.
    
    * stone들 중 제일 큰 값을 right로 준 이유는 모든 stone들 중에서 제일 오랫동안 버틸 수 있기 때문이다.
    
* 건널 수 있는지 판단여부는 다음과 같다.

    * n명이 지나가면 stone = stone - n이 되며, 0보다 작거나 같으면 그 돌은 다시 건널 수 없게 된다.
    
    * 따라서 이런 돌들이 연속해서 k개 이상이 나타나면 지나갈 수 없다는 의미이다.

## 느낀점
* 너무 생각이 안나 힌트를 보고 푼 문제이다. 

* 이분탐색이라는 것을 보고 놀랬다.. 이런 문제를 많이 접해보지 않아서 당황을 많이 하였다.

* 이런 문제도 많이 풀어봐야할거 같다.

## 코드

```java
class Solution {
    public int solution(int[] stones, int k) {
        int maxStone = Arrays.stream(stones).max().getAsInt();
        return binarySearch(stones, k, 0, maxStone);
    }

    private int binarySearch(int[] stones, int k, int left, int right){
        if(left >= right) return left;

        int mid = (left+right) / 2;
        if(canPass(stones, mid, k))
            return binarySearch(stones,k, mid+1, right);
        else
            return binarySearch(stones, k, left, mid);
    }

    private boolean canPass(int[] stones, int steps, int k){
        int jump = 0;
        for(int stone : stones){
            if(stone <= steps) jump++;
            else jump = 0;
            if(jump >= k) return false;
        }
        return true;
    }
}
```
