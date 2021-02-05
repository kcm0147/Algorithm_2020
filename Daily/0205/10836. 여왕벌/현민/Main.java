package solve10836;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main10836(String[] args) {
        Solution solution = new Solution();
        solution.init();
        int[][] result = solution.solve();
        for(int[] r : result){
            for(int size : r) System.out.print(size + " ");
            System.out.println();
        }
    }
}

class Solution {
    private int lineSize;
    private int maxDays;
    private int[][] numOfGrowing; // numOfGrowing[day][size]; size : 0,1,2중 하나
    private int[] totalGrow; // start left bottom to left up to up right
    private int totalGrowSize;

    private void calcOneTick(int numOf_0grow, int numOf_1grow, int numOf_2grow) {
        for(int i=0; i<totalGrowSize; i++){
            if (numOf_0grow > 0){
                numOf_0grow--;
            }
            else if (numOf_1grow > 0){
                numOf_1grow--;
                totalGrow[i] += 1;
            }
            else if (numOf_2grow > 0){
                numOf_2grow--;
                totalGrow[i] += 2;
            }
        }
    }

    private void calcTotalGrow(){
        for(int day=0; day<maxDays; day++){
            calcOneTick(numOfGrowing[day][0], numOfGrowing[day][1], numOfGrowing[day][2]);
        }
    }

    int[][] makeBoard(){
        int dr = -1, dc = 0;
        int row = lineSize-1, col = 0;
        int[][] board = new int[lineSize][lineSize];
        for(int i=0; i<lineSize*2 -1; i++){
            board[row][col] = totalGrow[i] + 1;
            if(row == 0){
                dr = 0;
                dc = 1;
            }
            row += dr;
            col += dc;
        }

        for(row=1; row<lineSize; row++){
            for(col=1; col<lineSize; col++){
                board[row][col] = board[0][col];
            }
        }

        return board;
    }

    int[][] solve(){
        calcTotalGrow();
        return makeBoard();
    }

    void init() {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            lineSize = Integer.parseInt(stringTokenizer.nextToken());
            maxDays = Integer.parseInt(stringTokenizer.nextToken());

            numOfGrowing = new int[maxDays][3];
            for(int day=0; day<maxDays; day++){
                stringTokenizer = new StringTokenizer(bufferedReader.readLine());
                for(int grow=0; grow<3; grow++){
                    numOfGrowing[day][grow] = Integer.parseInt(stringTokenizer.nextToken());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        totalGrowSize = lineSize * 2 -1;
        totalGrow = new int[totalGrowSize];
        Arrays.fill(totalGrow, 0);
    }
}
