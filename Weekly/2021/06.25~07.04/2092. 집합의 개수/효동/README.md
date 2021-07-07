<h1> 2092. 집합의 개수 </h1>

#### [분류]
- dp
<br><br>

#### [문제링크]
- https://www.acmicpc.net/problem/2092
<br><br>


#### [요구사항]
- 주어진 값에 대해 집합의 갯수의 합을 1000000으로 나누어 값을 구한다.<br><br> 

#### [풀이]

1. 적은 숫자로 만들 수 있는 집합의 갯수에서 더해나가는 식으로 메모이제이션 한다.<br><br>

2. 하나의 숫자로 만들 수 있는 집합의 갯수는 정해져 있으므로 우선 채운다.<br><br>

3. 이후에 두 개를 조합해서 만들 수 있는 수를 더하는 식으로 채워나간다.<br><br>

4. 결과를 출력한다.<br><br>


#### [코드]
```java
//BOJ_2092_집합의개수

import java.io.*;

class Main {
    static int T, A, S, B;
    static int[] count;
    static int[][] dp = new int[201][4001];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] s = br.readLine().split(" ");

        T= Integer.parseInt(s[0]);
        A= Integer.parseInt(s[1]);
        S= Integer.parseInt(s[2]);
        B= Integer.parseInt(s[3]);

        s = br.readLine().split(" ");

        count = new int[T+1];
        for(int i=0;i<A;i++){
            count[Integer.parseInt(s[i])]++;
        }

        for(int i=1;i<=T;i++){
            for(int j=0;j<=count[i];j++){
                dp[i][j] = 1;
            }//숫자 1개로 만들 수 있는 수들 갯수(1이면 공집합, 1, 11, 111, ...) 결국 하나씩 가능

            for(int j=1;j<=A;j++){
                dp[i][j] += dp[i-1][j]; //바로 이전꺼(1개로 만들 수 있는 그 조합들) 갯수 지금꺼에 추가시킴.

                //핵심 : 새로운 조합 만들기(예를 들어, 앞에서 어떤 조합들이 만들어져있다고 가정하고, 거기에 지금 숫자를 추가해서 새로운 조합 생성)
                for(int k=1;k<=count[i];k++){
                    if(k<j){
                        dp[i][j] += dp[i-1][j-k];
                        dp[i][j]%=1000000;
                    }
                }
            }
        }

        int res = 0;
        for(int i=S;i<=B;i++){
            res += dp[T][i]%1000000;
        }
        System.out.println(res%1000000);
    }
}
```
<br><br>

#### [통과여부]
![image](https://user-images.githubusercontent.com/54053016/124607244-9d4eef00-dea8-11eb-8a9e-07c65e73ded6.png)
<br><br>

#### [느낀점]
dp를 이용하는 건 알겠는데 도대체 어떤 식으로 dp를 채워나가야할지 전혀 감이 안잡혀서 어려웠다.