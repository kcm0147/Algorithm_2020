* Union-find


<br/>

문제에서 요구하는 것은 두가지입니다.

문제에서 부품마다 로봇에 속하게 되는데, 

```

1. 그 부품이 속한 로봇의 부품이 몇개인지 알 수 있어야 합니다.

2. 부품 두가지가 같은 로봇에 속하도록 해야합니다.

```

2번을 보고 이 문제를 `Union-find`로 풀면 되겠다고 생각했습니다.

일반적인 `Union-find`를 구현하면 되는데, 1번의 요구사항을 위해서 Union find의 구성요소인 Node에 root뿐만 아니라 

`weight`라는 요소를 두어서 현재 로봇에 속한 부품이 몇개인지 알 수 있도록 하였습니다.

weight또한 union을 시킬 때 값을 업데이트 하도록 해주었습니다.

`rootAry[root1].weight+=rootAry[root2].weight`

나머지는 일반적인 Union-find 함수와 똑같이 구현하면 됩니다.

<br/>



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