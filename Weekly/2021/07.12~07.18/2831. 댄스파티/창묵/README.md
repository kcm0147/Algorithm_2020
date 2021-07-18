# 2831 댄스파티

```java

    static Queue<Integer> manWantBigger;
    static Queue<Integer> manWantSmaller;
    static Queue<Integer> womanWantBigger;
    static Queue<Integer> womanWantSmaller;
    static int N;

    public static void main(String[] argv) throws IOException {
        init();
        System.out.println(calc());
    }


    public static void init() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        manWantBigger=new PriorityQueue<>();
        manWantSmaller=new PriorityQueue<>();
        womanWantBigger=new PriorityQueue<>();
        womanWantSmaller=new PriorityQueue<>();
        N=stoi(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        for(int i=0;i<N;i++){
            int num = stoi(st.nextToken());
            if (num < 0)
                manWantSmaller.add(Math.abs(num));
            else
                manWantBigger.add(Math.abs(num));
        }

        st=new StringTokenizer(br.readLine());

        for(int i=0;i<N;i++){
            int num=stoi(st.nextToken());
            if(num<0) womanWantSmaller.add(Math.abs(num));
            else womanWantBigger.add(Math.abs(num));
        }

    }

    public static int calc(){
        int manWant=0;
        int womanWant=0;
        int result=0;

        while(!manWantBigger.isEmpty() && !womanWantSmaller.isEmpty()){
            if(manWant==0) manWant=manWantBigger.poll();
            if(womanWant==0) womanWant=womanWantSmaller.poll();

            if(manWant<womanWant){
                result++;
                manWant=0;
                womanWant=0;
            }
            else
                womanWant=0;
        }

        manWant=womanWant=0;

        while(!manWantSmaller.isEmpty() && !womanWantBigger.isEmpty()){
            if(manWant==0) manWant=manWantSmaller.poll();
            if(womanWant==0) womanWant=womanWantBigger.poll();

            if(manWant>womanWant){
                result++;
                manWant=0;
                womanWant=0;
            }
            else
                manWant=0;
        }

        return result;
    }

    public static int stoi(String input){
        return Integer.parseInt(input);
    }
}

```

<br/>

난이도에 비해서 정말 쉽게 풀었다.. 차라리 1464. 뒤집기3 문제가 골드3 문제 같다..

일단 짝을 이루어지는 경우에는 `키큰사람을 원하는 사람 - 키작은 사람을 원하는 사람` 이렇게 짝이 무조건 지어지기 때문에 성별당 두 개의 우선순위 큐를 만들었다.

최대한 원하는 기준치의 키 차이가 적은 사람들끼리 짝을 이루어야 한다고 생각했기 떄문에 우선순위 큐를 사용하였다.

우선순위 큐에서 키큰사람을 원하는 사람을 기준으로 두어서 

상대방쪽(키작은 사람을 원하는 사람)을 큐에서 하나씩 꺼내면서 서로 조건에 일치를 한다면 result를 count하고, 

그렇지않으면 상대방쪽에서 조건에 일치할때까지 큐에서 계속 꺼낸다. 최종적으로 한쪽의 큐가 빌때까지 이를 실행한다 !