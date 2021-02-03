import java.util.Arrays;
import java.util.Scanner;

public class Main1053 {
    public static void main1053(String[] args) {
        Solution1053 solution = new Solution1053();
        solution.input();
        System.out.println(solution.solve());
    }
}

class Solution1053 {
    char[] string = new char[MAX_SIZE + 1];
    int[][] cache = new int[MAX_SIZE][MAX_SIZE];

    private static final int MAX_SIZE = 50;

    void input() {
        try (Scanner input = new Scanner(System.in)) {
            string = input.next().toCharArray();
        }
    }

    private void init() {
        for (int[] a : cache)
            Arrays.fill(a, -1);
    }

    private int solve(int left, int right) {
        if (right - left <= 0) return 0;
        if (string[left] == string[right]) return solve(left + 1, right - 1);

        if (cache[left][right] != -1) return cache[left][right];

        int ret = 1 + solve(left + 1, right); // insert right edge same as left or delete left
        ret = Integer.min(ret, 1 + solve(left, right - 1)); // insert left edge same as right or delete right
        ret = Integer.min(ret, 1 + solve(left + 1, right - 1)); // change edge character

        cache[left][right] = ret;
        return ret;
    }

    int solve() {
        init();
        int ret = solve(0, string.length - 1); // no used 4 condition

        for (int lo = 0; lo < string.length; lo++) {
            for (int hi = lo + 1; hi < string.length; hi++) {
                swap(lo, hi);
                init();
                ret = Integer.min(ret, 1 + solve(0, string.length - 1));
                swap(lo, hi);
            }
        }

        return ret;
    }

    private void swap(int idx1, int idx2) {
        char temp = string[idx1];
        string[idx1] = string[idx2];
        string[idx2] = temp;
    }
}