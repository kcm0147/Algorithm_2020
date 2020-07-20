/*baekjoon 17069, 17070 파이프 옮기기 1,2*/
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int[][] board;
	static int n;
	static boolean visited[][][];
	static long cnt[][][];
	static int horz = 0;
	static int vert = 1;
	static int diag = 2;
	
	static long total = 0;
	static int[] horzY = {0, 1};
	static int[] horzX = {1, 1};
	static int[] vertY = {1, 1};
	static int[] vertX = {0, 1};
	static int[] diagY = {0, 1, 1};
	static int[] diagX = {1, 0, 1};

	
	public static void main(String args[]) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		n = Integer.parseInt(s);
		
		board = new int[n+2][n+2];
		for(int i=0;i<n+2;i++) {
			Arrays.fill(board[i], 1);
		}
		
		visited = new boolean[n+2][n+2][3];
		
		for(int i=0;i<n+2;i++) {
			//Arrays.fill(visited[i], false);
			for(int j=0;j<n+2;j++)
				Arrays.fill(visited[i][j], false);
		}
		
		cnt = new long[n+2][n+2][3];
		
		for(int i=0;i<n+2;i++) {
			//Arrays.fill(visited[i], false);
			for(int j=0;j<n+2;j++)
				Arrays.fill(cnt[i][j], 0);
		}
		
		for(int i=1;i<=n;i++) {
			s = br.readLine();
			StringTokenizer st = new StringTokenizer(s," ");
			
			for(int j=1;j<=n;j++)
				board[i][j] = Integer.parseInt(st.nextToken());
			//board 다 받기
		}
		
		dfs(1, 2, horz);
		
		for(int i=0;i<3;i++)
			total+=cnt[1][2][i];
		
		System.out.println(total);
	}

	public static boolean isRight(int y, int x, int dir) {
		if(y>0 && x>0 && y<n+1 && x<n+1) {//for border
			if(dir == horz) {
				if(board[y][x]==0)
					return true;
			}else if(dir == vert){
				if(board[y][x]==0)
					return true;
			}else {//dir == diag
				if(board[y][x]==0 && board[y-1][x]==0 && board[y][x-1]==0)
					return true;
			}
		}
		return false;
	}
	
	public static void dfs(int y, int x, int dir) {
		visited[y][x][dir]= true;
		
		if(y==n && x==n) {
			cnt[y][x][dir]++;
			return;
		}
		
		int newY, newX;
		
		if(dir == horz) {
			//first step
			newY = y+horzY[0];
			newX = x+horzX[0];
			
			if(isRight(newY, newX, horz)){
				if(!visited[newY][newX][horz]) {
					dfs(newY, newX, horz);
				}
				cnt[y][x][dir]+=cnt[newY][newX][horz];
			}

			//second step
			newY = y+horzY[1];
			newX = x+horzX[1];
			
			if(isRight(newY, newX, diag)) {
				if(!visited[newY][newX][diag]) {
					dfs(newY, newX, diag);
				}
				cnt[y][x][dir]+=cnt[newY][newX][diag];
			}
		}
		
		else if(dir == vert) {
			//first step
			newY = y+vertY[0];
			newX = x+vertX[0];
			
			if(isRight(newY, newX, vert)){
				if(!visited[newY][newX][vert]) {
					dfs(newY, newX, vert);
				}
				cnt[y][x][dir]+=cnt[newY][newX][vert];
			}

			//second step
			newY = y+vertY[1];
			newX = x+vertX[1];
			
			if(isRight(newY, newX, diag)) {
				if(!visited[newY][newX][diag]) {
					dfs(newY, newX, diag);
				}
				cnt[y][x][dir]+=cnt[newY][newX][diag];
			}
		}
		
		else {//dir == diag
			//first step
			newY = y+diagY[0];
			newX = x+diagX[0];
			
			if(isRight(newY, newX, horz)){
				if(!visited[newY][newX][horz]) {
					dfs(newY, newX, horz);
				}
				cnt[y][x][dir]+=cnt[newY][newX][horz];
			}

			//second step
			newY = y+diagY[1];
			newX = x+diagX[1];
			
			if(isRight(newY, newX, vert)) {
				if(!visited[newY][newX][vert]) {
					dfs(newY, newX, vert);
				}
				cnt[y][x][dir]+=cnt[newY][newX][vert];
			}
			
			//third step
			newY = y+diagY[2];
			newX = x+diagX[2];
			
			if(isRight(newY, newX, diag)) {
				if(!visited[newY][newX][diag]) {
					dfs(newY, newX, diag);
				}
				cnt[y][x][dir]+=cnt[newY][newX][diag];
			}
		}
	}
}