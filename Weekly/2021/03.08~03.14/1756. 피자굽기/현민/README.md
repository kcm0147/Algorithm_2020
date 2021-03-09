# 1756. 피자 굽기

## 문제 해석

1. 오븐은 각 층마다 지름의 길이가 주어진다.

2. 각 피자도 지름의 길이가 주어진다.

3. 피자는 완성대는 순서대로 오븐에 들어간다.

4. 피자는 오븐 맨 위에서 떨어지며, 자기보다 작은 지름을 가진 오븐의 층 또는 그 아래에 피자 있을 때까지 떨어진다.

5. 오븐의 최상층의 깊이는 1이며, 아래로 내려갈수록 1씩 커진다.

## 구해야하는 것

1. 맨 위의 피자가 오븐의 몇 번째 층에 있는지 깊이를 출력하시오.

2. 만약 오븐에 모든 피자들을 넣는게 불가능하다면 0을 출력. 

## 아이디어

1. 문제에 주어진 것을 보면 피자와 오븐의 층의 갯수는 각각 최대 300,000이 될 수 있다. 

2. 따라서 N*D 알고리즘은 불가능하다. 

3. 여기서 중요한 포인트는 피자가 오븐에 들어간 후에, 그 다음 피자가 오븐에 들어오는 것이다.

4. 피자가 오븐에 들어간 층부터 그 아래 층까지는 그 다음 피자에서 고려하지 않아도 된다.

5. 이를 이용하면 투포인터를 활용할 수 있다.

6. 최대 600,000이므로 충분히 시간 내에 문제를 해결할 수 있다.

## 코드
```java
package week.w32.b1756;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static BufferedReader br;
    private static StringTokenizer st;

    private static int[] canRadius;
    private static int[] ovens;
    private static int[] pizzas;

    public static void main(String[] args) {
        int d = nextInt(), n = nextInt();
        ovens = new int[d];
        pizzas = new int[n];

        for(int i=0; i<d; i++) ovens[i] = nextInt();
        for(int i=0; i<n; i++) pizzas[i] = nextInt();

        canRadius = new int[d];
        canRadius[0] = ovens[0];
        int minRadius = canRadius[0];
        for(int i=1; i<canRadius.length; i++){
            minRadius = Math.min(minRadius, ovens[i]);
            canRadius[i] = minRadius;
        }


        System.out.println(solve());
    }


    private static int solve(){
        int ovenPoint = canRadius.length;
        for(int pizza : pizzas){
            while(ovenPoint > 0 && canRadius[--ovenPoint] < pizza);
            if(ovenPoint <= 0) return 0;
        }

        return ovenPoint+1;
    }

    private static int nextInt() { return Integer.parseInt(nextToken()); }

    private static String nextToken(){
        if(br == null) br = new BufferedReader(new InputStreamReader(System.in));
        if(st == null || !st.hasMoreTokens()) {
            try {
                st = new StringTokenizer(br.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return st.nextToken();
    }
}
```