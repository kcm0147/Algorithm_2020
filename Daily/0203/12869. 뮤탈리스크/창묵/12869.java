public class Main {

    static int N;
    static int[][][] dp;
    static int[] cost;
    static int h1,h2,h3;


    public static void main(String[] argv) throws IOException {

        init();
        System.out.println(calc(0,0,0));


    }

    public static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N=stoi(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        cost = new int[3];

        for(int i=0;i<N;i++){
            cost[i]=stoi(st.nextToken());
        }
        dp=new int[cost[0]+1][cost[1]+1][cost[2]+1];
        h1=cost[0]; h2=cost[1]; h3=cost[2];

        for(int i=0;i<=h1;i++){
            for(int j=0;j<=h2;j++)
                Arrays.fill(dp[i][j],-1);
        }

    }

    public static int calc(int cost1,int cost2,int cost3) {
        if(cost1>=h1 && cost2>=h2 && cost3>=h3) return 0;
        if(cost1>=h1) cost1=h1;
        if(cost2>=h2) cost2=h2;
        if(cost3>=h3) cost3=h3;

        if(dp[cost1][cost2][cost3]!=-1) return dp[cost1][cost2][cost3];

       int tmp=Integer.MAX_VALUE;

        tmp=Math.min(calc(cost1+9,cost2+3,cost3+1)+1,tmp);
        tmp=Math.min(calc(cost1+9,cost2+1,cost3+3)+1,tmp);
        tmp=Math.min(calc(cost1+3,cost2+9,cost3+1)+1,tmp);
        tmp=Math.min(calc(cost1+3,cost2+1,cost3+9)+1,tmp);
        tmp=Math.min(calc(cost1+1,cost2+3,cost3+9)+1,tmp);
        tmp=Math.min(calc(cost1+1,cost2+9,cost3+3)+1,tmp);

        dp[cost1][cost2][cost3]=tmp;
        return dp[cost1][cost2][cost3];


    }


    public static int stoi(String input){
        return Integer.parseInt(input);
    }

}