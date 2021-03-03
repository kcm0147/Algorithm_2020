/*baekjoon_15999_뒤집기*/
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int n, m;
	static long value = 0;
	static long total = 1;
	static boolean[][] board;
	
	public static void main(String args[]) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		String[] str = s.split(" ");
		
		n = Integer.parseInt(str[0]);
		m = Integer.parseInt(str[1]);
		
		board = new boolean[n][m];
		
		for(int i=0;i<n;i++) {
			s = br.readLine();
			
			for(int j=0;j<m;j++) {
				if(s.charAt(j)=='W')
					board[i][j]=true;
				else {
					board[i][j]=false;
				}
			}
		}
		//board 만들기
		
		for(int i=0;i<n;i++) {
			for(int j=0;j<m;j++) {
				if(isSame(i, j)) {
					value++;
				}
			}
		}

		for(int i=0;i<value;i++)
			total = ((total*2)%1000000007)%1000000007;
		System.out.println(total);
	}
	
	public static boolean isSame(int y, int x) {
		int[] nY = {0, -1, 0, 1};
		int[] nX = {1, 0, -1, 0};
		
		for(int i=0;i<4;i++) {
			int newY = y+nY[i];
			int newX = x+nX[i];
			
			if(newY>=0 && newX>=0 && newY<=n-1 && newX<=m-1) {
				if(board[newY][newX]!=board[y][x])
					return false;
			}
		}
		return true;
	}
}