# 18116 로봇 조립

## 조건 

* 호재는 두가지를 할 수 있다.

    * 두 부품이 같은 로봇의 부품인지
    
    * 한 개의 부품 i에 대해 이때까지 알아낸 robot(i)의 부품의 갯수는
  
    * robot(i)는 i가 부품으로 들어가는 로봇이다.
  
## 아이디어

* 이 문제는 같은 집합에 속하는지를 물어보는 유니온-파인드 문제로 볼 수 있다.

* 기본적인 disjoint set에 size를 추가하여 문제를 풀 수 있겠다.

## 코드

```java
package daily.day0214.boj18116;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

public class Main {
  static int[] parent;
  static int[] size;

  public static void main(String[] args) throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    int sizeQuestion = Integer.parseInt(new StringTokenizer(reader.readLine()).nextToken());

    parent = IntStream.iterate(0, i -> i+1).limit(1000001).toArray();
    size = IntStream.iterate(1, i -> 1).limit(1000001).toArray();

    for(int question=0; question<sizeQuestion; question++){
      String[] s = reader.readLine().split(" ");
      if(s[0].equals("I")){
        union(Integer.parseInt(s[1]), Integer.parseInt(s[2]));
      }else{
        int part = Integer.parseInt(s[1]);
        System.out.println(size[find(part)]);
      }
    }
  }

  static int find(int u){
    if(parent[u] == u) return u;
    return parent[u] = find(parent[u]);
  }

  static void union(int u, int v){
    u = find(u); v = find(v);
    if(u == v) return;

    size[u] += size[v];
    size[v] = 0;
    parent[v] = parent[u];
  }
}
```