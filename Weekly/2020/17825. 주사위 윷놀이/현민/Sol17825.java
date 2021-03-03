import java.util.*;

public class Sol17825{
    static final Board board = new Board();
    static final List<Integer> dices = new ArrayList<>();
    static int maxNum = 0;
    public static void main(String[] args){
        try(Scanner input = new Scanner(System.in)){
            for(int i=0; i<10; i++) dices.add(input.nextInt());
            calculateMaxScore(0);
            System.out.println(maxNum);
        }
    }

    private static void calculateMaxScore(int diceIndex){
        if(diceIndex >= dices.size()){
            maxNum = Integer.max(maxNum, board.getSumOfScore());
            return;
        }

        for(int hourse=0; hourse<board.getNumOfHourses(); hourse++){
            if(board.moveHourse(hourse, dices.get(diceIndex))){
                calculateMaxScore(diceIndex + 1);
                board.moveHoursePrev(hourse);
            }
        }
    }
}

class Board{
    private List<Hourse> hourses = new ArrayList<>();
    private Pos start = null;
    private Pos end = null;

    int getNumOfHourses() { return hourses.size(); }

    int getSumOfScore() {
        return hourses.stream()
                .map(Hourse::getScore)
                .reduce(0, Integer::sum);
    }

    boolean moveHoursePrev(int i){
        assert 0 <= i && i < hourses.size();
        hourses.get(i).movePrev();
        return true;
    }

    boolean moveHourse(int i, int distance){
        assert 0 <= i && i < hourses.size();

        Hourse hourse = hourses.get(i);
        Pos next = hourse.getPos().move(distance);

        if(!canMove(hourse, next)) return false;
       
        hourse.move(next);
        return true;
    }

    private boolean canMove(Hourse thisHourse, Pos next){
        if(thisHourse.getPos().equals(end)) return false;

        for(Hourse hourse : hourses){
            if(hourse.equals(thisHourse)) continue;
            if(end.equals(next) || start.equals(next)) continue;
            if(hourse.getPos().equals(next)) return false;
        }
        return true;
    }

    Board(){
        // board positions 만들기
        Pos pos = end = new Pos(0, null);
        Pos middlePos = null;
        for(int score = 40; score >= 2; score -= 2){
            pos = new Pos(score, pos);

            if(score == 40){
                Pos crossPos = new Pos(35, pos);
                crossPos = new Pos(30, crossPos);
                middlePos = new Pos(25, crossPos);
            }

            if(score == 30){
                Pos crossPos = new Pos(26, middlePos);
                crossPos = new Pos(27, crossPos);
                crossPos = new Pos(28, crossPos);
                pos.addBlueNext(crossPos);
            }

            if(score == 20){
                Pos crossPos = new Pos(24, middlePos);
                crossPos = new Pos(22, crossPos);
                pos.addBlueNext(crossPos);
            }

            if(score == 10){
                Pos crossPos = new Pos(19, middlePos);
                crossPos = new Pos(16, crossPos);
                crossPos = new Pos(13, crossPos);
                pos.addBlueNext(crossPos);
            }
        }
        start = new Pos(0, pos);
        
        // hourse들 만들기
        for(int i=0; i<4; i++) hourses.add(new Hourse(start));
    }

    // 히히힝~
    private class Hourse{
        private Stack<Pos> footPrints = new Stack<>();
        
        Pos getPos() { return footPrints.peek(); }

        int getScore() { 
            return footPrints.stream()
                .map((Pos p) -> p.score)
                .reduce(0, Integer::sum); 
        }

        Hourse(Pos pos) { footPrints.add(pos); }

        void move(Pos pos){ footPrints.push(pos); }

        void movePrev(){ footPrints.pop(); }
    }

    private class Pos{
        final int score;
        final Pos red_next;
        Pos blue_next;

        Pos(int score, Pos red_next){
            this.score = score;
            this.red_next = red_next;
        }

        void addBlueNext(Pos blue_next){
            this.blue_next = blue_next;
        }

        Pos move(int distance){
            if(red_next == null) return this;

            Pos ret = blue_next != null ? blue_next : red_next;
            for(int i=0; i<distance-1; i++){
                if(ret.red_next == null) break;
                ret = ret.red_next;
            }
            return ret;
        }
    }
}