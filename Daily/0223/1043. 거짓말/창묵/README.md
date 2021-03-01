문제에서 해결해야하는 중요한 것은 '초기에 진실을 아는 사람과 같은 파티에 참여하는 사람들 또한 진실을 알게 된다는 것' 입니다.

즉 초기에 입력으로 주어진 진실을 아는 사람들로 인해서, 진실을 알지 못하던 사람들도 진실을 알게 되기때문에 그 사람이 있는 파티에서도 거짓말을 할 수 없습니다.

이 부분을 어떻게 해결하면 좋을까 정말 많은 고민을 했는데, 알고보니 이것은 'Union-find'를 이용해서 문제를 해결하는 것이었습니다.


```
1. 같은 파티에 참여하는 사람들을 같은 집합으로 묶습니다.

2. 주어진 파티에 대해서 전부다 1번 과정을 실시합니다.

3. 파티에 참여한 사람들을 한명 한명씩 확인하면서, 이 사람들이 진실을 알고있는 사람들과 같은 집합에 속해져있는지 확인을 합니다.

4. 만약 그 사람들이 진실을 알고있는 사람들과 같은 집합에 속해져 있다면 그 파티에는 거짓말을 할 수 없기 때문에 카운트를 하지 않습니다.

5. 파티에 속해져 있는 사람들 전부가 진실을 아는 사람들과 같은 파티에 참여하지 않았다면, 그 파티에서는 거짓말을 할 수 있기 때문에 카운트를 합니다.

6. 파티를 전부 탐색 한 후에 count한 값을 출력합니다.

```

테스트케이스를 예로 들어 설명하면

4 5
1 1
1 1
1 2
1 3
1 4
2 4 1


진실을 아는 사람이 1이라면,

1 1 ~ 1 4 까지 진행을 하면 1에서 4는 전부 다 다른 집합에 속해져있습니다.

허나 2 4 1 에 의해서 4와 1은 같은 집합에 속하도록 Union을 진행해줍니다.

그럼 전체 집합은

4-1

2

3

으로 이렇게 세개의 집합이 나오게 됩니다.

이제 각 파티마다 사람들이 진실을 알고있는 사람의 집합에 속해져있는지 확인합니다.

1은 진실을 알고있기 때문에 1 1은 거짓말을 할 수있는 파티에 포함하지 않습니다.

2는 1과는 다른 Set이기 떄문에 거짓말을 할 수 있습니다.

3 또한 마찬가지로 가능합니다.

하지만 `1 4`에서는 4는 1과 같은 Union이기 때문에 거짓말을 할 수 없습니다.

'2 4 1' 또한 4가 진실을 알고있는 사람 1과 같은 Union이기 때문에 거짓말을 할 수 없습니다.

그래서 답은 5-3= 2가 됩니다.


Union-find의 응용을 할 수 있는 좋은 문제였다고 생각합니다.

<br/> <br/>

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