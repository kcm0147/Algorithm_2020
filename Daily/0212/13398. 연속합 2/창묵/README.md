요구사항]

N개의 수중 연속된 몇개의 수를 선택해서 구할 수 있는 합 중 가장 큰 합을 구하여야합니다. 

단 연속된 수 중에서 하나의 숫자는 제외를 할 수 있습니다.


<br/>

연속된 수의 합을 구하는 문제입니다. 다만 음수가 포함되어있으며, 도중에 숫자하나를 제외할 수 있는 조건이 걸려있습니다.


숫자의 수는 10만개이고, DP로 정의하여서 문제를 풀어야한다고 생각했습니다.

도중에 숫자하나를 제외할 수 있는 조건이 없다고 생각하고 연속된 수의 합을 구하는 DP를 정의해봅시다.

일단, 최대한 양의숫자를 유지하면서 더하는 것이 구할 수 있는 합 중 가장 큰 합을 구할 수 있을 것입니다.

1) 양수를 더할때는 그냥 더해주면 됩니다.

2) 음수를 더하려고 할 때, 합이 현재의숫자를 더하는것보다 더 작아진다면 `이때까지 더한 수의 합들을 포기하고 0에 현재의 숫자만 더하도록 합니다`

음수인 상태에서 그 다음 숫자를 더하는 것보다 0에다가 다음 숫자를 더한 것이 이득입니다.

여기서 이제 하나의 수를 제외할 수 있다고 했으니 그 조건을 추가해봅시다.

`DP[i][j] = i까지의 연속된 숫자의 최대 합 `

(j가 1일때는 숫자를 한개 제외했을 때, 0일떄는 숫자를 제외하지 않을 떄)

Dp[i][0]은 이전에 숫자를 더한 것에 현재의숫자를 더한 값과 현재의숫자를 더한 값 중 더 큰 값이 들어갑니다.

ex ) Dp[i-1][0] = -10이고 numAry[i]=-21이라면, -31보다 -21이 더 크기때문에 -21을 넣어줍니다.

Dp[i][1]은 이전에 숫자를 더한 것을 그대로 유지할 것인지(다만 숫자를 한개 제외한 값이 됩니다), 

이전에 숫자 한개가 제외 되었을때의 합에다가 현재의 숫자를 그대로 더한 것이 큰지 판단하여 값을 넣으면 됩니다.




---


<br/> <br/>

```java

public class Main {

    static int N;
    static int maxValue;
    static int[] numAry;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        init();
        calc();
        System.out.println(maxValue);

    }

    public static void init() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N=stoi(br.readLine());
        StringTokenizer st=new StringTokenizer(br.readLine());
        numAry=new int[N];

        dp = new int[N][2];

        for(int i=0;i<N;i++){
           numAry[i]=stoi(st.nextToken());
        }


    }


    public static void calc(){

        dp[0][0]=numAry[0];
        dp[0][1]=numAry[0];
        maxValue=numAry[0];

      for(int i=1;i<N;i++){
          dp[i][0]=Math.max(dp[i-1][0]+numAry[i],numAry[i]);
          dp[i][1]=Math.max(dp[i-1][0],dp[i-1][1]+numAry[i]);
          maxValue=Math.max(Math.max(maxValue,dp[i][0]),dp[i][1]);
      }

    }



    public static int stoi(String input){
        return Integer.parseInt(input);
    }


}

```