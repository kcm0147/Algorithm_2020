# 2661 좋은 수열

## 문제 해석

1. 수열 안에 동일한 부분수열이 이어져있으면 나쁜 수열이다.

2. 그렇지 않다면 좋은 수열이다.

3. 수열은 1,2,3의 숫자로면 이루어져있다.

## 풀어야하는 것

* 구해야하는 수열의 길이가 주어질때, 제일 작은 좋은 수열을 구하시오.

## 아이디어

1. 최대 주어지는 수열의 길이는 80이다.

2. 현재 i번째 수열의 숫자를 결정할 때, 그 숫자가 나쁜 수열을 만드는지 확인하면 된다.

3. 두 부분 수열 (i-2*size+1, i-size), (i-size+1,i)이 같은 수열인지 확인한다.
   
4. 1 <= size <= i/2

5. 가능한 모든 size에 대해 나쁜 수열이 아니라면, 다음 i+1번째 숫자를 결정한다.

6. 이를 반복하여 N까지 구한다.

## 코드

```java
package daily.day0227.boj2661;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static BufferedReader br;
    private static StringTokenizer st;

    private static int[] sequence;
    private static int size;

    public static void main(String[] args) {
        size = nextInt();
        sequence = new int[size];
        makeSequence(0);
        for(int i=0; i<size; i++) System.out.print(sequence[i]);
    }

    private static boolean makeSequence(int idx){
        if(idx >= size) return true;

        boolean ret = false;
        for(int num=1; num<=3; num++){
            sequence[idx] = num;
            if(!isGood(idx)) continue;
            ret = makeSequence(idx+1);
            if(ret) break;
        }
        return ret;
    }

    private static boolean isGood(int lastIdx) {
        int size=1;
        int left = lastIdx - 2*size + 1;
        int right = left + size;

        while(left >= 0){
            boolean same = true;
            for(;right<=lastIdx; left++, right++){
                if(sequence[left] != sequence[right]){
                    same = false;
                    break;
                }
            }
            if(same) return false; // 똑같은 부분수열이 만들어지므로 실패!

            size++;
            left = lastIdx - 2*size + 1;
            right = left + size;
        }
        return true; // 모든 케이스를 통과함. 같은 부분수열이 반복하지 않음.
    }

    private static int nextInt() { return Integer.parseInt(nextToken()); }

    private static String nextToken(){
        if(br == null) br = new BufferedReader(new InputStreamReader(System.in));
        try {
            if (st == null || !st.hasMoreTokens())
                st = new StringTokenizer(br.readLine());
        }catch (IOException e){

        }
        return st.nextToken();
    }
}
```
