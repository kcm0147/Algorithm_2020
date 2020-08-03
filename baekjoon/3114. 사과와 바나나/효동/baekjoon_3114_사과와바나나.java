/*baekjoon_3114_사과와바나나*/
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int r, c;
	static int[][][] fruit;
	static int[][] D;
	static int[][] Sum;
	static int A = 0, B = 1;

	public static void main(String args[]) throws IOException {
		_init();
		System.out.println(solve());
	}

	public static int solve() {
		for(int j=1;j<c;j++) {
			for(int i=1;i<r;i++) {
				int right = D[i][j-1] + nextSum(i, j);
				int down = D[i-1][j] - fruit[i][j][A];
				int right_down = D[i-1][j-1] + nextSum(i, j);
				
				D[i][j] = getMax(right, down, right_down);
			}
		}	
		return D[r-1][c-1];
	}
	
	public static void _init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
		r = Integer.parseInt(str[0]);
		c = Integer.parseInt(str[1]);

		D = new int[r][c];
		Sum = new int[r][c];
		fruit = new int[r][c][2];
		
		for (int i = 0; i < r; i++) {
			str = br.readLine().split(" ");

			for (int j = 0; j < c; j++) {
				if (str[j].charAt(0) == 'A') {
					fruit[i][j][A] = Integer.parseInt(str[j].substring(1));
					if(i>0)
						Sum[0][j]+=fruit[i][j][A];
					//0번째 행 초기값 설정을 위해 data받을 때 밑에있는 모든 A의 합을 저장
				}
				else
					fruit[i][j][B] = Integer.parseInt(str[j].substring(1));
			}
		}
		//A와 B가 심겨진 갯수를 저장
		
		D[0][0] = Sum[0][0];
		for(int j=1;j<c;j++)
			D[0][j] = D[0][j-1]+Sum[0][j];
		for(int i=1;i<r;i++)
			D[i][0] = D[i-1][0]-fruit[i][0][A];
		//맨 윗쪽 행과 맨 왼쪽 열을 초기값으로 미리 채움
	}
	
	public static int nextSum(int y, int x) {
		return Sum[y][x] = Sum[y-1][x]-fruit[y][x][A]+fruit[y-1][x][B];
	}
	
	public static int getMax(int a, int b, int c) {
		int value = a;
		
		if(value<b) value = b;
		if(value<c) value = c;
		
		return value;
	}
}