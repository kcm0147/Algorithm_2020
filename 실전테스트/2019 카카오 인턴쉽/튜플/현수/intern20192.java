/*
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class intern20192 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String line = sc.next();
        Solution sol = new Solution();
        System.out.println(sol.solution(line));
    }
}
class Solution {
    public int[] solution(String s) {
        int[] answer = {};
        boolean flag = false;
        List<String> ans = new ArrayList<>();
        List<List<String>> ansList = new ArrayList<>();
        List<String> temp = new ArrayList<>();

        for (int i = 1; i < s.length() - 1; i++) {
            char deli = s.charAt(i);
            if (deli == '{') {
                i += 1;
                int idx = 0;
                temp = new ArrayList<>();
                while (s.charAt(i + idx) != '}') {
                    if(s.charAt(i + idx) == ',') {
                        temp.add(s.substring(i, i + idx));
                        i = i + idx + 1;
                        idx = 0;
                    }
                    idx += 1;
                }
                temp.add(s.substring(i, i + idx));
                i = i + idx + 1;
                ansList.add(temp);
            }
        }
        flag = true;
        while(flag) {
            flag = false;
            String stand = "";
            for (List<String> integers : ansList) {
                if (integers.size() == 1) {
                    stand = integers.get(0);
                    ans.add(stand);
                    flag = true;
                    break;
                }
            }
            for (List<String> integers : ansList) {
                integers.remove(stand);
            }
        }
        answer = new int[ans.size()];
        for (int i = 0; i < ans.size(); i++) {
            answer[i] = Integer.parseInt(ans.get(i));
        }
        for (int i = 0; i < ans.size(); i++) {
            System.out.print(answer[i] + " ");
        }
        //System.out.println();
        return answer;

    }
}
*/
/*
{{2},{2,1},{2,1,3},{2,1,3,4}}

{{12},{12,113}}
 */
