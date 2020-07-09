# 7432. 디스크 트리

# 풀이법

1) 마치 디렉토리 처럼 그대로 구현하였다.
2) 제일 처음 dir는 root로 설정하였는데 각 Node는 다음과 같이 구성되어있다.

  class Node{
    TreeMap<String, Node> dirTable;
    int depth;

    Node(TreeMap<String, Node> dir, int depth){
      this.dirTable=dir;
      this.depth=depth;
    }

  }

=> 제일 중요한 TreeMap은 각 Node마다 아래와 같이 구성이 되어있다.

![캡처](https://user-images.githubusercontent.com/57346393/87018353-166f2400-c20c-11ea-9ef1-a0aa349a5a03.JPG)

3) parsing 함수를 통해서 이미 존재하는 String이면 , 그 String의 Node로 접근하고 존재하지 않는 String이면 현재 dir(Node)의 hash table에 String을 put한다!
4) 이렇게해서 다 완성하면 result를 print() 한다.

# 느낀점
구현은 깔끔하게 잘한 것 같지만, 메모리가 좀 많이 사용되는게 단점인 것 같다.
풀이방법을 보완해야겠다.