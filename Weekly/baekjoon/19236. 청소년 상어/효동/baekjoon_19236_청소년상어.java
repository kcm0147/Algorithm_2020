/*baekjoon_19236_청소년상어*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int[] dy = { 0, -1, -1, 0, 1, 1, 1, 0, -1 };
	static int[] dx = { 0, 0, -1, -1, -1, 0, 1, 1, 1 };
	static int max = Integer.MIN_VALUE;
	static int[][] map = new int[4][4];
	static boolean[] dead = new boolean[17];
	static Fish[] fishes = new Fish[17];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] s;

		for (int i = 0; i < 4; i++) {
			s = br.readLine().split(" ");
			for (int j = 0; j < 8; j += 2) {
				int x = j / 2;
				int a = Integer.parseInt(s[j]);
				int b = Integer.parseInt(s[j + 1]);

				map[i][x] = a;
				fishes[a] = new Fish(i, x, b);
			}
		}

		int snum = map[0][0];
		dead[snum] = true;
		int sdir = fishes[snum].dir;
		map[0][0]=-1;
		eatFish(0, 0, sdir, snum);

		System.out.println(max);
	}

	public static void eatFish(int sy, int sx, int sdir, int sum) {		
		int[][] copyMap = new int[4][4];
		Fish[] copyFishes = new Fish[17];
		
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				copyMap[i][j] = map[i][j];
			}
		}
		for(int i=1;i<=16;i++) {
			copyFishes[i] = fishes[i];
		}
		
		moveFish(sy, sx);
		
		for(int i=1;i<=3;i++) {
			int ny = sy + dy[sdir]*i;
			int nx = sx + dx[sdir]*i;
			
			if(!isBorder(ny, nx) && !dead[map[ny][nx]]) {
				if(map[ny][nx]==0) continue;
				
				map[sy][sx]=0;
				int n = map[ny][nx];
				map[ny][nx] = -1;
				dead[n]=true;
				eatFish(ny, nx, fishes[n].dir, sum+n);
				dead[n]=false;
				map[ny][nx]=n;
				map[sy][sx]=-1;
			}
		}
		
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				map[i][j] = copyMap[i][j];
			}
		}
		for(int i=1;i<=16;i++) {
			fishes[i] = copyFishes[i];
		}
		
		max = Math.max(sum, max);
	}

	public static void moveFish(int sy, int sx) {
		for (int k = 1; k <= 16; k++) {
			if (!dead[k]) {
				boolean flag = false;
				int y = fishes[k].y;
				int x = fishes[k].x;
				
				for(int t=fishes[k].dir; t<=8;t++) {
					int ny = y + dy[t];
					int nx = x + dx[t];
					
					if(isBorder(ny, nx)) continue;
					if(map[ny][nx]==-1) continue;
					
					int temp = map[ny][nx];
					map[ny][nx] = map[y][x];
					map[y][x]=temp;
					
					fishes[k]=new Fish(ny, nx, t);
					
					if(temp!=0) {
						fishes[temp] = new Fish(y, x, fishes[temp].dir);
					}
					flag = true;
					break;
				}

				if(!flag) {
					for(int t=1; t<fishes[k].dir;t++) {
						int ny = y + dy[t];
						int nx = x + dx[t];
						
						if(isBorder(ny, nx)) continue;
						if(map[ny][nx]==-1) continue;
						
						int temp = map[ny][nx];
						map[ny][nx] = map[y][x];
						map[y][x]=temp;
						
						fishes[k]=new Fish(ny, nx, t);
						
						if(temp!=0) {
							fishes[temp] = new Fish(y, x, fishes[temp].dir);
						}
						break;
					}
				}

			}
		}
	}
	public static boolean isBorder(int y, int x) {
		if(y<0 || x<0 || y>3 || x>3) return true;
		return false;
	}

	public static class Fish {
		int y;
		int x;
		int dir;

		public Fish(int y, int x, int dir) {
			this.y = y;
			this.x = x;
			this.dir = dir;
		}
	}
}