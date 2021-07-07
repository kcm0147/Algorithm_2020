# 크게 만들기

## 느낀 점

진짜... 1132 문제나 다른 여러 문제도 풀었었지만, 진짜 바로 코딩으로 들어가는 것은 매우 위험한 것 같다.

여러 예외 케이스를 미처 생각하지 못하고 시간을 배로 쓴 것 같다.

꼭 검증의 시간을 가지자.

## 풀이

```java
public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println(solve());
    }

    private static String solve() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] arr =Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int N = arr[0], K = arr[1];

        String str = br.readLine();
        StringBuilder sb = new StringBuilder();
        int deleted=0;

        Stack<Character> st = new Stack<>();
        for(int i=0; i<str.length(); i++){
            while(deleted < K && !st.isEmpty() && st.peek() < str.charAt(i)){
                st.pop();
                deleted++;
            }
            st.push(str.charAt(i));
        }

        while(deleted < K){
            st.pop();
            deleted++;
        }
        while(!st.isEmpty()){
            sb.insert(0, st.pop());
        }

        return sb.toString();
    }

    private static boolean isSmallerThanNext(String str, int i){
        return i+1 >= str.length() ? false : str.charAt(i) < str.charAt(i+1);
    }
}
```

1. 스택을 이용한 문제이다. 스택에는 숫자들이 들어있다.
   
2. for문을 순회하면서 스택의 top이 현재 숫자보다 크거나 작을 때까지 pop한다.

    * 이 때, deleted(삭제된 수의 갯수)를 증가시킨다.
    
    * 만약 deleted가 K보다 같거나 크다면 이 과정은 진행하지 않는다.

2. 현재 숫자를 push한다.

3. stack에서 꺼내서 string으로 만든다.