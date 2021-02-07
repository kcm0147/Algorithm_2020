# 호텔 방 예정

## 조건

* 고객이 원하는 번호의 방을 배정해준다.

* 방을 배정할 때는 고객들의 순서대로 배정해준다.

* 만약 방이 이미 배정되어 있다면, 그 방보다 번호가 큰 것 중에 제일 작은 번호의 비어있는 방을 배정해준다.

## 로직

* HashMap을 이용한 Union-Find 응용이다.

* map(key, value)에서 value : key번 방 다음으로 있는 방 중에서 제일 작은 방 번호

* map에 방이 없다면 방을 배정해준뒤에 map에 (key, key+1) 을 넣는다.

* 만약 이미 방이 배정되어 있다면 재귀호출을 통해 방을 찾는다.

    * 재귀호출 시 다음 방은 map.get(key)이다.

* 이 때 방 배정후 재귀호출을 올라오면서 map의 value를 새로이 배정된 방+1으로 갱신해준다.

## 느낀점

* 처음에 어떻게 풀어야 하나 고민하고 HashSet을 사용하여 풀었으나 효율성 테스트에서 고배를 마셨다.

* 결국 풀이를 보게되었는데, 유니온 파인드의 응용이라 굉장히 신기하였고 유니온 파인드 공부도 열심히 해야겠다고 느꼈다.

## 코드

```java
class Solution {
  public long[] solution(long k, long[] room_number) {
    long[] answer = new long[room_number.length];

    HashMap<Long, Long> next = new HashMap<>();
    for(int i=0; i<room_number.length; i++)
      answer[i] = getAssigned(next, room_number[i]);

    return answer;
  }

  public long getAssigned(HashMap<Long,Long> next, long room){
    if(!next.containsKey(room)){
      next.put(room, room+1);
      return room;
    }else{
      long find = getAssigned(next, next.get(room));
      next.compute(room, (k, v) -> v = find+1);
      return find;
    }
  }
}
```