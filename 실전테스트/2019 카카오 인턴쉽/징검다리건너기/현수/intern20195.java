
import java.util.Scanner;

public class intern20195 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int k = sc.nextInt(), stones[] = new int[10];
        for (int i = 0; i < 10; i++) {
            stones[i] = sc.nextInt();
        }
        Solution sol = new Solution();
        System.out.println(sol.solution(stones, k));
    }
}

class Solution {
    static int[] stones;
    static int k;
    public int solution(int[] mstones, int mk) {
        stones = mstones; k = mk;
        int left = 1, right = 200000000;
        while(left < right){
            int mid = (left + right) / 2;
            if(checkCondition(mid)) right = mid;
            else left = mid + 1;
        }
        return left;
    }

    private boolean checkCondition(int m) {
        for (int i = 0; i < stones.length; i++) {
            if(stones[i] - m <= 0){
                if(i + k - 1 >= stones.length) break;
                boolean flag = true;
                for (int j = i + 1; j < i + k; j++) {
                    if(stones[j] - m > 0) {
                        flag = false;
                        i = j;
                        break;
                    }
                }
                if(flag) return true;
            }
        }
        return false;
    }
}
/*
3
2 4 5 3 2 1 4 2 5 1
 */