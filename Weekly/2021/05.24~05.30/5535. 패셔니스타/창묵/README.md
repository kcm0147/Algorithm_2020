* DP

<br/>

문제를 처음 읽었을 때 minHeap과 maxHeap을 두개 사용하여 답을 구하면 될 것이라고 생각했습니다.

하지만 그렇게 해서는 답이 나오지 않아서 완전탐색으로 진행하려다가 효율적인 부분에서 문제가 될 것 같아서 **DP**로 접근을 하였습니다.

D날짜에 어떤 옷 N을 선택했으면, D+1 날짜에 어떤 옷을 선택하는 경우가 이전 D날짜에 영향을 받기 때문에 **DP**라고 생각을 하였습니다.

<br/>

DP 정의는 간단합니다.

`dp[날짜][몇번째 옷]`은 `날짜에 몇번째 옷을 입었을 때의 최대 화려도`가 담기도록 정의하였습니다.

top-down 방식으로 구현하였는데 예외 조건으로는 현재 날짜 day에 해당하는 날씨에 맞는 옷인지 if문으로 판단 후에 연산을 진행하면 됩니다.

day=1 일때는 day=0일때 고른 옷이 없기 때문에 Math.abs(cur.cost-prev)를 더해주지 않기 위해 예외처리를 하였습니다.


<br/>

```java

public class Main {


    static List<Node> clothList;
    static List<Integer> tempList;

    static int[][] dp;

    static int D,N;
    static final int INF = 999999999;


    public static void main(String[] args) throws IOException
    {
        int result=0;

        init();

        result=calc(1,0,0);

        System.out.println(result);


    }


    public static void init() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        D=stoi(st.nextToken());
        N=stoi(st.nextToken());

        clothList=new ArrayList<>();
        tempList=new ArrayList<>();
        tempList.add(-1);

        dp=new int[D+1][N+1];

        for(int i=0;i<D+1;i++)
            Arrays.fill(dp[i],INF);

        for(int i=0;i<D;i++)
            tempList.add(stoi(br.readLine()));

        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());

            int min = stoi(st.nextToken());
            int max = stoi(st.nextToken());
            int cost = stoi(st.nextToken());
            clothList.add(new Node(min, max, cost));
        }


    }


    public static int calc(int day,int cloth,int prev) {
        if (day > D) return 0;
        if (dp[day][cloth] != INF) return dp[day][cloth];

        int result=0;

        int curTemp=tempList.get(day);

        for(int i=0;i<clothList.size();i++){
            Node cur = clothList.get(i);

            if(day==1 && cur.minTemp<=curTemp && curTemp<=cur.maxTemp){
                result=Math.max(result,calc(day+1,i,cur.cost));
            }
            else if(cur.minTemp<=curTemp && curTemp<=cur.maxTemp){
                result=Math.max(result,calc(day+1,i,cur.cost)+Math.abs(cur.cost-prev));
            }
        }

        return dp[day][cloth]=result;
    }

    public static int stoi(String input){
        return Integer.parseInt(input);
    }

}

class Node{
    int minTemp,maxTemp,cost;

    Node(int minTemp,int maxTemp,int cost){
        this.minTemp=minTemp;
        this.maxTemp=maxTemp;
        this.cost=cost;
    }
}


```