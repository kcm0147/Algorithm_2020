import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	static int n;
	static int[] p;
	static int[] q;
	static int[] s;
	static int[] init;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] st = br.readLine().split(" ");
		n = Integer.parseInt(st[0]);

		p = new int[n];
		q = new int[n];
		s = new int[n];
		init = new int[n];

		st = br.readLine().split(" ");
		for (int i = 0; i < n; i++) {
			init[i] = p[i] = Integer.parseInt(st[i]);
		}
		st = br.readLine().split(" ");
		for (int i = 0; i < n; i++) {
			s[i] = Integer.parseInt(st[i]);
		}
		// input
		System.out.println(solve());
	}

	public static int solve() {
		int i, cnt=0;
		
		while(true) {
			for(i=0;i<n;i++) {
				if(p[i]==i%3) continue;
				break;
			}
			if(i==n) return cnt; 
			//동일한지 검사
			replace();
			cnt++;
			
			if(Arrays.equals(init, p))break;
		}
		return -1;
	}

	public static void replace() {
		for (int i = 0; i < n; i++) {
			q[s[i]] = p[i];
		}
		p = q.clone();
	}
}
