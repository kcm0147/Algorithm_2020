import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static Node[] node;
	static int[] parent;
	static int n, q;

	static class Node implements Comparable<Node>{
		int x1;
		int x2;
		int y;
		int num;
		
		public Node(int x1, int x2, int y, int num) {
			this.x1 = x1;
			this.x2 = x2;
			this.y = y;
			this.num = num;
		}
		
		@Override
		public int compareTo(Node tmp) {
			return this.x1-tmp.x1;
		}
		//x1을 비교하여 소팅하기 위한 메소드
	}
	
	public static void main(String args[]) throws IOException {
		StringBuilder str = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		StringTokenizer st = new StringTokenizer(s," ");
		
		n = Integer.parseInt(st.nextToken());
		q = Integer.parseInt(st.nextToken());

		parent = new int[n + 1];
		node = new Node[n + 1];
		
		for (int i = 0; i < n + 1; i++)
			parent[i] = i;
		//집합을 나타내기 위한 parent 배열생성
		
		node[0] = new Node(-1, -1, -1, 0);
		
		for (int i = 0; i < n; i++) {
			s = br.readLine();
			st = new StringTokenizer(s," ");
			
			int x1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			node[i+1] = new Node(x1, x2, y, i+1);
		}
		
		Arrays.sort(node);
		
		for(int i=1;i<=n-1;i++) {
			for(int j=i+1;j<=n;j++) {
				if(node[j].x1<=node[i].x2)
					union(node[i].num, node[j].num);
				else
					j=n+1;
			}
		}
		
		for(int k=0;k<q;k++) {
			s = br.readLine();
			st = new StringTokenizer(s," ");
			
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			if(isSameParent(x, y))
				str.append("1\n");
			else
				str.append("0\n");
		}
		
		System.out.println(str);
	}
	
	public static void union(int x, int y) {
		x = find(x);
		y = find(y);
		
		if(x<y)
			parent[y]=x;
		else
			parent[x]=y;
	}

	public static int find(int x) {
		//사실은 find하면서 동시에 위로 올라가면서 parent를 동일하게 만들어버리는 압축함수의 역할도 함.
		if(parent[x]==x)
			return x;
		
		return parent[x] = find(parent[x]);
	}
	
	public static boolean isSameParent(int x, int y) {
		x = find(x);
		y = find(y);
		
		if(x==y)
			return true;
		else
			return false;
		}
}