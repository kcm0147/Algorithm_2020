# 사촌 

## 느낀점 

형제 자매를 빼고 사촌만 답으로 해야하는 것을 모르고, 알고리즘을 잘못 풀어 고생을 하였다.

이유를 몰라 풀이를 봤는데 좋아보여서 참고하여 짜보았다.

## 코드

```java

public class Main {
   private static BufferedReader br;
   private static StringTokenizer st;

   private static int[] node = new int[1001];
   private static int[] parent = new int[1001];
   private static int size;
   private static int K;

   public static void main(String[] args) throws IOException {
      size = nextInt(); K = nextInt();
      while(size != 0 && K != 0){
         for(int i=0; i<size; i++) node[i] = nextInt();
         System.out.println(solve());
         size = nextInt(); K = nextInt();
      }

   }

   private static int solve(){
      int findIdx = -1, p=-1;
      parent[0] = -1;
      for(int i=1; i<size; i++){
         if(node[i] == K) findIdx = i;
         if(node[i-1] + 1 != node[i]) p++;
         parent[i] = p;
      }

      int ret=0;
      for(int i=1; i<size; i++){
         if(parent[i] != parent[findIdx] && parent[parent[i]] == parent[parent[findIdx]])
            ret++;
      }

      return ret;
   }

   private static int nextInt() throws IOException {
      if(br == null) br = new BufferedReader(new InputStreamReader(System.in));
      if(st == null || !st.hasMoreTokens())
         st = new StringTokenizer(br.readLine(), " ");
      return Integer.parseInt(st.nextToken());
   }
}
```

1. 루트의 부모는 -1로 둔다.

2. for문을 돌면서 부모의 값을 갱신해주고, K의 인덱스도 같이 찾는다.

   * 연속되지 않는 값이라면, 그 다음 인덱스를 부모로 한다.

3. 부모의 부모는 같으면서, 부모는 다른 인덱스들을 찾는다.

