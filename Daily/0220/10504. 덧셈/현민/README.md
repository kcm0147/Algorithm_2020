# 10504 숫자

## 조건

* 숫자가 주어진다.

## 구해야하는 것

* 연속된 숫자들로 주어진 숫자를 만들 수 있을 때, 제일 짧은 연속된 숫자들을 출력하시오.

## 아이디어...

1. brute force하게 2부터 시작하여 `divide^2 <= 2*n`까지 for문 돌린다.

2. for문의 인자는 수열안의 숫자들의 갯수가 되며, 이를 활용해 합을 만든 후 수와 비교한다.

3. 이를 for문이 끝날때까지나, 수열을 찾을 때까지 반복한다.

## 느낀 점

1. 뭔가 너무 진이 빠지는 문제다... 이분탐색인거 같아 계속 구간의 움직이는 조건을 찾으려 애썼는데...

2. 결국 답을 보고 말았는데 진짜 이런 문제는 힘이 빠진다.

## 코드

```java
package daily.day0220.boj10504;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = stoi(br.readLine());
        for(int t=0; t<T; t++){
            int n = stoi(br.readLine());

            int answer = -1;
            int limit = (int) Math.min(n, 2 * Math.sqrt(n));
            for(int divide=2; divide<=limit; divide++){
                if(mySum(getStarting(n,divide), divide) == n){
                    answer = divide;
                    break;
                }
            }

            if(answer == -1) System.out.println("IMPOSSIBLE");
            else print(n, answer);
        }
    }

    private static void print(int number, int divide){
        int start = getStarting(number,divide);
        System.out.print(number + " = ");
        for(int i=0; i<divide-1; i++){
            System.out.print(start + i + " + ");
        }
        System.out.println(start+divide-1);
    }

    private static int getStarting(int number, int divide){
        return divide % 2 != 0 ? number/divide - divide/2 : number/divide - divide/2 + 1;
    }

    private static int mySum(int start, int n) { return n * (2*start + n -1) /2; }
    private static int stoi(String s) { return Integer.parseInt(s); }
}
```