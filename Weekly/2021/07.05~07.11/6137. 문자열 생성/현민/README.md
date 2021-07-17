# 문자열 생성

## 느낀점 

양쪽에서 시작하는 투 포인터 문제였다.

## 코드

```java
public class Main {
   private static char[] string;

   public static void main(String[] args) throws IOException {
      init();
      solve();
   }

   private static void init() throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      int N = Integer.parseInt(br.readLine());

      string = new char[N];
      for(int i=0; i<N; i++){
         string[i] = br.readLine().charAt(0);
      }
   }

   private static void solve(){
      int left=0, right= string.length-1;

      int cnt=0;
      while(left <= right){
         if(string[left] < string[right]){
            System.out.print(string[left++]);
         }else if(string[left] > string[right]){
            System.out.print(string[right--]);
         }else{
            boolean isSmallerLeft = true;
            for(int l=left+1, r=right-1; l <= right; l++, r--){
               if(string[l] == string[r]) continue;
               if(string[l] > string[r]) isSmallerLeft = false;
               break;
            }

            if(isSmallerLeft) System.out.print(string[left++]);
            else System.out.print(string[right--]);
         }

         if(++cnt >= 80){
            System.out.println();
            cnt=0;
         }
      }
   }
}
```

1. 양 끝을 가리키는 left, right 포인터를 두 개 둔다.

2. 둘을 비교해서 더 작은 것을 출력하고 한 칸 움직인다.

3. 만약 같다면, 계속 같이 움직이면서 더 작은 값이 있는 쪽을 발견한다. 그리고 그쪽으로 움직인다.
