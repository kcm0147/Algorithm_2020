
```java

public class Main {


    static int N,K,answer;
    static boolean[] check;
    static List<Character> list;
    static String[] wordAry;
    static char[] preset={'a','n','t','i','c'};

    public static void main(String[] args) throws IOException {
        init();

        if(K<0){
            System.out.println(0);
        }
        else {
            dfs(0,0);
            System.out.println(answer);
        }
    }

    public static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N=stoi(st.nextToken());
        K=stoi(st.nextToken());

        list=new ArrayList<>();
        wordAry=new String[N];
        check=new boolean[26];


        for(int i=0;i<N;i++){
            wordAry[i]=br.readLine();
        }

        for(int i=0;i<5;i++){
            list.add(preset[i]);
            check[preset[i]-'a']=true;
        }
        K-=5;
    }


    public static void dfs(int index,int cnt){
        if(cnt==K){
            int returnValue=calc();
            answer=Math.max(returnValue,answer);
            return;
        }

        for(int i=index;i<26;i++){
            if(!check[i]){
                check[i]=true;
                dfs(i+1,cnt+1);
                check[i]=false;
            }
        }
    }

    public static int calc(){

        int j;
        int cnt=0;

        for(int i=0;i< wordAry.length;i++){

            for(j=4;j<wordAry[i].length()-3;j++){
                if(!check[wordAry[i].charAt(j)-'a']){
                    break;
                }
            }

            if(j==wordAry[i].length()-3){
                cnt++;
            }
        }

        return cnt;
    }


    public static int stoi(String input) {
        return Integer.parseInt(input);
    }
}






```