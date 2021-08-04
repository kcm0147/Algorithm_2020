# 느낀 점

1. 인풋을 보고 NlogN으로 풀어야겠다는 생각에 이진 탐색을 생각했다.

2. 하지만 처음에 너무 복잡하게 생각해서 이진탐색을 구현하였다.

3. 생각해보니, 오름차순이므로 현재 이후부터 이진탐색을 하면 되는 거였다..

4. 투포인터 방식도 있었는데, 이게 더 빨리 풀 수도 있을거 같다. 이걸로도 시도해보자.

5. 너무 어렵게 풀지 말자!

# 풀이

```java
public class Main {
    private static int N;
    private static int[] values;

    public static void main(String[] args) throws IOException {
        init();
        int[] answer = solve();
        System.out.println(answer[0] + " " + answer[1]);
    }

    private static int[] solve(){
        int[] ret = new int[2];
        int minAbsValue = Integer.MAX_VALUE;

        for(int i=0; i< values.length-1; i++){
            int j = binarySearch(-values[i], i+1, values.length-1);
            if(Math.abs(values[i] + values[j]) < minAbsValue){
                minAbsValue = Math.abs(values[i] + values[j]);
                ret[0] = values[i];
                ret[1] = values[j];
            }
        }

        return ret;
    }

    // index 반환
    private static int binarySearch(final int find, final int left, final int right){
        int l = left, r = right;
        while(l <= r){
            int mid = (l + r) / 2;
            if(find == values[mid]) return mid;
            else if(find > values[mid]) l = mid+1;
            else r = mid-1;
        }

        if(r < left) return l;
        if(l > right) return r;

        int leftGap =  Math.abs(find - values[l]);
        int rightGap =  Math.abs(find - values[r]);
        return leftGap < rightGap ? l : r;
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        values = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }
}
```

1. 숫자들을 오름차순으로 순회한다. 한 숫자의 음의 값을 이진탐색으로 검색한다. (범위는 그 다음 숫자부터 끝까지)
    
    * 범위를 그렇게 설정해도 되는 이유는 이전 값은 이미 계산이 되어 있기 때문이다.
   
    * 범위를 전체로 하면 2번 중복해서 계산하게 되며 로직도 매우 복잡해진다.

2. 찾은 숫자와 현재 숫자의 합의 절댓값이 기존 값보다 작으면 갱신해준다.