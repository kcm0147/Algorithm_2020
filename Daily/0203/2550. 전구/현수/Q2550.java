import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Q2550 {
    static int n, swtIdx[], swtValue[], bulbValue[], bulb[], bulbOfSwitch[], lis[];
    public static void main(String[] args) throws IOException {
        init();
        solution();
    }

    private static void solution() {
        int trace[] = new int[n + 1];
        int dpIdx = 0;
        lis[dpIdx] = bulbOfSwitch[0];
        trace[0] = dpIdx;
        for (int i = 1; i < n; i++) {
            int target = bulbOfSwitch[i];
            if(lis[dpIdx] < target) {
                lis[++dpIdx] = target;
                trace[i] = dpIdx;
            }
            else{
                int pos = binSearch(0, dpIdx, target);
                lis[pos] = target;
                trace[i] = pos;
            }
        }
        List<Integer> ans = new ArrayList<>();
        for (int i = n - 1; i >= 0; --i) {
            if(trace[i] == dpIdx) {
                ans.add(swtValue[i]);
                dpIdx -= 1;
            }
        }

        System.out.println(ans.size());
        Collections.sort(ans);
        for (Integer an : ans)
            System.out.print(an + " ");
    }

    static int binSearch(int start, int end, int target){
        while(start < end){
            int mid=(start + end) / 2;
            if(target > lis[mid]) start = mid+1;
            else end = mid;
        }
        return end;
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        swtIdx = new int[n + 1];
        bulbOfSwitch = new int[n + 1];
        bulb = new int[n + 1];
        lis = new int[n + 1];
        swtValue = new int[n + 1];
        bulbValue = new int[n + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            int value = Integer.parseInt(st.nextToken());
            swtIdx[value] = i;
            swtValue[i] = value;
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++){
            int value = Integer.parseInt(st.nextToken());
            bulb[value] = i;
            bulbValue[i] = value;
        }
        for (int i = 0; i < n; i++) {// bulbOrder[i] 에는 i 번째 스위치와 매핑된 전구의 위치 저장
            bulbOfSwitch[i] = bulb[swtValue[i]];
        }
    }
}