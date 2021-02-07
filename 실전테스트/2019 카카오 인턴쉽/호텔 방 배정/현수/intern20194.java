import java.util.*;
public class intern20194 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long k = sc.nextLong(), room_number[] = new long[200000];
        int m = sc.nextInt();
        for (int i = 0; i < m; i++)
            room_number[i] = sc.nextLong();

        Solution solution = new Solution();
        long[] answer = solution.solution(k, room_number);
        for (long l : answer) {
            System.out.println(l);
        }
    }
}
class Solution {
    static long k, room_number[];
    static Map<Long, Long> parent = new HashMap<>();
    public long[] solution(long kk, long[] room_numberr) {
        k = kk;
        room_number = room_numberr;
        long[] answer = {};
        List<Long> ansList = new ArrayList<>();
        for (int i = 0; i < room_number.length; i++) {
            long pos = makeUnion(room_number[i]);
            ansList.add(pos);
        }
        int idx = 0;
        answer = new long[ansList.size()];
        for (long aLong : ansList)
            answer[idx++] = aLong - 1;
        return answer;
    }
    long makeUnion(long num){
        if(!parent.containsKey(num)) {
            parent.put(num, num + 1);
            return num + 1;
        }
        long p = makeUnion(parent.get(num));
        parent.put(num, p);
        return p;
    }
}
/*
10 6
1 3 4 1 3 1
 */