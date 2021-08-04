# 느낀 점

1. 실수로 옆에 힙이라는 걸 봐버렸다.

2. 하지만 힙에 얼마만큼 들어가나 궁금했는데 확인할 수 있었던 좋은 문제였다.

3. 또 여러 최적 풀이를 보고 많이 배웠고 새로 적용시켜보았다.


# 풀이

```java
class Solution {
    public int solution(int[] scoville, int K) {
        int answer = 0;

        PriorityQueue<Long> pq = new PriorityQueue<>(Comparator.comparingLong(Long::longValue));
        for(int value : scoville) pq.add((long) value);

        while(pq.size() > 1 && pq.peek() < K){
            long food1 = pq.poll();
            long food2 = pq.poll();

            pq.add(Math.min(food1, food2) + 2 * Math.max(food1, food2));
            answer++;
        }

        return pq.peek() < K ? -1 : answer;
    }
}
```

1. 우선순위 큐에 2개 이상 값이 들어있고, 제일 작은 값이 K보다 작다면 while loop를 돈다.

2. while loop에서 2개를 큐에서 뽑아서 새로운 값으로 대체해서 넣어준다. 그리고 카운트한다.

3. while문이 끝나면 pq의 제일 작은 값이 K보다 작으면 1, 크면 카운트한 값을 반환한다.