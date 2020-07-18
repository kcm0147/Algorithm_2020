import java.util.*;
import java.io.IOException;


public class Sol1967 {
    private static List<Tree> trees = new ArrayList<Tree>();
    public static void main(String[] args) throws IOException{
        try(Scanner in = new Scanner(System.in)){
            int nodeNum = in.nextInt();
            for(int i=0; i<nodeNum+1; i++) trees.add(new Tree());
            for(int i=0; i<nodeNum-1; i++){
                int parent = in.nextInt(), child = in.nextInt(), distance = in.nextInt();
                trees.get(parent).addChild(trees.get(child), distance);
            }
        }

        System.out.println(trees.get(1).radius());
    }
}

class Tree implements Comparable<Tree>{
    private List<Tree> children = new ArrayList<>();

    private int distToParent = -1; 
    private int cacheLongest = -1; // 자식들 중 가장 긴 트리들
    private int cacheRadius = -1; // 이 트리의 지름

    public int longestDistFromParent() { return longestDist() + distToParent; }

    int radius(){
        if(children.size() == 0) return 0;
        if(cacheRadius == -1){
            cacheRadius = 0;
            // 이 트리의 루트를 포함하지 않고 제일 긴 지름 찾기
            for(Tree child : children)
                cacheRadius = Integer.max(cacheRadius, child.radius());
            
            // 이 트리의 루트를 포함한 가장 긴 지름 찾기
            cacheRadius = Integer.max(cacheRadius, longestDist());
            if(children.size() >= 2){
                int longestWithRoot = children.get(0).longestDistFromParent() + children.get(1).longestDistFromParent();
                cacheRadius = Integer.max(cacheRadius, longestWithRoot);
            }
        }
        return cacheRadius;
    }

    private int longestDist(){
        if(children.size() == 0) return 0;
        if(cacheLongest == -1){
            Collections.sort(children);
            cacheLongest = children.get(0).longestDistFromParent();
        }
        return cacheLongest;
    }

    void addChild(Tree child, int distance) { 
        child.distToParent = distance;
        children.add(child);
    }

    @Override public int compareTo(Tree o) {
        return Comparator.comparingInt(Tree::longestDistFromParent).reversed().compare(this, (Tree)o);
    }
}