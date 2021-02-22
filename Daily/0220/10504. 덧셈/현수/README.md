
```java
import java.io.IOException;
import java.util.Scanner;

public class Q10504 {
    static int tc;
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        tc = sc.nextInt();
        while(tc-- > 0){
            int n = sc.nextInt();
            int ansStart = -1, ansEnd = -1;
            int max = (int)Math.min(n, 2*Math.sqrt(n));
            for (int count = 2; count <= max; count++) {
                int start = getStart(n, count);
                if(sum(start, start + count - 1) == n){
                    ansStart = start;
                    ansEnd = start + count - 1;
                    break;
                }
            }
            if (ansEnd == -1) {
                System.out.println("IMPOSSIBLE");
            } else{
                System.out.print(n + " = ");
                for (int i = ansStart; i < ansEnd; i++) {
                    System.out.print(i + " + ");
                }
                System.out.println(ansEnd);
            }
        }
    }

    private static int getStart(int n, int div) {
        if(div % 2 == 0) return n / div - div / 2 + 1;
        return n / div - div / 2;
    }

    static int sum(int start, int end){
        int ret = (end - start + 1) / 2 * (start + end);
        if((end - start + 1) % 2 == 1) ret += (start + end)/ 2;
        return ret;
    }
}

```
