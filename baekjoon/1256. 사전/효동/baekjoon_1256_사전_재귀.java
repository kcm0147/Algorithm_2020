/*baekjoon_1256_사전_재귀*/
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	static int[][] dp;
	static int a, z, k;
	static int max = 1000000001;
	static String s = "";
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");

		a = Integer.parseInt(str[0]);
		z = Integer.parseInt(str[1]);
		k = Integer.parseInt(str[2]);

		dp = new int[a+1][z+1];
		dp[0][0]=0;
		
		for(int i=1;i<=a;i++)
			dp[i][0]=1;
		for(int j=1;j<=z;j++)
			dp[0][j]=1;
		
		for(int i=1;i<=a;i++) {
			for(int j=1;j<=z;j++)
				dp[i][j]=Math.min(dp[i-1][j]+dp[i][j-1], max);//max이상의 값은 의미가 없기때문 + 너무 커지면 int형에 넣지 못함
		}
		
		if(dp[a][z]<k) {
			System.out.println(-1);
			return;
		}
		
		make(a, z, k-1);
		
		System.out.println(s);
		return;
	}
	
	public static void make(int a, int z, int jump) {
		//우선 a만 남는 경우 또는 z만 남는 경우부터 처리
		if(z==0) {
			while(a>0) {
				s+='a';
				a--;
			}
			return;
		}
		if(a==0) {
			while(z>0) {
				s+='z';
				z--;
			}
			return;
		}
		
		int combA = dp[a-1][z];//맨 앞에 a를 넣었을 때 조합의 수
		
		if(jump < combA) {
			s+='a';
			make(a-1, z, jump);
		}
		else {
			s+='z';
			make(a, z-1, jump-combA);
		}
	}
}