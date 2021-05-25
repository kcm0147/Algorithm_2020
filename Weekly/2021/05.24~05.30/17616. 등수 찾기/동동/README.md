<h1> 17616. 등수 찾기 </h1>

#### [분류]
- 탐색
<br><br>

#### [문제링크]
- https://www.acmicpc.net/problem/17616
<br><br>


#### [요구사항]
- 학생X의 순위 범위를 구한다. <br><br> 

#### [풀이]

1. 아랫쪽에서 몇 등 할 수 있는지와 윗 쪽에서 몇 등을 할 수 있는지 각각 구하기 위해 단방향 그래프 두개를 생성한다.<br><br>

2. 두 개의 그래프에 대해서 각각 bfs 탐색을 수행하여 학생 X의 위에 최소 몇 개, 아래에 최소 몇 개인지 구한다.<br><br>

3. 최대 등수와 최소 등수를 구해서 출력한다.<br><br>


#### [코드]
```java
//baekjoon_17616_등수찾기


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.*;

public class Main {
    static int n, m, x;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        ListGraph up_graph = new ListGraph(n);
        ListGraph bottom_graph = new ListGraph(n);

        for(int i=0;i<m;i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            bottom_graph.addList(x, y);
            up_graph.addList(y, x);
        }

        int up = getValue(up_graph) + 1;
        int bottom = n - getValue(bottom_graph);
        System.out.println(up + " " + bottom);
    }

    static private int getValue(ListGraph graph){
        int value = 0;
        boolean visited[] = new boolean[n+1];
        Queue<Integer> q = new LinkedList<>();
        q.add(x);
        visited[x] = true;

        while(!q.isEmpty()){
            int p = q.poll();
            ArrayList<Integer> list = graph.get(p);

            for(int i=0;i<list.size();i++){
                int idx = list.get(i);
                if(!visited[idx]){
                    visited[idx]=true;
                    q.add(idx);
                    value++;
                }
            }
        }
        return value;
    }
}

class ListGraph{
    private ArrayList<ArrayList<Integer>> listGraph;

    public ListGraph(int initSize){
        this.listGraph = new ArrayList<ArrayList<Integer>>();

        for(int i=0;i<initSize+1;i++){
            listGraph.add(new ArrayList<Integer>());
        }
    }

    public void addList(int x, int y){
        listGraph.get(x).add(y);
    }

    public ArrayList<Integer> get(int p) {
        return listGraph.get(p);
    }
}
```
<br><br>

#### [통과여부]
![image](https://user-images.githubusercontent.com/54053016/119465956-21c53280-bd7f-11eb-9d70-c93eb66fc85a.png)
<br><br>

#### [느낀점]
처음에는 하나의 그래프로만 할려고 하다보니 코드가 복잡해지고 계속 틀려서 두 개를 만들자는 생각이 들었다. 역시 알고리즘은 생각의 전환이 좀 필요한 것 같다.