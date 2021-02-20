
```java
public class Main {

    static List<Integer> result;
    static BufferedWriter wr=new BufferedWriter(new OutputStreamWriter(System.out));

    static int tc,num;


    public static void main(String[] args) throws IOException {

        init();


    }

    public static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        result=new ArrayList<>();


        tc=stoi(br.readLine());

        for(int i=0;i<tc;i++){
            num=stoi(br.readLine());

            if(calc()){
                print();
            }
            else{
                wr.write("IMPOSSIBLE\n");
            }
        }

        wr.flush();
        wr.close();



    }

    public static void print() throws IOException{
        wr.write(num+" = ");
        for(int i=0;i<result.size();i++){
            wr.write(Integer.toString(result.get(i)));
            if(i!=result.size()-1){
                wr.write(" + ");
            }
        }
        wr.newLine();
    }

    public static boolean calc(){
        if(num%2!=0 && num!=1){
            result=new ArrayList<>();
            result.add(num/2);
            result.add((num/2)+1);
            return true;
        }
        else{
            if(binary(num)){
                return true;
            }
        }

        return false;

    }

    public static boolean binary(int num){
        int sum=1+2;
        int nextAdd=3;
        int cnt=2;

        while(true)
        {
            if(sum>num){
                break;
            }
            
            if((num-sum)%cnt==0){
                result=new ArrayList<>();
                int add=(num-sum)/cnt;
                for(int i=1;i<=cnt;i++){
                    result.add(i+add);
                }
                return true;
            }

            sum+=nextAdd;
            cnt++;
            nextAdd++;

        }

       return false;

    }


    public static int stoi(String input){
        return Integer.parseInt(input);
    }

}
```
