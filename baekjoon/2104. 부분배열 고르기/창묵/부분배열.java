import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static long[] num;


    public static void main(String[] args) throws IOException {
        init();
        System.out.println(div(0, num.length - 1));
    }

    public static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        num = new long[N];

        for (int i = 0; i < N; i++) {
            num[i] = Integer.parseInt(st.nextToken());
        }

    }

    public static long div(int left, int right) {

        long max;
        long sum, minValue;
        if (left == right) {
            return num[left] * num[left];
        }

        int mid = (left + right) / 2;
        max = Math.max(div(left, mid), div(mid + 1, right));
        sum = num[mid] + num[mid + 1];
        minValue = Math.min(num[mid], num[mid + 1]);

        max = Math.max(max, sum * minValue);
        int low = mid;
        int high = mid + 1;

        while (left < low || high < right) {
            if (left < low && ((right == high) || (num[low - 1] > num[high + 1]))) { // move left
                sum += num[--low];
                minValue = Math.min(num[low], minValue);
            } else { // move right
                sum += num[++high];
                minValue = Math.min(num[high], minValue);
            }
            max = Math.max(max, minValue * sum);

        }

        return max;
    }
}
