# 튜플

## 조건

* 원소가 중복되지 않는 튜플이 주어진다.

* 집합이 주어지는데 집합은 다음과 같다.

    * 튜플이 { a1, a2, a3, a4} 일 때
  
    * 집합은 {{a2, a1}, {a1}, {a3,a1,a2}, {a1,a2,a3,a4}} 로 표현할 수 있다.
  
    * 집합에서 하나의 부분 집합안에서는 원소의 순서가 상관이 없다.
  
    * 단지 부분집합은 크기가 각각 1,2,3,4...n개여야 한다.
  
## 로직

* 여기서 중요한 점은 집합에서 각 원소의 빈도수가 바로 튜플의 순서의 역순이라는 점이다.

* 따라서 집합을 파싱하여 원소들의 수를 센 후 

* 빈도수의 역순을 반환하면 된다.

## 느낀 점

* 크게 어려운 점은 없었으나.. 고수들의 풀이를 보고 놀랬다.

* 정규식은 문자열이 나오면 꼭 써야할 필수 아이템인 거 같다. 로직이 간단해보여 굉장히 좋아보인다.

## 코드

```java
class Solution {
    public int[] solution(String s) {
        HashMap<String, Integer> numberFreqMap = new HashMap<>();
        // i가 1시작 n-2까지 인것은 맨 양쪽 끝 {}는 고려할 필요가 없으므로
        int subStart = 2;
        for(int i=1; i<s.length()-1; i++){
            if(s.charAt(i) == '{'){
                subStart = i+1;
            }else if(s.charAt(i) == '}'){
                StringTokenizer stringTokenizer = new StringTokenizer(s.substring(subStart, i), ",");
                while(stringTokenizer.hasMoreTokens()){
                    String numStr = stringTokenizer.nextToken();
                    numberFreqMap.computeIfPresent(numStr, (key, value) -> ++value);
                    numberFreqMap.putIfAbsent(numStr, 1);
                }
            }
        }
        int maxFreq = numberFreqMap.values().stream().max(Integer::compare).get();
        int[] answer = new int[maxFreq];
        numberFreqMap.forEach((key, value) -> answer[maxFreq - value] = Integer.parseInt(key));

        return answer;
    }
}
```