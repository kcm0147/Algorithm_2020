[요구사항]

주어진 수열에서 구간을 M개 이하의 구간으로 나눈 후, 구간마다 최댓값-최소값의 결과값을 구한 후의 값들 중에서

가장 값이 작게 나올때의 값을 구해야합니다.

<br/>

문제를 보았을 때 가장 혼란스러운 점은 M개 이하의 구간으로 나누어야 한다는 것입니다.

즉 M개의 구간으로 나누는 것도 아니고, 각 구간마다 M개 숫자로 나누는 말이 아닙니다.

그래서 M개의 구간 중 몇개의 구간으로 나눠야할지 고민을 많이 했습니다.

하지만 구하려고 하는 것은 구간 중에 최솟값을 구하는 것이기에 최솟값에 focus를 두었습니다.

이분탐색으로 구현을 한다면, 구하려고하는 최솟값을 focus로 두고 mid로 잡았습니다.

맨 왼쪽에서 부터 탐색을 진행하면서 다음에 추가하려는 숫자로 인해서 구간내의 최대-최소값이 mid(설정한 구간 최솟값)보다 

값이 크게 된다면 구간을 새로 만들도록 하였습니다.(cnt를 +1하게 됩니다)


이렇게 수열의 끝까지 탐색을 진행 한 후에 cnt(구간 값)이 M개 이하로 만들어졌다면, 

좀 더 낮은 최솟값을 기대할 수 있다는 의미이기 떄문에 left=mid+1;로 설정합니다.

구간 M개 이상으로 만들어졌다면 구간의 최솟값을 좀 더 수를 크게해야하기 때문에 right=mid+1로 설정하고 재탐색을 합니다.

이렇게 탐색을 계속 진행하면서 answer의 값을 최소의 값으로 업데이트 해줍니다.

<br/>


```java

public class Main {

    static int[] numAry;
    static int N,M,ArymaxValue;


    public static void main(String[] args) throws IOException {
        init();
        System.out.println(calc());

    }

    public static int calc(){

        int left=0;
        int right=ArymaxValue;
        int answer= Integer.MAX_VALUE;

        while(left<=right){
            int mid=(left+right)/2;
            int cnt=1;
            int curMinValue=numAry[0];
            int curMaxValue=numAry[0];

            for(int i=1;i<N;i++){
                curMinValue=Math.min(curMinValue,numAry[i]);
                curMaxValue=Math.max(curMaxValue,numAry[i]);
                if(curMaxValue-curMinValue>mid){
                    cnt++;
                    curMinValue=numAry[i];
                    curMaxValue=numAry[i];
                }
            }

            if(cnt>M){
                left=mid+1;
            }
            else{
                right=mid-1;
                answer=Math.min(answer,mid);
            }
        }

        return answer;
    }

    public static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N=stoi(st.nextToken());
        M=stoi(st.nextToken());
        numAry=new int[N];

        ArymaxValue=Integer.MIN_VALUE;

        st=new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            numAry[i]=stoi(st.nextToken());
            ArymaxValue=Math.max(ArymaxValue,numAry[i]);
        }



    }



    public static int stoi(String input){
        return Integer.parseInt(input);
    }


```