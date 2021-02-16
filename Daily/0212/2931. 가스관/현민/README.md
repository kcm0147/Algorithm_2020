# 2931 가스관

## 조건

* M에서 Z까지 7가지의 블록을 통해 길이 생성되어 있음.

* 여기서 반드시 블록을 통하여 목적지에 도달해야 함.

* 하지만 이 파이프들 중 하나가 지워져 있음.

## 아이디어

* 이 문제는 M에서 Z로 가는 문제로 각 파이프의 성향에 따라 가는 방향이 정해진다.

* dfs로 탐색할 수 있으나, 파이프마다 제한되어있는 다음 방향이 있으므로 실제로는
 더욱 빠르게 탐색이 가능하다.
  
* 다음과 같이 방향을 설정할 수 있다.

```
Block | : 위, 아래 중 방문하지 않은 곳

Block - : 왼, 오른쪽 중 방문하지 않은 곳

Block + : 이전 블록의 위치에 따라 달라짐, 이전 블록의 방향을 그대로 가짐

Block 1 : 아래쪽, 오른쪽 중 방문하지 않은 곳

Block 2 : 위쪽, 오른쪽 중 방문하지 않은 곳

Block 3 : 왼쪽, 위쪽 중 방문하지 않은 곳

Block 4 : 왼쪽, 아래쪽 중 방문하지 않은 곳
```

* 또한 시작지점 주변에 여러 파이프들이 있다면 어떤 파이프로 시작해야 할 지 정해야 한다.

* 여기 문제에서 M에서 갈 수 있는 파이프도 파이프의 종류에 따라 다르다.

```
Block | : 위쪽, 아래쪽에서 오는 것을 허용

Block - : 왼쪽, 오른쪽에서 오는 것을 허용

Block + : 모든 곳을 허용

Block 1 : 오른쪽, 아래쪽에서 오는 것을 허용

Block 2 : 위쪽, 오른쪽 오는 것을 허용

Block 3 : 위쪽, 왼쪽에서 오는 것을 허용 

Block 4 : 아래쪽, 왼쪽에서 오는 것을 허용
```

* 중간에서 파이프가 하나가 사라졌으므로, 파이프를 따라가다보면 파이프도 없는 빈 곳에 도달할 것이다.

* 여기서 7개의 파이프 중 하나를 선택하는데, 이는 자신이 이전에 있었던 위치(즉, 어떤 방향에서 오는지)
와 자기 주변의 나머지 파이프들의 위치에 따라 달라진다.
  
```
만약 왼쪽에서 왔고 자기 아래쪽에만 파이프 하나가 있을 때

왼쪽에서 오는 것을 허용하고 아래쪽으로 가는 Block 4를 선택해야 할 것이다.
```

* 단, 자신의 주변에 파이프가 3개가(이전에 온 것 제외) 있다면 +로 해결해야 할 것이다.

## 느낀 점

1. 상당히 쉽지 않았다. 최대한 중복되는 부분을 최소화하려 했으나 일관성 또한 생각하느라 쉽지 않았다.

2. 이런 구현 문제 많이 접해봐야할 거 같다.

## 코드

