/*baekjoon_18808_스티커붙이기*/
import java.io.*;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Queue;

public class Main {
	static int n, m, k;
	static int rows, cols;
	static int[][] note;
	static int[][] sticker = new int[10][10];
	static int cnt;
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] s = br.readLine().split(" ");

		n = Integer.parseInt(s[0]);
		m = Integer.parseInt(s[1]);
		k = Integer.parseInt(s[2]);
		//n, m, k 받기
		note = new int[n][m];
		//notebook 만들기
		
		for (int i = 0; i < k; i++) {
			s = br.readLine().split(" ");
			rows = Integer.parseInt(s[0]);
			cols = Integer.parseInt(s[1]);

			for (int j = 0; j < rows; j++) {
				s = br.readLine().split(" ");
				for (int l = 0; l < cols; l++) {
					sticker[j][l] = Integer.parseInt(s[l]);
				}
			}
			// get sticker
			
			cnt=0;
			while(cnt<4) {
				boolean flag = solve(sticker, rows, cols);
				
				if(flag==true)break;
				else {
					rotate(sticker, rows, cols);
					//swap
					int temp = rows;
					rows = cols;
					cols = temp;
					cnt++;
				}
			}
		}
		
		//counts stickers on notebook
		cnt = 0;
		for(int i=0;i<n;i++) {
			for(int j=0;j<m;j++) {
				if(note[i][j]==1)cnt++;
			}
		}
		
		System.out.println(cnt);
	}

	public static boolean solve(int[][] sticker, int rows, int cols) {
		boolean flag = false;
		if(n>=rows && m>=cols) {
			flag = true;
			int nrStart, ncStart;
			for (nrStart = 0; nrStart < n; nrStart++) {
				for (ncStart = 0; ncStart < m; ncStart++) {
					// note돌기
					if(nrStart+rows>n || ncStart+cols>m) continue;
					
					flag = compare(sticker, ncStart, nrStart, rows, cols);
					if(flag == true) {
						put(sticker, ncStart, nrStart, rows, cols);
						return flag;
					}
				}
			}
		}
		return flag;
	}

	public static boolean compare(int[][] sticker, int ncStart, int nrStart, int rows, int cols) {
		int y = 0;
		int x = 0;
		for (int i = nrStart; i < nrStart + rows; i++) {
			for (int j = ncStart; j < ncStart + cols; j++) {
				if (sticker[y][x] == 1) {
					if (note[i][j] == 1)
						return false;
				}
				x++;
			}
			x=0;
			y++;
		}
		return true;
	}
	
	public static void put(int[][] sticker, int ncStart, int nrStart, int rows, int cols) {
		int x = 0;
		int y = 0;
		for(int i= nrStart;i<nrStart+rows;i++) {
			for(int j= ncStart;j<ncStart+cols;j++) {
				if(sticker[y][x]==1) {
					note[i][j]=1;
				}
				x++;
			}
			x=0;
			y++;
		}
	}
	
	public static void rotate(int[][] sticker, int rows, int cols) {
		int[][] temp = new int[10][10];
		
		for(int i=0;i<rows;i++) {
			for(int j=0;j<cols;j++) {
				temp[j][rows-i-1] = sticker[i][j];
			}
		}
		
		arrayCopy(sticker, temp);
	}
	
	public static void arrayCopy(int[][] arr, int[][] temp) {
		for(int i=0;i<10;i++) {
			arr[i]=temp[i].clone();
		}
	}
}