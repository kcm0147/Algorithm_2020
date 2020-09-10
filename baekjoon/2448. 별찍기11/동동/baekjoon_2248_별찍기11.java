/*baekjoon_2447_별찍기11*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	static int n;
	static char[][] map;
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String[] str = br.readLine().split(" ");

		n = Integer.parseInt(str[0]);
		
		map = new char[n][2*n-1];
		for(int i=0;i<n;i++) { 
			for(int j=0;j<2*n-1;j++)
				map[i][j]=' ';
		}
		//세로가 n일때 가로는 2n-1임을 알수있다.
		
		make(n, n, n-1);
		
		for(int i=0;i<n;i++) {
				bw.write(map[i]);
			bw.write('\n');
		}
		bw.flush();
		bw.close();
		br.close();
	}
	
	public static void make(int n, int y, int x) {
		if(n==3) {
			map[y-3][x]= map[y-2][x-1] = map[y-2][x+1] = '*';
			for(int i=x-2; i<=x+2;i++)
				map[y-1][i]='*';
			return;
		}

		make(n/2, y-(n/2), x);
		make(n/2, y, x-1-((n-1)/2));
		make(n/2, y, x+1+((n-1)/2));
	}
}