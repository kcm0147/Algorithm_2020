<h1> 5535. 패셔니스타 </h1>

#### [분류]
- dp
<br><br>

#### [문제링크]
- https://www.acmicpc.net/problem/5535
<br><br>


#### [요구사항]
- 상근이가 입는 옷의 화려함의 차이의 합의 최댓값을 출력한다.<br><br> 

#### [풀이]

1. DP를 이용해서 푼다.<br><br>

2. 우선 매일매일 입을 수 있는 옷의 조건이 온도에 따라 정해져있기 때문에 그 날 모든 옷에 대해서 입을 수 있는지 알아야한다.<br><br>

3. 즉, days(일)와 clothes(옷 종류)를 원소로 한 2차원 배열 d를 생성한다.<br><br>

4. d[day][cloth] = Math.max(d[day-1][cloth])의 논리로 d를 구해나간다.<br><br>

5. 구할 때, 만약 그 날 그 옷을 입을 수 없으면 패스하고, 그 전날 배열 d도 그 때 그 옷을 입을 수 없었다면 고려대상에서 제외한다.<br><br>

6. 맨 마지막 날 까지 다 구하고, 맨 마지막 날 구한 값들 중 최대 값을 출력한다.<br><br>



#### [코드]
```java
//baekjoon_5535_패셔니스타

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main{
    static final int MIN = Integer.MIN_VALUE;
    static int[][] d;
    static int days, n;
    static int[] max_temp;
    static Clothes[] clothes;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st= new StringTokenizer(br.readLine());
        days = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        max_temp = new int[days+1];
        for(int i=1;i<=days;i++){
            max_temp[i] = Integer.parseInt(br.readLine());
        }

        clothes = new Clothes[n+1];
        d = new int[days+1][n+1];
        for(int i=1;i<=n;i++){
            st = new StringTokenizer(br.readLine());
            int min_t = Integer.parseInt(st.nextToken());
            int max_t = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            clothes[i] = new Clothes(min_t, max_t, c);
        }

        for(int i=1;i<=n;i++){//기온 조건 고려
            if(clothesRight(i, 1)) d[1][i] = 0;
            else d[1][i] = MIN;
        }//첫째날은 뭘 입던 옷에 차이가 없다.

        for(int i=2;i<=days;i++){//days
            for(int j=1;j<=n;j++){//오늘의 옷 고르기
                if(!clothesRight(j, i)){
                    d[i][j] = MIN;
                    continue;
                }
                int sum = 0;
                for(int k=1;k<=n;k++){//어제까지 옷 입은 것 중에 최대
                    if(d[i-1][k]==MIN) continue;

                    sum = d[i-1][k] + Math.abs(clothes[k].c - clothes[j].c);
                    if(sum>d[i][j])d[i][j]=sum;
                }
            }
        }

        int sum = d[days][1];
        for(int i=2;i<=n;i++){
            sum = Math.max(sum, d[days][i]);
        }
        System.out.println(sum);
    }

    private static boolean clothesRight(int i, int day){
        return clothes[i].min_t<=max_temp[day] && clothes[i].max_t>=max_temp[day];
    }
    private static class Clothes{
        int min_t;
        int max_t;
        int c;

        private Clothes(int min_t, int max_t, int c){
            this.min_t = min_t;
            this.max_t = max_t;
            this.c = c;
        }
    }
}

```
<br><br>

#### [통과여부]
![image](https://user-images.githubusercontent.com/54053016/119703345-6b05a700-be91-11eb-91de-1ad0d5244781.png)
<br><br>

#### [느낀점]
오랜만에 dp를 풀었는데 생각보다 오래 걸렸다. 예전보다는 낫지만 그래도 더 많이 풀어봐야겠다. 화이팅!