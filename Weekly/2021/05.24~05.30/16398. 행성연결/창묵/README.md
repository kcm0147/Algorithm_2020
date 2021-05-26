* 최소 스패닝 트리

<br/>

문제를 봤을 때, 기본적인 최소 스패닝 트리를 만들면 되는 문제라고 생각을 했습니다.

그 이유는 **노드들을 최소 비용으로 전부 연결하는 것이 문제의 요구조건**이기 때문입니다.

<br/>

최소 스패닝 트리의 경우 **크루스칼 알고리즘**을 활용하여 구성하였습니다.

크루스칼 알고리즘은 **Union-find 기반의 알고리즘** 입니다.

<br/>

문제 풀이의 방식은 다음과 같습니다.

```
1. 행렬의 입력값을 Node로 만들어서 MinQue에 넣습니다.

2. MinQue는 cost 기준으로 정렬이 되어있는데 MinQue에서 Node를 하나씩 꺼내면서 union 할 수 있는지 확인합니다.

이때 union을 한다는 의미는 행성을 연결하는 행위를 의미합니다.

3. 무조건 N-1개를 연결하는것이 문제의 요구조건이기 때문에 N-1개의 행성이 연결되면 연산을 종료하고 답을 출력합니다.


```

<br/>




<br/>

```java

public class Main{


    static int N,answer,endPoint;
    static Tree tree;
    static boolean visit[];

    public static void main(String[] argv) throws IOException{



        init();
        dfs(1,0);
        Arrays.fill(visit,false);

        dfs(endPoint,0);


        System.out.println(answer);

    }

    public static void init() throws IOException {
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N=stoi(br.readLine());
        tree = new Tree(N+1);
        visit = new boolean[N+1];


        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            int baseIndex=stoi(st.nextToken());
            while(true){
                int index2=stoi(st.nextToken());
                if(index2==-1)
                    break;
                int weight=stoi(st.nextToken());
                tree.linkBinary(baseIndex,index2,weight);
            }

        }
    }

    public static void dfs(int index,int length){
        ArrayList<Node> curList=tree.getLinkList(index);

        visit[index]=true;

        for(int i=0;i<curList.size();i++){
            Node curNode = curList.get(i);
            if(!visit[curNode.index]){
                if(answer<length+curNode.length){
                    endPoint=curNode.index;
                    answer=length+curNode.length;
                }
                dfs(curNode.index,length+curNode.length);
            }
        }

    }


    public static int stoi(String string){
        return Integer.parseInt(string);
    }
}

class Tree{
    static ArrayList<ArrayList<Node>> edgeList;

    Tree(int size){
        edgeList=new ArrayList<>();

        for(int i=0;i<=size;i++)
            edgeList.add(new ArrayList<>());
    }

    public static void linkBinary(int index,int index2,int weight){
        edgeList.get(index).add(new Node(index2,weight));
        edgeList.get(index2).add(new Node(index,weight));
    }

    public static ArrayList<Node> getLinkList(int index){
        return edgeList.get(index);
    }


}

class Node{
    int index;
    int length;

    Node(int index,int length){
        this.index=index;
        this.length=length;
    }
}

```