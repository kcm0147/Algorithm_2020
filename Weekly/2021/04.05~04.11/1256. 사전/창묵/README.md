정말 상당히 어려운 문제였습니다.

이 문제는 **DP**를 이용해서 푸는 문제인데, 조합론을 알아야지 이해하기가 쉽습니다.

**The k-th Lexicographical String** 문제로 유명한 문제인데, 이번기회에 확실히 이해를 하였습니다.

---

본론으로 들어가기전에 어떻게 k번째 문자열을 찾을 수 있는지 부터 생각해봐야 합니다.

일단 하나하나 k번째 문자열을 찾는 것은 무리가 있습니다. 

그래서 저희는 대략적으로 몇번째 이상부터 탐색을 할지 생각을 해봐야합니다.

이것을 위해서 **조합**을 생각해볼 수 있습니다.

<br/>

문제에서 a와 z 문자로만 문자열이 구성되어있다고 하였는데, 

이는 조합으로 표현을 하면 **a+zCa == a+zCz**와 같습니다. (a는 a의 수 z는 z의 수)

그럼 3C1은 무엇을 의미할까요?

**3개의 문자열 길이 중에서 z는 1개 a는 2개를 이용한 문자열의 총 갯수**를 의미합니다.

이는 

```
aaz
zaa
aza

```

이렇게 표현할 수 있습니다.

<br/>

여기서 이해해야할 것은 **다음 a 문자를 붙이는 경우의 수는 저 위에 3개의 경우의 수의 갯수와 같습**니다.

또한 **z문자를 붙이는 경우의 수 또한 저 위에 3가지 경우의수와 같습**니다.

그러면 만약에 k번째 문자열이 저 위의 3C1보다 크다면? 그 다음 문자열은 a를 붙인 경우의 수가 아니라,

z를 붙인 경우의 수가 될 것입니다. 

왜냐하면 4번쨰문자에 a를 붙인다 한들, k번째 문자열에 도달할 수 없기 때문입니다.

<br/>

그래서 아래와 같이 a나 z를 붙이기 이전(aCnt+zCnt-1)의 경우의 수가 k번째보다 충분히 크다면

그것은 'a'문자열을 붙여야만 하는 것이고, 만약에 작다면? 무조건 'z'문자열을 붙여야 합니다.

이해하기가 많이 어렵습니다. 직접 그림을 그려가면서 이해하는 것이 쉽습니다.



```java

if(dp[aCnt+zCnt-1][zCnt]>=k){
        answer+='a';
        calc(aCnt-1,zCnt,k);
}
else{
        answer+='z';
        calc(aCnt,zCnt-1,k-dp[aCnt+zCnt-1][zCnt]);
}


```



---

다음은 DP 배열을 이해하고 어떻게 사용하는가 입니다.

DP[x][y] 를 xCy라고 생각하면, xCy=x-1Cy+x-1Cy-1 파스칼 삼각형 정리에 의해 정의가됩니다.

조합의 경우의 수를 DP를 사용하지 않고 해결한다면 시간초과가 되어, 문제를 해결할 수 없습니다.

그래서 DP를 이용하여 조합의 경우의 수를 계산하였습니다.

---


<br/>




<br/> <br/>

---

```java


public class Main {


    static String answer="";
    static int N,M,K;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        init();

        if (dp[N + M][M] < K) {
            System.out.println(-1);
        }
        else {
            calc(N, M, K);
            System.out.println(answer);
        }

    }

    public static void init() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st =new StringTokenizer(br.readLine());
        N=stoi(st.nextToken());
        M=stoi(st.nextToken());
        K=stoi(st.nextToken());

        dp=new int[N+M+1][M+1];

        for(int i=1;i<=N+M;i++){
            for(int j=0;j<=M;j++){
                if(j==0 || i==j){
                    dp[i][j]=1;
                    continue;
                }
                dp[i][j] = Math.min(1000000000, dp[i - 1][j] + dp[i-1][j - 1]);
            }
        }
    }

    public static int stoi(String input){
        return Integer.parseInt(input);
    }

    public static void calc(int aCnt,int zCnt,int k){

        if(aCnt==0 && zCnt==0) return;

        if(aCnt==0){
            answer+='z';
            calc(aCnt,zCnt-1,k);
        }
        else if(zCnt==0){
            answer+='a';
            calc(aCnt-1,zCnt,k);
        }
        else if(dp[aCnt+zCnt-1][zCnt]>=k){
            answer+='a';
            calc(aCnt-1,zCnt,k);
        }
        else{
            answer+='z';
            calc(aCnt,zCnt-1,k-dp[aCnt+zCnt-1][zCnt]);
        }
    }

}




```