처음 문제를 읽었을 때 이해하기가 어려웠습니다. 

문제에서 지름이라고 되어있는데 그림에서는 반지름이 표시되어있어서 헷갈렸는데, 그냥 *반지름*이라고 생각하고 문제를 해결하면 편합니다.

피자 반죽을 만드는 순서대로 위(1)에서 아래(D)로 피자를 떨어뜨린다고 생각하시면 됩니다. 

다만 중요한 것은 `피자 반죽의 반지름 크기보다 작은 구간을 만났을 때는 더 이상 이동 할 수 없습니다`

반죽의 크기가 작아서 통과를 못하기 때문입니다.

그러면 저희는 그냥 `오븐에 넣으려는 피자 반죽보다 작은 구간이 나오기 전`까지 탐색을 하면 됩니다.

다만 탐색의 범위는 이전에 넣은 피자의 위치보다 위에 둬야합니다.

<br/>

오븐에 넣을 수 있는 위치를 찾기 위해서는 효율성을 위해 `이분탐색`을 사용하였습니다.

하지만 이분탐색은 `정렬된 수열`에서만 사용이 가능합니다 !!!

그래서 저희는 피자오븐의 수열을 내림차순이든 오름차순이든 정렬을 해야하지만, 문제의 성격상 정렬을 하면 답이 달라질 수 있기에 정렬을 할 수 없습니다.

다만 수열의 값은 변경해도 상관이 없습니다. 

왜냐하면? 위에서 언급했듯이, `피자 반죽 반지름 크기보다 작은 구간을 만났을 때는 더 이상 이동 할 수 없기 떄문입니다`

<br/>

예를 들어 5 6 4 3 6 2 3 같은 경우는 

최대로 오븐에 들어갈 수 있는 반죽의 크기는 5 5 4 3 3 2 2 가 됩니다.

<br/>

이렇게 수열을 바꾼 후에 이분탐색을 이용해서 피자 반죽을 넣을 수 있는 오븐의 위치를 찾습니다.

만약 피자 반죽을 다 넣기 전에 아무곳에도 넣을 수가 없다면 => 이분탐색 진행을 할 수 없다면 `0`을 리턴합니다.



---

이분탐색을 하기 위해 수열을 바꿔야한다는 생각을 하게 해준 좋은 문제입니다.

이분탐색 이외에도 투포인트나 스택으로도 해결을 할 수 있는 것 같습니다.


<br/>




<br/>



```java

public class Main {

    static int D,N;
    static int[] depth;

    public static void main(String[] args) throws IOException {

        System.out.println(init());

    }


    public static int init() throws IOException{
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st =new StringTokenizer(br.readLine());
        D=stoi(st.nextToken());
        N=stoi(st.nextToken());

        depth=new int[D+1];
        st = new StringTokenizer(br.readLine());

        depth[0]=Integer.MAX_VALUE;

        for(int i=1;i<=D;i++){
            depth[i]=stoi(st.nextToken());
            depth[i]=Math.min(depth[i-1],depth[i]);
        }

        st=new StringTokenizer(br.readLine());
        int left=1;
        int right=D;
        int index=0;

        for(int i=1;i<=N;i++){
            int curDia=stoi(st.nextToken());
            index=calc(left,right,curDia);

            if(index==-1){
                return 0;
            }
            else
                right=index-1;
        }

        return index;
    }

    public static int calc(int left,int right,int value){
        int mid;
        int answer=-1;

        while(left<=right){
            mid=(left+right)/2;
            if(depth[mid]>=value) {
                left = mid + 1;
                answer=mid;
            }
            else
                right=mid-1;
        }

        return answer;
    }


    public static int stoi(String input){
        return Integer.parseInt(input);
    }


}

```
