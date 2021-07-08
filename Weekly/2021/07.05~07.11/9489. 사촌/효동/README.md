<h1> 9489. 사촌 </h1>

#### [분류]
- queue
<br><br>

#### [문제링크]
- https://www.acmicpc.net/problem/9489
<br><br>


#### [요구사항]
- 각 케이스마다 k의 사촌의 수를 구한다.<br><br> 

#### [풀이]

1. 트리 문제인 만큼 parent 배열을 의식해서 푼다.<br><br>

2. 우선 숫자를 하나씩 받으면서 형제면 같은 숫자끼리 p[i]를 채워준다.<br><br>

3. 이후, 우리가 구하려는 것은 k의 형제가 아닌 사촌의 수이므로 우선 p[i]!=p[idx]를 구하는 것은 당연하다. <br><br>

4. 여기에 조건이 하나 추가 되는데 p[p[i]]==p[p[idx]]이다. 즉, 자기 위의 라인이 동일한 노드의 숫자를 세는 것이다.<br><br>

5. 그러면 사촌의 수를 구할 수 있다.<br><br>

#### [코드]
```java
//BOJ_9489_사촌

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while(true){
            String[] s = br.readLine().split(" ");
            int n = Integer.parseInt(s[0]);
            int k = Integer.parseInt(s[1]);

            if(n==0 && k==0) break;
            s = br.readLine().split(" ");

            int[] a = new int[n+1];
            int[] p = new int[n+1];

            Arrays.fill(a, -1);
            p[0] = -1;

            int idx = 0;
            int cnt = -1;
            for(int i=1;i<=n;i++){
                a[i] = Integer.parseInt(s[i-1]);

                if(a[i]==k) idx = i;

                if(a[i]-a[i-1]!=1) cnt++;
                p[i] = cnt;
            }

            int res = 0;
            for(int i=1;i<=n;i++){
                if(p[i]!=p[idx] && p[p[idx]]==p[p[i]]) res++;
            }
            sb.append(res+"\n");
        }
        System.out.println(sb);
    }
}
```
<br><br>

#### [통과여부]
![image](https://user-images.githubusercontent.com/54053016/124760233-7efdf780-df6b-11eb-973f-243a644429b5.png)
<br><br>

#### [느낀점]
사실 처음에는 그냥 구현같은 느낌이어서 일일이 따로 저장을 하는 식으로 풀어야하나 싶었는데 도저히 방법이 생각이 안났다.
풀이를 보니 트리라서 그런지 역시 parent를 적극적으로 이용하고 생각해봐야한다는 것을 깨달았다.