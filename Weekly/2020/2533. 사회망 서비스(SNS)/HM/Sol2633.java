import java.util.*;

public class Sol2633 {
    public static void main(String[] args){
        try(Scanner in = new Scanner(System.in)){
            int edgeNum = in.nextInt();
            Solution sol = Solution.createInstance(edgeNum);

            for(int i=0; i<edgeNum-1; i++){
                sol.addEdge(in.nextInt(), in.nextInt());
            }

            System.out.println(Integer.min(sol.minAdaptor(1, 0), sol.minAdaptor(1, 1)));
        }
    }
}

class Solution{
    private int[][] cache;
    private List<List<Integer>> children;
    private static Solution instance = null;
    
    private Solution(int edgeNum){
        cache = new int[edgeNum+1][2];
        for(int[] c : cache) Arrays.fill(c, -1);

        children = new ArrayList<>();
        for(int i=0; i<edgeNum+1; i++) children.add(new ArrayList<>());
    }

    int minAdaptor(int root, int isAdaptor){
        if(cache[root][isAdaptor] != -1) return cache[root][isAdaptor];

        int ret = isAdaptor;
        for(int child : children.get(root)){
            if(isAdaptor == 1) ret += Integer.min(minAdaptor(child, 0), minAdaptor(child, 1));
            else ret += minAdaptor(child, 1);
        }
        
        cache[root][isAdaptor] = ret;
        return ret;
    }

    void addEdge(int u, int v) { children.get(u).add(v); }

    static Solution createInstance(int edgeNum){
        if(instance == null)  
            instance = new Solution(edgeNum);
        return instance;
    }
}