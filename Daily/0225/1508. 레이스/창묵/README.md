문제를 처음 읽었을 때 직선형태에 어떤것을 세우거나, 그 위치를 구해야하는 문제임을 확인하고 이분탐색이나 투포인트로 접근을 해보자 생각을 했습니다.

가장 가까운 두 심판의 거리를 `최대`로 만드는 것이 문제의 요구사항이었고, 이분탐색으로 접근을 해보자고 했는데 생각보다 많이 어려웠습니다.

```
1. left를 0으로잡고 right는 세울 수 있는 심판들의 위치 중 가장 오른쪽에 있는 위치로 잡습니다.

2. mid = left+right/2로 계산합니다. 이때 mid는 가장 가까운 두 심판의 거리를 의미합니다.

3. 맨 왼쪽 pos[0]부터 심판을 세울 수 있는지 탐색을 해야합니다. 만약 curPos(현재 심판이 세워진 위치) + nextPos(그다음심판의 거리)의 값이 mid보다 크다면? 

 nextPos에 심판을 세울 수 있음을 의미합니다. cnt++를 해줍니다.

 하지만 mid보다 값이 작다면, nextPos에는 심판을 세울 수가 없음을 의미합니다.

4. cnt가 세워야할 심판의 수 M보다 크다면, mid(제일 가까운 두 심판 사이의 거리)보다 좀더 큰 기댓값을 기대할 수 있기에 left=mid+1;로 하고 반대로, M보다 작다면 right=mid-1로 잡습니다.

5. 출력을 할 때는 사전순으로 출력을 해야하므로 pos[0]부터 answer의 거리에 해당하는지 확인하면서 1,0을 출력해야 합니다.

```

이분탐색 문제를 좀 더 잘 응용해야하는데 연습이 필요할 것 같습니다.
    

<br/>



<br/> <br/>

```java

public class Main {


    static int N,M,K,answer;
    static int[] pos;



    public static void main(String[] args) throws IOException {
        init();
        calc(0,pos[K-1]);
        print();

    }

    public static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N=stoi(st.nextToken());
        M=stoi(st.nextToken());
        K=stoi(st.nextToken());

        pos=new int[K];

        st=new StringTokenizer(br.readLine());
        for(int i=0;i<K;i++){
            pos[i]=stoi(st.nextToken());
        }


    }

    public static void print(){
        int cnt=0;
        int curPos=0;

        for(int i=0;i<pos.length;i++){
            if(curPos<=pos[i] && cnt<M){
                cnt++;
                curPos=pos[i]+answer;
                System.out.printf("1");
            }
            else{
                System.out.printf("0");
            }
        }
    }

    public static void calc(int left,int right){

        while(left<=right){
            int mid=(left+right)/2;

            if(check(mid)){
                left=mid+1;
                answer=Math.max(answer,mid);
            }
            else
                right=mid-1;
        }

    }

    public static boolean check(int length){

        int cnt=1;
        int curPos=pos[0];

        for(int i=1;i<pos.length;i++){
            if(pos[i]-curPos>=length){
                cnt++;
                curPos=pos[i];
            }
        }

        if(cnt>=M) return true;
        else
            return false;
    }

    public static int stoi(String input) {
        return Integer.parseInt(input);
    }
}





```