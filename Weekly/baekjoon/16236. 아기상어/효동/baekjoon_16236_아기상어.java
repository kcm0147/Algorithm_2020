import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int[][] map;
	static boolean[][] visited;
	static int n;
	static int cnt = 0;
	static int depth = 0;
	static int size = 2;
	static int startY, startX;
	static int[] ny = { -1, 0, 0, 1 };
	static int[] nx = { 0, -1, 1, 0 };
	static int count = 0;
	static int fish_num = 0;

	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());

		map = new int[n + 2][n + 2];

		for (int i = 0; i < n + 2; i++) {
			Arrays.fill(map[i], 10);
		}

		for (int i = 1; i <= n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");

			for (int j = 1; j <= n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 9) {
					map[i][j] = 0;
					startY = i;
					startX = j;
				}
				if(map[i][j]>0)
					fish_num++;

			}
		} // map생성

		while(true) {
			
			Queue<Pos> q = new LinkedList<>();
			ArrayList<Pos> fishes = new ArrayList<Pos>();
			visited = new boolean[n+2][n+2];
			
			Pos pos = new Pos(startY, startX, 0);
			q.offer(pos);
			visited[startY][startX] = true;

			int found=-1;
			while (!q.isEmpty()) {
				pos = q.poll();
				int dst = pos.dst;
				
				if(found == pos.dst)//여기까지만 bfs탐색.
					break;
				
				for (int i = 0; i < 4; i++) {
					int newY = pos.y + ny[i];
					int newX = pos.x + nx[i];

					if (!isBorder(newY, newX) && CanMove(newY, newX)) {
						visited[newY][newX] = true;
						
						if(CanEatFish(newY, newX)) {
							found = dst + 1;
							fishes.add(new Pos(newY, newX, dst+1));
							count++;
						}
						q.offer(new Pos(newY, newX, dst+1));
					}
				}
			}
			
			if(found == -1) break;
			else {
				if(fishes.size()>1) {
					Collections.sort(fishes, new fishSort());
				}
			}
			
			Pos fish = fishes.get(0);
			depth += found;
			map[startY][startX]=0;
			startX = fish.x;
			startY = fish.y;
			cnt++;
			if(size == cnt) {
				size++;
				cnt=0;
			}
		}
		System.out.println(depth);
	}
	
	public static class Pos {
		int y;
		int x;
		int dst;

		public Pos(int y, int x, int dst) {
			this.y = y;
			this.x = x;
			this.dst = dst;
		}
	}
	
	static class fishSort implements Comparator<Pos>{
		//가장 윗쪽, 가장 왼쪽에 있는 물고기를 선별하기 위한 소팅
		public int compare(Pos f1, Pos f2) {
			if(f1.y<f2.y) {
				return -1;
			}else if(f1.y == f2.y) {
				if(f1.x<f2.x) {
					return -1;
				}else if(f1.x == f2.x) {
					return 0;
				}
				return 1;
			}else {
				return 1;
			}
		}
	}
	
	public static boolean isBorder(int y, int x) {
		if (y <= 0 || x <= 0 || y >= n + 1 || x >= n + 1)
			return true;
		return false;
	}

	public static boolean CanMove(int y, int x) {
		if (map[y][x] <= size && !visited[y][x])
			return true;
		return false;
	}
	
	public static boolean CanEatFish(int y, int x) {
		if (map[y][x] < size && map[y][x]>0)
			return true;
		return false;
	}
}