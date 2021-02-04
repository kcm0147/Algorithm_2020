import java.util.Arrays;
import java.util.Scanner;

public class Main1099 {
    public static void main1099(String[] args){
        Solution1099 solution = new Solution1099();
        solution.init();
        System.out.println(solution.solve());
    }
}

class Solution1099{
    private static final int CANNOT_REACH = 10001;

    private int[] cache;
    private String sentence;
    private String[] words;

    void init(){
        try(Scanner input = new Scanner(System.in)){
            sentence = input.next();
            int numOfWords = input.nextInt();

            words = new String[numOfWords];
            for(int i=0; i<numOfWords; i++){
                words[i] = input.next();
            }
        }

        cache = new int[sentence.length()+1];
        Arrays.fill(cache, -1);
    }

    int solve(){
        int ret = solve(0);
        return ret != CANNOT_REACH ? ret : -1;
    }

    private int solve(int start){
        if(start == sentence.length()) return 0;
        else if(start > sentence.length()) return CANNOT_REACH;

        if(cache[start] != -1) return cache[start];

        int ret = CANNOT_REACH;
        for(String word : words){
            int matched = match(start, word);
            if(matched != -1)
                ret = Math.min(ret, matched + solve(start + word.length()));
        }

        cache[start] = ret;
        return ret;
    }

    private int match(int start, String word){
        if(sentence.length()-start < word.length()) return -1; // remaining substring is not longer than word

        int[] substrAlphaFreq = new int[26];
        int[] wordAlphaFreq = new int[26];
        Arrays.fill(substrAlphaFreq, 0);
        Arrays.fill(wordAlphaFreq, 0);

        int different = 0;
        for(int i=0; i<word.length(); i++){
            int substrAlpha = sentence.charAt(start+i) - 'a';
            int wordAlpha = word.charAt(i) - 'a';

            if(substrAlpha != wordAlpha) different++;
            substrAlphaFreq[substrAlpha]++;
            wordAlphaFreq[wordAlpha]++;
        }

        return canMake(substrAlphaFreq, wordAlphaFreq) ? different : -1;
    }

    private boolean canMake(int[] substrAlphaFreq, int[] wordAlphaFreq){
        for(int i=0; i<26; i++)
           if(substrAlphaFreq[i] != wordAlphaFreq[i]) return false;
        return true;
    }
}