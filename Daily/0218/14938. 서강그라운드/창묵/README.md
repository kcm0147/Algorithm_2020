[요구사항]

예은이가 주어진 수색범위 내의 모든 지역의 아이템을 습득가능하다고 할 때, 예은이가 얻을 수 있는 아이템의 최대갯수를 구해야합니다

<br/>

예은이는 자신의 수색범위내의 모든지역의 아이템을 습득이 가능하며, 처음 시작위치가 1로 고정되어있는 것이 아니라 출발지를 정할 수 있습니다.

`수색범위 = 이동할 수 있는 최대 거리`라고 생각하면, 일단 예은이는 각 지역마다 다른 지역으로 이동할 수 있는 최단거리를 알아야만 합니다.

다익스트라 알고리즘을 노드 1~N까지 적용시킬 수 있지만, 

지역의 개수 N이 100개 이하이기 때문에 `플로이드 워셜 알고리즘`을 적용해도 문제를 해결하는데 충분합니다.

플로이드 워셜 알고리즘은 모든 노드에 대해서 각각의 노드까지의 최단거리의 이동거리를 구할 수 있는데 사용하는 알고리즘입니다.

플로이드 워셜 알고리즘을 사용하여, 각 노드에서 다른 노드까지의 이동거리를 구한 후에

각 노드마다 얻을 수 있는 아이템의 합을 구하여 최대 answer값을 Update 해주면 문제를 해결할 수 있습니다.


<br/>


```java

public class Main {

    static int N,M,R;

    static int[] itemAry;
    static int[][] dist;
    static int[][] adj;

    static final int INF=1000000;

    public static void main(String[] args) throws IOException {
        init();
        calc();
        System.out.println(find());



    }

    public static void calc(){

        for(int k=1;k<=N;k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    dist[i][j]=Math.min(dist[i][j],dist[i][k]+dist[k][j]);
                }
            }
        }
    }

    public static int find(){
        int answer=0;

        for(int i=1;i<=N;i++){
            int sum=0;
            for(int j=1;j<=N;j++){
                if(dist[i][j]<=M){
                    sum+=itemAry[j];
                }
            }
            answer=Math.max(answer,sum);
        }

        return answer;
    }

    public static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N=stoi(st.nextToken());
        M=stoi(st.nextToken());
        R=stoi(st.nextToken());

        itemAry=new int[N+1];
        dist=new int[N+1][N+1];
        adj=new int[N+1][N+1];


        st=new StringTokenizer(br.readLine());
        for(int i=1;i<=N;i++){
            itemAry[i]=stoi(st.nextToken());
        }

        for(int i=0;i<R;i++){
            st=new StringTokenizer(br.readLine());
            int index1=stoi(st.nextToken());
            int index2=stoi(st.nextToken());
            int weight=stoi(st.nextToken());
            adj[index1][index2]=weight;
            adj[index2][index1]=weight;
        }

        for(int i=1;i<=N;i++){

            for(int j=1;j<=N;j++){
                if(i==j) dist[i][j]=0;
                else if(adj[i][j]!=0) dist[i][j]=adj[i][j];
                else
                    dist[i][j]=INF;
            }
        }

    }

    public static int stoi(String input){
        return Integer.parseInt(input);
    }
}

```