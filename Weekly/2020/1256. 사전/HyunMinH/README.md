# 1256 풀이

## 풀이법

1. 이 문제를 보고 종만북에서 봤던 문제가 생각나서 참고하였다. 완전 예제랑 똑같은 문제였다. 다른 점은 종만북은 k개를 skip한 후 단어를 구하는 거였다면 백준은 k번째 문제를 구하는 거였다. 따라서 그냥 skip = k-1로 두고 하면 되었다.

2. 여기서 문제푸는 방법은 조합을 활용한다. a가 z보다 사전순으로 앞서기 때문에 해당 지점에서 z를 선택한다면 n+m-1 에서 m-1 을 선택하는 갯수를 스킵하는 것이다. (n:a의 남은 갯수, m: z의 남은 갯수)

## 느낀점

1. 직접 손으로 쳐보니 역시 느끼는 점이 배가 되었다. 종만북의 센스있는 테크닉들을 보며 너무 감탄하며 풀었다.

2. 이후에 종만북에 나오는 다른 예제를 가지고 또 연습해봐야겠다.