문제를 처음에 읽었을 때 어떻게 풀지 고민을 하다가 처음에는 `DP`를 생각하였습니다.

하지만 DP로 풀기에는 dp[언니위치][현재시간] => 의 배열의 크기가 위치를 최대 10만 시간을 10만으로 잡으면 메모리 초과가 날 것 같아서

다른 방법을 생각해야만 했습니다.

그래서 생각해냈던 방법이 `BFS` 였습니다. 하지만 BFS를 이용해서 구현을 하기 위해서는 visit 처리를 잘 해줘야만 했습니다.

저 같은 경우에는 현재 cur.cost(시간)이 visit[]에 방문했었던 cost보다 많이 크다면 방문을 할 필요가 없다고 생각하였기 때문에 que에 추가 하지않았습니다.

만약에 visit 처리를 하지 않는다면, que에는 중복된 노드들이 엄청나게 들어가기 때문에 메모리 초과가 날 것입니다.


+ **다른 분들의 풀이를 볼 떄 visit처리를 que에 넣을 때 처리하지 않고, que에서 poll할 때 처리하는 식으로 구현하는 것을 봤습니다. 새로운 방법을 알게되었습니다.**

<br/>

밑의 코드와 같이 pos이 동생의 위치가 됐을 때 min(가장 짧은 시간)과 비교하여 같으면 cnt를 늘려주는 식으로 답을 구현하였습니다.

```java

if(cur.pos==b){
     if(cur.cost<min){
        min=cur.cost;
        cnt=1;
    }
    else if(cur.cost==min)
        cnt++;

    continue;
}

```

<br/>

```java

public class Main {

    static int a,b,cnt,min;

    public static void main(String[] args) throws IOException
    {
        init();
        calc();
        System.out.println(min);
        System.out.println(cnt);

    }

    public static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        a=Integer.parseInt(st.nextToken());
        b=Integer.parseInt(st.nextToken());
    }

    public static void calc(){
        min = 100001;
        Queue<Node> que = new LinkedList<>();
        int[] visit = new int[100001];

        Arrays.fill(visit,100001);
        visit[a]=0;
        que.add(new Node(a,0));
        while(!que.isEmpty()){
            Node cur = que.poll();

            if(cur.cost>min) continue;

            if(cur.pos==b){
                if(cur.cost<min){
                    min=cur.cost;
                    cnt=1;
                }
                else if(cur.cost==min)
                    cnt++;
                continue;
            }

            if(cur.pos-1>=0 && visit[cur.pos-1]>=cur.cost) {
                visit[cur.pos-1]=cur.cost;
                que.add(new Node(cur.pos - 1, cur.cost + 1));
            }

            if(cur.pos+1<=100000 && visit[cur.pos+1]>=cur.cost) {
                visit[cur.pos+1]=cur.cost;
                que.add(new Node(cur.pos + 1, cur.cost + 1));

            }

            if(cur.pos*2<=100000 && visit[cur.pos*2]>=cur.cost ) {
                visit[cur.pos*2]=cur.cost;
                que.add(new Node(cur.pos * 2, cur.cost + 1));
            }

        }
    }

}

class Node{
    int pos,cost;

    Node(int pos,int cost){
        this.pos=pos;
        this.cost=cost;
    }
}

```