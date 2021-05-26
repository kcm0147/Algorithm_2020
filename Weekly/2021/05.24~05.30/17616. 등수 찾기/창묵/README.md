* DFS


<br/>

등수찾기 문제를 처음에는 **위상 정렬**로 문제를 접근하여 해결을 하려고 했습니다.

하지만 일반적인 위상정렬로 문제를 해결하기에는 예외사항들이 있었고 문제가 잘 해결 되지 않았습니다.

<br/>

그래서 문제를 **DFS**를 이용하여 다시 접근해보았습니다.

<br/>

저 같은 경우에는 X노드의 Parent들을 찾는 findParent()를 통해서 parentNode들의 갯수를 연쇄적으로 찾았습니다.

**parentNode의 수는 자신보다 성적이 높은 사람의 수라는 의미**이기 때문에 최고등수는 `1+parentNode`로 결정이 됩니다.

<br/>

반대로 X노드의 Child들은 이미 방문했던 노드들을 제외하고 **모든 노드들을 방문해보면서 X노드에 연결이 되어있는지 확인**을 해주었습니다.

isLink[]라는 배열을 두어서 X노드에 연결이 되어있으면 true로 설정을 해주었습니다.

이때 **X노드에 연결이 되어있다는 말은 X노드보다 확정적으로 성적이 낮다는 의미**가 됩니다.

최저등수는 최저 N등으로 설정해놓고 isLink가 true인 Node에 한해서 **최저등수를 -1**씩하면서 답을 도출하였습니다.

<br/>

---

저는 생각보다 코드를 복잡하게 짠 것 같은데 다른 사람들의 풀이를 확인해보면

ParentGraph와 childGraph를 생성하여 각각 양방향으로 BFS나 DFS를 실행하면 답을 쉽게 구하는 것을 확인할 수 있었습니다.

좀 더 다양한 생각을 하도록 노력해야겠다고 생각했습니다.


<br/>

<br/>

---

```java

public class Main {


    static List<List<Integer>> graph;
    static boolean[] isLink;
    static boolean[] visit;
    static int N,M,X,min,max;

    public static void main(String[] args) throws IOException
    {

        init();
        findParent(X);
        for(int i=1;i<=N;i++){
            if(!visit[i]) {
                visit[i]=true;
                if(findChild(i)){
                    min--;
                }
            }
        }
        System.out.print(max+" "+min);


    }


    public static void init() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N=stoi(st.nextToken()); M=stoi(st.nextToken()); X=stoi(st.nextToken());

        graph=new ArrayList<>();

        isLink=new boolean[N+1];
        visit=new boolean[N+1];

        for(int i=0;i<=N;i++)
            graph.add(new ArrayList<>());

        for(int i=0;i<M;i++){
            st=new StringTokenizer(br.readLine());
            int index=stoi(st.nextToken());
            int otherIndex=stoi(st.nextToken());
            graph.get(otherIndex).add(index);
        }

        max=1;
        min=N;


    }

    public static void findParent(int index){

        List<Integer> linkList = graph.get(index);

        for(int nextIndex : linkList){
            if(!visit[nextIndex]){
                visit[nextIndex]=true;
                findParent(nextIndex);
                max++;
            }
        }

    }

    public static boolean findChild(int index){
        List<Integer> linkList = graph.get(index);

        for(int nextIndex : linkList){

            if(isLink[nextIndex] || nextIndex==X){
                isLink[index]=true;
            }

            if(!visit[nextIndex]){
                visit[nextIndex]=true;
                if(findChild(nextIndex)){
                    min--;
                    isLink[index]=true;
                }
            }

        }

        return isLink[index];
    }


    public static int stoi(String input){
        return Integer.parseInt(input);
    }

}

class Node{
    int index;
    boolean link;

    Node(int index,boolean link){
        this.index=index;
        this.link=link;
    }
}


```


