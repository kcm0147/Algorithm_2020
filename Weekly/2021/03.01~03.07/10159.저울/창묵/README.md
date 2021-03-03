처음에는 위상정렬의 순서대로 DFS를 진행하여 문제를 접근하였지만, 시간초과의 문제로 해결하지 못하였습니다.

그러다 다른 방법을 고민하였는데 , A&&B B&&C => A&&C 를 만족하는 문제 유형이 떠올랐고 순간 플로이드-워셜로 문제를 접근하면 해결이 될 것 같았습니다.

`플로이드-워셜`로 문제를 해결한 방법입니다.



```

1. 각 노드에 관해서 O(N^3)의 시간을 사용하여 거리를 Update해줍니다.

2. 이때 cost[i][j] , cost[j][i] 가 둘다 INF라면 i와 j는 서로의 무게를 비교할 수 없음을 의미합니다.

=> i와 j가 연결할 수 없기 떄문이죠

3. cost[i][j] , cost[j][i] 중 한 개라도 INF가 아니라면 서로 무게를 비교할 수 있음을 의미합니다.

4. 이렇게 한 노드에 대해서 무게를 확인할 수 있는지 없는지 count를 해주고 답을 출력하면 됩니다.

```

[[백준]역사](https://chmook.netlify.app/Algorithms/[BOJ]%201613%20%EC%97%AD%EC%82%AC/) << 역사 문제를 한번 푼적이 있어서 비교적 쉽게 풀 수 있었던 것 같습니다.


<br/>



<br/> <br/>

```java

public class Main {


    static int N,M;
    static int[][] cost;

    static final int INF = 10000000;

    public static void main(String[] args) throws IOException {

        init();
        print();
    }

    public static void init() throws IOException{
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N=stoi(br.readLine());
        M=stoi(br.readLine());

        cost=new int[N+1][N+1];
        for(int i=0;i<=N;i++) {
            for(int j=0;j<=N;j++){
                if(i==j) cost[i][j]=0;
                else
                    cost[i][j]=INF;
            }
        }

        for(int i=0;i<M;i++){
            st=new StringTokenizer(br.readLine());
            int index=stoi(st.nextToken());
            int index2=stoi(st.nextToken());
            cost[index2][index]=1;
        }

        makeCost();

    }
    public static void makeCost(){
        for(int k=1;k<=N;k++){
            for(int i=1;i<=N;i++){
                for(int j=1;j<=N;j++){
                    cost[i][j]=Math.min(cost[i][j],cost[i][k]+cost[k][j]);
                }
            }
        }
    }

    public static void print(){
        for(int i=1;i<=N;i++){
            int cnt=0;
          for(int j=1;j<=N;j++){
              if(cost[i][j]==INF && cost[j][i]==INF){
                  cnt++;
              }
          }
            System.out.println(cnt);
        }
    }
    public static int stoi(String input){
        return Integer.parseInt(input);
    }
}




```