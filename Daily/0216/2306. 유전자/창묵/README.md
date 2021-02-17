요구사항]

문제의 조건을 만족하는 DNA의 서열의 부분서열 중 길이가 최대가 되는 KOI 유전자를 찾아야합니다.


<br/>

처음 문제를 접근했을때는 KOI유전자가 aXt or gXc 즉 a가 무조건 t보다 먼저, g가 c보다 먼저 나와야하는 규칙을 보고 stack을 이용해서 풀려고 했습니다.

a와 g가 쌓인 상태에서 t가들어오면 top이 g이기 때문에 t를 KOI유전자에서 제외시키고, 

c 또한 KOI 유전자에서 제외시키면 결국 유전자의 길이는 0이됩니다.

하지만 `agtc`를 스택(a와g가 쌓이는)을 이용해서 푼다면 문제를 해결할 수 없습니다.

실제로 이 예제의 답안은 at or tc로서 2입니다.

그래서 다른방법으로 해결한 방법이 DP입니다.

문제의 조건 두가지를 이용해서 DP로 문제를 해결할 수 있습니다.

1번조건에서 양 옆이 a,t 거나 g,c이면 KOI유전자라고 했기 때문에 

양 옆의 문자를 KOI 유전자에 포함시키고 다음연산을 진행합니다.

```java

if((target.charAt(left)=='a' && target.charAt(right)=='t') || (target.charAt(left)=='g' && target.charAt(right)=='c')){
    dp[left][right]=Math.max(dp[left][right],calc(left+1,right-1)+2);
}


```

2번조건에서는 KOI유전자 두개를 연결한다면 그 유전자도 KOI 유전자라고 했기 때문에

i번째를 기준으로 양 옆의 유전자가 KOI유전자인지를 확인하고 최댓값을 갱신하도록 하였습니다.

```java

for(int i=left;i<right;i++){
    dp[left][right]=Math.max(dp[left][right],calc(left,i)+calc(i+1,right));
}

```

Dp문제 중에서 저한테는 조금 생각하기 어려웠던 문제였습니다.

좀 더 열심히 공부해서 설명을 잘 할 수 있도록 노력해보겠습니다.



<br/> <br/>

```java

public class Main {

    static String target;
    static int[][] dp;



    public static void main(String[] args) throws IOException {

        init();;
        System.out.println(calc(0,target.length()-1));
    }

    public static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        target=br.readLine();
        dp=new int[target.length()][target.length()];

        for(int i=0;i<target.length();i++)
            Arrays.fill(dp[i],-1);
    }


    public static int calc(int left,int right){

        if(left>=right) return 0;
        if(dp[left][right]!=-1) return dp[left][right];

        dp[left][right]=0;

        if((target.charAt(left)=='a' && target.charAt(right)=='t') || (target.charAt(left)=='g' && target.charAt(right)=='c')){
            dp[left][right]=Math.max(dp[left][right],calc(left+1,right-1)+2);
        }

        for(int i=left;i<right;i++){
            dp[left][right]=Math.max(dp[left][right],calc(left,i)+calc(i+1,right));
        }


        return dp[left][right];

    }


}

```