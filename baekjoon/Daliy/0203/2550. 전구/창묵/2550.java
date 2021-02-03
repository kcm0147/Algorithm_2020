public class Main {

    static int N,rear;
    static int[] lampPosAry;
    static int[] switchAry;
    static int[] indexAry;
    static int[] LIS;
    static int[] findIndex;

    static List<Integer> answer;

    public static void main(String[] argv) throws IOException {

        init();
        calc();
        insertAndsort();
        System.out.println(answer.size());

        for(int i=0;i<answer.size();i++){
            System.out.print(answer.get(i)+" ");
        }

    }

    public static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N=stoi(br.readLine());

        lampPosAry=new int[N+1];
        switchAry=new int[N+1];

        indexAry=new int[N+1];
        LIS=new int[N+1];
        findIndex=new int[N+1];

        answer=new ArrayList<>();

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=1;i<=N;i++){
            int switchNum=stoi(st.nextToken());
            switchAry[i]=switchNum;
        }

        st = new StringTokenizer(br.readLine());
        for(int i=1;i<=N;i++){
            int lampNum=stoi(st.nextToken());
            lampPosAry[lampNum]=i;
        }

        for(int i=1;i<=N;i++){
            indexAry[i]=lampPosAry[switchAry[i]];
        }

    }

    public static int calc(){
        rear=-1;

        for(int i=1;i<=N;i++){
            int curNum = indexAry[i];

            if(rear==-1){
                LIS[++rear]=curNum;
                findIndex[i]=rear;
            }
            else{
                if(LIS[rear]<curNum){
                    LIS[++rear]=curNum;
                    findIndex[i]=rear;
                }
                else{
                    int index=binary(0,rear,curNum);
                    LIS[index]=curNum;
                    findIndex[i]=index;
                }

            }
        }

        return rear;
    }


    public static int binary(int left,int right,int num){

        while(left<right){
            int mid=(left+right)/2;
            if(num>LIS[mid])
                left=mid+1;
            else
                right=mid;
        }
        return right;
    }

    public static void insertAndsort(){
        int targetIndex = rear;

        for(int i=N;i>=1;i--){
            if(findIndex[i]==targetIndex) {
                answer.add(switchAry[i]);
                targetIndex--;
            }
        }
        Collections.sort(answer);
    }



    public static int stoi(String input){
        return Integer.parseInt(input);
    }

}