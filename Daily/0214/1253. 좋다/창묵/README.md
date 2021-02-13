

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