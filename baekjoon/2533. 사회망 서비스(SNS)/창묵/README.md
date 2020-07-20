# 2533. 사회망 서비스(SNS)

# 풀이법

1. DP 임은 미리 알고 푸는거라 쉽게 풀릴 줄 알았다.

2. 결국 해결법을 찾지못해 serch를 하였는데 트리형식의 문제는 root를 기준으로 Dp를 푸는게 좋다고 하였다.

3. 즉 Dp(i)를 i를 root로 잡았을떄 i가 early일때, i가 not early일때 이렇게 두가지로 나누어서 문제를 해결 해야 했다.

4. Dp(1,early)를 실행했을때는 1의 아래 원소들이 early or notearly 둘 중에 더 작은 것들을 채택해서 result를 return 한다.

5. Dp(1,notEarly)를 실행했을 때는 1의 아래 원소들이 무조건 early여야하기때문에 Dp(childIndex,notEarly)의 값들을 result에 +시켜서 return 한다.

# 실수

분명 로직은 맞는데 계속 시간초과가 떴다.... 나는 Dp를 실행할때 Dp[][] 배열을 만들어서 값을 저장하지 않고 재귀함수로 값을 return 하는 식으로 
코드를 짰다.

Dp에 저장을 해서 if(Dp[index][state]!=0) return; 을 함으로서 연산 횟수를 조금이라도 줄여야한다. 많이 부족한 것 같다.

