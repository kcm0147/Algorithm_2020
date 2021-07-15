# 뒤집기

## 느낀점 

알고리즘을 풀 때 고민하다가 디피는 아닌거 같고 그리디로 푸는 거 같아 좀 고민하다가, 

알고리즘을 클릭해서 그리디인 것을 보고 말았다. 좀 더 확신을 가지고 문제를 풀려고 하는 노력을 하는게 좋겠다.

## 코드

```java
public class Main {
   public static void main(String[] args) throws IOException {
      System.out.println(solve());
   }

   private static String solve() throws IOException {
      final String string = new BufferedReader(new InputStreamReader(System.in)).readLine();

      StringBuilder sb = new StringBuilder();
      int start=0; sb.append(string.charAt(0));
      for(int next=1; next<string.length(); next++){
         if(string.charAt(start) <  string.charAt(next))
            sb.append(string.charAt(next));
         else {
            sb.insert(0, string.charAt(next));
            start = next;
         }
      }

      return sb.toString();
   }
}
```

1. 문자열을 뒤집는 문제지만 실제로 뒤집을 필요는 없다. 증가수열로 증가하다가, 만약 증가수열의 첫번째보다 작은 원소를 발견하면 총 2번의 뒤집기가 잃어난다.

    * 예를 들면  B, C, D 이렇게 가다가 A를 만나면 D, C, B 이렇게 뒤집고 다시 전체 뒤집으면 A, B, C, D가 된다.
    
    * 따라서 뒤집을 필요가 없고 B에서 시작해서 C, D를 차례대로 넣고, 자기자신보다 작거나 같은 A를 만나면 앞에 넣어주면 결과는 같게 된다.
    
2. 더 작거나 같은 문자를 만나 맨 앞에 추가하였다면, 이 문자를 시작으로 다시 계속한다. `start = next`