```java
package daily.day0212.boj2931;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] s = reader.readLine().split(" ");
        int rowSize = Integer.parseInt(s[0]);
        int colSize = Integer.parseInt(s[1]);

        char[][] board = new char[rowSize][colSize];
        for (int r = 0; r < rowSize; r++) {
            board[r] = reader.readLine().toCharArray();
        }
        new Solution().solution(board);
    }
}

class Solution {
    private final Map<Character, Set<Integer>> pipes = new HashMap<>() {{
        put('|', new HashSet<>(Arrays.asList(0, 2)));
        put('-', new HashSet<>(Arrays.asList(1, 3)));
        put('+', new HashSet<>(Arrays.asList(0, 1, 2, 3)));
        put('1', new HashSet<>(Arrays.asList(2, 3)));
        put('2', new HashSet<>(Arrays.asList(0, 3)));
        put('3', new HashSet<>(Arrays.asList(0, 1)));
        put('4', new HashSet<>(Arrays.asList(1, 2)));
    }};

    private char[][] board;

    private int nextRow(int row, int dir) {
        int[] dr = {-1, 0, 1, 0};
        return row + dr[dir] ;
    }

    private int nextCol(int col, int dir) {
        int[] dc = {0, -1, 0, 1}; // up, left, down, right
        return col + dc[dir];
    }

    private int backward(int dir){ return (dir+2) % 4; }

    private boolean isOutBoundary(int row, int col) {
        return row < 0 || row >= board.length || col < 0 || col >= board[0].length;
    }

    private boolean canMove(int row, int col, int dir, int beforeDir, boolean alreadyFindHole){
        int nr = nextRow(row, dir), nc = nextCol(col, dir);
        if(isOutBoundary(nr, nc) || backward(dir) == beforeDir) return false; // 바깥으로 가면 안됨! 원래 자리로 오는 것도 안됨.

        if(board[row][col] == '+' ){
            int empty=0;
            for(int d=0; d<4; d++)
                if(isOutBoundary(nextRow(row,d), nextCol(col,d)) || board[nextRow(row, d)][nextCol(col,d)] == '.') empty++;
            if((alreadyFindHole == false && empty > 1) || (alreadyFindHole && empty > 0)) return false;
        }

        if(board[nr][nc] == '.') return alreadyFindHole == false; // .은 한번만 통과 가능
        if(board[nr][nc] == 'Z') return true; // 도착지점은 당연 갈 수 있지
        if(board[nr][nc] == 'M') return false;
        return pipes.get(board[nr][nc]).contains(backward(dir)); // 진입을 허용하는
    }

    private int nextDir(int row, int col, int beforeDir, boolean alreadyFindHole){
        int dir = board[row][col] == '+' ?
                beforeDir : pipes.get(board[row][col]).stream().filter(d->d != backward(beforeDir)).mapToInt(i->i).toArray()[0];
        return canMove(row, col, dir, beforeDir, alreadyFindHole) ? dir : -1;
    }

    int holeRow = -1, holeCol = -1;
    int[][] visitFreq;

    private boolean isSuccess(){
        for(int row=0; row<board.length; row++){
            for(int col=0; col<board[0].length; col++){
                if(board[row][col] == '+') {
                    if (visitFreq[row][col] != 2) return false;
                }else if(board[row][col] != '.' && board[row][col] != 'M' && board[row][col] != 'Z' && visitFreq[row][col] != 1) return false;
            }
        }
        return true;
    }

    private void dfs(final int row, final int col, final int beforeDir, boolean alreadyFindHole) {
        visitFreq[row][col]++;

        if(board[row][col] == 'Z'){
            if(isSuccess())
                System.out.println((holeRow+1) + " " + (holeCol+1) + " " + board[holeRow][holeCol]);
        }else if(board[row][col] == '.'){
            if(!alreadyFindHole) {
                for (Map.Entry<Character, Set<Integer>> pipe : pipes.entrySet()) {
                    if (!pipe.getValue().contains(backward(beforeDir))) continue;

                    board[row][col] = pipe.getKey();
                    holeRow = row;
                    holeCol = col;
                    move(row, col, beforeDir, true);
                    board[row][col] = '.';
                }
            }
        }else move(row, col, beforeDir, alreadyFindHole);

        visitFreq[row][col]--;
    }

    private void move(int row, int col, int beforeDir, boolean alreadyFindHole){
        int ndir = nextDir(row, col, beforeDir, alreadyFindHole);
        if(ndir != -1) dfs(nextRow(row,ndir), nextCol(col, ndir), ndir, alreadyFindHole);
    }

    void solution(char[][] board){
        this.board = board;

        int startRow = -1, startCol = -1;
        for(int row=0; row<board.length && startRow == -1 ;row++){
            for(int col=0; col<board[0].length && startCol == -1; col++){
                if(board[row][col] == 'M'){
                    startRow = row;
                    startCol = col;
                }
            }
        }

        visitFreq = new int[board.length][board[0].length];
        for(int d=0; d<4; d++){
            if(!canMove(startRow, startCol, d, -1, false)) continue;
            for(int[] arr : visitFreq) Arrays.fill(arr, 0);
            dfs(nextRow(startRow, d), nextCol(startCol, d), d, false);
        }
    }
}
```