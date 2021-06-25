# 풀이

## 코드

```java
package week.week0524.p17616;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    private static List<List<Integer>> adjListLager = new ArrayList<>();
    private static List<List<Integer>> adjListSamller = new ArrayList<>();
    private static int N;
    private static int X;

    public static void main(String[] args) throws IOException {
        init();
        List<Integer> answer = solve();
        System.out.println(answer.get(0) + " " + answer.get(1));
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        List<Integer> numStrings = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).boxed().collect(Collectors.toList());
        N = numStrings.get(0);
        int M = numStrings.get(1);
        X = numStrings.get(2);

        for(int i=0; i<N+1; i++){
            adjListLager.add(new ArrayList<>());
            adjListSamller.add(new ArrayList<>());
        }

        for(int i=0; i<M; i++){
            numStrings = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).boxed().collect(Collectors.toList());
            adjListLager.get(numStrings.get(0)).add(numStrings.get(1));
            adjListSamller.get(numStrings.get(1)).add(numStrings.get(0));
        }
    }

    private static List<Integer> solve(){
        boolean[] visited = new boolean[N+1];

        Arrays.fill(visited, false);
        int end = N + 1 - bfs(X, visited, adjListLager);

        Arrays.fill(visited, false);
        int start = bfs(X, visited, adjListSamller);

        return List.of(start, end);
    }

    private static int bfs(int start, boolean[] visited, List<List<Integer>> adjList){
        int ret = 1;
        visited[start] = true;
        for(int next : adjList.get(start)){
            if(visited[next]) continue;
            ret += bfs(next, visited, adjList);
        }
        return ret;
    }
}
```