<h1>16946. 벽 부수고 이동하기4</h1>

#### [분류]
- 탐색
<br><br>

#### [문제링크]
- https://www.acmicpc.net/problem/16946
<br><br>


#### [요구사항]
- 모든 벽에 대해서 주변의 빈 공간으로 얼마나 이동할 수 있는지 구한다.<br><br> 

#### [풀이]

1. 빈 공간에 대해서 모두 bfs를 수행하고 해당 그룹마다 번호를 부여한다.<br><br>

2. 이후 각 벽에 대해서 상하좌우에 대해 같은 그룹이면 한번만, 다른 그룹이면 전부 영역 값을 더해준다.<br><br>

3. 정답을 구할 수 있다.<br><br>

#### [코드]
```java
//baekjoon_16946_벽부수고이동하기4

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main{
    static int n, m;
    static char[][] base;
    static int[][] group;
    static HashMap<Integer, Integer> map = new HashMap<>();
    static Queue<Pos> places = new LinkedList<>();
    static int[] dx = {0, -1, 0, 1};
    static int[] dy = {1, 0 , -1, 0};
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        base = new char[n][m];
        group = new int[n][m];
        for(int i=0;i<n;i++){
            char[] chars = br.readLine().toCharArray();
            for(int j=0;j<m;j++){
                base[i][j] = chars[j];
                if(base[i][j]=='0') places.add(new Pos(i, j));
            }
        }//data받는데 한번 돌기

        int groupCnt = 1;
        while(!places.isEmpty()){//벽이 아닌 좌표를 꺼내면서 bfs수행
            Pos place = places.poll();
            if(group[place.x][place.y]>0) continue;

            int res = bfs(place.x, place.y, groupCnt);
            map.put(groupCnt, res);
            groupCnt++;
        }

        for(int i=0;i<n;i++){
            StringBuilder sb = new StringBuilder();
            for(int j=0;j<m;j++){
                if(base[i][j]=='1'){
                    Set<Integer> set = new HashSet<>();
                    int sum = 1;
                    for(int k=0;k<4;k++){
                        int nx = i + dx[k];
                        int ny = j + dy[k];

                        if(!isBorder(nx, ny) && base[nx][ny]=='0'){
                            set.add(group[nx][ny]);
                        }
                    }
                    for(int cnt:set){
                        sum+=(map.get(cnt)%10);
                    }
                    sb.append(sum%10);
                }else{
                    sb.append(0);
                }
            }
            System.out.println(sb.toString());
        }
    }

    private static int bfs(int px, int py, int groupCnt){
        int res = 1;
        Queue<Pos> q = new LinkedList<>();
        group[px][py]=groupCnt;
        q.add(new Pos(px, py));

        while(!q.isEmpty()){
            Pos p = q.poll();

            for(int i=0;i<4;i++){
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];

                if(!isBorder(nx, ny) && base[nx][ny]=='0'
                        && group[nx][ny]==0){
                    group[nx][ny] = groupCnt;
                    q.add(new Pos(nx, ny));
                    res++;
                }
            }
        }
        return res;
    }

    private static boolean isBorder(int x, int y){
        return (x<0 || y<0 || x>n-1 || y>m-1);
    }

    private static class Pos{
        int x, y;

        private Pos(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
}
```
<br><br>

#### [통과여부]
![image](https://user-images.githubusercontent.com/54053016/118691847-70854080-b844-11eb-9804-a17038e11391.png)
<br><br>

#### [느낀점]
처음에는 단순하게 벽마다 모두 bfs 수행해서 구했더니 시간초과가 떠서 당황스러웠다. 하지만 곧 빈 공간을 bfs 수행하는 방법으로 바꿔야 겠다고 떠올렸고, 신선했다.