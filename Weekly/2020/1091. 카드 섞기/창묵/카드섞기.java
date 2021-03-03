import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[] S;
    static int[] P;
    static int[] card;

    public static void main(String args[]) throws IOException {

        init();
        System.out.println(calculate());
    }

    public static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N=Integer.parseInt(br.readLine());
        S=new int[N];
        P=new int[N];
        card=new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            P[i]=Integer.parseInt(st.nextToken());
            card[i]=i;
        }


        st=new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            S[i]=Integer.parseInt(st.nextToken());
        }
    }

    public static int calculate(){

        int cnt=0;
        while(true){
            if(giveCard()){
                return cnt;
            }

            if(suffle())
                cnt++;
            else
                return -1;
        }
    }

    public static boolean giveCard(){

        for(int i=0;i<N;i++){
            if(P[card[i]]!=i%3)
                return false;
        }
        return true;
    }

    public static boolean suffle(){

        int[] newcard = new int[N];

        for(int i=0;i<N;i++){
            newcard[S[i]]=card[i];
        }
        card=newcard;

        for(int i=0;i<N;i++){
            if(card[i]!=i)
                return true;
        }
        return false;

    }
}
