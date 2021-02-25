
```java
public class Main {


    static int N,M,answer;
    static ArrayList<Integer> known;
    static int[] parent;
    static ArrayList<ArrayList<Integer>> party;

    public static void main(String[] args) throws IOException{

        init();
        System.out.println(calcParty());
    }

    public static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N=stoi(st.nextToken());
        M=stoi(st.nextToken());

        parent=new int[N+1];
        party=new ArrayList<>();
        known=new ArrayList<>();

        st=new StringTokenizer(br.readLine());
        int K=stoi(st.nextToken());


        for(int i=0;i<N+1;i++){
            parent[i]=i;
        }

        for(int i=0;i<M+1;i++){
            party.add(new ArrayList<>());
        }

        if(K!=0) {
            for (int i = 0; i < K; i++) {
                int index=stoi(st.nextToken());
                known.add(index);
            }
        }

        for(int i=0;i<M;i++){
            st=new StringTokenizer(br.readLine());
            int cnt=stoi(st.nextToken());
            int previous,input;

            if(cnt>0) {
                previous = stoi(st.nextToken());
                party.get(i).add(previous);

                for (int j = 1; j < cnt; j++) {
                    input = stoi(st.nextToken());
                    party.get(i).add(input);
                    union(previous, input);
                    previous = input;
                }
            }
        }



    }

    public static int calcParty(){

        answer=M;

        for(int i=0;i<M;i++){

            boolean goCheck=false;

            for(int j=0;j<party.get(i).size();j++){
                int curPeople=party.get(i).get(j);
                for(int k=0;k<known.size();k++){
                    int knowPeople=known.get(k);

                    if(find(curPeople)==find(knowPeople)){
                        goCheck=true;
                        break;
                    }
                }

                if(goCheck){
                    answer--;
                    break;
                }

            }
        }

        return answer;
    }

    public static boolean union(int index1,int index2){
        int root1=find(index1);
        int root2=find(index2);

        if(root1!=root2){
            if(root1<root2){
                parent[root2]=root1;
            }
            else
                parent[root1]=root2;

            return true;
        }

        return false;
    }

    public static int find(int index){

        if(index==parent[index]){
            return parent[index];
        }
        else
            return parent[index]=find(parent[index]);

    }


    public static int stoi(String input){
        return Integer.parseInt(input);
    }

}



```