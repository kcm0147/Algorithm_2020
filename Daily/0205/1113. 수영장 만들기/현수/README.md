# 문제
- [1113. 수영장 만들기](https://www.acmicpc.net/problem/1113)

## 코드

<details><summary> 코드 보기 </summary>

``` java
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.List;

public class Q1113 {
    static int n, m, board[][], dx[] = {-1, 0, 1, 0}, dy[] = {0, 1, 0, -1};
    static boolean existHeight[], existWater[][], visited[][];
    static List<Point> testPool = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        init();
        solution();
    }

    private static void solution() {
        // 물 채우기 :: 둘러싸고 있는 벽 중 가장 낮은 벽이 최대.
        // 각 칸에 채울 수 있는 물의 양 = (최대 양 - 칸의 높이)
        int ans = 0;
        for (int max = 9; max > 1; --max) {
            if(!existHeight[max]) continue;
            int ret = 0;

            for (int x = 1; x < n - 1; x++) {
                for (int y = 1; y < m - 1; y++) {
                    if(existWater[x][y] || board[x][y] >= max)
                        continue;
                    for(int i=0; i<n; ++i)
                        Arrays.fill(visited[i], false);
                    visited[x][y] = true;

                    if(dfs(x, y, max))
                        ret += getDiff(max);
                    testPool = new ArrayList<>();
                }
            }
            ans += ret;
        }
        System.out.println(ans);
    }

    static boolean dfs(int x, int y, int max){
        for (int d = 0; d < 4; d++) {
            int nx = x + dx[d], ny = y + dy[d];
            if(!isBorder(nx, ny)) return false;

            if(existWater[nx][ny] || visited[nx][ny] || board[nx][ny] >= max)
                continue;
            visited[nx][ny] = true;
            if(!dfs(nx, ny, max))
                return false;
        }
        testPool.add(new Point(x, y));
        return true;
    }

    private static int getDiff(int max) {
        int diff = 0;
        for (Point p : testPool) {
            if(existWater[p.x][p.y]) continue;
            existWater[p.x][p.y] = true;
            diff += max - board[p.x][p.y];
        }
        return diff;
    }

    private static boolean isBorder(int x, int y) {
        return (x >= 0 && x < n && y >= 0 && y < m);
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        board = new int[n][m]; existHeight = new boolean[10];
        existWater = new boolean[n][m];
        visited = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < m; j++) {
                board[i][j] = line.charAt(j) - '0';
                existHeight[board[i][j]] = true;
            }
        }
    }
}
```

</details>

## ⭐️느낀점⭐️
> BFS로 풀었는데 메모리초과, DFS 로 풀었더니 통과.. 띠용
> 
> DFS 는 잘 안썼었는데 이번 기회에 오랜만에 써봐서 좋았다.
> 
> 그래프 탐색 문제에서 최단 경로같은 풀이법만 접해보다가, 탐색을 통해 감싸는 부분을 찾아내는 방식이 새로워서 좋았다. 

## 풀이 📣
<hr/>

1️⃣ 물을 가둘 수 있는 곳을 찾기 위해서 벽의 최대 높이를 정해줘서 기준을 잡았다.

    - 최대 높이 9부터 최소 높이 2 (1보다 작아지면 물을 가둘 수 없기 때문)까지 반복문을 돌며 높이의 한계값을 설정해준다.

    - 가장 자리에는 물을 가둘 수 없으므로 탐색에서 제외 시켜준다. (x = 0, n - 1, y = 0, y = m - 1 지점)

    - 한계값이 더 높을수록 해당 칸에는 더 많은 양의 물을 가둘 수 있다. -> 해당 칸에 최대 물의 양 = (높이의 한계값 - 해당 칸의 높이)

2️⃣ 현재 높이의 한계값보다 낮은 위치로 DFS 를 이어나갈 수 있다.

    - 또한, 이전에 더 높은 한계값으로 최대 물의 양을 구했던 칸은 재탐색하지 않는다.

    - 지금까지 건너온 칸은 되돌아가지 않는다. (DFS 기본 원리)


3️⃣ 만약 장외로 벗어나는 경우 물이 가둬지지 않고 밖으로 새어나가기 때문에 바로 DFS 를 종료시킨다.

    - 장외 벗어날 경우 false 를 반환해서, if(dfs == false) return false  조건을 통해 전체 탐색을 종료시켜버린다.  

4️⃣ 종료 조건에 걸리지 않고 탐색을 마친 경우 해당 칸을 리스트로 저장해놓고, 모든 탐색이 종료되면 저장된 좌표와 현재 높이의 한계값의 차를 구해 최종 결과에 더해준다.


5️⃣ 높이의 한계값을 낮춰가며 더 낮은 한계값으로 가둘 수 있는 물의 양도 모두 구해서 전체 가둬지는 물의 양을 출력한다.

<hr/>

## 실수 😅
- 처음에 한 시간동안 어떻게 풀지 전혀 떠올리지 못했다.

- 생각을 리프레시하기 위해서 커피를 마시고 화장실도 다녀오면서 계속 생각한 끝에 풀이법을 찾아냈다!

- 하지만 예상밖에 짜잘한 구현 실수와, BFS 구현 방식의 메모리 초과로 고통받았다.. 결국 DFS 만이 답인 것인가