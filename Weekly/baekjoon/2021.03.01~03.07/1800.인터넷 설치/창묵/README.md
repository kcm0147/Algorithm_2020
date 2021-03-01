정말 간만에 많은 생각을 하게 만든 문제였습니다. 최단거리를 구하는 문제도 아니고, 다익스트라, 플로이드-워셜 등 많은 것을 생각해봤지만 문제풀이 방법이 생각나지 않았습니다.

알고보니 이 문제는 `다익스트라` + '이분탐색`으로 문제를 해결해야하는 정말 *좋은문제* 였습니다.

구하고자 하는 가격을 mid로 잡고 이분탐색을 진행하며 다익스트라를 해야합니다.

K개의 전선이 넘어가면, 나머지 전선의 cost의 합을 내야하는 것이 아니라 나머지 전선의 cost의 최댓값만을 내면 된다는 점을 잘 생각해야합니다.

문제풀이 방법은 다음과 같습니다.

```
1. left를 0 right를 제일 비싼 전선의 가격으로 잡습니다.

2. mid를 구한 후에, 다익스트라를 진행하는데 이때 다익스트라를 구현할 때 전선의 길이 중 mid보다 큰 것만 코스트를 1로 잡고 다익스트라를 실행합니다.

=> 그 이유로는 어차피 cost가격이 최단값에 영향을 주는 것이 아니라, 나머지 전선의 cost의 최댓값이 답을 좌우하는 요인이기 때문입니다.

   코스트를 1로 잡는 다는 것은 목적지까지 몇개의 전선 수로 도달할 수 있는지? 를 중요하게 생각하고 있다는 점입니다.

3. 이렇게 출발지 1로부터 목적지 N까지의 cost를 확인해보면, 이때의 cost[N]은 mid값보다 이상인 전선의 수를 의미함으로, 이 갯수가 K개이하라면 어차피 내야하는 가격은 mid가 될 수 있음으로 return true를 해줍니다.

다만, K개 이상이라면? 내야하는 가격은 mid가 아니라 다른 최대 Cost가 된다는 말이 되기 때문에 return false를 합니다.

4. true가 return이 된다면 좀 더 작은 cost를 기대해볼 수 있기 떄문에 right=mid-1로 설정하고, false가 retrun이 된다면 좀 더 큰 cost를 확인 해봐야하므로 left=mid+1로 설정 후 재연산을 진행합니다.

```


물론 제 힘으로 풀진 못했지만, 정말로 좋은 문제였다고 생각합니다. 꼭 다시 풀어보겠습니다.

<br/>



<br/> <br/>

```java


public class Main {

    static int N,P,K,right;

    static List<List<Pair>> graph;
    static int[] cost;

    static final int INF = 999999999;


    public static void main(String[] args) throws IOException {

        init();
        System.out.println(calc(0,right));

    }

    public static void init() throws IOException{
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());

        N=stoi(st.nextToken());
        P=stoi(st.nextToken());
        K=stoi(st.nextToken());

        graph=new ArrayList<>();
        cost=new int[N+1];


        for(int i=0;i<=N;i++){
            graph.add(new ArrayList<>());
        }

        for(int i=0;i<P;i++){
            st=new StringTokenizer(br.readLine());
            int index=stoi(st.nextToken());
            int index2=stoi(st.nextToken());
            int cost=stoi(st.nextToken());
            graph.get(index).add(new Pair(index2,cost));
            graph.get(index2).add(new Pair(index,cost));
            right=Math.max(right,cost);
        }



    }

    public static boolean djk(int start,int max){
        PriorityQueue<Integer> que = new PriorityQueue<>();
        Arrays.fill(cost,INF);

        cost[start]=0;
        que.add(start);

        while(!que.isEmpty()){
            int cur = que.poll();
            List<Pair> linkList = graph.get(cur);

            for(Pair curNode : linkList){
                if(cost[curNode.index]>cost[cur]+(curNode.cost>max ? 1:0)){
                    cost[curNode.index]=cost[cur]+(curNode.cost>max ? 1:0);
                    que.add(curNode.index);
                }
            }

        }

        return cost[N]<=K;
    }

    public static int calc(int left,int right){

        int answer=-1;
        while(left<=right){
            int mid=(left+right)/2;

            if(djk(1,mid)){
                right=mid-1;
                answer=mid;
            }
            else
                left=mid+1;
        }

        return answer;

    }




    public static int stoi(String input){
        return Integer.parseInt(input);
    }
}

class Pair{
    int index,cost;

    Pair(int index,int cost){
        this.index=index;
        this.cost=cost;
    }
}



```