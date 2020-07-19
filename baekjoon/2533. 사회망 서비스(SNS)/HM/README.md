# 사회망 서비스 풀이

## 입력

서로 친구인 쌍이 n개 

## 출력

최소한의 얼리어답터 수

## 접근 방법

1) N이 백만임 -> N^2 이상으로 풀 수 없음! 완탐으로 하면 2^N이므로 풀수없으므로 -> dp!
 
dp는 cache때문에 경우의 수인 2N개가 시간복잡도가 된다.

2) minAdaptor(i,isAdaptor) : i가 루트인 트리에서 i가 isApator일때 최소 얼리 어탑터 수

3) minAdaptor(i, 0) = sum of minAdaptor(child, 1)

   minAdaptor(i, 1) = 1 + sum of min( minAdaptor(child, 0), minAdaptor(child, 1) )

4) 이전 부모 정보는 신경쓰지 않아도 된다! <- 최적부분구조가 성립하기 때문이다.

   부모의 상태에 따라 minAdaptor(child, 0), minAdaptor(child, 1)의 값에 영향을 주지 않기 때문!(사이드 이펙트 ㄴㄴ)

5) 단뱡향 트리로 만든 후 minAdaptor(1,0) 과 minAdaptor(1,1) 중 작은 것을 택하면 된다.

## 느낀점

1) 돌려보니 틀렸다고 나왔다. 무슨 ㅇ