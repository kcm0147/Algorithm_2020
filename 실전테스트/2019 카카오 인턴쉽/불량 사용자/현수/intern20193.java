import java.util.*;

public class intern20193 {
    public static void main(String[] args) {
        Solution sol = new Solution();
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), m =sc.nextInt();
        String user[] = new String[n], ban[] = new String[m];
        for (int i = 0; i < n; i++)
            user[i] = sc.next();
        for (int i = 0; i < m; i++)
            ban[i] = sc.next();
        System.out.println(sol.solution(user, ban));
    }
}
class Solution{
    public int solution(String[] user_id, String[] banned_id) {
        int n = user_id.length, m = banned_id.length, ans = 0;
        for (int num = (1 << n) - 1; num > 0 ; --num) {
            List<String> names = new ArrayList<>();
            for (int i = 0; (1 << i) <= num; i++) {
                if(((1 << i) & num) > 0)
                    names.add(user_id[i]);
            }
            if(names.size() != m) continue;

            boolean visited[] = new boolean[m];
            for (int j = 0; j < m; j++) {
                if(isMatched(names.get(0), banned_id[j])){
                    visited[j] = true;
                    if(count(1, names, banned_id, visited)) {
                        ans += 1;
                        break;
                    }
                    visited[j] = false;
                }
            }
        }
        return ans;
    }

    private boolean count(int num, List<String> users, String[] bans, boolean[] visited) {
        if(num >= bans.length)
            return true;

        String here = users.get(num);
        for (int i = 0; i < bans.length; i++) {
            if(visited[i]) continue;
            if(isMatched(here, bans[i])){
                visited[i] = true;
                if(count(num + 1, users, bans, visited))
                    return true;
                visited[i] = false;
            }
        }
        return false;
    }

    private boolean isMatched(String s, String line) {
        if(s.length() != line.length()) return false;
        for (int i = 0; i < line.length(); i++) {
            if(s.charAt(i) == line.charAt(i) || line.charAt(i) == '*') continue;
            return false;
        }
        return true;
    }
}
/*
5 2
frodo
fradi
crodo
abc123
frodoc
fr*d*
abc1**

5 3
frodo
fradi
crodo
abc123
frodoc
*rodo
*rodo
******


5 4
frodo
fradi
crodo
abc123
frodoc
fr*d*
*rodo
******
******
 */