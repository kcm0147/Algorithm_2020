package week.w31.boj14464;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
    private static BufferedReader br;
    private static StringTokenizer st;

    private static int[] chicken;
    private static Point[] cows;
    private static int[] visited;

    public static void main(String[] args) {
        int chickenSize = nextInt(), cowSize = nextInt();
        chicken = new int[chickenSize];
        for(int i=0; i<chickenSize; i++) chicken[i] = nextInt();
        cows = new Point[cowSize];
        for(int i=0; i<cowSize; i++) cows[i] = new Point(nextInt(),nextInt());

        Arrays.sort(chicken);
        Arrays.sort(cows, Comparator.comparingDouble(Point::getY).thenComparingDouble(Point::getX));

        visited = new int[cowSize];
        Arrays.fill(visited,0);
        System.out.println(solve());
    }

    private static int solve(){
        for(int chick=0; chick<chicken.length; chick++){
            int time = chicken[chick];
            for(int cow=0; cow<cows.length; cow++){
                if(visited[cow] == 1) continue;
                if(cows[cow].x <= time && time <= cows[cow].y){
                    visited[cow] = 1;
                    break;
                }
            }
        }
        return Arrays.stream(visited).sum();
    }

    private static int nextInt() { return Integer.parseInt(nextToken()); }

    private static String nextToken(){
        if(br == null) br = new BufferedReader(new InputStreamReader(System.in));
        if(st == null || !st.hasMoreTokens()) {
            try {
                st = new StringTokenizer(br.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return st.nextToken();
    }
}
