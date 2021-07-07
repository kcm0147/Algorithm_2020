<h1> 2342. Dance Dance Revolution </h1>


#### [분류]
- DP
<br><br>

#### [문제링크]
- https://www.acmicpc.net/problem/2342
<br><br>


#### [요구사항]
- 최소 힘을 구한다.<br><br> 

#### [풀이]

1. dp의 성분을 구하는 것이 중요하다.<br><br>

2. 단계, 왼발, 오른발을 원소로 하는 3차원 dp 배열을 이용해서 푼다.<br><br>

3. 앞의 단계에서 한쪽 발을 고정하고 다른 발로 해당 좌표를 밟을 때 힘을 계산해서 dp배열을 채운다.<br><br>

4. dp 배열을 완성하면 가장 작은 dp를 출력한다.<br><br>


#### [코드]
```java

//BOJ_2342_Dance Dance Revolution

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main {
    static int[][][] dp;//dp[step][left][right]
    static int INF = 987654321;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] s = br.readLine().split(" ");
        int[] list = new int[s.length];
        list[0] = 0;
        int size = list.length;
        for(int i=1;i<size;i++){
            if(s[i-1]=="0") break;
            list[i] = Integer.parseInt(s[i-1]);
        }

        dp = new int[size][5][5];
        for(int i=0;i<size;i++){
            for(int j=0;j<5;j++){
                Arrays.fill(dp[i][j], INF);
            }
        }

        dp[0][0][0] = 0;

        for(int step=0; step<size-1;step++){
            for(int i=0;i<5;i++){
                for(int j=0;j<5;j++){
                    int next = list[step+1];

                    if(i!=next){//i고정, j를 움직인다.
                        dp[step+1][i][next] = Math.min(dp[step+1][i][next], dp[step][i][j] + force(j, next));
                    }

                    if(j!=next){//j고정, i를 움직인다.
                        dp[step+1][next][j] = Math.min(dp[step+1][next][j], dp[step][i][j] + force(i, next));
                    }
                }
            }
        }

        int res = INF;
        for(int i=0;i<5;i++){
            for(int j=0;j<5;j++){
                res = Math.min(res, dp[size-1][i][j]);
            }
        }
        System.out.println(res);
    }

    public static int force(int i, int next){
        if(i==next) return 1;
        else if(i==0) return 2;
        else if(Math.abs(i-next)==2) return 4;
        return 3;
    }
}
```
<br><br>

#### [통과여부]
![image](https://user-images.githubusercontent.com/54053016/124604658-1c8ef380-dea6-11eb-8a88-a754e804f651.png)
<br><br>

#### [느낀점]
한 쪽 발을 고정하고 dp를 채워나간다는 게 뭔가 제대로 구현이 안되서 힘들었다.