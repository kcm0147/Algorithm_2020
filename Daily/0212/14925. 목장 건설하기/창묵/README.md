
```java

public class Main {

    static int N,M;
    static int[][] map;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        init();
        System.out.println(calc());
    }

    public static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N=stoi(st.nextToken());
        M=stoi(st.nextToken());

        dp=new int[N+1][M+1];
        map=new int[N+1][M+1];

        for(int i=1;i<=N;i++){
            st=new StringTokenizer(br.readLine());
            for(int j=1;j<=M;j++){
                map[i][j]=stoi(st.nextToken());
            }
        }

    }

    public static int calc(){
        int answer=0;

        for(int i=1;i<=N;i++){
            for(int j=1;j<=M;j++){
                if(map[i][j]==0) {
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
                    answer = Math.max(answer, dp[i][j]);
                }
            }
        }

        return answer;
    }


    public static int stoi(String input){
        return Integer.parseInt(input);
    }
}

```