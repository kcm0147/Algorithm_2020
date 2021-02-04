import java.util.Arrays;
import java.util.Scanner;

public class Main1039 {
    public static void main(String[] args) {
        Solution1039 solution = new Solution1039();
        solution.init();
        System.out.println(solution.solve());
    }
}

class Solution1039 {
    private int[] numbers;
    private int changeChance;
    private int[][] cache;

    void init() {
        try (Scanner input = new Scanner(System.in)) {
            numbers = toIntAry(input.nextInt());
            changeChance = input.nextInt();
        }
        cache = new int[1000001][11];
        for(int[] c : cache)
            Arrays.fill(c, -1);
    }

    int solve() {
        return change(changeChance);
    }

    private int change(int remainChange) {
        int number = toInt(numbers);
        if (remainChange == 0) return number;

        if(cache[number][remainChange] != -1) return cache[number][remainChange];

        int ret = -1;
        for (int i = 0; i < numbers.length; i++) {
            for (int j = i + 1; j < numbers.length; j++) {
                if (i == 0 && numbers[j] == 0) continue;
                swap(i, j);
                ret = Math.max(ret, change(remainChange - 1));
                swap(i, j);
            }
        }

        cache[number][remainChange] = ret;
        return ret;
    }

    private void swap(int i, int j) {
        int temp = numbers[i];
        numbers[i] = numbers[j];
        numbers[j] = temp;
    }

    private int toInt(int[] numbers) {
        int number = 0;
        int digitNum = 1;
        for (int i = numbers.length - 1; i >= 0; i--) {
            number += numbers[i] * digitNum;
            digitNum *= 10;
        }
        return number;
    }

    private int[] toIntAry(int number) {
        int size = 0;
        int[] temp = new int[100];
        while (number > 0) {
            temp[size++] = number % 10;
            number /= 10;
        }

        int[] ret = new int[size];
        for (int i = 0; i < size; i++) {
            ret[size - i - 1] = temp[i];
        }
        return ret;
    }
}