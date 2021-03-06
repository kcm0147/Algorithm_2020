---
title: '16946. 벽 부수고 이동하기4'
metaTitle: '만렙 개발자 키우기'
metaDescription: '알고리즘 문제를 풀고 정리한 곳입니다.'
tags: ['그래프 이론', '그래프 탐색', 'BFS', 'DFS']
date: '2021-05-21'
---

# 문제
- [16946. 벽 부수고 이동하기4](https://www.acmicpc.net/problem/16946)

## 코드

<details><summary> 코드 보기 </summary>

``` java
import java.awt.Point;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Q16946 {
    static int dx[] = {-1, 0, 1, 0}, dy[] = {0, 1, 0, -1};
    static int group[][], groupSize[];
    static int n, m, groupCnt, board[][];
    public static void main(String[] args) throws IOException {
        init();
        solution();
    }

    private static void solution() throws IOException {
        makeGroup();
        printBoard();
    }

    private static void printBoard() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == 0) {
                    bw.write("0");
                }
                else {
                    int size = 1;
                    Set<Integer> visited = new HashSet<>();
                    for (int d = 0; d < 4; d++) {
                        int nx = i + dx[d], ny = j + dy[d];

                        if(isBorder(nx, ny) && !visited.contains(group[nx][ny]) && board[nx][ny] == 0){
                            size += groupSize[group[nx][ny]];
                            visited.add(group[nx][ny]);
                        }
                    }
                    bw.write("" + size % 10);
                }
            }
            bw.write("\n");
        }
        bw.flush();
    }

    private static void makeGroup() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if(board[i][j] == 0 && group[i][j] == -1){
                    int size = 1;
                    Queue<Point> q = new LinkedList<>();
                    group[i][j] = groupCnt;
                    q.add(new Point(i, j));
                    while (!q.isEmpty()) {
                        Point here = q.poll();
                        for (int d = 0; d < 4; d++) {
                            int nx = here.x + dx[d], ny = here.y + dy[d];
                            if(!isBorder(nx, ny) || group[nx][ny] != -1 || board[nx][ny] == 1)
                                continue;
                            group[nx][ny] = groupCnt;
                            q.add(new Point(nx, ny));
                            size += 1;
                        }
                    }
                    groupSize[groupCnt++] = size;
                }
            }
        }
    }

    private static boolean isBorder(int x, int y) {
        return (x >= 0 && x < n && y >=0 && y< m);
    }


    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=  new StringTokenizer(br.readLine());
        n = stoi(st.nextToken());
        m = stoi(st.nextToken());
        board = new int[n][m];
        group = new int[n][m]; groupSize = new int[n*m];
        for (int i = 0; i < n; i++) {
            char[] line = br.readLine().toCharArray();
            for (int j = 0; j < m; j++) {
                board[i][j] = line[j] - '0';
                group[i][j] = -1;
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
> 입출력 때문에 시간초과가 계속 떴었던 것..
> 
> 다행히 로직은 틀리지 않아서 그나마 나았다.

## 풀이 📣
<hr/>

1️⃣ 미리 0인 구간이 이어지는 부분을 탐색해서 하나의 그룹으로 만들어놓는다.

    - 이차원 배열로 각 그룹에 번호를 부여하고, 일차원 배열을 통해 그룹에 포함된 0의 개수를 저장시켜놓는다.

    
2️⃣ 이후 2중 포문을 돌리면서 1인 지점을 찾을 때 마다 벽을 무너뜨린 후 갈 수 있는 지점의 개수를 찾아낸다.

    - 동서남북 방향으로 한 번씩 탐색해본다.

    - 만약 1칸 이웃한 지점이 0이라면 그 칸이 포함된 그룹의 번호를 찾아낸다. group[nx][ny]

    - 그 그룹이 이루고 있는 0의 개수를 찾아낸다. groupCount[group[nx][ny]]

    - 그 개수만큼 size 에 더해주고, 해당 그룹 번호는 재탐색을 하지 못하도록 Set에 넣어둔다.

    - 그렇게 동서남북 방향을 모두 탐색해서 현재 칸에서 갈 수 있는 길의 총 합을 구해서 10으로 나눈 나머지를 출력한다.


3️⃣ 이때, BufferedWriter 를 사용하여 출력 시간의 낭비를 줄여준다.

    - 그냥 System.out.println() 을 사용하면 시간초과가 발생한다.


## 실수 😅

- 그냥 배열을 반복문 돌 때마다 계속 생성하고 지우는걸 반복하면 비효율적일거 같아서 바로 Set을 사용했었는데, 
  
    그렇게 하면 안되는 이유가 배열을 재생성할때마다 배열의 크기만큼의 시간이 들기 때문이란걸 나중에 알았다. 
 
 
- **배열 재생성은 쓸데없는 시간 낭비라는 것 다시 한 번 기억해야겠다.**


- 자바 입출력 시간을 다시 한 번 상기시킬 수 있었다!