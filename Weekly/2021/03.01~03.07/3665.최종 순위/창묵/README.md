```java
public class Main {

    static int tc,n,m;
    static boolean[][] edge;
    static int[] linkCnt;

    static Queue<Integer> que;
    static List<Integer> answer;



    public static void main(String[] args) throws IOException {

        init();


    }


    public static void init() throws IOException{
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        tc=stoi(st.nextToken());

        for(int k=0;k<tc;k++) {
            n = stoi(br.readLine());
            edge = new boolean[n + 1][n + 1];
            linkCnt = new int[n + 1];
            que=new LinkedList<>();
            answer=new ArrayList<>();
            ArrayList<Integer> inputList = new ArrayList<>();


            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                int input = stoi(st.nextToken());
                for (int j : inputList) {
                    edge[input][j] = true;
                    linkCnt[j]++;
                }
                inputList.add(input);
            }

            m=stoi(br.readLine());

            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int index = stoi(st.nextToken());
                int index2 = stoi(st.nextToken());

                if (edge[index][index2]) {
                    edge[index][index2] = false;
                    edge[index2][index] = true;
                    --linkCnt[index2];
                    ++linkCnt[index];
                }
                else if(edge[index2][index]){
                    edge[index2][index] = false;
                    edge[index][index2] = true;
                    --linkCnt[index];
                    ++linkCnt[index2];
                }

            }

            for(int i=1;i<=n;i++){
                if(linkCnt[i]==0){
                    que.add(i);
                }
            }

            calc();

        }

    }



    public static void calc() {

        boolean isVague =false;

        while(!que.isEmpty()) {
            if (que.size() > 1){
                isVague = true;
                break;
            }
            int cur = que.poll();
            answer.add(cur);

            for (int next = 1; next <= n; next++){
                if (!edge[cur][next]) continue;
                linkCnt[next] -= 1;
                if (linkCnt[next]==0){
                    que.add(next);
                }
            }
        }

        if(isVague){
            System.out.println("?");
            return;
        }

        if(answer.size()!=n){
            System.out.println("IMPOSSIBLE");
            return;
        }



       for(int i=answer.size()-1;i>=0;i--){
           System.out.print(answer.get(i)+" ");
       }
        System.out.println();

    }



    public static int stoi(String input){
        return Integer.parseInt(input);
    }
}



```