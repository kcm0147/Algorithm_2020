# 문제풀이 

## 코드

```java
package week.week0510.p1082;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try(Scanner scanner = new Scanner(System.in)){
            int n = scanner.nextInt();

            int[] prices  = new int[n];
            for(int i=0; i<n; i++) prices[i] = scanner.nextInt();

            int money = scanner.nextInt();

            System.out.println(new Solve().solve(money, prices));
        }
    }

    private static class Solve{

        String solve(int money, int[] prices){
            int ret;

            List<Integer> numbers = deleteExpensiveThanHigher(prices);

            int maxDigits = getMaxDigits(money, numbers, prices);

            return selectNumbers(money, maxDigits, numbers, prices);
        }

        String selectNumbers(int money, int digits, List<Integer> numbers, int[] prices){
            String ret = "";
            int n = digits;

            money -= digits * prices[numbers.get(0)];
            while(n > 0 && numbers.size() > 1 && prices[numbers.get(1)] - prices[numbers.get(0)] <= money){
                for(int i=numbers.size()-1; i>=0; i--){
                    if(money < prices[numbers.get(i)] - prices[numbers.get(0)]) continue;

                    ret += numbers.get(i);
                    n--;
                    money -= (prices[numbers.get(i)] - prices[numbers.get(0)]);
                    break;
                }
            }

            for(; n > 0; n--){
                ret += numbers.get(0);
            }
            return ret;
        }

        int getMaxDigits(int money, List<Integer> numbers, int[] prices){
            if(numbers.get(0) == 0 && numbers.size() == 1) return 1;
            if (numbers.get(0) == 0 && prices[numbers.get(1)] > money) return 1;

            int ret = 0;
            if(numbers.get(0) == 0){
                money -= prices[numbers.get(1)];
                ret = 1;
            }
            return ret + money / prices[numbers.get(0)];
        }

        List<Integer> deleteExpensiveThanHigher(int[] prices){
            List<Integer> ret = new ArrayList<>();

            for(int i=0; i<prices.length; i++)
                ret.add(i);

            for(int i = 0; i<prices.length; i++){
                for(int j = i+1; j<prices.length; j++){
                    if(prices[i] >= prices[j]){
                        ret.remove(Integer.valueOf(i));
                        break;
                    }
                }
            }

            return ret;
        }
    }
}
```
