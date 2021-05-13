문제를 읽자마자, 그리디로 풀 수 있지 않을까? 라는 생각을 했습니다.

가장 적은 비용을 만들기 위해서는 최대한 큰 수를 처음부터 만들지 말아야 겠다는 생각을 했습니다.

그래서 우선순위 큐 MinHeap을 이용하여, Heap에서 두 개의 수를 꺼내서 더한 후에 Heap에 다시 수를 추가하는 과정을 반복하였는데

이러한 과정을 Heap에 수가 한개가 생길 때까지 계속해서 반복해서 진행해주었습니다.


<br/>

```java

public class Main {


    static int tc;
    static PriorityQueue<Long> que;

    public static void main(String[] args) throws IOException
    {
        init();
    }

    public static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        tc=Integer.parseInt(br.readLine());

        for(int i=0;i<tc;i++){
            int N=Integer.parseInt(br.readLine());
            que=new PriorityQueue<>();
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++){
                que.add(Long.parseLong(st.nextToken()));
            }
            System.out.println(calc());
        }


    }

    public static long calc(){
        long sum=0;
        long cur,next;


        while(!que.isEmpty()){
            cur=que.poll();
            next=que.poll();
            sum+=(cur+next);

            if(!que.isEmpty())
                que.add(cur+next);
        }

        return sum;
    }
}

```
