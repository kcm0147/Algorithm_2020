import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Q1099 {
    static final int ERROR = 99999;
    static String line;
    static String[] words;
    static int n, cache[];

    public static void main(String[] args) throws IOException {
        init();
        int ret = ERROR;
        ret = Math.min(ret, solution(0));
        if(ret == ERROR) System.out.println(-1);
        else System.out.println(ret);
    }

    private static int solution(int start) {
        if(start == line.length()) return 0;
        else if(start > line.length()) return ERROR;

        if(cache[start] > -1) return cache[start];

        int ret = ERROR;
        for(String word : words){
            int len = word.length();
            if(start + len - 1 >= line.length()) continue;
            int diff = countDiff(word, start);
            if(diff != -1)
                ret = Math.min(ret, diff + solution(start + len));
        }
        return cache[start] = ret;
    }

    private static int countDiff(String word, int start) {
        int ret = 0, wordFreq[] = new int[26], strFreq[] = new int[26];

        for (int i = 0; i < word.length(); i++) {
            int w = word.charAt(i) - 'a', s = line.charAt(start + i) - 'a';
            if(w != s) ret += 1;
            wordFreq[w] += 1;
            strFreq[s] += 1;
        }
        for (int i = 0; i < 26; i++)
            if(wordFreq[i] != strFreq[i]) return -1;
        return ret;
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        line = br.readLine();
        n = Integer.parseInt(br.readLine());
        cache = new int[51];
        words = new String[n];
        Arrays.fill(cache, -1);
        for (int i = 0; i < n; i++)
            words[i] = br.readLine();
        br.close();
    }
}
