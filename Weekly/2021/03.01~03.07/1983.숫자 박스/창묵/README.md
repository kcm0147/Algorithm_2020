```java
public class Main {

    static int N,up,down;
    static int[][][] dp;
    static int[][] num;

    static final int INF = 99999999;


    public static void main(String[] args) throws IOException {

        init();
        System.out.println(calc());

    }


    public static void init() throws IOException{
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        N=stoi(st.nextToken());

        num=new int[2][N+1];

        for(int j=0;j<2;j++) {
            st=new StringTokenizer(br.readLine());
            int pos=1;
            for (int i=1;i<=N;i++) {
                int input=stoi(st.nextToken());
                if(input!=0){
                    num[j][pos]=input;
                    pos++;
                }
                else if(j==0 && input==0){
                    up++;
                }
                else if(j==1 && input==0){
                    down++;
                }
            }
        }

        dp=new int[N+1][up+1][down+1];


    }

    public static int calc() {


        int answer=-INF;

        for(int i=1;i<=N;i++){
            for(int j=0;j<=Math.min(up,i);j++){
                for(int k=0;k<=Math.min(down,i);k++){
                    int result=-INF;

                    if(i-j>=1 && i-k>=1)
                        result=Math.max(result,dp[i-1][j][k]+(num[0][i-j]*num[1][i-k]));

                    if(i-k>=1 && j-1>=0)
                    result=Math.max(result,dp[i-1][j-1][k]+(0*num[1][i-k]));

                    if(i-j>=1 && k-1 >=0)
                    result=Math.max(result,dp[i-1][j][k-1]+(num[0][i-j]*0));

                    dp[i][j][k]=result;

                    if(i==N)
                        answer=Math.max(answer,dp[i][j][k]);
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
