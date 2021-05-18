<h1>20057. 마법사 상어와 토네이도</h1>

#### [분류]
- 구현
- 시뮬레이션
<br><br>

#### [문제링크]
- https://www.acmicpc.net/problem/20057
<br><br>


#### [요구사항]
- 토네이도가 (1,1)에 도달했을 때 경계 밖으로 나간 먼지 총 양을 구한다.<br><br> 

#### [풀이]

1. 문제에 적혀있는 순서대로 그대로 구현하면 된다.<br><br>

2. 방향에 따라 먼지가 퍼질 수 있는 경우 4가지를 배열로 선언해준다.<br><br>

3. 이제 cnt를 조절해가면서 방향을 바꾸면서 한 번의 차례마다 먼지를 퍼뜨려서 값을 갱신해준다.<br><br>

4. 토네이도가 (1,1)에 도착하면 밖으로 나간 먼지 총 양을 출력한다.<br><br>


#### [코드]
```java
//baekjoon_20057_마법사상어와_토네이도

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main{
    static int n;
    static int sx, sy;
    static int total = 0;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {-1, 0, 1, 0};
    static int[][] map;
    static int[][][] change= {
            {
                    {0, 0, 2, 0, 0},
                    {0, 10, 7, 1, 0},
                    {5, 0, 0, 0, 0},
                    {0, 10, 7, 1, 0},
                    {0, 0, 2, 0, 0}
            },
            {
                    {0, 0, 0, 0, 0},
                    {0, 1, 0, 1, 0},
                    {2, 7, 0, 7, 2},
                    {0, 10, 0, 10, 0},
                    {0, 0, 5, 0, 0}
            },
            {
                    {0, 0, 2, 0, 0},
                    {0, 1, 7, 10, 0},
                    {0, 0, 0, 0, 5},
                    {0, 1, 7, 10, 0},
                    {0, 0, 2, 0, 0}
            },
            {
                    {0, 0, 5, 0, 0},
                    {0, 10, 0, 10, 0},
                    {2, 7, 0, 7, 2},
                    {0, 1, 0, 1, 0},
                    {0, 0, 0, 0, 0}
            }
    };
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        map = new int[n+2][n+2];
        for(int i=1;i<=n;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=1;j<=n;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }//map정보받기
        sx = (n+1)/2; sy = (n+1)/2;

        int orderCnt = 0;
        int orderMax = 1;
        int order = 0;
        int cnt = 0;
        while(true){

            if(sx == 1 && sy == 1){
                System.out.println(total);
                return;
            }

            int cx = sx + dx[cnt];
            int cy = sy + dy[cnt];
            int cValue = map[cx][cy];

            for(int i=0;i<5;i++){
                for(int j=0;j<5;j++){
                    int nx = cx-2+i ;
                    int ny = cy-2+j;

                    if(isBorder(nx, ny)){
                        if(change[cnt][i][j]!=0){
                            total += (change[cnt][i][j]*cValue)/100;
                            map[cx][cy]-=(change[cnt][i][j]*cValue)/100;
                        }
                        continue;
                    }else{
                        map[nx][ny]+=(change[cnt][i][j]*cValue)/100;
                        map[cx][cy]-=(change[cnt][i][j]*cValue)/100;
                    }
                }
            }

            if(isBorder(cx+dx[cnt], cy+dy[cnt])) total += map[cx][cy];
            else map[cx+dx[cnt]][cy+dy[cnt]]+= map[cx][cy];

            map[cx][cy] = 0;
            sx = cx; sy = cy;

            order++;
            if(order==orderMax){
                order = 0;
                cnt = (cnt+1)%4;
                if(orderCnt%2!=0){
                    orderMax++;
                }
                orderCnt++;
            }
        }
    }

    private static boolean isBorder(int x, int y){
        return (x<=0 || y<=0 || x>n || y>n);
    }
}
```
<br><br>

#### [통과여부]
![image](https://user-images.githubusercontent.com/54053016/118690623-310a2480-b843-11eb-9a94-9c163348bd78.png)
<br><br>

#### [느낀점]
예전에 한 번 풀어봤어서 금방 할 줄 알았지만 역시 구현능력이 낮아서 오래 걸렸다. 특히 cnt를 구현하는 것이 좀 어려웠다.