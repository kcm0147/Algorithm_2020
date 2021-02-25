

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