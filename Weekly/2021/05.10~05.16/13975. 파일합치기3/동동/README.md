<h1>13975. 파일 합치기3"</h1>

#### [분류]
- 그리디
<br><br>

#### [문제링크]
- https://www.acmicpc.net/problem/13975
<br><br>


#### [요구사항]
- 최대 사용할 수 있는 회의의 최대 개수를 출력한다.<br><br> 

#### [풀이]

1. 숫자를 점점 더해간다는 점에서 무조건 작은 수를 계속 만들어 나가야하기 때문에 우선순위 큐를 사용해서 가장 작은 값을 더해줘야한다.<br><br>

2. 맨 앞의 값 두개를 꺼내서 더하고, 다시 큐에 넣는 작업을 반복한다.<br><br>

3. 값이 하나만 남았을 때 출력한다.<br><br>


#### [코드]
```java
//baekjoon_13975_파일합치기3

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main{

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] s = br.readLine().split(" ");

        long n;
        int t = Integer.parseInt(s[0]);

        while(t>0){
            t--;

            s = br.readLine().split(" ");
            n = Long.parseLong(s[0]);
            s = br.readLine().split(" ");

            PriorityQueue<Long> pq = new PriorityQueue<>();
            for(int i=0;i<n;i++){
                pq.add(Long.parseLong(s[i]));
            }

            long sum = 0;
            while(!pq.isEmpty()){
                long a = pq.poll();
                long b = pq.poll();

                sum+=(a+b);

                if(!pq.isEmpty())
                    pq.add(a+b);
            }

            System.out.println(sum);
        }

    }
}
```
<br><br>

#### [통과여부]
![image](https://user-images.githubusercontent.com/54053016/118159858-87005600-b458-11eb-9965-c1dea7df41fd.png)<br><br>

#### [느낀점]
예전에 dp로 푼 것이 기억이 났었는데 이거는 k가 10만까지 되길래 일단 안될거라고 생각했다. 그런데 그 다음에 어떤 식으로 풀어야할지 너무 생각이 안나서 다른 문제를 풀다가 생각이 났는데 힘들었다.