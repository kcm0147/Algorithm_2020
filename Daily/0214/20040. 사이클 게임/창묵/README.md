


```java

public class Main {

    static int[] root;

    public static void main(String[] args) throws IOException {
        System.out.println(init());

    }

    public static int init() throws IOException{
            BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n=stoi(st.nextToken());
            int m=stoi(st.nextToken());

            root=new int[n];

            for(int i=0;i<n;i++){
                root[i]=i;
            }

            for(int i=0;i<m;i++){
                st=new StringTokenizer(br.readLine());
                int index1=stoi(st.nextToken());
                int index2=stoi(st.nextToken());

                if(!union(index1,index2)){
                    return i+1;
                }
            }

            return 0;

    }

    public static int find(int index){
        if(index==root[index]){
            return index;
        }
        else
            return root[index]=find(root[index]);
    }

    public static boolean union(int index1,int index2){
        int root1=find(index1);
        int root2=find(index2);

        if(root1==root2) return false;

        if(root1>root2){
            root[root2]=root1;
        }
        else
            root[root1]=root2;

        return true;
    }

    public static int stoi(String input){
        return Integer.parseInt(input);
    }

}



```