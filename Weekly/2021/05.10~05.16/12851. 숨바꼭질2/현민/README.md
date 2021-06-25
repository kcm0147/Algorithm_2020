# 풀이

## 코드

```java
package week.week0510.p12851;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int MAX_INT = Integer.MAX_VALUE / 2;
    private static int N;
    private static int K;

    public static void main(String[] args) throws IOException {
        init();
        List<Integer> answer = solve();
        System.out.println(answer.get(0));
        System.out.println(answer.get(1));
    }

    private static List<Integer> solve(){
        int[] distance = new int[100000+1]; // 0 ~ 100,000
        Arrays.fill(distance, MAX_INT);
        distance[N] = 0;

        int count = 0;

        Queue<Integer> q = new LinkedList<>();
        q.add(N);

        while(!q.isEmpty()){
            int u = q.poll();
            if(distance[u] > distance[K]) break; // 더 긴것은 볼 필요 없음!

            if(u == K) count++;

            addIfMinDistance(u, u+1, q, distance);
            addIfMinDistance(u, u-1, q, distance);
            addIfMinDistance(u, 2*u, q, distance);
        }
        return Arrays.asList(distance[K], count);
    }

    private static void addIfMinDistance(int cur, int next, Queue<Integer> q, int[] distance){
        if(next < 0 || next > 100000) return;
        if(distance[next] < distance[cur] + 1) return;
        distance[next] = distance[cur] + 1;
        q.add(next);
    }

    private static void init() throws IOException {
        try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))){
            String[] inputs = bufferedReader.readLine().split(" ");
            N = Integer.parseInt(inputs[0]);
            K = Integer.parseInt(inputs[1]);
        }
    }
}
```