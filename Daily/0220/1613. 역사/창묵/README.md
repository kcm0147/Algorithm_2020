문제의 설명을 읽어보면, 사건의 전후관계에 관한 문제같습니다.

문제를 읽자마자 `위상 정렬`로 접근을 하면 어떨까 생각을 했습니다.

하지만 위상정렬로 접근을 하면 서로 연결이 되어있지 않은 노드간의 순서를 정확히 확인할 수 없습니다.

그래서 문제를 해결하다가 틀렸는데, 플로이드-워셜 알고리즘으로 해결할 수 있는 문제임에 충격이었습니다.

각 노드간의 거리를 1로 고정하고, 플로이드-워셜로 모든노드간의 최단거리를 구한 후에 문제에서 묻고자하는 

노드들끼리의 거리에 따라서 -1,1,0을 출력하면 됩니다.

1) index1과 index2의 거리가 INF가 아니라면, index1->index2 순서이기떄문에 Index1이 먼저 사건이 일어났으므로 -1을 출력합니다

2) index1과 index2의 거리가 INF인데, index2와 index1의 거리가 INF가 아니라면 index2가 사건이 먼저 일어났기때문에 1을 출력합니다

3) index1,index2 사이 거리가 INF라면 둘의 사건의 전후관계 파악을 모르기 때문에 0을 출력합니다.



플로이드-워셜을 이용해서 사건의 전후관계문제에 활용될 수 있음에 놀랬던 문제입니다.


<br/>



```java

 public class Main {


    static int[][] dist;

    static int N,K,S;
    static final int INF = 1000000;


    public static void main(String[] args) throws IOException {

        init();


    }

    public static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st=new StringTokenizer(br.readLine());
        N=stoi(st.nextToken());
        K=stoi(st.nextToken());

        dist=new int[N+1][N+1];

        for(int i=0;i<N+1;i++)
            Arrays.fill(dist[i],INF);


        for(int i=0;i<K;i++){
            st=new StringTokenizer(br.readLine());
            int index1=stoi(st.nextToken());
            int index2=stoi(st.nextToken());
            dist[index1][index2]=1;
        }

        calc();

        S=stoi(br.readLine());

        for(int i=0;i<S;i++){
            st=new StringTokenizer(br.readLine());
            int index1=stoi(st.nextToken());
            int index2=stoi(st.nextToken());

            if(dist[index1][index2]!=INF){
                wr.write(-1+"\n");
            }
            else if(dist[index1][index2]==INF && dist[index2][index1]==INF){
                wr.write(0+"\n");
            }
            else if(dist[index1][index2]==INF && dist[index2][index1]!=INF){
                wr.write(1+"\n");
            }
        }

        wr.flush();
        wr.close();


    }

    public static void calc(){


        for(int k=1;k<=N;k++){
            for(int i=1;i<=N;i++){
                for(int j=1;j<=N;j++){
                    if(i==j){
                        dist[i][j]=0;
                        continue;
                    }
                    dist[i][j]=Math.min(dist[i][j],dist[i][k]+dist[k][j]);
                }
            }
        }
    }

    public static int stoi(String input){
        return Integer.parseInt(input);
    }



}

```