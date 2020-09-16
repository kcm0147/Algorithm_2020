/*baekjoon_1725_히스토그램*/
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int[] d;
	static int n;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());
		d = new int[n];

		for (int i = 0; i < n; i++) {
			d[i] = Integer.parseInt(br.readLine());
		}
		// 초기 값 받기완료

		System.out.println(solve(0, n - 1));

	}

	public static int solve(int s, int e) {
		if (s == e)
			return d[s];

		int m = (s + e) / 2;

		int left = solve(s, m);
		int right = solve(m + 1, e);
		// 왼쪽부분에서의 값과 오른쪽 부분에서의 값을 각각 구함

		int lm = m - 1;
		int rm = m + 1;
		// 중간 점 기준 왼쪽, 오른쪽 점

		int mid = d[m];
		int height = d[m];
		int width = 1;
		int tmp = 0;

		while (s <= lm || rm <= e) {// 중간 기준에서 양쪽 끝까지 갈때까지 수행

			if (s <= lm && (rm > e || d[lm] >= d[rm])) {//rm>e 조건을 넣어야 d[rm]이 존재하는 경우만 검사한다
				if (d[lm] < height) {
					height = d[lm];
				}
				lm--;
				width++;
			} else {
				if (d[rm] < height) {
					height = d[rm];
				}
				rm++;
				width++;

			}
			tmp = width * height;
			mid = Math.max(mid, tmp);
		}

		return getMax(left, mid, right);
	}

	public static int getMax(int l, int m, int r) {
		int max = Math.max(l, m);
		return Math.max(max, r);
	}
}
