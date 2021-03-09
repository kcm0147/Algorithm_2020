package kakao_21.p3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] info = br.readLine().replaceAll("[^a-z0-9,]", " ").trim().split(" , ");
        String[] query = br.readLine().replaceAll("[^a-z0-9,-]", " ").trim().split(" , ");
        int[] result = new Solution().solution(info, query);
        for (int i : result) System.out.println(i);
    }
}

class Solution {
    public int[] solution(String[] info, String[] query) {
        Node root = new Node("root");
        for (String applyStr : info) {
            String[] apply = applyStr.trim().split(" ");
            root.addChildren(apply, 0);
        }
        root.sort();

        int[] answer = new int[query.length];
        for (int i = 0; i < query.length; i++) {
            String[] queryStr = query[i].replaceAll("and", " ").trim().split("[ ]+");
            answer[i] = root.query(queryStr, 0);
        }

        return answer;
    }

    private static class Node {
        private String info;
        private List<Node> children;
        private int[] scores;
        private int scoreSize=0;

        Node(String info) {
            this.info = info;
            children = new ArrayList<>();
        }

        void addChildren(String[] info, int idx) {
            if (info.length <= idx + 1) {
                if (scores == null) scores = new int[100001];
                scores[scoreSize++] = Integer.parseInt(info[idx]);
                return;
            }

            Node child = null;
            for (Node node : children) if (node.info.equals(info[idx])) child = node;
            if (child == null) {
                child = new Node(info[idx]);
                children.add(child);
            }
            child.addChildren(info, idx + 1);
        }

        void sort(){
            if(scores != null){
                Arrays.sort(scores, 0, scoreSize);
                return;
            }
            for(Node child : children) child.sort();
        }

        int query(String[] query, int idx) {
            if (idx + 1 >= query.length) {
                int underBound = Integer.parseInt(query[idx]);
                int left=0, right=scoreSize-1, mid = (left+right)/2;
                if(scores[scoreSize-1] < underBound) return 0;

                while(left <= right){
                    mid = (left+right)/2;
                    if(underBound == scores[mid]){
                        while(mid-1 >= 0 && scores[mid-1] >= underBound) mid--;
                        break;
                    }else if(scores[mid] > underBound)
                        right = mid-1;
                    else
                        left = mid+1;
                }

                return scores[mid] < underBound ? scoreSize - mid -1 : scoreSize - mid;
            }

            int ret = 0;
            for (Node child : children) {
                if (query[idx].equals("-") || child.info.equals(query[idx]))
                    ret += child.query(query, idx + 1);
            }
            return ret;
        }
    }
}