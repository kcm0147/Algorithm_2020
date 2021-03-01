문제를 보면 5분 간격으로 90분 동안 A팀이나 B팀의 득점이 소수인 확률을 구하라고 하였습니다.

DP로 문제를 풀면 좋겠다고 바로 생각을 하였습니다.

`Dp[몇번째간격][A팀의 득점수][B팀의 득점수] = 확률`로 정의를 하였으며, 5분 간격마다 일어날 수 있는 경우의수를 생각해보았습니다.

```
1. A팀도 득점하고 B팀도 득점을 할 때

2. A팀이 득점하고 B팀이 득점하지 못할 때

3. A팀이 득점하지 못하고 B팀이 득점할 때

4. A팀과 B팀이 득점을 하지 못할 때

```

이렇게 경우의수를 나눠서 Dp를 진행하면 됩니다.

저는 Top-down형식으로 문제를 해결하였는데, Dp에 넣을 확률을 게산할때 가장 중요한 점은 아래와 같이 `확률을 계산할 때` 입니다. 

```java

result += calc(index + 1, aScore + 1, bScore)*A*(1-B);
result += calc(index + 1, aScore, bScore + 1)*B*(1-A);
result += calc(index + 1, aScore + 1, bScore + 1)*A*B;
result += calc(index + 1, aScore, bScore)*(1-A)*(1-B);

```

A는 A팀 득점을 넣을 확률이면 1-A는 득점을 하지 못할 확률입니다.
B는 B팀 득점을 넣을 확률이면 1-B는 득점을 하지 못할 확률입니다.

만약에 2번같은 경우에는 A가 득점을 할 확률에 B가 득점을 하지 못할 확률까지 계산을 해주어야 합니다 !



후에 90분의 간격 = index가 19가 된다면 A나 B의 팀의 득점수 중 소수가 있는지 판단을 해줍니다.

```java

public static boolean check(int num) {

        int cnt=0;

        if (num<2)
            return false;

        for(int i=2;i<=num;i++){
            if(num%i==0){
                cnt++;
            }

            if(cnt>1){
                return false;
            }
        }

        return true;
    }


```

이렇게 소수를 판단해주는 함수를 작성하였는데, 저는 실수로 0을 소수라고 판단을 해버려서 계속 문제를 틀렸던 기억이 있습니다.

0과 1은 소수가 아니므로 `if(num<2) return false` 로 선언하였습니다.

소수판단에 실수만 하지 않으면 좋을텐데 하는 아쉬움이 남습니다.


<br/>



<br/> <br/>

```java

public class Main {


    static double A, B;
    static double[][][] dp;


    public static void main(String[] args) throws IOException {

        init();
        System.out.println(calc(0,0,0));
    }

    public static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        A = stod(br.readLine()) * 0.01;
        B = stod(br.readLine()) * 0.01;

        dp = new double[19][19][19];
        for(int i=0;i<19;i++){
            for(int j=0;j<19;j++){
                for(int k=0;k<19;k++)
                    dp[i][j][k]=-1.0;
            }
        }
    }

    public static double calc(int index, int aScore, int bScore) {
        if (index == 18){
            if(check(aScore) || check(bScore))
                return 1.0;
            else
                return 0;
        }
        if (dp[index][aScore][bScore] != -1)
            return dp[index][aScore][bScore];

        double result = 0;


        result += calc(index + 1, aScore + 1, bScore)*A*(1-B);
        result += calc(index + 1, aScore, bScore + 1)*B*(1-A);
        result += calc(index + 1, aScore + 1, bScore + 1)*A*B;
        result += calc(index + 1, aScore, bScore)*(1-A)*(1-B);


        return dp[index][aScore][bScore] = result;

    }

    public static boolean check(int num) {

        int cnt=0;

        if (num<2)
            return false;

        for(int i=2;i<=num;i++){
            if(num%i==0){
                cnt++;
            }

            if(cnt>1){
                return false;
            }
        }

        return true;
    }


    public static double stod(String input) {
        return Double.parseDouble(input);

    }
}



```