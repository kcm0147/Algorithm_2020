/*baekjoon_2104_부분배열고르기*/
import java.io.*;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int n;
	static long[] a;
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] s = br.readLine().split(" ");
		
		n = Integer.parseInt(s[0]);
		
		a = new long[n+1];
		
		s = br.readLine().split(" ");
		for(int i=1;i<n+1;i++)
			a[i] = Long.parseLong(s[i-1]);
		
		getScore(1, n);

		System.out.println(getScore(1, n));
	}
	
	public static long getScore(int s, int e) {
		if(s==e) return a[s]*a[s];
		
		int mid = (s+e)/2;
		long max = Math.max(getScore(s, mid), getScore(mid+1, e));
		//mid기준 양쪽에서 나온 score 중 최댓값비교
		
		//여기부터는 중간부근에서 나온 최대 score비교위한 과정
		int left = mid;
		int right = mid+1;
		
		long sum = a[left]+a[right];
		long min = Math.min(a[left], a[right]);
		
		max = Math.max(max, sum*min);
		//맨 처음 초기값 만들고 비교
		
		while(s<left || right<e) {
			//왼쪽, 오른쪽 중 어느쪽을 선택할지 매우 중요!!!
			//무조건 왼쪽이든 오른쪽이든 끝까지 가보면서 max를 비교해보는 과정 필요
			if(s<left && (right==e || (a[right+1]<a[left-1]))) {//양쪽 비교시 어느쪽으로 갈때 더 이득인지 생각
				left--;
				sum+=a[left];
				min = Math.min(a[left], min);
			}
			else {
				right++;
				sum+=a[right];
				min = Math.min(a[right], min);
			}
			long tmp = min*sum;
			max = Math.max(tmp, max);
		}
		
		return max;
	}

}