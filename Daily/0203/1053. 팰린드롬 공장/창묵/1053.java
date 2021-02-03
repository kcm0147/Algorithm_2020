public class Main {

    static String target;
    static int[][] dp;
    static char[] targetAry;


    public static void main(String[] argv) throws IOException {

        init();
        int answer=calc(0,targetAry.length-1);

        for(int i=0;i<target.length();i++){
            for(int j=i+1;j<target.length();j++){
                char tmp=targetAry[i];
                targetAry[i]=targetAry[j];
                targetAry[j]=tmp;

                for(int k=0;k<=targetAry.length;k++)
                    Arrays.fill(dp[k],-1);

                answer=Math.min(answer,calc(0,targetAry.length-1)+1);

                tmp=targetAry[i];
                targetAry[i]=targetAry[j];
                targetAry[j]=tmp;
            }
        }

        System.out.println(answer);
    }



    public static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        target=br.readLine();
        targetAry=target.toCharArray();
        dp=new int[target.length()+1][target.length()+1];

        for(int i=0;i<=target.length();i++)
            Arrays.fill(dp[i],-1);
    }

    public static int calc(int left,int right) {

        if(left>=right) return 0;

        if(dp[left][right]!=-1)
            return dp[left][right];

        dp[left][right]=Integer.MAX_VALUE;

        if(targetAry[left]==targetAry[right]){
            dp[left][right]=calc(left+1,right-1);
        }
        else{
            dp[left][right]=calc(left+1,right-1)+1;
        }

        dp[left][right]=Math.min(Math.min(dp[left][right],calc(left+1,right)+1),calc(left,right-1)+1);

        return dp[left][right];
    }

}