알고리즘 문제에 자신감을 가지고 어느정도 자만을 가졌을 때 적잖은 충격을 먹은 문제입니다.

이 문제를 봤을 때, 완점탐색을 이용해서 문제를 해결하기에는 이동하기에 너무 많은 경우의 수가 존재하기 때문에 생각하기가 힘들었습니다.

DP 또한 마찬가지로 정말 많은 경우의 수가 존재해서 어떻게 풀어야할지 생각하기가 쉽지 않았습니다.

어쩔 수 없이 다른사람의 문제풀이를 보았는데, 제가 제일 처음 알고리즘을 시작할 때 공부한 LIS 문제였습니다...

왜 이 문제가 LIS 문제이냐면, 문제에서 요구하는 것은 **오름차순으로 정렬할 때 최소 이동횟수**입니다.

그렇기에 연속적 또는 부분적으로 증가하고 있는 번호를 가진 아이들을 기준으로 정한후에 나머지 아이들을 옮기면 해결되기 때문입니다.

`전체길이 - LIS의 길이`를 답으로 출력하면 문제는 해결됩니다.

---

<br/>

저는 LIS를 구할때 binary Search를 사용하여 문제를 해결하였습니다.

하지만 아래와 같이 DP를 사용하여 문제를 해결할 수 도 있습니다.

```java

for(int i=0;i<arr.length;i++){
    dp[i]=1;
    for(int j=0;j<i;j++){
        if(arr[i]>arr[j])
            dp[i]=Math.max(dp[i],dp[j]+1);
    }
}


```

<br/>



<br/> <br/>

```java


public class Main {


    static int N,end;
    static int[] LIS;
    static int[] input;


    public static void main(String[] args) throws IOException {

        init();
        calc();
        System.out.println(N-(end+1));
    }


    public static void init() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = stoi(br.readLine());

        input=new int[N];
        LIS=new int[N];

        for(int i=0;i<N;i++){
           input[i]=stoi(br.readLine());
        }


    }

    public static void calc(){
        LIS[0]=input[0];

        for(int i=1;i<N;i++){
            if(LIS[end]<input[i]){
                LIS[++end]=input[i];
            }
            else{
                int index=BinarySearch(input[i]);
                LIS[index]=input[i];
            }
        }

    }


    public static int BinarySearch(int input){
        int left=0;
        int answer=0;
        int right=end;

        while(left<=right){
            int mid=(left+right)/2;

            if(LIS[mid]>input){
                right=mid-1;
            }
            else{
                left=mid+1;
                answer=Math.max(answer,left);
            }
        }

        return answer;
    }
    public static int stoi(String input){
        return Integer.parseInt(input);
    }

}


```