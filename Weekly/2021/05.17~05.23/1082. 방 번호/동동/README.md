<h1>8972. 미친 아두이노</h1>

#### [분류]
- 구현
- 시뮬레이션
<br><br>

#### [문제링크]
- https://www.acmicpc.net/problem/8972
<br><br>


#### [요구사항]
- 첫째 줄에 백은진이 가지고 있는 돈으로 만들 수 있는 가장 큰 수를 출력한다.<br><br> 

#### [풀이]

1. 문제에 적혀있는 순서대로 그대로 구현하면 된다.<br><br>

2. 종수가 움직이는 것을 구현한다. 이 때, 움직일 좌표에 미친 아두이노가 있으면 종료한다.<br><br>

3. 이후, 미친 아두이노들을 움직인다. <br><br>

4. 미친 아두이노를 움직일 때, 그 자리에 종수가 있으면 바로 종료한다.<br><br>

5. 가장 중요한 미친 아두이노가 겹칠 때를 위해서 visited를 두어 여러 아두이노 오는 아두이노마다 1씩 더해준다.<br><br>

6. 이렇게 해주면 나중에 visited가 2이상 되는 칸에 오는 아두이노는 큐에 다시 넣지않고 삭제해줄 수 있다.<br><br>

#### [코드]
```java
//baekjoon_8972_미친아두이노

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main{
    static int n, m;
    static int cnt = 0;
    static int MAX = 202;
    static int jx=0, jy=0;
    static char[][] map;
    static String order;
    static Queue<Node> Crazy = new LinkedList<>();
    static int dx[] = {100, 1, 1, 1, 0, 0, 0, -1, -1, -1};
    static int dy[] = {100, -1, 0, 1, -1, 0, 1, -1, 0, 1};
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new char[n][m];
        for(int i=0;i<n;i++){
            map[i] = br.readLine().toCharArray();
            for(int j=0;j<m;j++){
                if(map[i][j]=='I'){
                    jx = i; jy = j;
                }else if(map[i][j] == 'R'){
                    Crazy.add(new Node(i, j));
                }
            }
        }//정보 받기

        order = br.readLine();
        for(int l=0;l<order.length();l++){
            int idx = order.charAt(l)-'0';

            //종수이동
            int nx = jx + dx[idx];
            int ny = jy + dy[idx];

            cnt++;
            if(map[nx][ny]=='R') {
                System.out.println("kraj " + cnt);
                return;
            }
            map[nx][ny]='I';
            if(idx!=5){
                map[jx][jy]='.';
            }
            jx = nx; jy = ny;

            //미친 아두이노 이동
            Queue<Node> Temp = new LinkedList<>();
            int[][] visited = new int[n][m];
            while(!Crazy.isEmpty()){

                Node node = Crazy.poll();
                map[node.x][node.y]='.';//이동하니까 원래 자리는 초기화

                Node point = getPoint(node.x, node.y);//미친 아두이노의 방향 얻기
                if(point.x==jx && point.y==jy){
                    System.out.println("kraj " + cnt);
                    return;
                }//움직인 곳이 종수있는 곳이라면 종료

                visited[point.x][point.y]++;
                Temp.add(new Node(point.x, point.y));
            }
            while(!Temp.isEmpty()){
                Node tmp = Temp.poll();
                if(visited[tmp.x][tmp.y]==1){
                    map[tmp.x][tmp.y]='R';
                    Crazy.add(tmp);
                }else{
                    map[tmp.x][tmp.y]='.';
                }
            }
        }

        printMap();
    }

    static private class Node{
        int x, y;

        private Node(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    static private void printMap(){
        for(int i=0;i<n;i++){
            StringBuilder sb = new StringBuilder();
            for(int j=0;j<m;j++)
                sb.append(map[i][j]);
            System.out.println(sb.toString());
        }
    }

    static private Node getPoint(int cx, int cy){
        int res = MAX;
        int rx=cx, ry=cy;
        for(int i=1;i<=9;i++){
            if(i==5) continue;
            int nx = cx + dx[i];
            int ny = cy + dy[i];

            if(isBorder(nx, ny)) continue;
            int dis = Math.abs(jx-nx)+Math.abs(jy-ny);
            if(res>dis){
                res = dis;
                rx =nx; ry=ny;
            }
        }
        return new Node(rx, ry);
    }

    static private boolean isBorder(int x, int y){
        return (x<0 || y<0 || x>n-1 || y>m-1);
    }
}
```
<br><br>

#### [통과여부]
![image](https://user-images.githubusercontent.com/54053016/118471670-f6b95e00-b742-11eb-9111-ddbade9a1477.png)
<br><br>

#### [느낀점]
정말 어이없는 곳에서 시간을 너무 많이 허비했다. 분명히 이동한 횟수를 출력하라고 해서 제자리에 있을 때는 뺐는데 포함하는 것이 답이었다. 이건 정말 문제의 조건을 더 제대로 적어줘야할 것 같다. 화난다.