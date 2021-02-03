import java.util.Scanner;

class Solution{
    private final int MAX_NUM_OF_CAHPTER = 500;
    private int[][] cache = new int[MAX_NUM_OF_CAHPTER+1][MAX_NUM_OF_CAHPTER+1];
    private int[] files = new int[MAX_NUM_OF_CAHPTER+1];
    private int[] subSum = new int[MAX_NUM_OF_CAHPTER+1];
    private int numOfFiles;

    Solution(Scanner scanner){
        numOfFiles = scanner.nextInt();
        subSum[0] = 0;
        for(int file=1; file<=numOfFiles; file++){
            files[file] = scanner.nextInt();
            subSum[file] = subSum[file-1] + files[file];
        }
        initCache();
    }

    private void initCache(){
        for(int i=0; i<=numOfFiles; i++){
            for(int j=0; j<=numOfFiles; j++){
                cache[i][j] = -1;
                if(i == j) cache[i][j] = files[i];
            }
        }
    }

    private int minCost(int left, int right){
        if(left == right) return 0;
        if(cache[left][right] != -1) return cache[left][right];

        int ret=Integer.MAX_VALUE;
        int sum = subSum[right] - subSum[left-1];
        for(int mid=left; mid<right; mid++){
            ret = Integer.min(ret, sum + minCost(left, mid) + minCost(mid+1, right));
        }
        cache[left][right] = ret;
        return ret;
    }

    int minCost(){
        return minCost(1, numOfFiles);
    }
}

public class Main11066{
    public static void main(String[] args){
        try(Scanner scanner = new Scanner(System.in)){
            int numOfTestCase = scanner.nextInt();
            for(int testcase=1; testcase <= numOfTestCase; testcase++){
                Solution solution = new Solution(scanner);
                System.out.println(solution.minCost());
            }
        }
    }
}