# 공황

## 느낀 점

생각보다 한방에 풀려서 당황했다. 다른 친구들의 풀이도 보니 다 생각한게 비슷했다.

역시 사람 사는 것은 다 똑같은 것 같다.

## 풀이

```java
public class Main {
    private static DisjointSet disjointSet;
    private static int[] flights;

    public static void main(String[] args) throws IOException {
        init();
        System.out.println(solve());
    }

    private static int solve(){
        int ret=0;
        for(int i=0; i<flights.length; i++){
            int gate = disjointSet.find(flights[i]);
            if(gate == 0) break;

            disjointSet.union(gate, gate-1);
            ret++;
        }
        return ret;
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int gateSize = Integer.parseInt(br.readLine());
        disjointSet = new DisjointSet(gateSize);

        int flightSize = Integer.parseInt(br.readLine());
        flights = new int[flightSize];

        for(int i=0; i<flightSize; i++){
            flights[i] = Integer.parseInt(br.readLine());
        }
    }
}

class DisjointSet{
    int[] parent;

    DisjointSet(int size){
        parent = new int[size+1];
        for(int i=0; i<parent.length; i++)
            parent[i] = i;
    }

    int find(int u){
        if(parent[u] == u) return u;
        return parent[u] = find(parent[u]);
    }

    void union(int u, int v) {
        u = find(u);
        v = find(v);

        if (u > v) {
            int temp = u;
            u = v;
            v = temp;
        }

        parent[v] = u;
    }
}
```

1. 그리디 문제였다. 그 이유는 다음과 같다.

    * 가능한 한 제일 큰 게이트에 도킹하는 것이 좋다 -> 그래야 이후 더 많은 비행기들이 도킹할 수 있다.
    
    * 만약 현재 비행기가 그보다 작은 게이트에 도킹하는 것이 더 좋은 결과를 내는가? -> 귀류법으로 증명할 수 있다.
    
2. union find가 필요했다. 이유는 다음과 같다.

    * 현재 가능한 제일 큰 게이트에 도킹해야 한다. 하지만 단순히 brute로 탐색하면 N^2이 걸릴 수도 있기 때문에 다른 방법이 필요하다.
    
    * 이 때 union find가 제격이다. i 지점 이전에서 제일 큰 도킹 가능한 게이트를 저장하기 제격이다.
