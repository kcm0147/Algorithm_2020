# 숫자

## 느낀 점

쉽지 않은 문제였다. 결국 다른 블로그의 풀이의 싸이클을 보고 싸이클을 고려해 풀었다.

## 풀이

```java
public class Main {
    private static final int MAX_NUM = 1000000;
    private static int[] cache = new int[MAX_NUM+1];

    private static int start;
    private static int end;
    private static int K;

    private static int[] n_pow_k = new int[10];

    public static void main(String[] args) throws IOException {
        init();
        System.out.println(solve());
    }

    private static long solve(){
        Arrays.fill(cache, -1);
        long ret=0;
        for(int i=start; i<=end; i++){
            ret += calc(i);
        }

        return ret;
    }

    private static int calc(int n){
        if(cache[n] == -1)
            calcSequence(n);
        return cache[n];
    }

    private static void calcSequence(int n){
        List<Integer> numbers = new ArrayList<>();

        while(cache[n] == -1){
            numbers.add(n);
            cache[n] = n;
            n = funcSK(n);
            while(n > MAX_NUM) n = funcSK(n); // 1,000,000 넘는 것은 그냥 넘어가면 됨.
        }

        // 사이클이 있으면, 사이클 중 제일 작은 걸 찾아 사이클에 적용시키기.
        if(numbers.contains(n)) {
            int min=Integer.MAX_VALUE/2;
            for (int i = numbers.indexOf(n); i < numbers.size(); i++) {
                min = Math.min(min, numbers.get(i));
            }
            for (int i = numbers.indexOf(n); i < numbers.size(); i++) {
                cache[numbers.get(i)] = min;
            }
        }

        int min = cache[n];
        for(int i=numbers.size()-1; i>=0; i--){
            n = numbers.get(i);
            min = Math.min(min, cache[n]);
            cache[n] = min;
        }

        numbers.clear();
    }

    private static int funcSK(int n){
        int ret=0;
        while(n > 0){
            ret += n_pow_k[n%10];
            n /= 10;
        }
        return ret;
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        List<Integer> numbers = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).boxed().collect(Collectors.toList());
        start = numbers.get(0);
        end = numbers.get(1);
        K = numbers.get(2);

        for(int i=0; i<=9; i++) n_pow_k[i] = (int) Math.pow(i, K);
    }
}
```

1. 한가지 중요한 점은 반드시 사이클이 존재한다는 점이다. 그 이유는 비둘기집의 원리이다. 

2. 이 풀이는 dp(cache)를 활용하였다. `dp[i] = i부터 시작하는 수열 중 제일 작은 수` 이다.

3. dp[i]를 구하기 위해서 수열을 구해나간다. 이 때 `dp[i] = i`로 초기화한다.

4. 이미 구한 수열이 있을 경우 또는 싸이클이 발생한 경우 멈춘다. 싸이클이 생긴 경우 `dp[i] = i`로 초기화 되어 있을 것이다.

5. 마지막값이 이 때까지 구한 수열 중에 있으면 싸이클이 있다는 말이다. 싸이클 안의 dp 값들을 모두 싸이클 중 제일 작은 값으로 한다.

6. 이 때까지 구한 수열의 역순으로 진행해나가며, dp값을 최신값으로 갱신한다.