package kakao_21.p4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    private static BufferedReader br;
    public static void main(String[] args) throws IOException {
        int n = nextInt(), s = nextInt(), a = nextInt(), b = nextInt();
        String[] number = br.readLine().replaceAll("[^0-9,]", " ").trim().split(" , ");

        int[][] fares = new int[number.length][3];
        int i=0;
        for(String numStr : number){
            String[] num = numStr.split(",");
            for(int j=0; j<3; j++)
                fares[i][j] = Integer.parseInt(num[j].trim());
            i++;
        }

        System.out.println(new Solution().solution(n,s,a,b,fares));
    }

    private static int nextInt() throws IOException {
        if(br == null) br = new BufferedReader(new InputStreamReader(System.in));
        return Integer.parseInt(br.readLine());
    }
}

class Solution {
    int[][] distance;

    public int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = 0;

        distance = new int[n+1][n+1];
        for(int[] d : distance) Arrays.fill(d, Integer.MAX_VALUE/10);
        for(int i=1; i<=n; i++) distance[i][i] = 0;
        for(int[] fare : fares){
            int u = fare[0], v = fare[1], dist = fare[2];
            distance[u][v] = distance[v][u] = dist;
        }

        calcDistance(n);

        answer = distance[s][a] + distance[s][b];
        for(int i=1; i<=n; i++){
            int newDist = distance[s][i] + distance[i][a] + distance[i][b];
            answer = Math.min(newDist, answer);
        }

        return answer;
    }

    private void calcDistance(int n){
        for(int k=1; k<=n; k++){
            for(int i=1; i<=n; i++){
                for(int j=1; j<=n; j++){
                    distance[i][j] = Math.min(distance[i][k] + distance[k][j], distance[i][j]);
                }
            }
        }
    }
}
