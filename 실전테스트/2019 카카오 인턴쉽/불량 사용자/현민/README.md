# 불량 당첨자

## 조건

* 불량 당첨자의 경우 개인정보를 위해 하나 이상의 문자가 *로 바껴서 표시된다.

    * 문자 하나당 *로 표시된다.
    
## 구해야 하는 것

* 응모자의 이름들과, 제재 아이디들이 주어졌을 때 `가능한 제재 경우의 수`

* 여기서 조심해야 할 것은 제재 가능한 `경우의 수`라는 것이다.

## 로직

* 이용한 방식은 모든 banned를 사용하였을 때, 밴 당한 유저들의 조합이 새로운 것인지 확인하는 것이다.

  * 이는 비트마스크 배열을 통하여 구현하였다.
  
  * 새로운 것이면 비트마스크 배열의 해당 원소에 1을 추가한다.
  
## 느낀 점

* 로직은 빠르게 생각났으나 아쉬운 점이 많았다.

* 다른 사람의 풀이를 보고 최적화할 부분이 더 있음을 보고 많이 배웠다고 생각했다.

  * 나는 해당 조합이 사용되었음을  `int[] visited = new int[100001]` 으로 했지만
  
  * `Set<Integer> visited`를 이용하면 해당 조합만큼만 원소를 가질 수 있어 공간 절약이 된다.
  
  * 또한 뭔가 String 2개를 비교할 때 뭔가 마음에 안 들었는데
  
  * 보니까 정규식을 이용하여 불량 사용자 패턴을 정규화하여 비교하는 것을 보고 놀랬다.
  
* 아직 너무 부족하다...

## 코드

```java
class Solution {
    private int[] visited = new int[100001]; // 이 조합으로 끝까지 도달했는가

    public int solution(String[] user_id, String[] banned_id) {
        Arrays.fill(visited, 0);
        dfs(0, 0, user_id, banned_id);
        return Arrays.stream(visited).sum();
    }

    private void dfs(int ban, int banned, String[] user_id, String[] banned_id){
        if(ban >= banned_id.length){
            visited[banned] = 1;
            return;
        }
        // ban하는 경우
        for(int i=0; i< user_id.length; i++){
            if((banned & (1<<i)) > 0) continue;
            if(!isSamePattern(user_id[i], banned_id[ban])) continue;
            dfs(ban+1, banned | (1<<i), user_id, banned_id);
        }
    }

    private boolean isSamePattern(String str1, String str2) {
        if(str1.length() != str2.length()) return false;

        for(int i=0; i<str1.length(); i++){
            if(str2.charAt(i) != '*' && str1.charAt(i) != str2.charAt(i))
                return false;
        }
        return true;
    }
}
```