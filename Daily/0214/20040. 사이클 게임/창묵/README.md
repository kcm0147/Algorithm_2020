* Union-find

사이클 판단의 유무를 판단하는 문제입니다.

사이클을 판단할때 사용하는 알고리즘이 'Union-find`이기 때문에

곧바로 문제에 Union-find를 활용하여 문제를 해결하였습니다.

주어진 두 개의 수를 Union시켰을 때, 두 개의 수가 가리키는 Root가 연결하기도 전에 `같은 루트`가 나온다면, 이때 연결을 하면 사이클이 발생한다는 의미입니다.

사이클이 발생하면 그떄의 차례가 몇번쨰인지 return 시켜주고, 사이클이 발생하지않고 끝까지 다 연결할 수 있다면 0을 return 해줍니다.

사이클을 판단하는 함수는 보통 크루스칼 알고리즘에서도 사용되기 때문에 손쉽게 문제를 해결 할 수 있었습니다.

<br/>



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