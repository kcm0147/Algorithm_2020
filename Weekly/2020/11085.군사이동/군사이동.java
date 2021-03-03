import java.io.*;
import java.util.*;

public class Main {

    static int[] root;
    static int[] length;
    static Node[] roadList;
    static int N,M;
    static int B,C;


    public static void main(String[] args) throws IOException {

        init();

    }

    public static void init() throws IOException {
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(read.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        root = new int[N];
        length = new int[N];
        roadList = new Node[M];


        for (int i = 0; i < N; i++) { // root set.
            root[i] = i;
        }

        st = new StringTokenizer(read.readLine());
        B = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(read.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int width = Integer.parseInt(st.nextToken());

            roadList[i] = new Node(start, end, width);
        }

        Arrays.sort(roadList,new Comparator<Node>(){
            @Override
            public int compare(Node n1, Node n2) {
                if (n1.width > n2.width)
                    return -1;
                else if(n1.width<n2.width)
                    return 1;
                else
                    return 0;

            }
        });

        for(int i=0;i<M;i++){
            union(roadList[i].start,roadList[i].end);

            if(find(B)==find(C)) {
                System.out.println(roadList[i].width);
                return;
            }
        }
    }



    public static void union(int a,int b){
        int aHead=find(a);
        int bHead=find(b);

        if(aHead!=bHead){
            if(length[aHead]>=length[bHead]){
                root[bHead]=aHead;
                length[aHead]++;
            }
            else{
                root[aHead]=bHead;
                length[bHead]++;
            }
        }
    }

    public static int find(int a){
        if(root[a]==a) return a;
        else
            return find(root[a]);
    }

}


class Node{
    int start;
    int end;
    int width;

    Node(int start,int end,int width){
        this.start=start;
        this.end=end;
        this.width=width;
    }
}


