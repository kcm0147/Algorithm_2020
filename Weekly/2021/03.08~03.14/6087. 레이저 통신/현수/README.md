# 문제
- [6087. 레이저 통신](https://www.acmicpc.net/problem/6087)

## 코드

<details><summary> 코드 보기 </summary>

``` java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Info{
    int x, y, mirror, d;

    public Info(int x, int y, int mirror, int d) {
        this.x = x;
        this.y = y;
        this.mirror = mirror;
        this.d = d;
    }
}
public class Q6087 {
    static int w, h, dx[] = {-1, 0, 1, 0}, dy[] = {0, 1, 0, -1};
    static char board[][];
    static int sx = -1, sy = -1, ex, ey, ans = 987654321;
    static int cache[][][] = new int[100][100][4];

    public static void main(String[] args) throws IOException {
        init();
        bfs(sx, sy, 0);
    }

    private static void bfs(int x, int y, int cnt) {
        Queue<Info> q = new LinkedList<>();
        for (int d = 0; d < 4; d++){
            int nx = x + dx[d], ny = y + dy[d];
            if(!isBorder(nx, ny) || board[nx][ny] == '*')
                continue;
            cache[nx][ny][d] = 987654321 - 1;
            q.add(new Info(nx, ny, 0, d));
        }

        while (!q.isEmpty()) {
            Info here = q.poll();
            x = here.x; y = here.y; cnt = here.mirror;
            int dir = here.d;

            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d], ny = y + dy[d];
                if(!isBorder(nx, ny) || board[nx][ny] == '*')
                    continue;

                int left = (dir + 3) % 4; // 좌회전
                int right = (dir + 1) % 4; // 우회전

                // 거울을 설치하는 경우
                if(d == left || d == right){
                    if(cache[nx][ny][d] > cnt + 1){ // 현재까지 설치한 거울의 수가 저장된 수보다 적을 때만 갱신
                        cache[nx][ny][d] = cnt + 1;
                        q.add(new Info(nx, ny, cnt + 1, d));
                    }
                }
                // 거울을 설치하지 않는 경우
                else {
                    if(cache[nx][ny][d] > cnt){ // 현재까지 설치한 거울의 수가 저장된 수보다 적을 때만 갱신
                        cache[nx][ny][d] = cnt;
                        q.add(new Info(nx, ny, cnt, d));
                    }
                }
            }
        }
        for (int i = 0; i < 4; i++) // 목표 지점까지 4가지 방향으로 도달하는데 필요했던 최소 거울 수 찾기
            ans = Math.min(ans, cache[ex][ey][i]);

        System.out.println(ans);
    }

    private static boolean isBorder(int x, int y) {
        return (x >= 0 && x < h && y >= 0 && y < w);
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        w = stoi(st.nextToken());
        h = stoi(st.nextToken());
        board = new char[h][w];
        for (int i = 0; i < h; i++) {
            String line = br.readLine();
            for (int j = 0; j < w; j++) {
                board[i][j] = line.charAt(j);
                if(board[i][j] == 'C'){
                    if(sx == -1) {
                        sx = i;
                        sy = j;
                    }
                    else {
                        ex = i;
                        ey = j;
                    }
                }
                Arrays.fill(cache[i][j], 987654321);
            }
        }
    }

    private static int stoi(String str) {
        return Integer.parseInt(str);
    }
}

```

</details>

## ⭐️느낀점⭐️
<hr/>

> 저장된 상태보다 더 최적의 조건일 경우에만 재방문 및 값 갱신을 반복하여 탐색하는 BFS 문제였다. 
> 
> 여러 방법으로 재방문을 방지하기 위해서 고민해봤던 좋은 문제였다.

## 풀이 📣
<hr/>

1️⃣ 시작점의 `(sx,sy)` 좌표와 도착점의 `(ex,ey)` 좌표를 저장한다.


2️⃣ 시작점부터 가능한 모든 방향으로 이동한 위치를 이동한 방향값과 함께 큐에 저장한다.

    - 움직인 방향에 따라서 다음 방향으로 이동할 때 거울의 개수가 달라지기 때문이다.


3️⃣ 현재 위치에서 4가지 방향을 모두 탐색해 본 후, 만약 좌.우 90도 회전이 필요하다면 거울의 개수를 1개 증가시킨 값을 캐시에 저장해둔다.

    - 왼쪽 90 도 회전 : (dir + 3) % 4

    - 오른쪽 90도 회전 : (dir + 1) % 4

    - cache[nx][ny][d] > (nx, ny) 위치로 이동하는데 필요한 거울의 수 일 때만 큐에 다시 재삽입 및 값 갱신.

    - 회전하는 경우에는 (현재까지 위치까지 오는데 필요했던 거울의 수 + 1) 개의 거울 필요

    - 그렇지 않은 경우에는 현재까지 위치까지 오는데 필요했던 거울의 수만큼 필요


4️⃣ 모든 탐색을 종료한 뒤, 목적지에 저장된 4가지 방향으로 들어오는 경우 필요한 거울의 수 중 최소값을 찾아서 출력한다.

    - 목적지 좌표까지 들어오는 방향은 4가지 방법이 있으므로, 그 중 거울의 개수가 가장 적게 필요한 경우를 찾아 출력한다.

## 실수 😅
<hr/>

- 그래프 탐색에서 재방문 처리를 한 번에 떠올리지 못했다.

- DFS, BFS 를 모두 시도해봤지만 결국 재방문 처리가 핵심이어서 쓸데없는 시간을 많이 낭비했다.

- 최단경로 + 최적 해 문제였는데, 다익스트라 처럼 값이 최적의 상태로 갱신되는 경우에만 탐색을 계속 진행해야 했던 문제였다.