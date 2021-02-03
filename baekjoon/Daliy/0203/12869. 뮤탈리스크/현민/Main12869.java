import java.util.Arrays;
import java.util.Scanner;

public class Main12869 {
    public static void main12869(String[] args){
        Solution12869 solution = new Solution12869();
        System.out.println(solution.solve());
    }
}

class Solution12869{
    int[][][] cache = new int[MAX_HP+1][MAX_HP+1][MAX_HP+1];

    private static final int MAX_HP = 60;

    int solve(){
        try(Scanner input = new Scanner(System.in)){
            int numOfScv = input.nextInt();
            int[] scv = new int[3];
            Arrays.fill(scv, 0);
            for(int i=0; i<numOfScv; i++){
                scv[i] = input.nextInt();
            }
            for(int[][] arr : cache)
                for(int[] a : arr)
                    Arrays.fill(a, -1);

            return solve(scv[0], scv[1], scv[2]);
        }
    }

    private int solve(int scv1, int scv2, int scv3){
        if(scv1<=0 && scv2<=0 && scv3<=0) return 0;

        scv1 = scv1<=0 ? 0 : scv1;
        scv2 = scv2<=0 ? 0 : scv2;
        scv3 = scv3<=0 ? 0 : scv3;

        if(cache[scv1][scv2][scv3] != -1) return cache[scv1][scv2][scv3];

        int ret = 1 + solve(scv1-9, scv2-3, scv3-1);
        ret = Integer.min(ret, 1 + solve(scv1-9, scv2-1, scv3-3));

        ret = Integer.min(ret, 1 + solve(scv1-3, scv2-9, scv3-1));
        ret = Integer.min(ret, 1 + solve(scv1-1, scv2-9, scv3-3));

        ret = Integer.min(ret, 1 + solve(scv1-3, scv2-1, scv3-9));
        ret = Integer.min(ret, 1 + solve(scv1-1, scv2-3, scv3-9));

        cache[scv1][scv2][scv3] = ret;
        return ret;
    }
}
