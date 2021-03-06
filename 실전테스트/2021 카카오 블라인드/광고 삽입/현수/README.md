# 문제
- [카카오 2021 블라인드 공개 채용. 5번 광고 삽입](https://programmers.co.kr/learn/courses/30/lessons/72414)

## 코드

<details><summary> 코드 보기 </summary>

``` java
public class Solution {
    public static String solution(String play_time, String adv_time, String[] logs) {
        int ad[] = new int[360000];
        int N = strToSec(play_time); // 전체 구간을 초 단위로 환산.
        int len = strToSec(adv_time); // 광고 구간을 초 단위로 환산.
        for (String log : logs) { // 해당 구간에 시청자 수를 카운트
            String sp[] = log.split("-");
            int start = strToSec(sp[0]);
            int end = strToSec(sp[1]);
            for (int i = start; i < end; i++)
                ad[i] += 1;
        }
        long sum = 0, maxSum = 0;
        int idx = 0, lo = 0;

        for (int i = 0; i < len; i++)
            sum += ad[i];
        maxSum = sum;

        // 투 포인터 방식 : 가장 많은 시청자수 구간을 찾아서 그때의 시작시간을 저장
        for (int hi = len; hi < N; hi++) {
            sum += ad[hi];
            sum -= ad[lo++];
            if(sum > maxSum){
                maxSum = sum;
                idx = hi - len + 1;
            }
        }

        String answer = secToStr(idx);
        return answer;
    }

    private static String secToStr(int t) {
        return String.format("%02d:%02d:%02d", t/3600, (t/60) % 60, t%60);
    }

    private static int strToSec(String time) {
        String sp[] = time.split(":");
        int ret = Integer.parseInt(sp[0]) * 3600;
        ret += Integer.parseInt(sp[1]) * 60;
        ret += Integer.parseInt(sp[2]);
        return ret;
    }

    public static void main(String[] args) {
        String play_time1 = "02:03:55";
        String play_time2 = "99:59:59";
        String play_time3 = "50:00:00";

        String adv_time1 = "00:14:15";
        String adv_time2 = "25:00:00";
        String adv_time3 = "50:00:00";

        String log1[] = {"01:20:15-01:45:14", "00:40:31-01:00:00", "00:25:50-00:48:29", "01:30:59-01:53:29", "01:37:44-02:02:30"};
        String log2[] = {"69:59:59-89:59:59", "01:00:00-21:00:00", "79:59:59-99:59:59", "11:00:00-31:00:00"};
        String log3[] = {"15:36:51-38:21:49", "10:14:18-15:36:51", "38:21:49-42:51:45"};

        System.out.println(solution(play_time1, adv_time1, log1));
        System.out.println(solution(play_time2, adv_time2, log2));
        System.out.println(solution(play_time3, adv_time3, log3));
    }
}
```

</details>

## ⭐️느낀점⭐️
> 핵심은 시간 문자열을 초 단위로 환산해서 계산하는것이었다!! 시간 문자열 파싱 문제는 처음 풀어봐서 당황했다.

## 풀이 📣
<hr/>

1️⃣ 전체 플레이 타임과 광고 타임, 로그 정보의 시간 구간 모두 초 단위로 환산한다.

    - 전체 플레이 타임 만큼의 크기를 갖는 배열을 만든다.

    - 초단위로 환산한 로그 정보에 해당하는 구간에 해당하는 원소에 +1씩 해서 누적 시청자 수를 카운트해준다. 


2️⃣ 시작 지점부터 슬라이딩 윈도우 기법을 사용해서 고정된 광고 시간만큼을 이동하면서 구간 내에 가장 많은 누적 시청자 수를 찾는다.

    - 시작점과 끝점을 옮겨가며 누적 합을 구한다.

    - 만약 최대 누적합이 변동된다면, 최대값과 그 때의 시작시간을 갱신해준다.


3️⃣ 누적합이 최대가 될 때의 광고 시작 시간을 다시 시간 문자열 포맷으로 변환하여 출력한다.


<hr/>

## 실수 😅
- 시간 문자열을 초 단위로 환산해서 생각해야하는지 모르고 계속 그냥 문자열 자체를 비교하면서 풀려고했다.

- 처음에 접근한 방법대로하면 가능할 것 같긴 한데, 문자열로 계속해서 다루는거라 매우 비효율적이라 생각하고 풀이를 보았다. 