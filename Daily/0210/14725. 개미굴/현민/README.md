# 14725 개미굴

## 조건

* 개미로봇은 더 이상 갈 수 없을 때 왔던 길에 있던 먹이들을 순서대로 알려준다.

* 개미굴의 구조는 다음과 같이 표현할 수 있다.
    
```
1 층 -> 먹이이름
2 층 -> --먹이이름
n 층 -> (-- * n)먹이이름

만약 같은 층이 여러개라면 사전 순서대로 먹이 정보를 나열한다.

이 때 먹이 정보는 거쳐왔던 먹이 순서들이다.
```

## 구현해야 하는 것

* 먹이정보들이 주어졌을 개미굴의 구조를 표현

## 아이디어

* 개미굴의 구조를 트리 형태로 표현 한 후에

* preorder로 출력한다.

## 시간 제한

``` 
먹이 정보의 갯수가 최대 1000, 그리고 한 먹이 정보의 최대 먹이 갯수는 15이다.

즉 모두 다 다른 먹이가 나온다고 해도 1000 * 15 -> 15,000개의 먹이가 나오며

가질 수 있는 길의 갯수는 아무리 많아도 먹이의 갯수보다 적다

즉 15,000만큼의 시간을 소요하니 충분히 풀 수 있는 문제이다.
```

## 구현

* 여기서 중요한 점은 사전 순서대로 나와야 한다는 것이다.

* 따라서 아래와 같이 구현하자

* children의 경우 사전 순서대로 정렬되어야 하니 무엇보다 TreeSet이 적합하다.

* child중 검색을 해야할 경우와 넣는 경우 모두 logN이므로 매우 적합하다.
```
Node{
    String name;
    int depth;
    TreeMap<String, Node> children;
}
```

* 새로운 먹이정보를 추가하는 과정이다.

```
먹이정보를 입력받던 도중 기존에 있었던 먹이정보들과 

언제 다른 갈래로 들어서게 되는지 확인한다.

그 지점에서 새로운 서브트리를 만들어 children에 넣어준다.
```

## 느낀 점

1. map을 통해 child를 찾는걸 logN에 하였는데, 보니까 트라이를 이용하면 더 빠르게 구할 수 있는거 같다.

2. 다음에 이런 비슷한 문제가 나올 때 트라이를 사용해보자!

## 코드

```java
package daily.day0210.boj14725;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int sizeInfo = Integer.parseInt(new StringTokenizer(reader.readLine()).nextToken());

        Solution solution = new Solution();
        for(int i=0; i<sizeInfo; i++){
            String[] info = reader.readLine().split(" ");
            solution.addTree(info);
        }
        solution.solution();
    }
}

class Solution{
    private Node root = new Node("root", -1);

    void addTree(String[] info){
        root.add(info, 1);
    }

    void solution(){
        root.printTree();
    }
}

class Node{
    String name;
    int depth;
    TreeMap<String, Node> children;

    Node(String name, int depth) {
        this.name = name;
        this.depth = depth;
        children = new TreeMap<>();
    }

    void add(String[] info, int cur){
        if(cur >= info.length) return;
        String food = info[cur];
        children.putIfAbsent(food, new Node(food, depth+1));
        children.get(food).add(info, cur+1);
    }

    void printTree(){
        if(depth >= 0){
            for(int i=0; i<depth; i++) System.out.print("--");
            System.out.println(name);
        }
        for(Node child : children.values()) child.printTree();
    }
}
```