# 14600 샤워실 바닥 깔기

## 조건

* 타일은 ㄱ 자 모양이다.

* 바닥은 2 * 2 또는 4 * 4 형태로 이루어져있다.

* 하수구가 하나 있다.

## 구해야하는 것

* 하수구를 제외한 나머지부분을 타일로 덮는 경우를 출력

## 아이디어

* 여기서 하수구 위치가 제일 중요하다.

* 하수구의 위치만 알면 타일을 까는 방법은 정해져있기 때문이다.

* 2 * 2는 그냥 깔지만 4 * 4는 네 부분으로 나눠 분할정복을 한다.

* 2 * 2 부분을 깔 때, 하수구가 있다면 나머지를 채운다.

* 하수구가 없다면 중앙부분을 제외하고 나머지를 채운다.

    - 이는 미리 중앙부분을 채워나서 조건을 확인하게 한다.
    
## 느낀점

1. 흠 어렵지 않은 문제였으나, 뭔가 구현이 더 깔끔한 알고리즘이 있으면 좋겠다.

## 코드

```java
package daily.day0218.boj14600;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.function.BiPredicate;
import java.util.function.ToIntFunction;

public class Main {
    private static int[][] board;
    private static int color = 1;

    private static ToIntFunction<String> stoi = s -> Integer.parseInt(s);

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int lineSize = (int) Math.pow(2,stoi.applyAsInt(reader.readLine()));
        board = new int[lineSize][lineSize];

        String[] number = reader.readLine().split(" ");
        int blackCol = stoi.applyAsInt(number[0]) - 1;
        int blackRow = lineSize - stoi.applyAsInt(number[1]);

        if(lineSize > 2){
            int mid = lineSize/2;
            board[mid-1][mid-1] = 1;
            board[mid][mid-1] = 1;
            board[mid-1][mid] = 1;
            board[mid][mid] = 1;
            color++;
        }
        board[blackRow][blackCol] = -1;

        fillBoard(0,0,lineSize);
        for(int row=0; row<lineSize;row++){
            for(int col=0; col<lineSize;col++){
                System.out.print(board[row][col] + " ");
            }
            System.out.println("");
        }
    }

    private static BiPredicate<Integer,Integer> canFill = (r,c) -> board[r][c] == 0;
    private static BiPredicate<Integer,Integer> canFillHasBlack = (r,c) -> board[r][c] != -1;

    private static boolean isThereBlack(int row, int col){
        for(int r=row; r<row+2; r++){
            for(int c=col; c<col+2; c++){
                if(board[r][c] == -1) return true;
            }
        }
        return false;
    }


    private static void fillBoard(int row, int col, int lineSize){
        if(lineSize == 2){
            BiPredicate<Integer,Integer> canFillFunction = canFill;
            if(isThereBlack(row, col)) canFillFunction = canFillHasBlack;

            for(int r=row; r<row+2; r++){
                for(int c=col; c<col+2; c++){
                    if(canFillFunction.test(r,c))
                        board[r][c] = color;
                }
            }
            color++;
            return;
        }

        fillBoard(row, col, lineSize/2);
        fillBoard(row+lineSize/2, col, lineSize/2);
        fillBoard(row, col+lineSize/2, lineSize/2);
        fillBoard(row+lineSize/2, col+lineSize/2, lineSize/2);
    }
}
```