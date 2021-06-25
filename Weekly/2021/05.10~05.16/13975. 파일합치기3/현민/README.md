# 풀이

## 코드

```java
package week.week0510.solve13975;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {
    private static BufferedReader bufferedReader;
    private static StringTokenizer stringTokenizer;

    public static void main(String[] args) {
        int T = nextInt();
        PriorityQueue<Long> numbers = new PriorityQueue<>(Long::compare);
        for(int tc=1; tc<=T; tc++){
            int n = nextInt();
            for(int i=0; i<n; i++){
                numbers.add((long) nextInt());
            }
            System.out.println(solve(numbers));
            numbers.clear();
        }
    }

    private static long solve(PriorityQueue<Long> numbers){
        long cost = 0;
        while(numbers.size() > 1){
            long n1 = numbers.poll();
            long n2 = numbers.poll();

            cost += n1 + n2;
            numbers.add(n1 + n2);
        }
        return cost;
    }

    private static int nextInt(){
        if(bufferedReader == null) bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        if(stringTokenizer == null || !stringTokenizer.hasMoreTokens()){
            try{
                stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        return Integer.parseInt(stringTokenizer.nextToken());
    }
}
```