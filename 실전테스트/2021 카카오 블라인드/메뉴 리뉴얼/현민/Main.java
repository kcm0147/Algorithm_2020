package kakao_21.p2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] orders = br.readLine().replaceAll("[^A-Z,]", " ").trim().split(" , ");


        String[] number = br.readLine().replaceAll("[^0-9,]", " ").trim().split(",");
        int[] course = new int[number.length];
        for(int i=0; i<course.length; i++) course[i] = Integer.parseInt(number[i]);

        String[] answer = new Solution().solution(orders, course);
        for(String s : answer) System.out.println(s);
    }
}

class Solution {
    private List<String> answer = new ArrayList<>();
    private int maxCount;
    private int selectSize;

    public String[] solution(String[] orders, int[] course) {
        boolean[] visited = new boolean[26];
        for(boolean v : visited) v = false;

        for(int i=0; i<course.length; i++){
            maxCount = 2;
            selectSize = course[i];
            combination(orders, visited, 0, selectSize);
        }

        String[] ret = answer.toArray(new String[0]);
        Arrays.sort(ret);
        return  ret;
    }

    int check(boolean[] visited, String[] orders){
        int count=0;
        for(String order : orders){
            boolean canMake = true;
            for(int i=0; i<26; i++){
                if(!visited[i]) continue;
                if(!order.contains(Character.toString(i+'A'))){
                    canMake = false;
                    break;
                }
            }
            if(canMake) count++;
        }

        return count;
    }

    void combination(String[] orders, boolean[] visited, int start, int r){
        if(r == 0){
            int count = check(visited,orders);
            if(count > maxCount){
                answer.removeIf(s -> s.length() == selectSize);
                maxCount = count;
            }

            if(count >= maxCount){
                StringBuilder sb = new StringBuilder();
                for(int i=0; i<26; i++){
                    if(!visited[i]) continue;
                    sb.append((char)('A'+i));
                }
                answer.add(sb.toString());
            }
            return;
        }

        for(int i=start; i<26; i++){
            visited[i] = true;
            combination(orders, visited, i+1, r-1);
            visited[i] = false;
        }
    }
}
