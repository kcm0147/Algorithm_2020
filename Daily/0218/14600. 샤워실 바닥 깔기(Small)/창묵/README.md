문제의 해결방법을 생각하기에 까다로운 문제였습니다.

혼자의 힘으로 해결할 수 없어서 풀이를 참고하였습니다.

대표적인 트로미노 알고리즘인데, 이는 분할정복을 이용한 문제입니다.

주어진 사각형의 중심 점을 먼저 채운 후에, 사각형을 4등분 한 후에, 다시 연산을 진행합니다.

check()함수를 통해서 4등분한 사각형 중 하수구가 존재한다면, 중앙점을 부여하지않도록 합니다.

조금 더 자세한 풀이는 다음 블로그에서 참조하였습니다.

[참조풀이](https://rebro.kr/64)

분할정복을 좀 더 공부를 해야할 것 같습니다.


```java

 public static void calc(int x,int y,int size){
        num++;
        int ns=size/2;
        if(check(x,y,ns)) map[x+ns-1][y+ns-1]=num;
        if(check(x+ns,y,ns)) map[x+ns][y+ns-1]=num;
        if(check(x,y+ns,ns)) map[x+ns-1][y+ns]=num;
        if(check(x+ns,y+ns,ns)) map[x+ns][y+ns]=num;

        if(size==2) return;

        calc(x,y,ns);
        calc(x+ns,y,ns);
        calc(x,y+ns,ns);
        calc(x+ns,y+ns,ns);

    }

    public static boolean check(int x,int y,int s){
        for(int i=x;i<x+s;i++){
            for(int j=y;j<y+s;j++){
                if(map[i][j]!=0) return false;
            }
        }

        return true;
    }

```



<br/>


```java

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {


    static int[][] map;
    static int x,y,N;
    static int num;



    public static void main(String[] args) throws IOException {
        init();
        calc(1,1,N);
        print();


    }

    public static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N=stoi(st.nextToken());
        N=(int)Math.pow(2,N);
        map=new int[N+1][N+1];
        st = new StringTokenizer(br.readLine());
        x=stoi(st.nextToken());
        y=stoi(st.nextToken());

        map[y][x]=-1;

    }

    public static void print(){
        for(int i=N;i>=1;i--){
            for(int j=1;j<=N;j++){
                System.out.printf(map[i][j]+" ");
            }
            System.out.println();
        }
    }

    public static void calc(int x,int y,int size){
        num++;
        int ns=size/2;
        if(check(x,y,ns)) map[x+ns-1][y+ns-1]=num;
        if(check(x+ns,y,ns)) map[x+ns][y+ns-1]=num;
        if(check(x,y+ns,ns)) map[x+ns-1][y+ns]=num;
        if(check(x+ns,y+ns,ns)) map[x+ns][y+ns]=num;

        if(size==2) return;

        calc(x,y,ns);
        calc(x+ns,y,ns);
        calc(x,y+ns,ns);
        calc(x+ns,y+ns,ns);

    }

    public static boolean check(int x,int y,int s){
        for(int i=x;i<x+s;i++){
            for(int j=y;j<y+s;j++){
                if(map[i][j]!=0) return false;
            }
        }

        return true;
    }



    public static int stoi(String input){
        return Integer.parseInt(input);
    }
}

```