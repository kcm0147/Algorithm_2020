# 1062 가르침

## 조건

1. 남극의 모든 단어는 anta로 시작하고 tica로 끝난다.

2. 남극의 모든 단어는 8글자 이상 15글자 이하이다.

3. 학생들은 K개의 알파벳을 배울 수 있다.
   
4. K개의 알파벳의 조합으로 단어를 만들 수 있다면, 그 단어는 학생이 배울 수 있다.

## 구해야하는 것

* 학생들이 배울 수 있는 단어의 최대 갯수

## 아이디어

1. 맨 앞 4글자와 맨 뒤 4글자는 고정되어 있다.

2. 이로 무조건 5개의 알파벳을 배워야한다.

3. 따라서 학생들이 추가로 배울 수 있는 알파벳의 갯수는 K-5개가 된다.

4. 글자의 최대 길이는 15이며 8글자는 제외한 총 7가지 부분에서 서로 단어가 달라진다.

5. 즉, 만들어질 수 있는 단어의 갯수는 21 * 20 * 19 * 18 * 17 * 16 * 15 이다.

6. 이는 충분히 시간안에 만들 수 있는 조합이다.

7. 백트랙킹을 통해, 위의 조합을 만든 후 배울 수 있는 단어의 갯수를 카운트한다.

8. 만약 K-5가 7보다 작다면, K-5만큼 진행하면 된다.

9. 만약 K-5가 7보다 크면, 그 만큼 여유분이 생긴다.

## 코드

```java
package daily.day0227.boj1062;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static BufferedReader br;
    private static StringTokenizer st;

    private static int canUse;
    private static int answer = 0;
    private static boolean[][] words;

    public static void main(String[] args) throws IOException {
        int wordsSize = nextInt();
        canUse = nextInt() - 5;
        words = new boolean[wordsSize][26];

        for(int i=0; i<wordsSize; i++){
            Arrays.fill(words[i], false);
            String s = nextToken();
            for(char alpha : s.toCharArray()){
                words[i][alpha-'a'] = true;
            }
        }

        boolean[] visited = new boolean[26];
        Arrays.fill(visited,false);
        visited['a'-'a'] = visited['n'-'a'] = visited['t'-'a'] = visited['i'-'a'] = visited['c'-'a'] = true;

        dfs(1, visited, 0);
        System.out.println(answer);
    }

    private static void dfs(int nextAlpha, boolean[] visited, int used){
        if(used >= canUse){
            answer = Math.max(calcMax(visited, canUse-used), answer);
            return;
        }

        for(int i=nextAlpha; i<26; i++){
            if(visited[i]) continue;
            visited[i] = true;
            dfs(i+1, visited, used+1);
            visited[i] = false;
        }
    }

    private static int calcMax(boolean[] visited, int remain) {
        int ret=0;
        for(boolean[] word : words){
            int canMoreUsed = remain;
            for(int i=0; i<26; i++){
                if(!word[i]) continue;
                if(!visited[i]) canMoreUsed--;
            }
            if(canMoreUsed >= 0) ret++;
        }

        return ret;
    }

    private static int nextInt() throws IOException { return Integer.parseInt(nextToken()); }

    private static String nextToken() throws IOException {
        if(br == null) br = new BufferedReader(new InputStreamReader(System.in));
        if(st == null || !st.hasMoreTokens()) st = new StringTokenizer(br.readLine());
        return st.nextToken();
    }
}
```