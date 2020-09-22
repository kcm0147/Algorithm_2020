import java.io.*;
import java.util.*;

public class Main {

    static int[] Dp;
    static int N;


    public static void main(String[] args) throws IOException {

        init();

    }

    public static void init() throws IOException {
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
        String st = "Messi Gimossi Messi";
        Dp=new int[1000];
        N=Integer.parseInt(read.readLine());

        Dp[0]=0; Dp[1]=5; Dp[2]=13;
        int index=3;

        while(true){
            if(Dp[index-1]+Dp[index-2]>0){
                Dp[index]=Dp[index-1]+Dp[index-2]+1;
                index++;
            }
            else
                break;
        }

        for(int i=index;i>=3;i--){
            if(N>=Dp[i]){
                N-=(Dp[i]+1);
            }
        }

        if(N==-1 || N==5 || N==13 ) System.out.println("Messi Messi Gimossi");
        else
            System.out.println(st.charAt(N));


    }

}


