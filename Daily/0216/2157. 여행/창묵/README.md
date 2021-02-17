문제를 봤을 떄, 가장 크게 실수 한 것으로는 무조건 N번 도시에 도착을 한다는 조건을 고려하지 않은 것입니다.

1. 무조건 1->N쪽으로 이동을 해야만 합니다.

2. N번 도시에 도착했을떄의 기내식의 최댓값을 구해야만 합니다.

그리고 도시 사이에 여러 항로가 있을 수 있기 떄문에, 항로들 중에서 최대 항로만을 Set하고 나서 연산을 진행하여야 합니다.

DP배열은 반드시 M 개 이하의 도시를 여행을 해야하기 때문에 M을 셀 수 있는 count가 필요했고

이러한 이유로 `Dp[index][cnt]= index도시에서 cnt번째로 방문했을 떄 먹을 수 있는 최대 기내식 양` 으로 정의하였습니다.

<br/>

```java

if(index==N) return 0;
if(cnt==M) return -INF;

```

만약에 index가 도착점에 도착하지않았는데도 M개의 도시를 탐방했다면 이때의 answer은 update가 되면 안되기 때문에 -INF를 return 시켜주었습니다.






<br/> <br/>

```java

public class Main {


    static int[][] dp;
    static int[][] dist;

    static int N,M,K;
    static final int INF = 98765432;


    public static void main(String[] args) throws IOException {
        init();
        System.out.println(calc(1,1));

    }

    public static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N=stoi(st.nextToken());
        M=stoi(st.nextToken());
        K=stoi(st.nextToken());

        dist=new int[N+1][N+1];
        dp=new int[N+1][M+1];

        for(int i=0;i<=N;i++)
            Arrays.fill(dp[i],-1);



        for(int i=0;i<K;i++){
            st= new StringTokenizer(br.readLine());
            int index1=stoi(st.nextToken());
            int index2=stoi(st.nextToken());
            int Iweight=stoi(st.nextToken());

            if(index1>index2) continue;

            dist[index1][index2]=Math.max(dist[index1][index2],Iweight);

        }

    }

    public static int calc(int index,int cnt){

        if(index==N) return 0;
        if(cnt==M) return -INF;
        if(dp[index][cnt]!=-1) return dp[index][cnt];

        int ans=-INF;

        for(int next=index+1;next<=N;next++){
            if(dist[index][next]!=0){
                ans=Math.max(ans,dist[index][next]+calc(next,cnt+1));
            }
        }

        return dp[index][cnt]=ans;

    }



    public static int stoi(String input){
        return Integer.parseInt(input);
    }
}



```