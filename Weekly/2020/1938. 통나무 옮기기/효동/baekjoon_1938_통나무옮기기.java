import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int n;
	static int[][][] visited;
	static char[][] map;
	static Queue<Pos> q = new LinkedList<>();
	static int count = 0;
	static int y, x;
	static int b, e;
	static int horiz = 0;
	static int vert = 1;
	static Pos start, end;

	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		n = Integer.parseInt(st.nextToken());
		// 변수 n 받기 완료

		map = new char[n][n];
		visited = new int[n][n][2];

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++)
				Arrays.fill(visited[i][j], 0);
		}
		// map, visited 초기화

		for (int i = 0; i < n; i++) {
			String s[] = br.readLine().split("");

			for (int j = 0; j < n; j++) {
				map[i][j] = s[j].charAt(0);
				// System.out.println(map[i][j]);
				if (map[i][j] == 'B') {
					b++;
				} else if (map[i][j] == 'E')
					e++;

				if (b == 2) {
					b = -1;
					if (i - 1 >= 0 && map[i - 1][j] == 'B')
						start = new Pos(i, j, 0, vert);
					else
						start = new Pos(i, j, 0, horiz);
				}

				if (e == 2) {
					e = -1;
					if (i - 1 >= 0 && map[i - 1][j] == 'E')
						end = new Pos(i, j, 0, vert);
					else
						end = new Pos(i, j, 0, horiz);
				}
			}
		}
		// map 다받기

		System.out.println(bfs(start.y, start.x, start.shape));
	}

	public static int bfs(int y, int x, int shape) {
		int[] Y = { 0, -1, 0, 1 };
		int[] X = { 1, 0, -1, 0 };

		Pos pos = new Pos(y, x, 0, shape);

		q.offer(pos);
		visited[y][x][pos.shape] = 1;

		while (!q.isEmpty()) {
			pos = q.poll();
			y = pos.y;
			x = pos.x;
			//System.out.println(y + " " + x);

			if (pos.y == end.y && pos.x == end.x && pos.shape == end.shape)
				return pos.cnt;

			for (int i = 0; i < 4; i++) {
				int newY = y + Y[i];
				int newX = x + X[i];

				if (!isBorder2(newY, newX, pos.shape)) {
					// 우선 가능한지 검사
					if (map[newY][newX]!='1' && visited[newY][newX][pos.shape] == 0) {
						visited[newY][newX][pos.shape] = 1;
						if (pos.shape == vert) {
							if (map[newY - 1][newX] != '1' && map[newY + 1][newX] != '1') {
								q.offer(new Pos(newY, newX, pos.cnt + 1, pos.shape));
							}
						} else {
							if (map[newY][newX + 1] != '1' && map[newY][newX - 1] != '1') {
								q.offer(new Pos(newY, newX, pos.cnt + 1, pos.shape));
							}
						}
					}
				}
			}

			// 회전이 가능한지 검사
			if (isRotate(y, x, pos.shape)) {

				if (pos.shape == horiz) {
					if (visited[y][x][vert] == 0) {
						visited[y][x][vert] = 1;
						q.offer(new Pos(y, x, pos.cnt + 1, vert));
					}
				} else {
					if (visited[y][x][horiz] == 0) {
						visited[y][x][horiz] = 1;
						q.offer(new Pos(y, x, pos.cnt + 1, horiz));
					}
				}
			}
		}

		return 0;

	}

	public static boolean isBorder2(int newY, int newX, int shape) {
		if (shape == vert) {
			if (newY <= 0 || newX < 0 || newY >= n - 1 || newX > n - 1)
				return true;
		} else {
			if (newY < 0 || newX <= 0 || newY > n - 1 || newX >= n - 1)
				return true;
		}
		return false;
	}

	public static boolean isBorder(int newY, int newX) {

		if (newY <= 0 || newX <= 0 || newY >= n - 1 || newX >= n - 1)
			return true;

		return false;
	}

	public static boolean isRealBorder(int newY, int newX) {
		if (newY < 0 || newX < 0 || newY > n - 1 || newX > n - 1)
			return true;
		return false;
	}

	public static boolean isRotate(int y, int x, int shape) {
		if (!isBorder(y, x)) {
			if (shape == vert) {
				if (visited[y][x][horiz] == 1)
					return false;

				int[] dirY = { 0, -1, -1, 0, 1, 1 };
				int[] dirX = { 1, 1, -1, -1, -1, 1 };

				for (int k = 0; k < 6; k++) {
					int newY = y + dirY[k];
					int newX = x + dirX[k];

					if (!isRealBorder(newY, newX)) {
						if (map[newY][newX] == '1')
							return false;
					}
				}
			} else {
				if (visited[y][x][vert] == 1)
					return false;

				int[] dirY = { -1, -1, -1, 1, 1, 1 };
				int[] dirX = { 1, 0, -1, -1, -0, 1 };

				for (int k = 0; k < 6; k++) {
					int newY = y + dirY[k];
					int newX = x + dirX[k];

					if (!isBorder(newY, newX)) {
						if (map[newY][newX] == '1')
							return false;
					}
				}
			}

			return true;
		}

		return false;

	}

	public static class Pos {
		int y;
		int x;
		int shape;
		int cnt;

		Pos(int y, int x, int cnt, int shape) {
			this.y = y;
			this.x = x;
			this.shape = shape;
			this.cnt = cnt;
		}
	}
}