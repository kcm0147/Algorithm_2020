package week.w31.boj1800;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    private static BufferedReader br;
    private static StringTokenizer st;

    private static int N;
    private static int P;
    private static int K;
    private static int[][] adjMatrix;
    private static final int MAX_NUM = 1000000;

    public static void main(String[] args) {
        N = nextInt(); P=nextInt(); K=nextInt();
        adjMatrix = new int[N+1][N+1];
        for(int[] m : adjMatrix) Arrays.fill(m, -1);
        for(int edge=0; edge<P; edge++){
            int u = nextInt(), v = nextInt();
            adjMatrix[u][v] = adjMatrix[v][u] = nextInt();
        }
        System.out.println(binarySearch());
    }

    private static int binarySearch(){
        int left = 0, right = MAX_NUM;
        int answer = MAX_NUM+1;
        while(left <= right){
            int mid =(left+right)/2;
            if(canMake(mid)){
                answer = Math.min(answer, mid);
                right = mid-1;
            }else{
                left = mid+1;
            }
        }
        return answer > MAX_NUM ? -1 : answer;
    }

    // upperCost이하로 망을 설치할 수 있는
    private static boolean canMake(int upperCost){
        int[] distance = new int[N+1];
        Arrays.fill(distance, MAX_NUM+1);
        distance[1] = 0;

        PriorityQueue<Point> pq = new PriorityQueue<>((p1,p2) -> Integer.compare(p1.y, p2.y));
        pq.add(new Point(1, 0));
        while(!pq.isEmpty()){
            int u = pq.poll().x;

            for(int v=1; v<=N; v++){
                if(adjMatrix[u][v] == -1) continue; // 연결되어 있지 않다면 넘어가기
                int overflow = adjMatrix[u][v] <= upperCost ? 0 : 1;
                if(distance[v] > distance[u] + overflow){
                    distance[v] = distance[u] + overflow;
                    pq.add(new Point(v, distance[v]));
                }
            }
        }

        return distance[N] <= K ? true : false;
    }

    private static int nextInt() { return Integer.parseInt(nextToken()); }

    private static String nextToken(){
        if(br == null) br = new BufferedReader(new InputStreamReader(System.in));
        try {
            if (st == null || !st.hasMoreTokens())
                st = new StringTokenizer(br.readLine());
        }catch (IOException e){

        }
        return st.nextToken();
    }
}
