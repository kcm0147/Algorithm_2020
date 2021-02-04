import java.awt.*;
import java.util.Scanner;

public class Q1039 {
    static int n, k, ans = -1;
    static boolean cache[][] = new boolean[1000001][11];
    static String line;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt(); k = sc.nextInt();
        line = String.valueOf(n);
        findMax(line, 0);
        System.out.println(ans);
    }

    private static void findMax(String str, int cnt) {
        if(cnt == k) {
            ans = Math.max(ans, Integer.parseInt(str));
            return;
        }
        if(cache[Integer.parseInt(str)][cnt]) return;
        cache[Integer.parseInt(str)][cnt] = true;
        int ret = 0;
        StringBuilder sb = new StringBuilder(str);
        for (int i = 0; i < str.length() - 1; i++) {
            for (int j = i + 1; j < str.length(); j++) {
                if(i == 0 && sb.charAt(j) == '0') continue;
                swap(sb, i, j);
                if(!cache[Integer.parseInt(sb.toString())][cnt + 1])
                    findMax(sb.toString(), cnt + 1);
                swap(sb, i, j);
            }
        }
    }

    private static void swap(StringBuilder sb, int i, int j) {
        char temp = sb.charAt(i);
        sb.setCharAt(i, sb.charAt(j));
        sb.setCharAt(j, temp);
    }
}
