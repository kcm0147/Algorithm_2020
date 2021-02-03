import java.io.*;
import java.util.StringTokenizer;


public class Main {

    static int N;
    static int[] cost;
    static Node[][] DP;


    public static void main(String[] args) throws IOException {
        init();
    }

    public static void init() throws IOException {

        int tc;


        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        tc = Integer.parseInt(br.readLine());

        for (int i = 0; i < tc; i++) {
            N = Integer.parseInt(br.readLine());
            cost = new int[N+1];

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                cost[j] = Integer.parseInt(st.nextToken());
            }

            DP = new Node[N + 1][N + 1];

            for(int j=0;j<N+1;j++){
                for(int k=0;k<N+1;k++){
                    DP[j][k]=new Node(0,Integer.MAX_VALUE);
                }
            }

            for(int dia=0;dia<=N-1;dia++){
                Dp(dia);
            }
            System.out.println(DP[1][N].previous); // 전체 비용
        }
    }

    public static void Dp(int dia) {

        for (int row = 1; row <= N - dia; row++) {
            int col = row + dia;
            if (dia == 0) {
                DP[row][col].curSum = cost[row];
                DP[row][col].previous=0;
            }
            else if (dia == 1) {
                DP[row][col].curSum = cost[row] + cost[col];
                DP[row][col].previous=cost[row]+cost[col];
            }
            else{
                for(int i=0;i<dia;i++){
                    int weight=DP[row][row+i].curSum+DP[row+1+i][col].curSum+DP[row][row+i].previous+DP[row+1+i][col].previous;
                    if(DP[row][col].previous>weight){
                        DP[row][col].previous=weight;
                        DP[row][col].curSum=DP[row][row+i].curSum+DP[row+i+1][col].curSum;
                    }
                }
            }

        }
    }
}

class Node{
    int curSum; // 현재 파일의 비용합
    int previous; // 이파일을 생성할때까지의 전체 비용합

    Node(int curSum,int previous){
        this.curSum=curSum;
        this.previous=previous;
    }
}

