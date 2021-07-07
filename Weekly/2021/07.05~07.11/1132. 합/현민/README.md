# 합

## 느낀 점

의외로 생각할 점이 많았었다. 여러 예외 케이스들을 생각하지 못했었다. 

코딩하기 전에 한번 검증의 시간을 항상 가지도록 하는 것이 시간을 오히려 절약하는 방법인 것 같다.

단순하다고 생각해서 바로 코딩으로 손을 옮기지 말자! 오히려 몇 배의 시간을 써야할 수도 있다.

## 풀이

```java
public class Main {
    private static Alpha[] numAlpha = new Alpha[10];
    private static boolean[] canZero = new boolean[10];

    public static void main(String[] args) throws IOException {
        init();
        System.out.println(solve());
    }

    private static long solve(){
        Arrays.sort(numAlpha, comparing(Alpha::getFreq).reversed());

        int[] mulNums = new int[10];
        Arrays.fill(mulNums, -1);
        for(int i=9; i>=0; i--){
            if(canZero[numAlpha[i].alpha]){
                mulNums[i] = 0;
                break;
            }
        }

        for(int num=9, i=0; num>=1;  i++){
            if(mulNums[i] != -1) continue;
            mulNums[i] = num--;
        }

        long ret=0;
        for(int i=0; i<10; i++){
            ret += mulNums[i] * numAlpha[i].freq;
        }

        return ret;
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        for(int i=0; i<10; i++) numAlpha[i] = new Alpha(i,0); // A ~ Z 까지라구

        Arrays.fill(canZero, true);
        for(int i=0; i<N; i++){
            String s = br.readLine();

            long mulNum = 1;
            for(int j=s.length()-1; j>=0; j--){
                numAlpha[((int)s.charAt(j)-'A')].freq += mulNum;
                mulNum *= 10;

                if(j==0) canZero[((int)s.charAt(j)-'A')] = false;
            }
        }
    }
}

class Alpha {
   int alpha;
   long freq;

   public Alpha(int alpha, long freq) {
      this.alpha = alpha;
      this.freq = freq;
   }

   public long getFreq() {
      return freq;
   }
}
```

1. 각 알파벳의 횟수를 구한다. ABC라면 -> A는 100번, B는 10번, C는 1번이다.

2. 각 알파벳의 횟수가 제일 큰 순서대로 제일 큰 숫자를 부여한다. 단, 맨 앞에 나온 숫자가 0을 부여받는 일이 없도록 선처리한다.
