```
1) 문제에서 모든 동물에 대해 사격가능한 사대가 있는지 완전탐색을 하는 경우에는 시간초과가 발생할것입니다.

2) 특정 사대에서 사격가능한 동물들을 탐색하려고해도, 사격거리내의 동물들을 하나하나 확인하는 것 또한
시간초과 문제가 발생할 것입니다.

3) 그럼 동물들의 입장에서 사대를 확인하는 경우를 살펴봐야합니다.
```

동물들의 입장에서 자신을 잡을 수 있는 사대를 찾아야만 합니다. 

동물들 중에서 자신에게 가장 가까운 사대(왼쪽,오른쪽)을 찾아서, 그 사대들만을 확인한다면 나머지 사대들은 확인할 필요가 없습니다.

왜냐하면, 가장 가까운사대에서도 동물을 잡지 못한다면 다른 사대에서도 당연히 동물을 잡을 수 없기 떄문이죠

동물 하나하나를 탐색할 때마다 가장 가까운 사대 `Lower Upper bound`를 찾아야만 하는데 이때 `이분탐색`을 사용하여 Lower Upper bound를 찾습니다.

Lower Uppper bound의 사대를 찾은 후에 현재 동물을 잡을 수 있는지 없는지 판단하고 동물의 수를 count합니다.

만약 Lower Upper Bound에서 동물을 잡지 못한다면 바로 그 오른쪽 사대(동물 기준 오른쪽에 있는 사대 중 가장 근접한 사대)에서도 동물을 잡을 수 있는지 판단을 합니다.

이렇게 모든 동물들에 대해서 계산을 진행하여 answer를 출력하며 됩니다.


---

<br/>

```java

public class Main {
    static int M,N,L,answer;
    static int[] pos;
    static Animal[] animals;

    public static void main(String[] args) throws IOException {

        init();
        calc();
        System.out.println(answer);
    }

    public static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M=stoi(st.nextToken());
        N=stoi(st.nextToken());
        L=stoi(st.nextToken());

        pos=new int[M];
        animals=new Animal[N];


        st=new StringTokenizer(br.readLine());
        for(int i=0;i<M;i++){
            pos[i]=stoi(st.nextToken());
        }

        for(int i=0;i<N;i++){
            st=new StringTokenizer(br.readLine());
            animals[i]=new Animal(stoi(st.nextToken()),stoi(st.nextToken()));
        }

        Arrays.sort(pos);
        Arrays.sort(animals, (o1, o2) -> {
            if(o1.x<o2.x)
                return -1;
            else
                return 1;
        });

    }

    public static void calc(){
        for(int i=0;i<N;i++){
            Animal cur=animals[i];
            int left=findUpperBound(cur.x);

            if(left!=-1 && calcLength(pos[left],cur)<=L){
                answer++;
                cur.dead=true;
                continue;
            }

            if(left+1<M && calcLength(pos[left+1],cur)<=L){
                answer++;
                cur.dead=true;
            }
        }
    }


    public static int findUpperBound(int index){
        int left=0;
        int right=M-1;
        int value=-1;

        while(left<=right){
            int mid=(left+right)/2;

            if(index>=pos[mid]){
                value=mid;
                left=mid+1;
            }
            else{
                right=mid-1;
            }
        }

        return value;
    }

    public static int calcLength(int gun,Animal animal){
        return Math.abs(gun-animal.x)+animal.y;
    }

    public static int stoi(String input){
        return Integer.parseInt(input);
    }

}

class Animal{
    int x,y;
    boolean dead;
    Animal(int x,int y){
        this.x=x;
        this.y=y;
        dead=false;
    }
}


```
