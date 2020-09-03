/*baekjoon_2933_미네랄*/
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	static int n, m, k;
	static int[] arr;
	static boolean[][] visited;
	static int[] dy = { 0, -1, 0, 1 };
	static int[] dx = { 1, 0, -1, 0 };
	static char[][] map;
	static boolean flag = false;

	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] s = br.readLine().split(" ");
		n = Integer.parseInt(s[0]);
		m = Integer.parseInt(s[1]);

		map = new char[n][m];

		for (int i = 0; i < n; i++) {
			s = br.readLine().split(" ");
			for (int j = 0; j < m; j++) {
				map[i][j] = s[0].charAt(j);
			}
		}

		s = br.readLine().split(" ");
		k = Integer.parseInt(s[0]);

		arr = new int[k];
		s = br.readLine().split(" ");
		for (int i = 0; i < k; i++) {
			arr[i] = Integer.parseInt(s[i]);
		}
		// 입력 모두 받기

		for (int i = 0; i < k; i++) {
			Pair pair = remove(i);

			if (pair.y == -1 && pair.x == -1)
				continue;
			//아무것도 제거되지 않았을 경우 아무일도 일어나지 않으므로 다시 위로 돌아감
			
			else {
				solve(pair.y, pair.x);
			}
		}

		for (int i = 0; i < n; i++) {
				System.out.print(map[i]);
			System.out.println();
		}
	}

	public static void solve(int sy, int sx) {
		Queue<Pair> air = null;
		for (int i = 0; i < 4; i++) {
			int ny = sy + dy[i];
			int nx = sx + dx[i];

			if (!isBorder(ny, nx) && map[ny][nx] == 'x') {
				flag = false;
				visited = new boolean[n][m];
				air = bfs(ny, nx);
				if (flag == false) {
					break;
				}
			}
		} // 공중에 떠있는 cluster 발견 시 break
		if (flag == true)
			return;
		// 공중에 떠있는 cluster가 없을 경우 그냥 넘어가기

		boolean fall = true;
		while (fall) {
			for (Pair pair : air) {
				if(pair.y==n-1) return;
				
				int ny = pair.y + 1;
				int nx = pair.x;

				if (!isBorder(ny, nx) && !visited[ny][nx]) {
					if (map[ny][nx] == 'x') {
						fall = false;
						break;
					}
				}
			} // 떠있는 cluster의 좌표를 한번 다 돌면서 모두 한칸씩 밑으로 내려갈 수 있는지 확인

			if (fall) {
				for (Pair pair : air) {
					map[pair.y][pair.x]='.';
					pair.y++;
				}
			} // 확인해서 문제없을 시에만 한칸씩 모두 내림
			
			for (Pair pair : air) {
				map[pair.y][pair.x]='x';
				visited[pair.y][pair.x]=true;
			}
		}
		// 얼마나 내릴 수 있는지 확인하고 내리기
	}

	public static Queue<Pair> bfs(int sy, int sx) {
		Queue<Pair> airQ = new LinkedList<>();
		Queue<Pair> q = new LinkedList<>();
		q.offer(new Pair(sy, sx));
		airQ.offer(new Pair(sy, sx));
		visited[sy][sx] = true;

		while (!q.isEmpty()) {
			Pair p = q.poll();

			if (p.y == n - 1) {
				flag = true;
				return airQ;
			}

			for (int i = 0; i < 4; i++) {
				int ny = p.y + dy[i];
				int nx = p.x + dx[i];

				if (!isBorder(ny, nx)) {
					if (map[ny][nx] == 'x' && !visited[ny][nx]) {
						airQ.offer(new Pair(ny, nx));
						q.offer(new Pair(ny, nx));
						visited[ny][nx] = true;
					}
				}
			}
		}
		return airQ;
	}

	public static Pair remove(int num) {
		int row = n - arr[num];

		if (num % 2 == 0) {
			for (int i = 0; i < m; i++) {
				if (map[row][i] == 'x') {
					map[row][i] = '.';
					return new Pair(row, i);
				}
			}
		}

		else {
			for (int i = m - 1; i >= 0; i--) {
				if (map[row][i] == 'x') {
					map[row][i] = '.';
					return new Pair(row, i);
				}
			}
		}

		return new Pair(-1, -1);
	}

	public static boolean isBorder(int y, int x) {
		if (y < 0 || y >= n || x < 0 || x >= m) {
			return true;
		}
		return false;
	}

	public static class Pair {
		int y;
		int x;

		public Pair(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}

}