
public class Main {

    static String target;
    static String[] wordAry;
    static int[][] dp;
    static int N;


    static final int INF =10000000;


    public static void main(String[] argv) throws IOException {

        init();
        int answer=calc();

        if(answer>=INF)
            System.out.println(-1);
        else
            System.out.println(answer);


    }



    public static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        target=br.readLine();
        N=stoi(br.readLine());

        wordAry=new String[N+1];
        dp=new int[target.length()+1][target.length()+1];

        for(int i=1;i<=N;i++){
            wordAry[i]=br.readLine();
        }

        for(int i=0;i<target.length();i++)
            Arrays.fill(dp[i],INF);
    }

    public static int calc(){


        for(int length=0;length<target.length();length++){
            for(int left=0;left<target.length()-length;left++){
                int right=left+length;


                for(int j=1;j<=N;j++) {
                    String curWord = wordAry[j];
                    if (curWord.length()==right-left+1 && equal(curWord, left,right)) {
                        dp[left][right] = Math.min(dp[left][right],compare(curWord, left,right));
                    }
                }
                for(int k=left;k<=right-1;k++){
                    dp[left][right]=Math.min(dp[left][right],dp[left][k]+dp[k+1][right]);
                }
            }
        }



        return dp[0][target.length()-1];

    }

    public static boolean equal(String word,int startIndex,int endIndex){
        String sub=target.substring(startIndex,endIndex+1);
        int[] subAry = new int[26];
        int[] targetAry=new int[26];

        for(int i=0;i<sub.length();i++){
            subAry[sub.charAt(i)-'a']++;
            targetAry[word.charAt(i)-'a']++;
        }

        for(int i=0;i<26;i++){
            if(subAry[i]!=targetAry[i])
                return false;
        }

        return true;


    }

    public static int compare(String word,int startIndex,int endIndex){
        String sub=target.substring(startIndex,endIndex+1);
        int cnt=0;
        for(int i=0;i<word.length();i++){
            if(sub.charAt(i)!=word.charAt(i))
                cnt++;
        }

        return cnt;
    }




    public static int stoi(String input){
        return Integer.parseInt(input);
    }

}

