/*baekjoon_17090_미로탈출하기*/
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	static int n, m;
	static boolean[][] visited;
	static boolean[][] possible;
	static char[][] map;
	static int[] dy = { 0, -1, 0, 1 };
	static int[] dx = { 1, 0, -1, 0 };
	static int cnt = 0;
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] s = br.readLine().split(" ");
		n = Integer.parseInt(s[0]);
		m = Integer.parseInt(s[1]);

		map = new char[n][m];

		for (int i = 0; i < n; i++) {
			s[0] = br.readLine();
			for (int j = 0; j < m; j++) {
				map[i][j] = s[0].charAt(j);
			}
		}
		//지도 받기 완료
		visited = new boolean[n][m];
		possible = new boolean[n][m];
		
		for(int i=0;i<n;i++) {
			for(int j=0;j<m;j++) {
				if(!visited[i][j]){
					visited[i][j]=true;
					dfs(i,j);
				}
			}
		}
		
		System.out.println(cnt);
	}

	public static void dfs(int y, int x) {
		int ny, nx;
		
		if(map[y][x]=='R') {
			ny = y+dy[0];
			nx = x+dx[0];
		}
		
		else if(map[y][x]=='U') {
			ny = y+dy[1];
			nx = x+dx[1];
		}
		
		else if(map[y][x]=='L') {
			ny = y+dy[2];
			nx = x+dx[2];
		}
		
		else {
			ny = y+dy[3];
			nx = x+dx[3];
		}
		
		if(!isBorder(ny, nx)) {
			if(!visited[ny][nx]) {
				visited[ny][nx]=true;
				dfs(ny, nx);
				if(possible[ny][nx]) {
					possible[y][x]=true;
					cnt++;
				}
			}
			else {
				if(possible[ny][nx]) {
					possible[y][x]=true;
					cnt++;
					return;
				}
			}
		}
		else {
			visited[y][x]=true;
			possible[y][x]=true;
			cnt++;
			return;
		}
	}

	public static boolean isBorder(int y, int x) {
		if (y < 0 || y >= n || x < 0 || x >= m) {
			return true;
		}
		return false;
	}
}