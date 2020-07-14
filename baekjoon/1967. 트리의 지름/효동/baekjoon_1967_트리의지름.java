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
		//�ʱ�ȭ
		
		for (int i = 0; i < n - 1; i++) {
			String s[] = br.readLine().split(" ");
			int a = Integer.parseInt(s[0]);
			int b = Integer.parseInt(s[1]);
			int w = Integer.parseInt(s[2]);

			list[a].add(new Node(b, w));
			list[b].add(new Node(a, w));

		} 
		// �׷��� �����Ϸ�

		int maxNode = bfs(1);
		//��Ʈ��忡�� ���� �ָ��ִ�(����ġ ���� ���� ū) ����� vertex�˾Ƴ���
		
		Arrays.fill(visited, false);
		max_weight = -9999999;
		//�ٽ� �ʱ�ȭ
		
		bfs(maxNode);
		//��Ʈ�κ��� ���� �� �Ÿ��� ��忡�� ���� �� ��� ã��(����)
		System.out.println(max_weight);
	}

	public static int bfs(int i) {
		int return_vertex=1;
		visited[i] = true;

		Node node = new Node(i, 0);
		// ù��° ���� �ڱ� ��ȣ�� weight=0

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