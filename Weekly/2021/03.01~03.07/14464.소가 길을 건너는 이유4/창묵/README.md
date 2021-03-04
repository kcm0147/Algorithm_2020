```java

public class Main {


    static int C,N;
    static int[] chicken;
    static Pair[] cow;



    public static void main(String[] args) throws IOException {

        init();
        System.out.println(calc());


    }

    public static void init() throws IOException{
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        C=stoi(st.nextToken());
        N=stoi(st.nextToken());

        chicken=new int[C];
        cow=new Pair[N];

        for(int i=0;i<C;i++){
            chicken[i]=stoi(br.readLine());
        }

        for(int i=0;i<N;i++){
            st=new StringTokenizer(br.readLine());
            cow[i]=new Pair(stoi(st.nextToken()),stoi(st.nextToken()));
        }

        Arrays.sort(chicken);
        Arrays.sort(cow);


    }

    public static int calc() {
        int answer=0;

       for(int i=0;i<cow.length;i++){
           Pair curCow=cow[i];
           for(int j=0;j<chicken.length;j++){
               if(chicken[j]==-1) continue;
               if(chicken[j]>curCow.end) break;

               if(chicken[j]>=curCow.start && curCow.end>=chicken[j]){
                   answer++;
                   chicken[j]=-1;
                   break;
               }
           }
       }

       return answer;

    }
    public static int stoi(String input){
        return Integer.parseInt(input);
    }
}

class Pair implements Comparable<Pair>{
    int start,end;
    Pair(int start,int end){
        this.start=start;
        this.end=end;
    }

    @Override
    public int compareTo(Pair o) {
        if(o.end<this.end)
            return 1;
        else if(o.end==this.end){
            if(o.start<this.start)
                return 1;
            else
                return -1;
        }
        else
            return -1;
    }
}

```