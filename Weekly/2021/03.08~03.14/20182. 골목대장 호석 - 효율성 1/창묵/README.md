일단 저는 이 문제를 *다익스트라 알고리즘*으로만 해결하였습니다.

다른 풀이도 있었는데 *이분탐색* + *다익스트라 알고리즘*을 이용하여 해결할 수도 있습니다.

일단 저는 N의 갯수도 많고, M의 갯수도 많아서 최단거리를 구하는 알고리즘 중에서 '다익스트라 알고리즘'을 생각했습니다.

다만, 저희가 아는 최단거리 구하기 문제와는 좀 다른데 그 이유는 이 문제의 답은 최단거리와는 전혀 상관이 없습니다.

그저 지나간 골목의 cost를 최소화 시키는 답을 구하기만 하면 되기 때문입니다.

그래서 저는 dist[i]배열에 

1. 출발지로부터 i번째까지 오는데 내야하는 돈의양(costSum) 

2. 출발지로부터 i번째까지 오는데 내야하는 돈의양 중 최대값(maxValue)

두개를 저장하였습니다.

처음에 dist 배열의 모든 maxValue를 INF로 초기화한 이유는 저희가 구해야할 maxValue를 최솟값으로 Update를 해야하기 때문입니다.

다익스트라 알고리즘의 조건도 일반적인 다익스트라 알고리즘의 조건과는 조금 변형을 하여 구현하였습니다.

<br/>

```java

 ArrayList<Node> linkGraph=graph.get(cur.index);

for(Node linkNode: linkGraph){
    int Sum=dist[cur.index].costSum+linkNode.cost;
        if(Sum<=money && dist[linkNode.index].maxValue>Math.max(dist[cur.index].maxValue,linkNode.cost)){
            dist[linkNode.index].costSum=Sum;
            dist[linkNode.index].maxValue=Math.max(dist[cur.index].maxValue,linkNode.cost);
            que.add(new Node(linkNode.index,dist[linkNode.index].costSum));
    }
}


```

Sum은 현재 이전에 연결된 노드(cur)까지 오는데 사용되는 돈의 양 + curNode->linkNode 가는데 사용되는 돈의 양(linkNode.cost)를 의미하는데,

이 Sum이 제가 최대로 가지고있는 money보다 크다면 curNode->linkNode로는 움직일 수 없음을 의미하기때문에 update를 하지 않습니다.

또한 dist[linkNode.index].maxValue는 linkNode.index까지 오는데 내야하는 돈의 최댓값인데, 내야하는 돈의 값이 dist[linkNode.index].maxValue보다 크다면 굳이 update를 할 필요가 없습니다.

dist[cur.index].maxValue는 `출발지에서 linkNode에 도달하기 바로 전 노드까지 가는데 내야하는 cost중 최댓값`을 의미합니다.

즉 `Math.max(dist[cur.index].maxValue,linkNode.cost)`는 이전노드(curNode)까지 가는데 내야하는 cost의 최댓값과 curNode->linkNode로 가기 위해 내야하는 cost중 max값을 의미합니다.

이렇게 update를 진행하다가 dist[목적지].maxValue를 답으로 출력하면 문제는 해결하게 됩니다.

<br/>

이분탐색으로 문제를 해결하는 방법은 이 문제에서 각 골목 당 cost의 최댓값은 1~20이기 때문에

이분탐색으로 범위 내에 mid 값을 잡아서 최소 비용이 money 이하로 나오면 right 를 줄이고, money보다 커지면 left 를 키워주는 형식으로 탐색을 진행해봅니다.

mid값을 정하고나서 다익스트라 알고리즘을 진행하는데 이때 간선의 길이가 mid값보다 큰 간선은 제외시키고 탐색을 진행합니다.

이렇게 이분탐색을 진행하다가 나올 수 있는 mid의 최솟값을 답으로 출력하면 됩니다.


<br/>



<br/>



```java

public class Main {


    static int N,M,start,end,money;

    static ArrayList<ArrayList<Node>> graph;
    static aryNode[] dist;

    static final int INF = 99999999;

    public static void main(String[] args) throws IOException {

        init();
        calc(start);
        if (dist[end].maxValue == INF) {
            System.out.println(-1);
        }
        else
            System.out.println(dist[end].maxValue);
    }


    public static void init() throws IOException{
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st =new StringTokenizer(br.readLine());

        N=stoi(st.nextToken());
        M=stoi(st.nextToken());
        start=stoi(st.nextToken());
        end=stoi(st.nextToken());
        money=stoi(st.nextToken());

        graph = new ArrayList<>();
        dist=new aryNode[N+1];


        for(int i=0;i<=N;i++){
            graph.add(new ArrayList<Node>());
            dist[i]=new aryNode(INF,0);
        }

        for(int i=0;i<M;i++){
            st=new StringTokenizer(br.readLine());
            int from=stoi(st.nextToken());
            int to=stoi(st.nextToken());
            int cost=stoi(st.nextToken());
            graph.get(from).add(new Node(to,cost));
            graph.get(to).add(new Node(from,cost));
        }



    }

    public static void calc(int start){

        dist[start].costSum=0;
        dist[start].maxValue=0;

        PriorityQueue<Node> que = new PriorityQueue<>();
        que.add(new Node(start,0));

        while(!que.isEmpty()){
            Node cur = que.poll();
            ArrayList<Node> linkGraph=graph.get(cur.index);
            for(Node linkNode: linkGraph){
                int Sum=dist[cur.index].costSum+linkNode.cost;
                if(Sum<=money && dist[linkNode.index].maxValue>Math.max(dist[cur.index].maxValue,linkNode.cost)){
                    dist[linkNode.index].costSum=Sum;
                    dist[linkNode.index].maxValue=Math.max(dist[cur.index].maxValue,linkNode.cost);
                    que.add(new Node(linkNode.index,dist[linkNode.index].costSum));
                }

            }
        }

    }

    public static int stoi(String input){
        return Integer.parseInt(input);
    }


}

class Node implements Comparable<Node>{
    int index,cost;
    Node(int index,int cost){
        this.index=index;
        this.cost=cost;
    }

    @Override
    public int compareTo(Node a){
        if(this.cost<=a.cost)
            return -1;
        else
            return 1;
    }

}

class aryNode{
    int maxValue,costSum;

    aryNode(int maxValue,int costSum){
        this.maxValue=maxValue;
        this.costSum=costSum;
    }
}



```