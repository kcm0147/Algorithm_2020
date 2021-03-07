package kakao_21.p1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println(new Solution().solution(br.readLine()));
    }

}

class Solution {
    public String solution(String new_id) {
        String answer = new_id.toLowerCase();
        answer = answer.replaceAll("[^0-9a-z-_.]", "");
        answer = answer.replaceAll("[.]+", ".");

        answer = answer.charAt(0) != '.' ? answer : answer.length() == 1 ? "a" : answer.substring(1);
        answer = answer.charAt(answer.length()-1) != '.' ? answer : answer.length() == 1 ? "a" : answer.substring(0, answer.length()-1);

        answer = answer.length() == 0 ? "a" : answer;
        answer = answer.length() < 16 ? answer : answer.charAt(14) == '.' ? answer.substring(0,14) : answer.substring(0, 15);

        while(answer.length() < 3){
            answer += answer.charAt(answer.length()-1);
        }

        return answer;
    }
}