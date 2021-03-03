import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {


    static int row,col;
    static int[][] banana;
    static int[][] apple;
    static int[][] sum;

    public static void main(String[] args) throws IOException {
        init();
        Dp();
        System.out.println(sum[row-1][col-1]);

    }


    public static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        row=Integer.parseInt(st.nextToken());
        col=Integer.parseInt(st.nextToken());
        banana=new int[row][col];
        apple=new int[row][col];
        sum=new int[row][col];

        for(int i=0;i<row;i++){
            st=new StringTokenizer(br.readLine());
            for(int j=0;j<col;j++){
                String input=st.nextToken();

                if(input.charAt(0)=='A'){
                    apple[i][j]=Integer.parseInt(input.substring(1));
                }
                else{
                    banana[i][j]=Integer.parseInt(input.substring(1));
                }
            }
        }

    }

    public static void Dp(){

        for(int i=1;i<row;i++){
            for(int j=0;j<col;j++){
                banana[i][j]+=banana[i-1][j];
                apple[i][j]+=apple[i-1][j];
            }
        }

        sum[0][0]=apple[row-1][0]-apple[0][0];

        for(int i=1;i<col;i++){ // 0행 1
            sum[0][i]=apple[row-1][i]-apple[0][i]+sum[0][i-1];
        }
        for(int i=1;i<row;i++){ // i행 0열
            sum[i][0]=apple[row-1][0]-apple[i][0];
        }




        for(int i=1;i<row;i++){
            for(int j=1;j<col;j++){
                sum[i][j]=Math.max(sum[i][j],sum[i][j-1]+banana[i-1][j]+apple[row-1][j]-apple[i][j]);
                sum[i][j]=Math.max(sum[i][j],sum[i-1][j-1]+banana[i-1][j]+apple[row-1][j]-apple[i][j]);
                sum[i][j]=Math.max(sum[i][j],sum[i-1][j]-(apple[i][j]-apple[i-1][j]));
            }
        }



    }
}