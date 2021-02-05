import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Q10836 {
    static int m, n, worm[][];
    public static void main(String[] args) throws IOException {
        // init
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        worm = new int[m][m];
        for (int i = 0; i < m; i++) Arrays.fill(worm[i], 1);

        // calc
        int grow[] = new int[3], growAround[][] = new int[m][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++)
                grow[j] = Integer.parseInt(st.nextToken());
            fillGrowAround(growAround, grow);
        }
        solution(growAround);
    }

    private static void solution(int growArround[][]) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                if(i == 0 || j == 0)
                    bw.write((growArround[i][j] + 1) + " ");
                else
                    bw.write((growArround[0][j] + 1) + " ");
            }
            bw.newLine();
        }
        bw.close();
    }

    private static void fillGrowAround(int[][] growAround, int grow[]) {
        int row = m - 1, col = 0;
        for (int growth = 0; growth < 3; growth++) {
            int count = grow[growth], idx = 0;
            if(count == 0) continue;
            for (idx = 0; idx < count && row > 0; idx++)
                growAround[row--][0] += growth;
            for (int i = idx; i < count; i++)
                growAround[0][col++] += growth;
        }
    }
}