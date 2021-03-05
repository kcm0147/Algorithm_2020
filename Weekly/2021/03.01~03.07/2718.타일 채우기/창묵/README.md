    ```java

public class Main {

    static int tc,N;
    static int[][] dp;

    static final int INF = 999999999;


    public static void main(String[] args) throws IOException {

        init();


    }


    public static void init() throws IOException{
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
        tc=stoi(br.readLine());
        dp=new int[500][16];

        for(int i=0;i<500;i++)
            Arrays.fill(dp[i],-INF);

        for(int i=0;i<tc;i++){
            N=stoi(br.readLine());
            System.out.println(calc(N,0));
        }
    }



    public static int calc(int index,int last) {
        if(index==0 && last==0) return 1;
        if(index<0) return 0;
        if(dp[index][last]!=-INF) return dp[index][last];

        int result=0;

        if(last==0){
            result=calc(index-1,0)+calc(index-1,9)+calc(index-1,3)+calc(index-1,12)+calc(index-2,0);
        }
        else if(last==3){
            result=calc(index-1,0)+calc(index-1,12);
        }
        else if(last==6){
            result=calc(index-1,9);
        }
        else if(last==9){
            result=calc(index-1,0)+calc(index-1,6);
        }
        else if(last==12){
            result=calc(index-1,0)+calc(index-1,3);
        }

        return dp[index][last]=result;
    }



    public static int stoi(String input){
        return Integer.parseInt(input);
    }
}

    ```