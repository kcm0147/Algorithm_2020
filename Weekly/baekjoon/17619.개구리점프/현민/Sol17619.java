import java.util.*;

public class Sol17619 {
    public static void main(String[] args){
        try(Scanner input = new Scanner(System.in)){
            int numOfTree = input.nextInt();
            int numOfQuestion = input.nextInt();

            List<Tree> trees = new ArrayList<>();
            for (int i = 0; i < numOfTree; i++) {
                trees.add(new Tree(input.nextInt(), input.nextInt(), input.nextInt(), i));
            }

            Solution sol = new Solution(trees);
            for (int i = 0; i < numOfQuestion; i++) {
                System.out.println(sol.canMove(input.nextInt() -1 , input.nextInt() -1));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}

class Solution{
    private List<Tree> trees;
    private int[] group;

    int canMove(int t1, int t2){
        return group[t1] == group[t2] ? 1 : 0;
    }

    private boolean canJump(int u, int v){
        return trees.get(u).x2 >= trees.get(v).x1;
    }

    private void grouping(){
        trees.sort(Tree::compareTo);

        group = new int[trees.size()];
        Arrays.fill(group, -1);

        int groupNum = 1;
        for(int u=0; u<trees.size(); u++){
            if(group[trees.get(u).index] == -1)
                group[trees.get(u).index] = groupNum++;
            for(int v=u+1; v<trees.size(); v++){
                if(!canJump(u, v)) break;
                if(group[trees.get(v).index] != -1) continue;
                group[trees.get(v).index] = group[trees.get(u).index];
            }
        }
    }

    Solution(List<Tree> trees) {
        this.trees = trees;
        grouping();
    }
}

class Tree implements Comparable<Tree>{
    final int x1;
    final int x2;
    final int y;
    final int index;

    Tree(int x1, int x2, int y, int index) { 
        this.x1=x1; this.x2=x2; this.y=y; this.index = index; 
    }

    @Override
    public int compareTo(Tree o) {
        return Comparator.comparingInt((Tree t) -> t.x1)
            .thenComparingInt((Tree t) -> t.y).compare(this, o);
    }
}