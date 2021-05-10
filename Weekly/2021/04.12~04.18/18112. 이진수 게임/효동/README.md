<h1>18112. 이진수 게임</h1>

#### [분류]
- 탐색
<br><br>

#### [문제링크]
- https://www.acmicpc.net/problem/18112
<br><br>


#### [요구사항]
- 목표 이진수가 되기 위한 최소 시행 횟수를 구한다.<br><br> 

#### [풀이]

1. 주어진 이진수를 목표 이진수로 바꾸기 위해 우선 StringBuilder로 받고 이를 Integer형으로 바꾼다.<br><br>

2. bfs를 이용하여 문제에서 주어진 3개의 경우를 모두 큐에 넣고 다시 꺼내는 과정을 반복한다.<br><br>

3. 이 때, 한번 발생했던 숫자는 제외하고, 숫자가 1024보다 커질 경우에는 시도횟수가 너무 많아서 시간초과가 발생하므로 조건을 건다.<br><br>

4. 결과 출력!<br><br>


#### [코드]
```java
//baekjoon_18112_이진수게임
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    static Queue<Info> q = new LinkedList<>();
    static boolean[] visited = new boolean[1025];

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder(br.readLine()).reverse();
        StringBuilder tsb = new StringBuilder(br.readLine()).reverse();

        int sb_num = getNum(sb);
        int tsb_num = getNum(tsb);
        Info info = new Info(sb_num, 0);
        q.add(info);

        while(!q.isEmpty()){
            Info inf = q.poll();
            if(inf.num == tsb_num){
                System.out.println(inf.cnt);
                return;
            }

            if(inf.num+1<1024 && !visited[inf.num+1]){
                q.add(new Info(inf.num+1, inf.cnt+1));
                visited[inf.num+1] = true;
            }//plus
            if(inf.num>=1 && !visited[inf.num-1]){
                q.add(new Info(inf.num-1, inf.cnt+1));
                visited[inf.num-1] = true;
            }//minus

            StringBuilder temp_sb = getSb(inf.num);
            for(int i=0;i<temp_sb.length()-1;i++){
                int res = comp(temp_sb, i);
                if(!visited[res]){
                    q.add(new Info(res, inf.cnt+1));
                    visited[res] = true;
                }
            }//complement
        }
    }

    public static class Info{
        int num;
        int cnt;

        public Info(int num, int cnt){
            this.num = num;
            this.cnt = cnt;
        }
    }

    public static int comp(StringBuilder sb, int idx){
        StringBuilder res_sb = new StringBuilder(sb);
        if(res_sb.charAt(idx)=='0') res_sb.setCharAt(idx, '1');
        else res_sb.setCharAt(idx, '0');

        return getNum(res_sb);
    }

    public static int getNum(StringBuilder sb){
        int res = 0;
        int mult = 1;
        for(int i=0;i<sb.length();i++, mult*=2){
            if(sb.charAt(i)=='1'){
                res+=mult;
            }
        }
        return res;
    }

    public static StringBuilder getSb(int num){
        StringBuilder sb = new StringBuilder();
        int res = num;
        while(res>1){
            int r = res%2;
            sb.append(r);
            res/=2;
        }
        if(res==1) sb.append(res);

        return sb;
    }
}
```
<br><br>

#### [통과여부]
![image](https://user-images.githubusercontent.com/54053016/117647988-a4b78c00-b1c8-11eb-89e8-906c68213a71.png)
<br><br>

#### [느낀점]
다른 시험들 준비로 알고리즘 공부에 소홀했는데 오랜만에 익숙한 bfs로 풀어서 괜찮았다. 다시 열심히 해야겠다.