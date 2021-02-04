public class Main {

    static StringBuilder target;
    static int K;
    static Map<String,Boolean> visit;



    public static void main(String[] argv) throws IOException {

        init();
        System.out.println(calc());


    }

    public static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        target=new StringBuilder(st.nextToken());
        K=stoi(st.nextToken());
        visit = new HashMap<>();

    }

    public static int calc(){

        Queue<String> que = new LinkedList<>();
        int curK=-1;
        int maxValue=0;

        if(target.length()==1)
            return -1;
        else if(target.length()==2 && K>=1){
            if(target.charAt(target.length()-1)=='0')
                return -1;
        }

        que.add(target.toString());
        visit.put(target.toString(),true);


        while(!que.isEmpty()){
            int curSize=que.size();
            visit=new HashMap<>();

            if(++curK==K)
                break;


            for(int q=0;q<curSize;q++) {

                StringBuilder curString = new StringBuilder(que.poll());
                for (int i = 0; i < curString.length() - 1; i++) {

                    for (int j = i + 1; j < curString.length(); j++) {
                        if(i==0 && curString.charAt(j)=='0')
                            continue;

                        swap(i, j,curString);
                        if (!visit.containsKey(curString.toString())) {
                            if (curK == K-1 && maxValue < stoi(curString.toString())) {
                                maxValue = stoi(curString.toString());
                            }
                            que.add(curString.toString());
                            visit.put(curString.toString(), true);
                        }
                        swap(i, j,curString);
                    }
                }
            }
        }


        return maxValue;
    }

    public static void swap(int i,int j,StringBuilder cur){
        char front = cur.charAt(i);
        char back = cur.charAt(j);

        cur.replace(i,i+1,Character.toString(back));
        cur.replace(j,j+1,Character.toString(front));
    }



    public static int stoi(String input){
        return Integer.parseInt(input);
    }

}