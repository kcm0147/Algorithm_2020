[요구사항]

문을 옮겨서 요구하는 벽장을 열도록 해야하는데, 이떄 벽장문을 이동하는 최솟값을 구해야합니다.



<br/>
 
 벽장문은 두개로 고정이 되어 있습니다.

 문제에서 요구하는 벽장의 위치는

 ```
 
 1.left 벽장 보다 왼쪽에 있을 때 
 
 2.right벽장 보다 오른쪽에 있을 때

 3.left벽장과 right 벽장 사이에 있을 때
 
 ```

 총 3가지의 경우의 수가 존재합니다.

 1번의 경우에는 열려고하는 벽장문을 오픈되어있는 left쪽으로 미는 것이 최소 이동 횟수가 될 것이며

 3번의 경우에는 열려고하는 벽장문을 오픈되어있는 Right쪽으로 미는 것이 최소 이동 횟수가 될 것입니다. 

 2번의 경우에는 left쪽으로 미는지 right쪽으로 미는지 둘다 고려를 해봐야 합니다.

 이를 생각해보면 Dp를 dp[left][right]로 정의하고 몇번째 index까지 탐색하고 있는지를 알아야하기 때문에

 `dp[left][right][index]`로 정의를 할 수 있습니다.

 `dp[left][right][index]= left,right에 벽장이 열려있을 때 caseAry[index]를 열고 싶을 때 최소 누적 이동횟수`

 

<br/> <br/>

```java

public class Main {


    static int initLeft,initRight,N,M;
    static int[] caseAry;
    static int[][][] dp;

    static final int INF = 1000000;


    public static void main(String[] args) throws IOException {

        init();
        System.out.println(calc(initLeft,initRight,1));


    }

    public static void init() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N=stoi(br.readLine());
        StringTokenizer st =new StringTokenizer(br.readLine());
        initLeft=stoi(st.nextToken());
        initRight=stoi(st.nextToken());
        M=stoi(br.readLine());

        dp=new int[N+1][N+1][M+1];

        for(int i=0;i<N+1;i++){
            for(int j=0;j<N+1;j++){
                Arrays.fill(dp[i][j],-1);
            }
        }

        caseAry=new int[M+1];

        for(int i=1;i<=M;i++){
            caseAry[i]=stoi(br.readLine());
        }
    }


    public static int calc(int left,int right,int index){
        if(index>M) return 0;
        if(dp[left][right][index]!=-1) return dp[left][right][index];

        int curNum=caseAry[index];
        int result=INF;

        if(curNum>left && curNum<right){
            result=Math.min(result,calc(left,curNum,index+1)+Math.abs(curNum-right));
            result=Math.min(result,calc(curNum,right,index+1)+Math.abs(left-curNum));
        }
        else if(curNum<=left){
            result=Math.min(result,calc(curNum,right,index+1)+Math.abs(left-curNum));
        }
        else if(curNum>=right){
            result=Math.min(result,calc(left,curNum,index+1)+Math.abs(curNum-right));
        }

        return dp[left][right][index]=result;

    }

    public static int stoi(String input){
        return Integer.parseInt(input);
    }
}


```