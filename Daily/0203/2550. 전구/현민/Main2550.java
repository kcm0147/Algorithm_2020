import java.util.*;

public class Main2550 {
    public static void main(String[] args){
        Solution2550 solution = new Solution2550();
        solution.input();
        ArrayList<Integer> result = solution.solve();
        System.out.println(result.size());
        for(int i : result) System.out.print(i + " ");
    }
}

class Solution2550{
    private static int MAX_SIZE = 10000;

    private int[] lis = new int[MAX_SIZE+1];
    private int[] next = new int[MAX_SIZE+1];

    private int[] idxOfSwitch = new int[MAX_SIZE+1];
    private int[] numOf = new int[MAX_SIZE+1];
    private int size;

    void input(){
        try(Scanner input = new Scanner(System.in)){
            size = input.nextInt();

            for(int i=0; i<size; i++){
                idxOfSwitch[input.nextInt()] = i;
            }

            for(int i=0; i<size; i++){
                numOf[i] = input.nextInt();
            }
        }
    }

    private void calcLis(){
        for(int i=size-1; i>=0; i--){
            next[i] = i;
            lis[i] = 1;
            int idxOfSwitchI = idxOfSwitch[numOf[i]];
            for(int j=i+1; j<size; j++){
                int idxOfSwitchJ = idxOfSwitch[numOf[j]];

                if(idxOfSwitchI < idxOfSwitchJ && lis[i] < 1 + lis[j]){
                    lis[i] = 1 + lis[j];
                    next[i] = j;
                }
            }
        }
    }

    ArrayList<Integer> solve(){
        calcLis();
        int ret=0;
        int idx = 0;
        for(int i=0; i<size; i++){
            if(ret < lis[i]){
                ret = lis[i];
                idx = i;
            }
        }

        ArrayList<Integer> result =  new ArrayList<>();
        result.add(numOf[idx]);
        while(next[idx] != idx){
            idx = next[idx];
            result.add(numOf[idx]);
        }

        result.sort(Comparator.naturalOrder());
        return result;
    }
}
