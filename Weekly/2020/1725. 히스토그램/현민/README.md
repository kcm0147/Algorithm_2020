# 1725 히스토그램   

## 접근법

* 먼저 이 문제는 알고리즘 문제해결 전략에 있는 문제이므로 먼저 분할정복을 떠올려보았다.

* 분할정복일 경우 nlogn이므로 충분히 분할정복으 가능한 문제라고 생각이 들었다.

## 풀이

* 먼저 중간을 기준으로 maxArea(left, mid) 와 maxArea(mid+1, right)를 구한 뒤 비교한다.

* 그 비교한 값과 mid와 mid+1를 포함한 구역중 제일 큰 영역과 비교해서 제일 큰 부분을 반환한다.

* 여기서 당연히 빠뜨리는 부분은 없다. 
  
  * left < right이므로 중간 2개를 포함하는 구역은 있다.

  * 또한 중간 2개를 포함하는 구역과 포함하지 않는 구역으로 나눈다면 

  * (left, mid), (mid, mid+1), (mid+1, right)이렇게 세 경우가 나온다.

## 느낀 점

* 왜 틀렸는지 계속 이해가 안되서 여러 코드들과 알문해전 코드들을 봤는데 결국에는 나의 실수가 있었다.

* minArea(0, numOfSticks)을 호출해버렸던 것이다. 두번째 인자에 numOfSticks-1을 넘겼어야 했는데...

* 모든부분을 차근차근 검증해봐야하겠다.

* 또한 n타임에 풀수있는 방법도 있으니 꼭 그걸 알문해전에서 보고 풀어보자. 