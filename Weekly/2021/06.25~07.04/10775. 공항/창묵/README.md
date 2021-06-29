

```java

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int G,P,result;

    static int[] root;

    public static void main(String[] args) throws IOException{
        init();
        System.out.println(result);
    }


    public static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        G=stoi(br.readLine());
        P=stoi(br.readLine());

        root=new int[G+1];
        for(int i=0;i<=G;i++){
            root[i]=i;
        }

        for(int i=0;i<P;i++){
            int gi=stoi(br.readLine());
            int gRoot = find(gi);

            if(gRoot==0) return;
            union(gRoot,gRoot-1);
            result++;
        }

    }

    public static int find(int index){
        if(root[index]==index){
            return index;
        }
        else{
            return root[index]=find(root[index]);
        }
    }

    public static void union(int index1,int index2){
        int rootIndex1=find(index1);
        int rootIndex2=find(index2);

        if(rootIndex1<rootIndex2){
            root[rootIndex2]=rootIndex1;
        }
        else
            root[rootIndex1]=rootIndex2;

    }

    public static int stoi(String input){
        return Integer.parseInt(input);
    }


}

```