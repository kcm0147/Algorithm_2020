문제 자체에서 힌트를 보여주네요.

문자열을 이용해서 트리를 만들어서 문제를 해결해야할 것 같습니다.

저는 문자열 트리를 보고서는 트라이로 풀면 어떨까 생각을하여 트라이 자료구조를 사용하여 문제를 해결하였습니다.

트라이의 노드는 `String` `Trie 리스트`로 구성하였습니다.

```java

class Trie{

    String value;
    List<Trie> list;

    Trie(String value){
        this.value=value;
        list=new ArrayList<>();
    }
}

```

문자열의 각 노드의 리스트가 의미하는 바는 child list를 의미합니다.

트라이를 만들 때는 사전순으로 정렬하지않고 출력을 할때 사전순으로 출력하게끔 선언하였습니다.

코드 자체는 문자열 트리를 만드는 과정이기 때문에 자세한 설명은 생략하였습니다..



<br/> <br/>

```java

public class Main {

    static Trie root;
    static StringBuilder sb;

    public static void main(String[] argv) throws IOException{

        init();
        printTrie(root,-1);
        System.out.println(sb.toString());
    }


    public static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N=stoi(br.readLine());
        root=new Trie("");
        sb=new StringBuilder();

        Trie parent;

        for(int i=0;i<N;i++){
            st=new StringTokenizer(br.readLine());
            int k=stoi(st.nextToken());
            parent=root;

            for(int j=0;j<k;j++){
                String cur = st.nextToken();
                int index=-1;

                for(int z=0;z<parent.list.size();z++){
                    if(parent.list.get(z).value.equals(cur)){
                        index=z;
                        break;
                    }
                }

                if(index==-1){
                    parent.list.add(new Trie(cur));
                    parent=parent.list.get(parent.list.size()-1);
                }
                else{
                    parent=parent.list.get(index);
                }
            }

        }
    }


    public static void printTrie(Trie parent,int cnt){
        Collections.sort(parent.list,(Trie o1,Trie o2)->o1.value.compareTo(o2.value));

        if(cnt!=-1) {
            for (int i = 0; i < cnt; i++) {
                sb.append("--");
            }
            sb.append(parent.value + "\n");
        }

        for(int i=0;i<parent.list.size();i++){
            printTrie(parent.list.get(i),cnt+1);
        }

    }


    public static int stoi(String input){
        return Integer.parseInt(input);
    }

}


class Trie{

    String value;
    List<Trie> list;

    Trie(String value){
        this.value=value;
        list=new ArrayList<>();
    }
}

```