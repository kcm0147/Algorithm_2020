처음에 문제를 읽었을 때, 문제의 해결방법을 생각하기가 쉽지 않았습니다.

당연히 완전탐색으로 문제를 해결했다가 시간초과가 뜰 것 같아서, 탐색을 최대한 줄이는 방법을 찾아야만 했습니다.

그리고 문제의 해결방법을 생각해냈는데 일단 이 문제는 중복적으로 숫자를 이용할 수 있으며, 숫자의 자릿수가 클 수록 큰 수라는 것을 우선적으로 파악하고 문제를 접근하였습니다.

문제의 해결방법은 다음과 같이 하였습니다.

**1. 가지고 있는 돈으로 최대한 많은 자릿수를 이용하여 숫자를 만든다.**

저는 tempAry와 numAry두었는데 tempAry는 입력그대로 배열을 유지하였고, numAry의 같은 경우 cost를 기준으로 오름차순으로 정렬하였습니다.

이때 cost가 제일 낮은 것을 확인하는데 **이 숫자가 0이면 안되기 때문에** 예외처리를 해주어야 합니다.

<br/>

**2. 이후에 남아있는 돈으로 맨 왼쪽자리 숫자부터 숫자를 큰 수로 바꿀 수 있는지 판단을 합니다.**

최대한 숫자가 큰 수로 바꾸는 것이 큰 수를 만드는 조건이기 때문에, 가장 큰 수부터 바꿀 수 있는지 판단을 해주어야합니다.


**3. 답을 출력합니다.**


<br/>



```java

public class Main {

    static int n,money;
    static Node[] numAry;
    static Node[] tempAry;
    public static void main(String[] args) throws IOException
    {
        init();
        System.out.println(calc());
    }

    public static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n=stoi(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        numAry=new Node[n];
        tempAry=new Node[n];
        for(int i=0;i<n;i++){
            int cost=stoi(st.nextToken());
            numAry[i]=new Node(i,cost);
            tempAry[i]=new Node(i,cost);
        }

        Arrays.sort(numAry);

        money=stoi(br.readLine());

    }

    public static int stoi(String input){
        return Integer.parseInt(input);
    }

    public static String calc(){
        StringBuilder sb = new StringBuilder();

        if(numAry[0].num==0) {
            if(n==1 || numAry[1].cost>money) return "0";
            sb.append(numAry[1].num);
            money-=numAry[1].cost;
        }

        while(money>=numAry[0].cost){
            sb.append(numAry[0].num);
            money-=numAry[0].cost;
        }


        for(int i=0;i<sb.length();i++){
            for(int j=tempAry.length-1;j>=0;j--){
                if(i==0 && j==0) continue;

                if(money+tempAry[sb.charAt(i)-'0'].cost>=tempAry[j].cost){
                    money+=(tempAry[sb.charAt(i)-'0'].cost-tempAry[j].cost);
                    sb.setCharAt(i,(char)(tempAry[j].num+'0'));
                    break;
                }
            }
        }

        return sb.toString();
    }
}

class Node implements Comparable<Node>{
    int num,cost;

    Node(int num,int cost){
        this.num=num;
        this.cost=cost;
    }

    @Override
    public int compareTo(Node o){
        if(this.cost<o.cost)
            return -1;
        else if(this.cost==o.cost){
            if(this.num>=o.num)
                return -1;
            else
                return 1;
        }
        else
            return 1;
    }
}


```
