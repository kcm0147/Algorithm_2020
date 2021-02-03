import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try(Scanner input = new Scanner(System.in)){
            int n = input.nextInt();
            int m = input.nextInt();
            Solution sol = new Solution(n, m);
            
            int skip = input.nextInt() - 1;
            if(sol.bino[n+m][n] <= skip) System.out.println("-1");
            else{
                StringBuilder resultBuilder = new StringBuilder();
                sol.kth(n, m, skip, resultBuilder);
                System.out.println(resultBuilder.toString());
            }
        }
    }
}

class Solution{
    private final long MAX_INT = 1_000_000_000;
    int[][] bino;
    int numOfA;
    int numOfZ;
    
    Solution(int numOfA, int numOfZ){
        this.numOfA = numOfA;
        this.numOfZ = numOfA;
        initBino();
    }
    
    void initBino(){
        bino = new int[201][201];
        for(int[] b : bino) Arrays.fill(b, 0);
        for(int i=0; i<=200; ++i){
            bino[i][0] = bino[i][i] = 1;
            for(int j=1; j < i; ++j){
                bino[i][j] = (int)Math.min(MAX_INT, bino[i-1][j-1] + bino[i-1][j]);
            }
        }
    }
    
    void kth(int n, int m, int skip, StringBuilder ret){    
        if(n == 0){
            char[] remainStirng = new char[m];
            Arrays.fill(remainStirng, 'z');
            ret.append(remainStirng);
            return;
        }
        
        if(skip < bino[n+m-1][m]){
            ret.append("a");
            kth(n-1, m, skip, ret);
        }else{
            ret.append("z");
            kth(n, m-1, skip - bino[n+m-1][m], ret);
        }
    }
}