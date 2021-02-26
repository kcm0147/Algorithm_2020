```java
public class Main {


    static int N,S,D,answer;
    static ArrayList<ArrayList<Integer>> tree;

    static boolean[] visit;
    static int[] length;

    public static void main(String[] args) throws IOException {
        init();
        visit[S]=true;
        dfs(S);
        visit=new boolean[N+1];
        visit[S]=true;
        calc(S);

        System.out.println(answer);
    }

    public static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N=stoi(st.nextToken());
        S=stoi(st.nextToken());
        D=stoi(st.nextToken());

        tree=new ArrayList<>();
        visit=new boolean[N+1];
        length=new int[N+1];

        for(int i=0;i<=N;i++){
            tree.add(new ArrayList<>());
        }

        for(int i=0;i<N-1;i++){
            st=new StringTokenizer(br.readLine());
            int index1=stoi(st.nextToken());
            int index2=stoi(st.nextToken());
            tree.get(index1).add(index2);
            tree.get(index2).add(index1);
        }


    }

    public static int dfs(int index){
        ArrayList<Integer> linkList= tree.get(index);

        int max=0;

        for(int i=0;i<linkList.size();i++){
            int next=linkList.get(i);

            if(!visit[next]){
                visit[next]=true;
                max=Math.max(max,dfs(next)+1);
            }
        }

        return length[index]=max;

    }

    public static void calc(int index){

        ArrayList<Integer> linkList= tree.get(index);

        for(int i=0;i<linkList.size();i++){
            int next=linkList.get(i);

            if(!visit[next] && length[next]>=D){
                answer+=1;
                visit[next]=true;
                calc(next);
                answer+=1;
            }
        }

    }



    public static int stoi(String input) {
        return Integer.parseInt(input);
    }
}


```