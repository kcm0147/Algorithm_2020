import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Trie{
    Trie node[] = new Trie[26];
    List<String> line;

    public Trie() {
        for (int i = 0; i < 26; i++)
            this.node[i] = null;
        line = new ArrayList<>();
    }

    public void makeTrie(Queue<String> wordQ){
        if(wordQ.isEmpty()) return;
        String str = wordQ.poll();
        if(!line.contains(str))
            line.add(str);

        int idx = str.charAt(0) - 'A';
        if(node[idx] == null)
            node[idx] = new Trie();
        node[idx].makeTrie(wordQ);
    }
}

public class Q14725 {
    public static void main(String[] args) throws IOException {
        Trie trie = getTrie();
        printNode(trie, 0);
    }

    static Trie getTrie() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        Trie trie = new Trie();
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int count = Integer.parseInt(st.nextToken());
            Queue<String> q = new LinkedList<>();
            for (int j = 0; j < count; j++)
                q.add(st.nextToken());
            trie.makeTrie(q);
        }
        br.close();
        return trie;
    }

    static void printNode(Trie trieNode, int depth) {
        if(trieNode == null) return;
        Collections.sort(trieNode.line);
        for (int i = 0; i < trieNode.line.size(); i++) {
            String here = trieNode.line.get(i);
            for (int j = 0; j < depth; j++)
                System.out.print("--");
            System.out.println(here);
            printNode(trieNode.node[here.charAt(0) - 'A'], depth + 1);
        }
    }
}
