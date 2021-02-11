```java

public class Main {

    static int N,M,answer;
    static int[][] map;
    static boolean[][] visit;

    static int[][] dir={{0,1},{1,0},{-1,0},{0,-1}};


    public static void main(String[] argv) throws IOException {

        init();


        for(int k=1;k<=9;k++) {
            visit=new boolean[N][M];
            for (int i = 0; i < N; i++) {
                for (int j=0;j<M;j++){
                    if(map[i][j]==k && !visit[i][j]){
                        bfs(i,j,k);
                    }
                }
            }
        }

        System.out.println(answer);



    }

    public static void bfs(int x,int y,int k){
        Queue<Node> que =new LinkedList<>();
        ArrayList<Node> nodeList = new ArrayList<>();

        Node init=new Node(x,y);
        que.add(init);
        nodeList.add(init);
        visit[x][y]=true;

        int minValue;


        while(!que.isEmpty()){
            Node cur = que.poll();

            for(int i=0;i<4;i++){
                int nx=cur.x+dir[i][0];
                int ny=cur.y+dir[i][1];

                if(nx<0 || nx>=N || ny<0 || ny>=M|| visit[nx][ny]) continue;

                if(map[nx][ny]==k){
                    visit[nx][ny]=true;
                    Node nNode = new Node(nx,ny);
                    nodeList.add(nNode);
                    que.add(nNode);
                }
            }
        }

        minValue=findMinValue(nodeList);


        if(minValue>k) {
            for (int i = 0; i < nodeList.size(); i++) {
                Node cur = nodeList.get(i);
                answer += (minValue - map[cur.x][cur.y]);
                map[cur.x][cur.y] = minValue;
            }
        }
    }


    public static int findMinValue(ArrayList<Node> list){
        int minValue=Integer.MAX_VALUE;

        for(int i=0;i<list.size();i++) {

            Node cur = list.get(i);

            for (int j = 0; j < 4; j++) {
                int nx = cur.x + dir[j][0];
                int ny = cur.y + dir[j][1];

                if (nx < 0 || nx >= N || ny < 0 || ny >= M)
                    return -1;

                if (!visit[nx][ny]) {
                    minValue = Math.min(minValue, map[nx][ny]);
                }
            }
        }

        return minValue;

    }

    public static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N=stoi(st.nextToken());
        M=stoi(st.nextToken());

        map=new int[N][M];


        for(int i=0;i<N;i++){
            String cur=br.readLine();
            for(int j=0;j<M;j++){
                map[i][j]=stoi(Character.toString(cur.charAt(j)));
            }
        }
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




```