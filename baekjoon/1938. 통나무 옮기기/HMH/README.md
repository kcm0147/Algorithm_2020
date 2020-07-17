# 1938. 통나무 옮기기

# How To Solve

1) 문제를 보았을 때 bfs 문제인것을 파악하였다. 



2) 문제를 풀 때 각 판의 상황을 status라고 하였다. 처음에는 익숙한 2차원 배열로 구현하려 했으나 굳이 그럴 필요가 없다 느꼈다.

    Status : 현재 상황을 표현, 통나무 위치 3개를 데이터로 가지고 있다.

    Dir : Status의 enum 이다. 필요에 의해 apply함수를 추가하여 각 enum값에 대하여 적절한 움직임을 수행할 수 있도록 하였다.

    getAdjList : 각 Status에서 움직임이 가능한 다음 Status들을 반환한다.

    hashCode와 equals의 오버라이딩 : 나중에 distance라는 HashMap의 키로 Status가 사용되므로 오버라이딩 해주었다.



3) Board라는 판을 만들었다. 각 tree를 HashSet에 저장해놓으면 contains를 통해 tree에 걸치는지 아닌지를 알 수 있으므로 2차원 배열로 구현하지 않았다.



4) 각 지점의 x,y좌표를 저장하는 Pos 클래스를 만들었다. HashSet에 넣어야함으로 hashCode와 equals를 오버라이딩하였다.



5) bfs를 이용하였다. 만약 end라는 Status와 Queue에서 Poll한 Status가 같으면 거리를 반환하며 큐가 비어도 그런 경우가 없으면 0을 반환한다.


# 느낀점

1) 처음에 간과한 사실이 turn할 때 너무 단순하게 생각하였다. 나중에 보니 turn한 후 그것도 범위에 넘어서지 않는지 판단을 했어야 했다.