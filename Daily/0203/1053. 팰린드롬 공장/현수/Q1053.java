import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Q1053 {
    static String line, ori;
    static int cache[][] = new int[51][51], ans = 99999, len = 0;
    public static void main(String[] args) throws IOException {
        init();
        int right = isPalindrome(line), left = line.length() - 1 - right;
        for (int i = 0; i < len - 1; i++) {
            for (int j = i + 1; j < len; j++) {
                for (int l = 0; l < 51; l++) Arrays.fill(cache[l], -1);
                swap(i, j, ori);
                right = isPalindrome(line); left = line.length() - 1 - right;
                if (right == -1) {
                    System.out.println(1);
                    return;
                }
                ans = Math.min(ans, 1 + solution(left, right));
            }
        }
        System.out.println(ans);
    }
    static int solution(int left, int right) {
        if(left >= right) return 0;
        if(line.charAt(left) == line.charAt(right)) return solution(left + 1, right - 1);
        int ret = Integer.MAX_VALUE;
        if(cache[left][right] != -1) return cache[left][right];

        // 1. 삽입, 삭제
        ret = Math.min(ret, 1 + solution(left + 1, right));
        ret = Math.min(ret, 1 + solution(left, right - 1));

        // 2. 다른 문자로
        ret = Math.min(ret, 1 + solution(left + 1, right - 1));
        return cache[left][right] = ret;
    }
    static void swap(int right, int i, String temp) {
        StringBuilder sb = new StringBuilder(temp);
        char t = sb.charAt(i);
        sb.setCharAt(i, sb.charAt(right));
        sb.setCharAt(right, t);
        line = sb.toString();
    }
    static int isPalindrome(String str){ // OK : -1, NO : right index
        for (int left = 0; left < str.length(); left++) {
            int right = str.length() - 1 - left;
            if(left > right) break;
            if(str.charAt(left) != str.charAt(right)) return right;
        }
        return -1;
    }
    static void init() throws IOException {
        for (int l = 0; l < 51; l++) Arrays.fill(cache[l], -1);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        line = br.readLine(); ori = line;
        len = line.length();
        int right = isPalindrome(line), left = line.length() - 1 - right;
        if(right == -1) {
            System.out.println(0);
            return;
        }
        ans = Math.min(ans, solution(left, right));
    }
}