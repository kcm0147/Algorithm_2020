# 풀이법

1. DP 문제에 많이 약해서 힘들 것 같았는데 전형적인 diagonal 문제였다.
2. diagonal을 이용하여 DP에 있는 배열을 계산을 해야했다. dp[row][col] = dp[row][row+i]+dp[row+i][col];
3. 위와 같은 점화식을 이용하여 계산을 하였다.


# 느낀점

DP문제 많이 풀어서 연습을 많이 해봐야 할 것 같다.
