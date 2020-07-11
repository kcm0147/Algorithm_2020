import java.io.IOException;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) throws IOException{
        final DiskTree root = new DiskTree();

        try(Scanner input = new Scanner(System.in)){
            int pathNum = input.nextInt();
            for(int i=0; i<pathNum; i++){
                String[] pathStrings = input.next().split("\\\\");
                addDir(root, pathStrings, 0);
            }
            root.printTree();
        }
    }

    private static void addDir(DiskTree tree, String[] s, int idx){
        if(idx >= s.length) return;
        DiskTree child = tree.putChildIfAbsent(s[idx]);
        addDir(child, s, idx+1);
    }
}

class DiskTree{
    private final Map<String, DiskTree> children = new TreeMap<String, DiskTree>();
    
    public DiskTree putChildIfAbsent(String childData){
        children.putIfAbsent(childData, new DiskTree());
        return children.get(childData);
    }

    public void printTree(){
        printChildren(0);
    }
    
    public DiskTree(){}

    private void printChildren(int depth){
        Set<Map.Entry<String, DiskTree>> sortedChildren = children.entrySet();
        for(Map.Entry<String, DiskTree> child : sortedChildren){
            for(int i=0; i<depth; i++) System.out.print(" ");
            System.out.println(child.getKey());
            child.getValue().printChildren(depth+1);
        }
    }
}