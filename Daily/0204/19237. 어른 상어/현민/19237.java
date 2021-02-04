import java.util.Arrays;
import java.util.Scanner;

public class Main19237 {
    public static void main19237(String[] args) {
        Solution19237 solution = new Solution19237();
        solution.input();
        System.out.println(solution.solve());
    }
}

class Solution19237 {
    BoardItem[][] board;
    Shark[] sharks;

    private int widthAndHeight;
    private int numOfShark;
    private int remainingTimeK;

    void input() {
        try (Scanner input = new Scanner(System.in)) {
            widthAndHeight = input.nextInt();
            numOfShark = input.nextInt();
            remainingTimeK = input.nextInt();

            board = new BoardItem[widthAndHeight][widthAndHeight];
            for (int row = 0; row < widthAndHeight; row++)
                for (int col = 0; col < widthAndHeight; col++)
                    board[row][col] = new BoardItem();

            sharks = new Shark[numOfShark];

            // input board
            for (int row = 0; row < widthAndHeight; row++) {
                for (int col = 0; col < widthAndHeight; col++) {
                    int num = input.nextInt();
                    if (num <= 0) continue;
                    sharks[num - 1] = new Shark(num - 1, row, col);
                }
            }

            // input dir of sharks
            for (Shark shark : sharks) shark.dir = input.nextInt() - 1;

            for (Shark shark : sharks) {
                for (int dir = 0; dir < 4; dir++) {
                    for (int priority = 0; priority < 4; priority++) {
                        shark.dirByDirAndPriority[dir][priority] = input.nextInt() - 1;
                    }
                }
            }
        }
        for (Shark shark : sharks) shark.makeSmell(board, remainingTimeK);
    }

    int solve() {
        int time;
        for (time = 1; time <= 1000; time++) {
            oneTick();
            int numOfAlive = (int) Arrays.stream(sharks).filter(shark -> shark.alive).count();
            if (numOfAlive <= 1) break;
        }
        return time <= 1000 ? time : -1;
    }

    private void oneTick() {
        for (Shark shark : sharks) shark.move(board);

        for (int i = 0; i < sharks.length; i++)
            for (int j = i + 1; j < sharks.length; j++)
                sharks[i].fight(sharks[j]);

        for (BoardItem[] line : board)
            for (BoardItem boardItem : line)
                boardItem.smellDown();

        for (Shark shark : sharks) shark.makeSmell(board, remainingTimeK);
    }
}

class BoardItem {
    int whoseSmell;
    int remainingTime;

    void init() {
        whoseSmell = -1;
        remainingTime = 0;
    }

    BoardItem() {
        init();
    }

    void smellDown() {
        if (remainingTime > 0) remainingTime--;
        if (remainingTime <= 0) whoseSmell = -1;
    }
}

class Shark {
    private static int[] dr = {-1, 1, 0, 0}; // none, up down, left, right
    private static int[] dc = {0, 0, -1, 1}; // none, up, down, left, right

    boolean alive;
    int number;
    int row;
    int col;
    int dir; // 0:up, 1:down, 2:left, 3:right

    int[][] dirByDirAndPriority = new int[4][4]; // nextDir[currentDir][priority]

    Shark(int number, int row, int col) {
        this.alive = true;
        this.number = number;
        this.row = row;
        this.col = col;
    }

    void move(BoardItem[][] board) {
        if (alive == false) return;

        int wantSmell = -1; // -1 is no smell
        for (int i = 0; i < 2; i++) {
            // move if there exist want smell area
            for (int priority = 0; priority < 4; priority++) {
                int nextDir = dirByDirAndPriority[dir][priority];
                int nextRow = row + dr[nextDir];
                int nextCol = col + dc[nextDir];
                if (!isInBoundary(nextRow, nextCol, board.length, board[0].length)) continue;
                if (board[nextRow][nextCol].whoseSmell == wantSmell) {
                    row = nextRow;
                    col = nextCol;
                    dir = nextDir;
                    return;
                }
            }

            wantSmell = this.number;
        }
    }

    private boolean isInBoundary(int row, int col, int height, int width) {
        return (row >= 0 && row < height) && (col >= 0 && col < width);
    }

    void makeSmell(BoardItem[][] board, int remainingTimeK) {
        if (alive == false) return;
        board[row][col].whoseSmell = number;
        board[row][col].remainingTime = remainingTimeK;
    }

    void fight(Shark shark) {
        if (this.alive == false || shark.alive == false) return;
        if (!(this.col == shark.col && this.row == shark.row)) return;

        if (this.number < shark.number) shark.alive = false;
        else this.alive = false;
    }
}