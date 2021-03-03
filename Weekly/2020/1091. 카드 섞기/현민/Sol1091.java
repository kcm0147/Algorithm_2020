import java.util.*;
import java.util.stream.IntStream;

public class Sol1091 {
    public static void main(String[] args){
        try(Scanner input = new Scanner(System.in)){
            int size = input.nextInt();
            int[] toDeliveries = new int[size];
            int[] nextPositions = new int[size];

            for(int i=0; i<size; i++) toDeliveries[i] = input.nextInt();
            for(int i=0; i<size; i++) nextPositions[i] = input.nextInt();

            Deck deck = new Deck(new SufflePolicy(nextPositions), new JudgementPolicy(toDeliveries), size);
            System.out.println(solve(deck));
        }
    }

    private static int solve(Deck initialDeck){
        Deck deck = new Deck(initialDeck);
        int ret = 0;
        while(!deck.isDestination()){
            deck.suffle(); ret++;
            if(deck.equals(initialDeck)) return -1; // hasCycle
        }

        return ret;
    }
}

class Deck{
    private int[] cards;
    SufflePolicy sufflePolicy;
    JudgementPolicy judgementPolicy;

    Deck(SufflePolicy sufflePolicy, JudgementPolicy judgementPolicy, int size){
        cards = IntStream.iterate(0, i -> i+1).limit(size).toArray();
        this.sufflePolicy = sufflePolicy;
        this.judgementPolicy = judgementPolicy;
    }

    Deck(Deck deck){
        this.cards = deck.cards.clone();
        this.judgementPolicy = deck.judgementPolicy;
        this.sufflePolicy = deck.sufflePolicy;
    }

    void suffle(){
        cards = sufflePolicy.suffle(cards);
    }

    boolean isDestination(){
        return judgementPolicy.isSuccess(cards);
    }

    @Override
    public boolean equals(Object obj) {
        Deck deck = (Deck) obj;
        return Arrays.equals(this.cards, deck.cards);
    }

    @Override
    public int hashCode() {
        return cards.hashCode();
    }
}

class SufflePolicy{
    private int[] nextPositions;
    private int[] copied;

    SufflePolicy(int ... nextPositions){
        this.nextPositions = nextPositions;
        copied = new int[nextPositions.length];
    }

    int[] suffle(int ... cards){
        assert(nextPositions.length == cards.length);

        for(int i=0; i<cards.length; i++){
            int nextIdx = nextPositions[i];
            copied[nextIdx] = cards[i];
        }

        for(int i=0; i<cards.length; i++)
            cards[i] = copied[i];

        return cards;
    }
}

class JudgementPolicy{
    int[] toDeliveries;

    JudgementPolicy(int... toDeliveries){
        this.toDeliveries = toDeliveries;
    }

    boolean isSuccess(int ... cards){
        int player = 0;
        for(int card : cards){
            if(toDeliveries[card] != player) return false;
            player = (player + 1) % 3;
        }
        return true;
    }
}