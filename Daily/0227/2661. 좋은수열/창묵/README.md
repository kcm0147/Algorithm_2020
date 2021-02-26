

```java

public class Main {


    static int N;
    static StringBuilder answer;

    public static void main(String[] args) throws IOException {

        init();
        dfs(0);
    }

    public static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N=Integer.parseInt(br.readLine());
        answer=new StringBuilder();

    }

    public static void dfs(int cnt) {
        if(cnt==N){
            System.out.println(answer.toString());
            System.exit(0);
        }
        for(int i=1;i<=3;i++){
            answer.append(i);
            if(check(cnt)){
                dfs(cnt+1);
            }
            answer.deleteCharAt(answer.length()-1);
        }
    }

    public static boolean check(int index){

        for(int cnt=1;cnt<=index;cnt++){
            String substring = answer.substring(index - cnt+1, index + 1);
            if(index-cnt-cnt+1>=0) {
                String other = answer.substring(index-cnt-cnt+1, index - cnt+1);
                if (substring.equals(other)) {
                    return false;
                }
            }
            else
                break;
        }

        return true;
    }
    
}



```