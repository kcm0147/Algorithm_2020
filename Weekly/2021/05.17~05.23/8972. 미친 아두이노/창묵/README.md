* 시뮬레이션

<br/>


이 문제는 특별한 알고리즘이 필요한 것은 아닙니다.

그저 문제에서 요구하는 사항을 순서에 맞게 구현을 하면 됩니다.

다만 문제에서 로봇들이 한칸에 2대 이상이 있으면 파괴를 한다고 했는데, 이때 로봇을 파괴되는 시점을

`로봇들이 전부 다 움직이고 나서` 한칸에 2대 이상 있는 로봇들을 파괴시키도록 하면 됩니다.

저 같은 경우에는 Set을 사용하여 칸에 있는 로봇들을 관리하였습니다.


<br/>

Set을 사용한 이유는, List를 사용할 때에 비해서 칸마다 종수가 있는지, 미친아두이노들이 있는지 확인을 하기 위해서 Set을 사용하였습니다.

`calc()`의 메소드가 메인 함수라고 생각하시면 됩니다.


<br/>



---

<br/>

```java

public class Main {

    static int N,M,robotCnt;
    static String command;
    static Pair[][] map;
    static List<Robot> robotList;
    static Queue<Node> destoryQue;
    static Robot me;
    static int[][] dir ={{1,-1},{1,0},{1,1},{0,-1},{0,0},{0,1},{-1,-1},{-1,0},{-1,1}};

    public static void main(String[] args) throws IOException
    {
        init();
        int answer=calc();

        if (answer == -1) {
            print();
        } else {
            System.out.println("kraj "+answer);
        }
    }

    public static void print() throws IOException{
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for(int i=0;i<N;i++){

            for(int j=0;j<M;j++){
                if(map[i][j].robotSet.size()==0)
                    bw.write('.');
                else if(map[i][j].robotSet.size()==1 && map[i][j].robotSet.contains(0))
                    bw.write('I');
                else if(map[i][j].robotSet.size()==1)
                    bw.write('R');
            }
            bw.newLine();
        }
        bw.newLine();
        bw.flush();
    }


    public static void init() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N=stoi(st.nextToken()); M=stoi(st.nextToken());
        map=new Pair[N][M];
        robotList=new ArrayList<>();
        destoryQue=new LinkedList<>();


        for(int i=0;i<N;i++){
            String cur = br.readLine();
            for(int j=0;j<M;j++){
                map[i][j]=new Pair();
                if(cur.charAt(j)=='R'){
                    robotList.add(new Robot(i,j,++robotCnt));
                    map[i][j].robotSet.add(robotCnt);
                }
                else if(cur.charAt(j)=='I'){
                    me=new Robot(i,j,0);
                    map[i][j].robotSet.add(0);
                }
            }
        }

        command=br.readLine();

    }

    public static void destoryCheck(){
        for(int i=0;i<robotList.size();i++){
            Robot cur = robotList.get(i);
            if(!cur.status) continue;

            if(map[cur.x][cur.y].robotSet.size()>1) destoryQue.add(new Node(cur.x,cur.y));
        }

    }


    public static int calc() throws IOException{

        for(int i=0;i<command.length();i++){
            int num=command.charAt(i)-'0';
            if(!myMove(num-1)) return i+1;
            if(!robotMove()) return i+1;

            destoryCheck();

            if(!destoryQue.isEmpty()){
                destory();
            }
        }

        return -1;
    }

    public static void destory(){
        while(!destoryQue.isEmpty()){
            Node cur =destoryQue.poll();
            List<Integer> destoryList=new ArrayList<>(map[cur.x][cur.y].robotSet);

            for(Integer i : destoryList){
                robotList.get(i-1).status=false;
                map[cur.x][cur.y].robotSet.remove(i);
            }
        }
    }


    public static boolean myMove(int num){
        int nx = me.x+dir[num][0];
        int ny = me.y+dir[num][1];

        map[me.x][me.y].robotSet.remove(me.index);
        map[nx][ny].robotSet.add(me.index);

        me.x=nx;
        me.y=ny;

        if(map[nx][ny].robotSet.size()>1) return false;

        return true;
    }

    public static boolean robotMove(){
        for(int i=0;i<robotList.size();i++){
            Robot cur = robotList.get(i);
            if(!cur.status) continue;

            int nx,ny;
            int minCost=Integer.MAX_VALUE;
            int minDir=-1;
            for(int j=0;j<9;j++){
                nx=cur.x+dir[j][0];
                ny=cur.y+dir[j][1];

                if(!checkBound(nx,ny)) continue;

                if(Math.abs(nx-me.x)+Math.abs(ny-me.y)<minCost){
                    minDir=j;
                    minCost=Math.abs(nx-me.x)+Math.abs(ny-me.y);
                }
            }
            nx=cur.x+dir[minDir][0];
            ny=cur.y+dir[minDir][1];

            if(map[nx][ny].robotSet.contains(0)) return false;

            map[nx][ny].robotSet.add(cur.index);
            map[cur.x][cur.y].robotSet.remove(cur.index);
            cur.x=nx;
            cur.y=ny;

        }

        return true;
    }

    public static boolean checkBound(int x,int y){
        if(x<0 || x>=N || y<0 || y>=M) return false;

        return true;
    }
    public static int stoi(String input){
        return Integer.parseInt(input);
    }

}

class Node{
    int x,y;

    Node(int x,int y){
        this.x=x;
        this.y=y;
    }
}

class Pair{
    Set<Integer> robotSet;
    Pair(){
        robotSet=new HashSet<>();
    }
}

class Robot{
    int x,y,index;
    boolean status;

    Robot(int x,int y,int index){
        this.x=x;
        this.y=y;
        this.index=index;
        this.status=true;
    }
}

```
