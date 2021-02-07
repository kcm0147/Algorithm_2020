# 크레인 인형 뽑기 게임

## 조건

* N * N 크기의 격자에 M개의 종류의 인형이 주어짐
    * n : 5 ~ 100
    * m : 1 ~ 100
    
* 1번의 move당 격자의 n라인에서 제일 위 인형을 뽑아 바구니에 담을 수 있음

    * moves 는 주어짐

* 만약 바구니에 똑같은 인형 위에 담는다면 그 2개의 인형은 사라짐

## 구해야 하는 것

* moves와 격자 상태가 주어질 때 사라진 인형의 갯수

## 로직

* 단순한 시뮬레이션으로 바구니는 스택으로 구현한다.

* 현재 집은 인형이 바구니의 제일 위와 똑같다면 pop한뒤 answer += 2를 해준다.

## 느낀 점

* 너무 쉬워서 깜짝 놀랬고 그래서 더 만만하게 생각했던 거 같다. 

* 뒤에 문제들을 위해 빠르게 푸는 연습을 해야겠다.

## 코드

```java
class Solution {
    public int solution(int[][] board, int[] moves) {
        int answer = 0;
        Stack<Integer> baguni = new Stack<>();

        for(int i=0; i<moves.length; i++){
            int col = moves[i] - 1;

            int doll = -1;
            // row는 0이 제일 높은 거임
            // 0부터 탐색해나가는 것 -> 격자 제일 위에서부터 확인하는 것!
            for(int row=0; row<board.length; row++){
                if(board[row][col] > 0){
                    doll = board[row][col];
                    board[row][col] = 0;
                    break;
                }
            }
            if(doll == -1) continue;

            if (!(baguni.isEmpty()) && baguni.peek().intValue() == doll){
                baguni.pop();
                answer += 2;
            }else{
                baguni.push(doll);
            }
        }

        return answer;
    }
}
```