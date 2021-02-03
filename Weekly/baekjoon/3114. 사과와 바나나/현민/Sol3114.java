import java.util.*;

public class Sol3114 {
    public static void main(String[] args){
        try(Scanner input = new Scanner(System.in)){
            int rowSize = input.nextInt(); 
            int colSize = input.nextInt();

            Solution solution = new Solution(colSize, rowSize);
            for(int y=0; y<rowSize; y++){
                for(int x=0; x<colSize; x++){
                    String tree = input.next();

                    int apple = 0;
                    int banana = 0;

                    if(tree.charAt(0) == 'A'){
                        apple = Integer.parseInt(tree.substring(1));
                    }
                    else if(tree.charAt(0) == 'B'){
                        banana = Integer.parseInt(tree.substring(1));
                    }
                    
                    solution.addApple(x, y, apple);
                    solution.addBanana(x, y, banana);
                }
            }

            System.out.println(solution.getMax(0, 0));
        }
    }
}

class Solution{
    private int[][] cache;
    private int[][] sumOfBanana; // Sub sum 가로
    private int[][] sumOfApple; // Sub sum 세로
    private int rowSize;
    private int colSize;

    Solution(int colSize, int rowSize){
        this.colSize = colSize; 
        this.rowSize = rowSize;

        cache = new int[colSize][rowSize];
        for(int[] a : cache) Arrays.fill(a, -1);

        sumOfApple = new int[colSize][rowSize];
        for(int[] a : sumOfApple) Arrays.fill(a, 0);
        
        sumOfBanana = new int[colSize][rowSize];
        for(int[] a : sumOfBanana) Arrays.fill(a, 0);
    }

    void addBanana(int x, int y, int num) { 
        if(x == 0) sumOfBanana[x][y] = num;
        else sumOfBanana[x][y] = num + sumOfBanana[x-1][y];  
    }
    void addApple(int x, int y, int num) { 
        if(y == 0) sumOfApple[x][y] = num;
        else sumOfApple[x][y] = num + sumOfApple[x][y-1]; 
    }

    int getMax(int x, int y){
        if(x >= colSize && y >= rowSize) return 0;
        else if(x >= colSize || y >= rowSize) return Integer.MIN_VALUE;

        if(cache[x][y] != -1) return cache[x][y];

        int apple = sumOfApple[x][rowSize-1] - sumOfApple[x][y]; // 세로에 있는 애뽈 합계 구하기
        int banana = sumOfBanana[colSize-1][y] - sumOfBanana[x][y]; // 가로에 있는 봐나나 합계 구하기

        int ret = getMax(x+1, y) + apple;
        ret = Integer.max(ret, getMax(x, y+1) + banana);
        ret = Integer.max(ret, getMax(x+1, y+1) + apple + banana);

        cache[x][y] = ret;
        return ret;
    }
}