import java.io.*;
import java.util.*;

public class Main{

    static int tc,hole;
    static int[][] map;
    static ArrayList<Node> holelist;


    public static void main(String[] args) throws IOException{

        init();

    }

    public static void init() throws IOException {
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));

        map = new int[9][9];
        holelist=new ArrayList<>();

            for(int row=0;row<9;row++){ // make
                String temp = read.readLine();
                for(int col=0;col<9;col++){
                    map[row][col]=temp.charAt(col)-'0';
                    if(map[row][col]==0) {
                        holelist.add(new Node(row,col));
                    }
                }
            }

            if(find(0)){
                printMatrix();
            }

    }

    public static boolean checkVali(){

        boolean[] visit=new boolean[10];

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (visit[map[i][j]]&& map[i][j]!=0) return false;
                visit[map[i][j]] = true;
            }
        }

        Arrays.fill(visit,false);

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (visit[map[j][i]] && map[i][j]!=0) return false;
                visit[map[j][i]] = true;
            }
        }

        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++)
                    for (int l = 0; l < 3; l++) {
                        int index = map[i * 3 + k][j * 3 + l];
                        if (visit[index]&& index!=0) return false;
                        visit[index] = true;
                    }
            }
        return true;
    }

    public static void printMatrix() throws IOException {
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

        for(int row=0;row<9;row++){
            for(int col=0;col<9;col++) {
                out.write(String.valueOf(map[row][col]));
            }
            out.newLine();
            out.flush();
        }
    }

    public static boolean find(int cnt){

        if(cnt==holelist.size()){
            return true;
        }
        for(int i=1;i<10;i++){
            Node temp=holelist.get(cnt);
            if(check(temp.x,temp.y,i)){
                map[temp.x][temp.y]=i;
                if(find(cnt+1)){
                    return true;
                }
            }
            map[temp.x][temp.y]=0;
        }

        return false;

    }


    public static boolean check(int x,int y,int val){ // check

       boolean[] visit=new boolean[10];

        for(int row=0;row<9;row++){
            if(map[row][y]==val) return false;
        }

        Arrays.fill(visit,false);

        for(int col=0;col<9;col++){
            if(map[x][col]==val) return false;
        }

        Arrays.fill(visit,false);
        int startX=(x/3)*3;
        int startY=(y/3)*3;

        for(int row=startX;row<startX+3;row++){

            for(int col=startY;col<startY+3;col++){
                if(map[row][col]==val) return false;
            }
        }

        return true;
    }


}


class Node{
    int x;
    int y;

    Node(int x,int y){
        this.x=x;
        this.y=y;
    }
}