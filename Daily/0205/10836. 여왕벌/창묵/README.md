
```java

public class Main {

    static int size,date;
    static int[][] cur;
    static int[][] pre;
    static int[] cost;


    public static void main(String[] argv) throws IOException {

        init();



    }



    public static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        size=stoi(st.nextToken());
        date=stoi(st.nextToken());

        cur=new int[size][size];
        pre=new int[size][size];
        cost=new int[3];

        for(int i=0;i<size;i++) {
            Arrays.fill(cur[i], 1);
            Arrays.fill(pre[i],1);
        }


        for(int i=0;i<date;i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                cost[j] = stoi(st.nextToken());
            }
            calc();

        }

        print();


    }

    public static void print(){
        for(int i=size-1;i>=0;i--){
            for(int j=0;j<size;j++){
                if(j==0)
                    System.out.print(cur[i][j]+" ");
                else
                    System.out.print(cur[size-1][j]+" ");
            }
            System.out.println();
        }
    }





    public static void calc(){
        int row=0;
        int col=0;

        if(cost[0]>0){
            if(row+cost[0]>=size){
                int diff=row+cost[0]-size;
                row=size-1;
                col+=(diff+1);
            }
            else
                row+=cost[0];
        }

        for(int j=1;j<3;j++) {

            for (int i = 0; i < cost[j]; i++) {
                if (row<size-1) {
                    cur[row][col] += j;

                    if(row+1<=size-1)
                        row++;
                }
                else {
                    cur[row][col++] += j;
                }
            }
        }

    }


    public static int stoi(String input){
        return Integer.parseInt(input);
    }

}




```