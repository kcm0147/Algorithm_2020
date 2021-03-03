import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main{

    static int N;
    static int[] cost;

    public static void main(String[] args) throws IOException{

        init();
        System.out.println(divide(0,N-1));
    }

    public static void init() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N=Integer.parseInt(br.readLine());
        cost=new int[N];

        for(int i=0;i<N;i++){
            cost[i]=Integer.parseInt(br.readLine());
        }
    }

    public static int divide(int left,int right){

        if(left==right){
            return cost[left];
        }

        int mid=(left+right)/2;
        int edgeMax=Math.max(divide(left,mid),divide(mid+1,right));

        int low=mid;
        int high=mid+1;
        int width=0;
        int curHeight=-1;
        int returnSum=0;
        int curSum;

        while(left<=low || high<=right){ // 끝에 도달할 때 까지 계속 색
            if(left<=low &&(high>right || cost[low]>cost[high])){
                if(curHeight==-1 || curHeight>cost[low]) {
                    curHeight = cost[low];
                }
                low--;
            }
            else{
                if(curHeight==-1 || curHeight>cost[high]){
                    curHeight=cost[high];
                }
                high++;
            }
            width++;

            curSum=curHeight*width;
            returnSum=Math.max(returnSum,curSum);
        }

        return Math.max(edgeMax,returnSum);
    }
}