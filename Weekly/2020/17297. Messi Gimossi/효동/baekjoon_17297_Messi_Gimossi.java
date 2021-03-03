/*baekjoon_17297_Messi_Gimossi*/

import java.util.*;

public class Main{
	static int max = 1073741824;
	static int[] d = new int[41];
	static int m;
	static String s = "Messi Gimossi";
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		m = sc.nextInt();

		getArr();
		solve();
	}
	
	public static void getArr() {
		int n = 3;
		d[1]=5;
		d[2]=13;
		
		while(d[n-1]<max) {
			d[n]=d[n-1]+1+d[n-2];
			n++;
		}
	}
	
	public static void solve() {
		int i=0;
		
		while(d[i]<m) {
			i++;
		}
		//m 바로 위의 d[i]를 찾는다
		
		while(i>2) {
			if(m==d[i-1]+1) {//중간(즉, 공백자리일때)
				m=0;
			}
			else if(m>d[i-1]+1) {
				m-=d[i-1]+1;
				i-=2;
			}
			else
				i--;
		}
		
		if(m==0 || m==6) {
			System.out.println("Messi Messi Gimossi");
		}
		else {
			System.out.println(s.charAt(m-1));
		}
	}
}