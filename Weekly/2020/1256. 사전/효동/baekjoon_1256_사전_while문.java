/*baekjoon_1256_사전*/
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
		
		System.out.println(make());
	}
	
	public static String make() {
		if(dp[a][z]<k) return "-1";
		
		String s = "";
		
		while(a>0 && z>0) {
			if(dp[a-1][z]>=k) {//a를 맨앞에 넣고 나머지로 만들 수 있는 갯수보다 k가작거나 같은 경우로 판단
				a--;
				s+="a";
			}
			else {//그 반대의 경우에는 맨앞에 z를 넣는다고 생각(z를 넣는다는 것은 a를 넣었을 경우를 모두 제외시키고 생각해야하기때문에 빼기
				k-=dp[a-1][z];
				z--;
				s+="z";
			}
		}//a와 z가 둘다 남아있는 경우 무엇이 먼저 앞에오는지 dp값에 기반하여 결정
		
		//이후 남은 a와 z를 처리
		while(a>0) {
			a--;
			s+="a";
		}
		
		while(z>0) {
			z--;
			s+="z";
		}
		
		return s;
	}
}