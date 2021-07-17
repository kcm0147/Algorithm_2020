# 코딩은 이쁘게 

## 느낀점 

알고리즘을 세우고 검증한다고 생각했는데, 양수에서 증가랑, 감소랑 부분을 또 나눠야한다는 점을 빠뜨렸다.

될 줄 알았는데 한 번 손으로 해볼 껄하는 아쉼움이 남는다.

## 코드

```java
public class Main {
    private static int[] diff;

    public static void main(String[] args) throws IOException {
        init();
        System.out.println(solve());
    }

    private static int solve(){
        boolean positive = diff[0] >= 0 ? true : false;
        int lineGap=Math.abs(diff[0]), ret=0;

        for(int i=1; i<diff.length; i++){
            int gap = Math.abs(diff[i]);

            if(positive != (diff[i] >= 0)){
                ret += lineGap;
                positive = !positive;
            }else if(lineGap > gap){
                ret += (lineGap - gap);
            }

            lineGap = gap;

            if(i+1 >= diff.length) ret += lineGap;
        }

        return ret;
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        diff = new int[N];

        int[] num = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] dest = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        for(int i=0; i<N; i++){
            diff[i] = dest[i] - num[i];
        }
    }
}
```

1. 부호가 바뀌거나, Gap의 증감이 바뀌지 않는다면 같은 그룹으로 선택한다.

2. 부호가 바뀐다면, 그룹 중 제일 큰 Gap 값이 결과에 반영된다. 이 gap이 lineGap이다.
   그리고 새로 시작한다.

3. 감소된다면, 그 이전 값과 비교해 그 차이를 결과에 반영한다.
