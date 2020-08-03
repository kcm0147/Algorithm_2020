/*baekjoon_4179_불*/
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	static char[][] map;
	static boolean[][] visited;
	static int n, m;
	static int jihunX, jihunY;
	static ArrayList<Pos> arr= new ArrayList<>();
	
	public static void main(String args[]) throws IOException {
		_init();
		solve();
	}
	
	public static void _init() throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
		
		n = Integer.parseInt(str[0]);
		m = Integer.parseInt(str[1]);
		map = new char[n][m];
		
		for(int i=0;i<n;i++) {
			String s = br.readLine();
			for(int j=0;j<m;j++) {
				map[i][j] = s.charAt(j);
				if(map[i][j]=='J') {
					jihunY = i;
					jihunX = j;
				}
				else if(map[i][j]=='F') {
					arr.add(new Pos(i, j, 0));
				}
			}
		}//map 초기값 get
	}
	
	public static void solve() {
		Queue<Pos> fires = new LinkedList<>();
		Queue<Pos> q = new LinkedList<>();
		visited = new boolean[n][m];
		int[] dy = {-1, 0, 1, 0};
		int[] dx = {0, -1, 0, 1};
		Pos fpos, jpos;
		int fcnt = 0, jcnt = 0;
		
		for(int i=0;i<arr.size();i++) {
			Pos spos = arr.get(i);
			fires.offer(spos);
			visited[spos.y][spos.x] = true;
		}//'F'값을 전부 fires큐에 넣어준다.
		
		q.offer(new Pos(jihunY, jihunX, 0));
		//'J'값을 큐에 넣어준다.
		
		while(true) {
			if(fires.isEmpty() && q.isEmpty()) {
				System.out.println("IMPOSSIBLE");
				return;
			}//fires큐와 q큐가 모두 비었으면 더이상 진행이 되지 않으므로 종료.
			
			while(!fires.isEmpty()){	
				if(fcnt != fires.peek().dst) {
					fcnt++;
					break;
				}//같은 dst를 가진 pos만 bfs돌리고 턴을 넘겨야하는게 핵심.

				fpos = fires.poll();
				int fy = fpos.y;
				int fx =  fpos.x;
				int fdst = fpos.dst;
				
				for(int i=0;i<4;i++) {
					int fny = fy+dy[i];
					int fnx = fx+dx[i];
					
					if(!isBorder(fny, fnx) && !isWall(fny, fnx) && !visited[fny][fnx]) {
							fires.offer(new Pos(fny, fnx, fdst+1));
							visited[fny][fnx] = true;
					}
				}	
			}//이 코드와 아래 코드는 거의 동일

			while(!q.isEmpty()) {
				if(jcnt != q.peek().dst) {
					jcnt++;
					break;
				}//역시나 같은 dst를 가진 pos만 bfs돌리고 턴을 넘겨야하는게 핵심.
				
				jpos = q.poll();
				int jy = jpos.y;
				int jx = jpos.x;
				int jdst = jpos.dst;
				
				for(int i=0;i<4;i++) {
					int jny = jy+dy[i];
					int jnx = jx+dx[i];
					
					if(isBorder(jny, jnx)) {
						System.out.println(jdst+1);
						return;
					}

					if(!isBorder(jny, jnx) && !isWall(jny, jnx) 
						&& !isFire(jny, jnx) && !visited[jny][jnx]) {
						q.offer(new Pos(jny, jnx, jdst+1));
						visited[jny][jnx] = true;
					}
				}
			}
		}
	}
	
	public static class Pos{
		int y;
		int x;
		int dst;
		
		public Pos(int y, int x, int dst) {
			this.y = y;
			this.x = x;
			this.dst = dst;
		}
	}
	
	public static boolean isBorder(int y, int x) {
		if(y<0 || x<0 || y>n-1 || x>m-1)
			return true;
		return false;
	}
	
	public static boolean isWall(int y, int x) {
		if(map[y][x]=='#')
			return true;
		return false;
	}
	
	public static boolean isFire(int y, int x) {
		if(map[y][x]=='F')
			return true;
		return false;
	}
	
	public static boolean isExit(int y, int x) {
		if(y==0 || x==0 || y==n-1 || x==m-1) {
			if(!isWall(y, x))
				return true;
		}
		return false;
	}
}