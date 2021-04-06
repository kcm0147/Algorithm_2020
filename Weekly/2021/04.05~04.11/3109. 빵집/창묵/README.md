```java

public class Main {


    static int R,C,answer;
    static char[][] map;
    static int[][] dp;
    static int dir[][] = {{-1,1},{0,1},{1,1}};

    static final int INF = 999999;
    public static void main(String[] args) throws IOException {
        init();
        for(int i=0;i<R;i++)
            calc(i,0,i);
        System.out.println(answer);
    }


    public static void init() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R=stoi(st.nextToken());
        C=stoi(st.nextToken());
        map=new char[R][C];
        dp=new int[R][C];

        for(int i=0;i<R;i++){
            String input = br.readLine();
            for(int j=0;j<C;j++){
                map[i][j]=input.charAt(j);
            }
        }

        for(int i=0;i<R;i++){
            Arrays.fill(dp[i],-INF);
        }

    }

   

    public static int calc(int x,int y,int index) {
        if (dp[x][y] != -INF)
            return dp[x][y];
        if (y == C - 1) {
            answer++;
            return 1;
        }

        int result = -1;
        for (int i = 0; i < 3; i++) {
            int nx = x + dir[i][0];
            int ny = y + dir[i][1];
            if (!checkBound(nx, ny))
                continue;

            if (map[nx][ny] == '.') {
                result = calc(nx, ny,index);
                if(result!=-1){
                    map[nx][ny]=(char)('0'+index);
                    break;
                }
            }
        }

        return dp[x][y] = result;
    }

    public static boolean checkBound(int x,int y){
        if(x<0 || x>=R || y<0 || y>=C) return false;
        return true;
    }

    public static int stoi(String input){
        return Integer.parseInt(input);
    }


}

```