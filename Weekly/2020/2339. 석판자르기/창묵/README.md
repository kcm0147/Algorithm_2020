# 2339. 석판자르기

# 풀이법

1) 석판자르기 처음에 DP로 접근을 하였다. 하지만 알고리즘 분류를 보고 분할정복이었다. 
2) 분할정복.. 일단 우선적으로 불순물을 제거해야한다고 생각을 했다. 불순물을 찾았을때, 세로로 자르고 가로로 잘라야한다고 생각을 했다.
3) 세로로 자르거나 가로로 자를 수 있다면, 자른부분을 기준으로 두개의 영역이 생기는데 그 영역에 관해서도 계속해서 자르기를 실시한다.
4) 불순물이 없으며, 보석이 1개만있으면 자르기에 성공한 것이니 return 1을 하고 만약 불순물이 없고 보석도 없으면 자르기에 실패한것임으로 return 0;를 한다.
5) 분할정복은 독립시행이기 때문에 result값을 곱해야한다. 더해서는 안된다.

# 느낀점
분할정복 문제를 처음 풀어서 애를 먹었다. DP로 접근해서 result를 더해도 되지만 마음처럼 되지않았다.
또한 분할정복문제의 답들은 곱하기를 해주어야한다... 분할정복문제를 더 풀어봐야겠다.