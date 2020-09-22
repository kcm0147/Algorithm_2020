/*baekjoon_4056_스스스도쿠*/
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
	static int[][] map;
	static int n;
	static ArrayList<Pair> list = new ArrayList<>();
	static ArrayList<Pair> blist = new ArrayList<>();
	static boolean flag = false;

	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s;
		
		s=br.readLine();
		n = Integer.parseInt(s);
		map = new int[9][9];

		for(int k=0;k<n;k++) {
			blist.clear();
			list.clear();
			flag=false;
			for (int i = 0; i < 9; i++) {
				s = br.readLine();
				for (int j = 0; j < 9; j++) {
					map[i][j] = s.charAt(j)-'0';
					blist.add(new Pair(i,j));
					if (map[i][j] == 0) {
						list.add(new Pair(i, j));						
					}
				}
			}
			
			if(before()) {
				solve(0);
			}
			
			if(!flag) {
				System.out.println("Could not complete this grid.");
				System.out.println();
			}
		}
	}

	public static void solve(int cnt) {
		if (cnt == list.size()) {
			printMap();
			System.out.println();
			flag = true;
			return;
		}

		Pair p = list.get(cnt);
		for (int i = 1; i < 10; i++) {
			map[p.y][p.x] = i;
			if (check(p.y, p.x)) {
				solve(cnt + 1);
			}
			if (flag) return;
		}
		map[p.y][p.x]=0;
	}
	
	public static boolean before() {
		for(int i=0;i<blist.size();i++) {
			Pair p = blist.get(i);
			if(map[p.y][p.x]!=0) {
				if(!bcheck(p.y, p.x)) {
					return false;
				}
			}
		}		
		return true;
	}

	public static boolean bcheck(int y, int x) {
		int value = map[y][x];
		int i = 0;
		while (i <= 8) {
			if (i == x) {
				i++;
				continue;
			}
			if (map[y][i] == value)
				return false;
			else i++;
		}
		// 가로
		i = 0;
		while (i <= 8) {
			if (i == y) {
				i++;
				continue;				
			}
			if (map[i][x] == value)
				return false;
			else i++;
		}
		// 세로

		int sy = y - y % 3;
		int sx = x - x % 3;
		
		for (i = sy; i < sy + 3; i++) {
			for (int j = sx; j < sx + 3; j++) {
				if (i == y && j == x)
					continue;
				if (map[i][j] == value)
					return false;
			}
		}
		// 박스

		return true;
	}
	
	
	public static boolean check(int y, int x) {
		int value = map[y][x];
		int i = 0;
		while (i <= 8) {
			if (i == x) {
				i++;
				continue;
			}
			if (map[y][i] == value)
				return false;
			else i++;
		}
		// 가로
		i = 0;
		while (i <= 8) {
			if (i == y) {
				i++;
				continue;				
			}
			if (map[i][x] == value)
				return false;
			else i++;
		}
		// 세로

		int sy = y - y % 3;
		int sx = x - x % 3;
		
		for (i = sy; i < sy + 3; i++) {
			for (int j = sx; j < sx + 3; j++) {
				if (i == y && j == x)
					continue;
				if (map[i][j] == value)
					return false;
			}
		}
		// 박스

		return true;
	}

	public static void printMap() {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++)
				System.out.print(map[i][j]);
			System.out.println();
		}
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