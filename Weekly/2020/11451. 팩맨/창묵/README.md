11451. 팩맨

# 풀이법

1. 문제를 봤을 때, 그냥 전형적인 bfs 시뮬레이션 문제라고 생각을 했다.
2. 시뮬레이션 조건은 그렇게 복잡한 편은 아니지만, 팩맨을 한칸 씩 움직일 때 둘 중에 하나라도 그 다음 칸이 벽이면 움직이지 않게 설정하며, 유령을 만난다면 애초에 그 방향으로는 움직이면 안된다.
또한 맵의 바깥으로 나간다면 반대편 자리로 위치 해있어야 한다는 것. 이것만 잘 처리해준다면 그냥 미로찾기 문제와 다름이 없었다.
3. bfs()를 이용해서 두 개의 팩맨이 만나는 시점을 최단거리로 구하면 끝


# 느낀 점
정말 바보같이 다른 tc 시작을 하면, 팩맨의 위치 또한 초기화를 해주어야하는데 팩맨의 위치 초기화를 하지 않아서
문제를 계속해서 틀렸다... 실수하지 말자