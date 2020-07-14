import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	static int n;
	static LinkedList<Node>[] list;
	static Queue<Node> q;
	static boolean[] visited;
	static int max_weight = -9999999;

	public static class Node {
		int vertex;
		int weight;

		public Node(int vertex, int weight) {
			this.vertex = vertex;
			this.weight = weight;
		}
	}

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());

		list = new LinkedList[n + 1];

		for (int i = 0; i < n + 1; i++)
			list[i] = new LinkedList<>();

		q = new LinkedList<>();

		visited = new boolean[n + 1];
		Arrays.fill(visited, false);
		//초기화
		
		for (int i = 0; i < n - 1; i++) {
			String s[] = br.readLine().split(" ");
			int a = Integer.parseInt(s[0]);
			int b = Integer.parseInt(s[1]);
			int w = Integer.parseInt(s[2]);

			list[a].add(new Node(b, w));
			list[b].add(new Node(a, w));

		} 
		// 그래프 생성완료

		int maxNode = bfs(1);
		//루트노드에서 가장 멀리있는(가중치 합이 가장 큰) 노드의 vertex알아내기
		
		Arrays.fill(visited, false);
		max_weight = -9999999;
		//다시 초기화
		
		bfs(maxNode);
		//루트로부터 가장 먼 거리의 노드에서 가장 먼 노드 찾기(정답)
		System.out.println(max_weight);
	}

	public static int bfs(int i) {
		int return_vertex=1;
		visited[i] = true;

		Node node = new Node(i, 0);
		// 첫번째 노드는 자기 번호랑 weight=0

		q.offer(node);

		while (!q.isEmpty()) {
			node = q.poll();
			int vertex = node.vertex;
			int weight = node.weight;

			if (weight > max_weight) {
				max_weight = weight;
				return_vertex = vertex;
			}
			
			for (int j = 0; j < list[vertex].size(); j++) {
				if (!visited[list[vertex].get(j).vertex]) {
					visited[list[vertex].get(j).vertex] = true;

					q.offer(new Node(list[vertex].get(j).vertex, weight + list[vertex].get(j).weight));
				}
			}
		}
		return return_vertex;
	}
}