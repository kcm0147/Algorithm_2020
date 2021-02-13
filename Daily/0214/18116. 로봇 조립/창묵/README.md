

```java

public class Main {

    static Node[] rootAry;
    static final int size = 1000001;


    public static void main(String[] args) throws IOException {
        init();
    }

    public static int init() throws IOException{
            BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n=stoi(st.nextToken());


            rootAry=new Node[size];

            for(int i=0;i<size;i++){
                rootAry[i]=new Node(i,1);
            }


            for(int i=0;i<n;i++){
                st=new StringTokenizer(br.readLine());

                char how = st.nextToken().charAt(0);

                if(how=='I'){
                    int index1=stoi(st.nextToken());
                    int index2=stoi(st.nextToken());
                    union(index1,index2);
                }
                else if(how=='Q'){
                    int index1=stoi(st.nextToken());
                    int curRoot=find(index1);
                    System.out.println(rootAry[curRoot].weight);
                }
            }

            return 0;

    }

    public static int find(int index){
        if(rootAry[index].index==index){
            return index;
        }
        else{
            return rootAry[index].index=find(rootAry[index].index);
        }

    }

    public static boolean union(int index1,int index2){


        int root1=find(index1);
        int root2=find(index2);

        if(root1==root2) return false;

        if(root1>root2){
            rootAry[root2].index=root1;
            rootAry[root1].weight+=rootAry[root2].weight;
        }
        else {
            rootAry[root1].index=root2;
            rootAry[root2].weight+=rootAry[root1].weight;
        }

        return true;
    }


    public static int stoi(String input){
        return Integer.parseInt(input);
    }
}

class Node{
    int index;
    int weight;

    Node(int index,int weight){
        this.index=index;
        this.weight=weight;
    }
}



```