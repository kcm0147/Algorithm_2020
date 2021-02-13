* 투 포인터


<br/>

[요구사항]

N개의 수 중에서 다른 두개의 합으로 표현되는 수의 갯수를 출력해야합니다.


<br/>

일단 문제를 봤을 때, 수열의 위치는 sorting을 하여도 문제의 답에 영향을 끼치지 않아서 sorting을 진행하였습니다.

처음에는 이분탐색으로 접근을 하였으나, 이분탐색으로 접근을하면 모든 경우에 대해서 탐색이 되지 않는 다고 생각했기 때문에 '투포인터'로 접근을 하였습니다.

이 문제의 핵심은 두가지입니다.

1) 음수도 나올 수 있다

=> 아마 양수만 나온다면 투포인터의 탐색을 left를 0 right를 i-1로 잡고 탐색을 진행하면 됩니다.

왜냐하면 i이상인 수까지 탐색을 진행하다면, i번쨰 이상인 수는 그 자체만으로 i번째 수보다 크기 때문입니다.

```

ex) 1 7 9 10 12

10이라는 숫자가 어떠한 두수로 합이 정해지는지 판단을 하는건데 굳이 12를 탐색할 필요가 없습니다.

```

하지만 음수의 값도 포함되기 때문에 left를 0 right를 N-1로 잡고 전체 탐색을 진행해주어야합니다.

그리고 투 포인터 탐색을 진행할 때 i번째는 탐색을 하는 대상이기 때문에 

left나 right는 i번째를 가리키고 있어서는 안됩니다. 


```java

if(left!=i && right!=i){ 
    answer++;
}

```

i번째를 가리키고 있을 때 답이 count된다면 그 경우는 제외 시켜야 합니다.


2) 주어진 수의 위치가 다르면 다른 수로 판단한다

=> 즉 숫자의 값이 같은 수의 합으로 나온 숫자도 Count가 되어야 합니다.

가령 2 2개로 4를 만들 수 있는 것처럼 말이죠.


이 문제를 접근할 때는 DP-> 그리디 -> 이분탐색 -> 투포인터 로 접근을 하였습니다.

더 확실하게 접근하는 법을 연습해야겠습니다.


<br/>



```java

public class Main {

    static int N;
    static long[] numAry;

    public static void main(String[] args) throws IOException {
        init();
        System.out.println(calc());
    }

    public static void init() throws IOException{
            BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
            N=stoi(br.readLine());

            numAry=new long[N];

            StringTokenizer st =new StringTokenizer(br.readLine());
            for(int i=0;i<N;i++){
             numAry[i]=Long.parseLong(st.nextToken());
            }

            Arrays.sort(numAry);
    }


    public static int calc(){
        int answer=0;
        long previous=Long.MIN_VALUE;

        for(int i=0;i<N;i++){
            if(previous==numAry[i] || find(0,N-1,i)){
                answer++;
                previous=numAry[i];
            }
        }

        return answer;
    }


    public static boolean find(int left,int right,int targetIndex){


        long target = numAry[targetIndex];

        while(left<right){
            long curNum=numAry[left]+numAry[right];
            if(curNum>target){
                --right;
            }
            else if(curNum<target){
                ++left;
            }
            else if(curNum==target){
                if(left!=targetIndex && right!=targetIndex){
                    return true;
                }
                else if(left==targetIndex){
                    ++left;
                }
                else if(right==targetIndex){
                    --right;
                }
            }
        }

        return false;
    }

    public static int stoi(String input){
        return Integer.parseInt(input);
    }
}

```