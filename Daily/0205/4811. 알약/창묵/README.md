
```java

public class Main {


    static long[][] dp;



    public static void main(String[] argv) throws IOException {

        init();

    }



    public static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       
        dp=new long[32][62];


        for(int i=0;i<32;i++){
            Arrays.fill(dp[i],-1);
        }

        calc(30,0);

        while(true){
            int tc=stoi(br.readLine());
            if(tc==0)
                break;
            else
                System.out.println(dp[tc][0]);
        }




    }

    public static long calc(int cnt,int halfcnt){
       if(cnt==0 && halfcnt==0) return 1;

       if(dp[cnt][halfcnt]!=-1) return dp[cnt][halfcnt];


       dp[cnt][halfcnt]=0;

       if(halfcnt>0){
           dp[cnt][halfcnt]+=calc(cnt,halfcnt-1);
       }

       if(cnt>0)
        dp[cnt][halfcnt]+=calc(cnt-1,halfcnt+1);


       return dp[cnt][halfcnt];
    }

    public static int stoi(String input){
        return Integer.parseInt(input);
    }

}





```