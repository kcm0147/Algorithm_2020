처음에 문제를 해결하는 방법을 쉽게 생각하지 못한 문제입니다.

DP로 접근을 하여 문제를 해결하려고 했으나 `투포인터`를 사용하여 풀어야하는 문제였습니다.

<br/>

투포인터 문제의 대표적인 백준 문제로는 **용액**, **세 용액** 문제가 존재합니다.

**용액**같은 경우는 두 개의 포인터를 추후에 구해야하는 기댓값에 따라 포인터를 움직이는 문제였습니다.

**세 용액**같은 경우는 Sliding window처럼 한개를 지정해놓고 나머지 두 포인터를 기댓값에 따라 움직여야하는 문제였습니다.

<br/>

이 문제는 마치 **네 용액**과 같이 문제를 해결해야만 했습니다.

그래서 두개의 용액을 for문을 이용하여 탐색을 하여야 하는데, for문으로 두 용액을 정해놓고 나머지 두개의 포인터를 기댓값에 따라 움직이게 해야합니다.


<br/>


일단 투 포인터를 사용하기 위해서 오름차순으로 Sorting을 진행해주었습니다.

for문을 이용해서 하나의 눈사람을 만들기 위한 재료를 선정합니다. 

<br/>

다른 눈사람은 만든 눈사람의 left+1, right-1로 설정하여 연산을 진행합니다.

여기서 `sum=ary[i]+ary[j]-(ary[left]+ary[right])`으로 설정하였는데

**sum>0 이라면 ary[i],ary[j]가 눈사람의 크기가 더 크다는 의미가 됩니다.**

**우리가 구해야할 것은 두 눈사람의 차가 최대한 적게 되어야합니다**

그러면 sum을 최대한 0에 수렴하기 위해서는 안쪽 눈사람의 left를 한칸 오른쪽으로 당겨줍니다.

<br/>

만약 **sum<0이라면 ary[left], ary[right]의 눈사람이 더 크다는 의미가 됩니다**

그러면 sum을 최대한 0에 수렴시키기 위해서는 안쪽 눈사람의 right를 한칸 왼쪽으로 당겨주면 됩니다.

이러한 과정을 이중 for문을 이용하여 탐색을 끝낼때까지 진행해줍니다.



---

```java

public class Main {


    static long[] ary;
    static long answer;


    public static void main(String[] args) throws IOException {
        init();
        System.out.println(calc());
    }

    public static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N=Integer.parseInt(st.nextToken());

        ary=new long[N];

        st=new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            ary[i]=stol(st.nextToken());
        }

        Arrays.sort(ary);

    }

    public static long calc(){

        answer=Long.MAX_VALUE;
        for(int i=0;i<ary.length;i++){

            for(int j=i+3;j<ary.length;j++){
                int left=i+1;
                int right=j-1;

                while(left<right){
                    long temp = ary[i]+ary[j]-(ary[left]+ary[right]);

                    answer=Math.min(Math.abs(temp),answer);

                    if(temp<0) right--;
                    else left++;
                }
            }
        }



        return answer;
    }



    public static long stol(String input){
        return Long.parseLong(input);
    }

}


```