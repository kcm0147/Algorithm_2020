import java.util.Scanner;

public class Main1725 {
    public static void main(String[] args){
        Solution solution = new Solution();
        solution.inputAndInit();
        System.out.println(solution.maxSqureArea());
    }
}

class Solution{
    private int[] sticks;
    private int numOfSticks;

    void inputAndInit(){
        try(Scanner input = new Scanner(System.in)){
            numOfSticks = input.nextInt();
            sticks = new int[numOfSticks];
            for(int i=0; i<numOfSticks; i++){
                sticks[i] = input.nextInt();
            }
        }
    }

    private int maxSqureArea(int left, int right){
        if(left == right) return sticks[right];

        int mid = (left + right) / 2;
        int ret = Integer.max(maxSqureArea(left, mid), maxSqureArea(mid+1, right));
        
        return Integer.max(ret, maxAreaIncludeMid2Sticks(left, right));
    }

    private int maxAreaIncludeMid2Sticks(int left, int right){
        int lo = (left + right) / 2;
        int hi = lo + 1;
        int height = Integer.min(sticks[lo], sticks[hi]);

        int ret = 2 * height;
        while(left < lo || hi < right){
            if(hi < right && (left == lo || sticks[lo-1] < sticks[hi+1])){
                hi++;
                height = Integer.min(height, sticks[hi]);
            }else{
                lo--;
                height = Integer.min(height, sticks[lo]);
            }

            ret = Integer.max(ret, (hi - lo + 1) * height);
        }
        return ret;
    }

    int maxSqureArea(){
        return maxSqureArea(0, numOfSticks-1);
    }
}