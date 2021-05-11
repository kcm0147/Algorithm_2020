<h1>12851. 숨바꼭질 </h1>

#### [분류]
- 탐색
<br><br>

#### [문제링크]
- https://www.acmicpc.net/problem/12851
<br><br>


#### [요구사항]
- 첫째 줄에 수빈이가 동생을 찾는 가장 빠른 시간을 출력한다.<br>
- 둘째 줄에는 가장 빠른 시간으로 수빈이가 동생을 찾는 방법의 수를 출력한다.<br><br> 

#### [풀이]

1. n이 k보다 크거나 같으면 무조건 X-1 방법으로 도달하는 방법밖에 없으므로 방법은 1가지이다.<br><br>

2. n이 k보다 작으면 X*2와 X+1 두가지 방법을 통해서 도달할 수 있는데 큐에 경우를 넣고 빼는 방식으로 셀 수 있다. <br><br>

3. 핵심은 X*2와 X+1은 같은 좌표에 도달해도 다른 방법이라는 점, 그리고 visited=true를 큐에서 뺄때 해주어야한다는 점이다. 넣을 때 해주면 모든 경우를 생각할 수 없게된다.<br><br>

4. 결과 출력!<br><br>


#### [코드]
```java
//baekjoon_12851_숨바꼭질2
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] s = br.readLine().split(" ");
        int k, n;
        int time=100001, num=1;
        n = Integer.parseInt(s[0]);
        k = Integer.parseInt(s[1]);

        if(n>=k){
            System.out.println(n-k);
            System.out.println(1);
            return;
        }

        Queue<Info> q = new LinkedList<>();
        boolean visited[] = new boolean[100001];
        q.add(new Info(n, 0));

        while(!q.isEmpty()){
            Info info = q.poll();

            int pos = info.pos;
            int cnt = info.cnt;
            visited[pos] = true;

            if(pos == k){
                if(time>cnt) time = cnt;
                else if(time == cnt) num++;
                continue;
            }

            if(pos-1>=0 && !visited[pos-1]){
                q.add(new Info(pos-1, cnt+1));
            }
            if(pos+1<=100000 && !visited[pos+1]){
                q.add(new Info(pos+1, cnt+1));
            }
            if(pos*2<=100000 && !visited[pos*2]){
                q.add(new Info(pos*2, cnt+1));
            }
        }

        System.out.println(time);
        System.out.println(num);
    }

    private static class Info{
        int pos;
        int cnt;

        private Info(int pos, int cnt){
            this.pos = pos;
            this.cnt = cnt;
        }
    }
}
```
<br><br>

#### [통과여부]
![image](https://user-images.githubusercontent.com/54053016/117770300-bacc5780-b26f-11eb-9fb2-4c6a28f38f58.png)
<br><br>

#### [느낀점]
1에서 2로 갈 때, 두 가지 경우는 무조건 똑같이 1에서 2로 가는 방식이라 같은 방식이라서 빼고 생각해야한다고 생각했는데 아닌 것이 좀 모호하다는 생각이 들었다..