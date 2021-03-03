import java.util.*;

public class Sol2339 {
    public static void main(String[] args){
        try(Scanner input = new Scanner(System.in)){
            int size = input.nextInt();
            Solution solution = new Solution(size);

            for(int y=0; y<size; y++){
                for(int x=0; x<size; x++){
                    solution.addState(x, y, input.nextInt());
                }
            }

            int result = solution.countHorizon(0, 0, size-1, size-1) 
                            + solution.countVerticle(0, 0, size-1, size-1);

            if(result <= 0) System.out.println(-1);
            else System.out.println(result);
        }
    }
}

class Solution{
    private State[][] board;

    private enum State{ JEWEL, IMPURITY, EMPTY }

    int countVerticle(int startX, int startY, int endX, int endY){
        if(numOf(startX, startY, endX, endY, State.JEWEL) <= 0) return -1;

        if(numOf(startX, startY, endX, endY, State.JEWEL) == 1 
            && numOf(startX, startY, endX, endY, State.IMPURITY) == 0) return 1;

        int ret = 0;
        for(int x = startX; x <= endX; x++){
            if(!canDivideVerticle(startY, endY, x)) continue;
            int div1 = countHorizon(startX, startY, x - 1, endY);
            int div2 = countHorizon(x + 1, startY, endX, endY);
            if(div1 > 0 && div2 > 0) ret += div1 * div2;
        }

        return ret;
    }

    int countHorizon(int startX, int startY, int endX, int endY){
        if(numOf(startX, startY, endX, endY, State.JEWEL) <= 0) return -1;

        if(numOf(startX, startY, endX, endY, State.JEWEL) == 1 
            && numOf(startX, startY, endX, endY, State.IMPURITY) == 0) return 1;

        int ret=0;
        for(int y = startY; y <= endY; y++){
            if(!canDivideHorizontal(startX, endX, y)) continue;
            int div1 = countVerticle(startX, startY, endX, y - 1);
            int div2 = countVerticle(startX, y + 1, endX, endY);
            if(div1 > 0 && div2 > 0) ret += div1 * div2;
        }
        
        return ret;
    }


    private boolean canDivideHorizontal(int startX, int endX, int y){
        if(numOf(startX, y, endX, y, State.JEWEL) > 0) return false;
        if(numOf(startX, y, endX, y, State.IMPURITY) <= 0) return false;
        return true;
    }

    private boolean canDivideVerticle(int startY, int endY, int x){
        if(numOf(x, startY, x, endY, State.JEWEL) > 0) return false;
        if(numOf(x, startY, x, endY, State.IMPURITY) <= 0) return false;
        return true;
    }

    private int numOf(int startX, int startY, int endX, int endY, State state){
        int ret = 0;
        for(int x = startX; x <= endX; x++)
            for(int y = startY; y <= endY ; y++)
                if(board[x][y] == state) ret++;
        return ret;
    }

    Solution(int size){
        this.board = new State[size][size];
    }

    void addState(int x, int y, int value) { 
        switch(value){
        case 0: board[x][y] = State.EMPTY; break;
        case 1: board[x][y] = State.IMPURITY; break;
        case 2: board[x][y] = State.JEWEL; break;
        }
    }
}