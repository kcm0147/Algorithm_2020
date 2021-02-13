* DP


<br/>

[요구사항]

주어진 M*N 배열에서 0으로 채워진 가장 큰 정사각형의 한변 길이를 구하여야 합니다.


<br/>

언뜻보면 미로찾기와 같은 문제처럼 보이지만, 주어진 맵에서 0으로 이루어진 가장 큰 정사각형의 한변의 길이를 구하는 것이 문제의 목적입니다.

BFS나 DFS로 정사각형의 넓이를 하나씩 탐색하는 것은 힘들다고 판단이 되어 `DP`로 문제를 해결하였습니다.

사각형의 맨 오른쪽 아래의 점을 중심으로 잡아서, 만들 수 있는 가장 큰 정사각형의 변의 길이를 저장하였습니다.

`DP[i][j]=i,j에서 만들 수 있는 가장 큰 정사각형의 변의 길이`

결론부터 말하자면 `DP[i][j]=Math.min(DP[i-1][j],DP[i][j-1],DP[i-1][j-1])+1` 이 됩니다.

<br/>

![그림2](https://user-images.githubusercontent.com/57346393/107841235-8e35aa80-6dfc-11eb-896f-b27c53903c22.jpeg)

<br/>

사각형의 오른쪽 아래 점을 중심으로 대각선 방향, 위 방향, 왼쪽방향에서 만들 수 있는 사각형의 최소의 변 길이에 +1하는 것과 같기 때문입니다.

문제에서 묻는 정사각형의 누적 변의 길이는 대각선,각 변의 길이의 깊이가 전부 다 같습니다.

쉽게 생각하면 누적 변의 길이의 공통 최솟값에 +1한다고 생각하면 됩니다!

<br/>



```java

public class Main {

    static int N,M;
    static int[][] map;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        init();
        System.out.println(calc());
    }

    public static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N=stoi(st.nextToken());
        M=stoi(st.nextToken());

        dp=new int[N+1][M+1];
        map=new int[N+1][M+1];

        for(int i=1;i<=N;i++){
            st=new StringTokenizer(br.readLine());
            for(int j=1;j<=M;j++){
                map[i][j]=stoi(st.nextToken());
            }
        }

    }

    public static int calc(){
        int answer=0;

        for(int i=1;i<=N;i++){
            for(int j=1;j<=M;j++){
                if(map[i][j]==0) {
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
                    answer = Math.max(answer, dp[i][j]);
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