<h1> 10775. 공항 </h1>


#### [분류]
- union-find
<br><br>

#### [문제링크]
- https://www.acmicpc.net/problem/10775
<br><br>


#### [요구사항]
- 도킹할 수 있는 최대 비행기 수를 구한다.<br><br> 

#### [풀이]

1. 먼저 오는 비행기부터 가능한 최고 뒤의 게이트부터 채워나간다.<br><br>

2. 이 때, union-find를 이용해서 채워진 게이트의 parent를 앞 좌표로 설정하는 union 작업을 한다.<br><br>

3. 계속해서 채워나가다가 게이트와 매칭이 불가능하면 종료한다.<br><br>


#### [코드]
```java
//BOJ_10775_공항

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
    static int G, P, cnt=0;
    static int[] gates, parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        G = Integer.parseInt(br.readLine());
        P = Integer.parseInt(br.readLine());

        gates = new int[G + 1];
        parent = new int[G + 1];

        for(int i=0;i<=G;i++){
            parent[i] = i;
        }

        for (int i = 1; i <= P; i++) {
            int num = Integer.parseInt(br.readLine());

            int p = find(num);
            if(gates[p]>0) break;
            else{
                gates[p] = i;
                if(p>1){
                    union(p-1, p);
                }
                cnt++;
            }
        }
        System.out.println(cnt);
    }

    public static void union(int x, int y) {
        x = find(x);
        y = find(y);

        if(x!=y) parent[y] = parent[x];
    }

    public static int find(int x){
        if(parent[x]==x) return x;
        return parent[x] = find(parent[x]);
    }
}
```
<br><br>

#### [통과여부]
![image](https://user-images.githubusercontent.com/54053016/124605599-0b92b200-dea7-11eb-8338-ae7da4d3bbf7.png)
<br><br>

#### [느낀점]
예전에 한 번 풀어본 유형의 문제라서 괜찮았다.