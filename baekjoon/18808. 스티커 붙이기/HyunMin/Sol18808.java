package project;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Solution sol;
        
        try(Scanner input = new Scanner(System.in)){
            sol = new Solution(input.nextInt(), input.nextInt());
            
            int numOfBlocks = input.nextInt();
            for(int i=0; i<numOfBlocks; i++){
                int rowSize = input.nextInt();
                int colSize = input.nextInt();
                
                Sticker type = new Sticker(rowSize, colSize);
                for(int r=0; r<rowSize; r++){
                    for(int c=0; c<colSize; c++){
                        type.add(r,c,input.nextInt());
                    }
                }
                sol.add(type);
            }
        }
        
        
        System.out.println(sol.cover(0));
    }
}

class Solution{
    private List<Sticker> types = new ArrayList<>();
    private int[][] board;
    private int rowSize;
    private int colSize;
    
    Solution(int rowSize, int colSize){
        this.rowSize = rowSize;
        this.colSize = colSize;
        board = new int[rowSize][colSize];
        for(int[] b: board) Arrays.fill(b, 0);
    }
    
    void add(Sticker type){
        types.add(type);
    }
    
    public int cover(int typeIdx){
        if(typeIdx >= types.size()) return numOfCovered();
        
        List<Sticker> typesRoatationed = types.get(typeIdx).generateRotations();
        for(Sticker type : typesRoatationed){
            for(int y=0; y<rowSize;y++){
                for(int x=0; x<colSize; x++){
                    if(set(type, y, x, 1)) return cover(typeIdx+1);
                    set(type, y, x, -1);
                }
            }
        }
        
        return cover(typeIdx+1);
    }
    
    private boolean set(Sticker type, int y, int x, int delta){
        boolean ok = true;
        
        for(int r=0; r<type.rowSize; r++){
            for(int c=0; c<type.colSize; c++){
                if(type.blocks[r][c] == 0) continue;
                
                int nr = y + r;
                int nc = x + c;
                if(nr <0 || nr >= rowSize || nc < 0 || nc >= colSize)
                    ok = false;
                else if((board[nr][nc] += delta) > 1)
                    ok = false;
            }
        }
        
        return ok;
    }
    
    private int numOfCovered(){
        return Arrays.stream(board).mapToInt(arr -> Arrays.stream(arr).sum()).sum();
    }
}
   
class Sticker{
    int[][] blocks;
    int rowSize;
    int colSize;
    
    Sticker(int rowSize, int colSize){
        this.rowSize = rowSize;
        this.colSize = colSize;
        this.blocks = new int[rowSize][colSize];
        for(int[] b : blocks) Arrays.fill(b, 0);
    }
    
    void add(int row, int col, int val){
        blocks[row][col] = val;
    }
    
    List<Sticker> generateRotations(){
        List<Sticker> ret = new ArrayList<>();
        ret.add(this);
        Sticker rotated = this.rotate();
        for(int i=0; i<3; i++){
            ret.add(rotated);
            rotated = rotated.rotate();
        }
        return ret;
    }
    
    private Sticker rotate(){
        Sticker ret = new Sticker(colSize, rowSize);
        for(int r=0; r<rowSize; r++){
            for(int c=0; c<colSize; c++){
                ret.add(c, rowSize - r -1, blocks[r][c]);
            }
        }
        return ret;
    }
}