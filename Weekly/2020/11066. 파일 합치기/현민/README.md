# 11066 파일 합치기 

## 접근법 

1. minCost(a, b)는 a부터 b파일까지의 최소 비용이다.

    * 비용은 a부터 b까지 파일을 2개씩 계속 합쳐가며 최종적으로 하나의 파일으로 합쳤을 때의 비용이다.

    * 파일은 연속된 두 파일만 합칠 수 있다.

2. minCost(a,b)는 a부터 b파일 구간을 포함하는 더 큰 구간에서 재사용될수 있다. 즉 부분문제의 중복이므로 메모이제이션이 필요하다.

3. minCost(a,b)는 cache[a, i] + cache[i+1, b] + sum(a,b) 이다. (a <= i <= b)

    * sum(a,b)가 더해진 이유는 File(a,i)와 File(i+1,b)를 합칠 때 a,b까지의 파일의 크기들의 합이 추가로 비용으로 합산되기 때문이다.

## 느낀 점

1. 너무 아쉬웠던 거는 문제를 이해를 할 때 빠진 부분이 있었다는 것이다. 연속된 두 파일을 합쳐야하는데 그냥 아무 두 파일이나 합쳐도 된다는 의미로 받아들여 힘들었다.

2. 문제에서 암묵적으로 나오는 내용도 확실하게 파악해야 겠다.
