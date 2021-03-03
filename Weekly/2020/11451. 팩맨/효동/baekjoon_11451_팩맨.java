/*baekjoon_11451_팩맨*/
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	static int n, m, t;
	static char map[][];
	static boolean[][][][] visited;
	static int[] dy = { 1, 0, -1, 0 };
	static int[] dx = { 0, 1, 0, -1 };
	static char[] d = { 'S', 'E', 'N', 'W'};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] s = br.readLine().split(" ");
		t = Integer.parseInt(s[0]);

		int ay = 0, ax = 0, by = 0, bx = 0;
		while (t > 0) {
			s = br.readLine().split(" ");
			n = Integer.parseInt(s[0]);
			m = Integer.parseInt(s[1]);
			map = new char[n][m];

			int pac = 0;
			for (int i = 0; i < n; i++) {
				s = br.readLine().split(" ");
				for (int j = 0; j < m; j++) {
					map[i][j] = s[0].charAt(j);
					if (map[i][j] == 'P' && pac == 0) {
						ay = i;
						ax = j;
						pac++;
					} else if (map[i][j] == 'P' && pac == 1) {
						by = i;
						bx = j;
					}
				}
			}//map에서 정보를 받는 부분

			System.out.println(bfs(ay, ax, by, bx));
			t--;
		}
	}

	public static String bfs(int ay, int ax, int by, int bx) {
		int cnt = 0;
		visited = new boolean[n][m][n][m];
		Queue<Info> q = new LinkedList<>();
		q.offer(new Info(ay, ax, by, bx, " "));

		while (!q.isEmpty()) {
			int len = q.size();
			for (int l = 0; l < len; l++) {
				Info p = q.poll();
				if (p.ay == p.by && p.ax == p.bx) {
					System.out.print(cnt);
					return p.dir;
				}
				for (int i = 0; i < 4; i++) {
					int nay = p.ay + dy[i];
					int nax = p.ax + dx[i];
					int nby = p.by + dy[i];
					int nbx = p.bx + dx[i];
					String ndir = p.dir + d[i];
					
					nay = correct(0, nay);
					nax = correct(1, nax);
					nby = correct(0, nby);
					nbx = correct(1, nbx);
					// 좌표 보정

					if (map[nay][nax] == 'G' || map[nby][nbx] == 'G') {
						continue;
					} // 다음 칸이 유령일 경우 절대 안되지

					if (map[nay][nax] == 'X') {
						nay = p.ay;
						nax = p.ax;
					}

					if (map[nby][nbx] == 'X') {
						nby = p.by;
						nbx = p.bx;
					}
					// 다음 칸이 벽일 경우 제자리 유지해야지

					if(!visited[nay][nax][nby][nbx]) {
						q.offer(new Info(nay, nax, nby, nbx, ndir));
						visited[nay][nax][nby][nbx]=true;
					}
				}
			}
			cnt++;
		}
		return "IMPOSSIBLE";
	}

	public static int correct(int type, int i) {
		if(type==0) {//y일 경우
			if (i < 0)i = n - 1;
			else if (i > n - 1)i = 0;
		}
			
		else {
			if (i < 0) i = m - 1;
			else if (i > m - 1) i = 0;
		}
		return i;
	}

	public static class Info {
		int ay;
		int ax;
		int by;
		int bx;
		String dir;

		public Info(int ay, int ax, int by, int bx, String dir) {
			this.ay = ay;
			this.ax = ax;
			this.by = by;
			this.bx = bx;
			this.dir = dir;
		}
	}
}