# 집합의 갯수

## 느낀 점

만만하게 봤다가 혼난 문제였다. 처음에 너무 복잡하게 3차 디피로 생각하다가 혼났다.



## 풀이

```java
public class Main {
    private static final int MOD_NUM = 1000000;

    private static int T;
    private static int A;
    private static int minSetNUm;
    private static int maxSetNum;

    private static int[] seq;
    private static int[] startOf;
    private static int[][] dp; // index i부터 시작해서 K개의 집합을 만들 수 있는 갯수.

    public static void main(String[] args) throws IOException {
        init();
        System.out.println(solve());
    }

    private static int solve(){
        for(int i = 0; i< seq.length; i++) dp[i][1] = 1;

        for(int k=2; k<=maxSetNum; k++){
            for(int i = 0; i< seq.length-1; i++){
                for(int num = seq[i+1], cur = i+1; num<=T ; cur = startOf[++num]){
                    if(cur == -1) continue;
                    dp[i][k] = (dp[i][k] + dp[cur][k-1]) % MOD_NUM;
                }
            }
        }

        int ret=0;
        for(int k=minSetNUm; k<=maxSetNum; k++){
            for(int num=1; num<=T; num++) {
                if(startOf[num] == -1) continue;
                ret = (ret + dp[startOf[num]][k]) % MOD_NUM;
            }
        }

        return ret;
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        T = arr[0]; A = arr[1]; minSetNUm = arr[2]; maxSetNum = arr[3];

        seq = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        Arrays.sort(seq);

        startOf = new int[T+2];
        Arrays.fill(startOf, -1);
        for(int i = 0; i< seq.length; i++){
            int num = seq[i];
            if(startOf[num] == -1) startOf[num] = i;
        }

        dp = new int[seq.length+1][maxSetNum+1];
        for(int[] a: dp) Arrays.fill(a, 0);
    }
}
```

1. dp를 활용해 문제를 해결하였다. `dp[i][k] = i부터 시작해서 K개의 set을 만들 수 있는 경우` 로 하였다.

2. `dp[i][k] = sum of dp[j][k], j=num[i] ~ T if j를 쓸 수 있을 때`라고 하고 문제를 풀었다.
