

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