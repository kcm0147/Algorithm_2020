# 추석 트래픽 [programmers]

### 작성자 : O-star

<br/>

<br/>

### 풀이 : 

쉽지 않은 문제이다. 구현양도 많을 뿐더러 처음부터 문자열은 어떻게 처리해서 시간단위로 변환할 것이고, 각 트래픽이 해당 1초 구간에 들어오는지 판별하기 위해 각 구간별 포지션 경우의 수를 잘 따져주어야 한다.

![image](https://user-images.githubusercontent.com/57346455/129830170-d429ac65-9316-496b-9da7-efc7a56f97d1.png)

해당 1초 구간 내에 들어오는 트래픽 막대인 경우는 다음과 같이 3가지 경우의 수가 존재한다. 따라서 해당 3가지 경우의 수 중 하나라도 만족하는 막대만을 카운트해야 한다.

<br/>

#### **[ 세부 구현사항 ]**

1. 매개변수로 넘어오는 문자열 벡터 'lines'에서 구조체 TimeInfo 형으로 변환된 벡터인 `vector<TimeInfo> timeLines ` 를 만들어 주는 과정을 첫 번째로 수행한다.
2. 각 타임라인 별 시작 시간, 종료 시간을 모두 담은 오름차순 pq를 생성한다. => 종료시간은 기존 timelinse 정보를 사용하고 시작시간은 calculStartTime 함수를 활용하여 계산해준다.
3. pq가 빌 때까지 loop를 돌면서 해당 시간에서 1초 구간(pq에 들어있는 시간부터 앞으로 1초 간의 구간, 시작 시간, 종료 시간이 다 포함되어 있기 때문에 각 타임라인이 걸쳐 있는 최대 갯수 구간을 파악할 수 있음)에 포함되는 최대 갯수를 파악할 수 있다.
4. 각 구간에 포함된 타임라인 막대를 판단하는 경우에는 위 그림의 3가지 경우의 수 중 하나라도 포함되는지 판단한다.

<br/>

<br/>

### 코드 : 

```c++
#include <string>
#include <vector>
#include <queue>
#include <algorithm>

using namespace std;

struct TimeInfo {
    int hour, minute;
    double second;
};

struct compare {
    bool operator()(TimeInfo a, TimeInfo b) {
        if (a.hour == b.hour) {
            if (a.minute == b.minute)
                return a.second > b.second;
            return a.minute > b.minute;
        }
        return a.hour > b.hour;
    }
};

TimeInfo stringToTimeInfo(string str) {
    return TimeInfo{
            stoi(str.substr(11, 2)),
            stoi(str.substr(14, 2)),
            stod(str.substr(17, 6)),
    };
}

double spliceProcessTime(string str) {
    string processStr = "";
    for (int k = 24;; k++) {
        if (str[k] == 's') break;
        processStr += str[k];
    }
    return stod(processStr);
}

TimeInfo calculStartTime(TimeInfo endInfo, double processTime) {
    TimeInfo startInfo = {endInfo.hour, endInfo.minute, endInfo.second};
    if ((startInfo.second -= processTime - 0.001) < 0) {
        startInfo.second += 60;
        if ((startInfo.minute -= 1) < 0) {
            startInfo.minute += 60;
            if ((startInfo.hour -= 1) < 0) return TimeInfo{0, 0, 0};
        }
    }

    return startInfo;
}

bool isGreaterEqualTime(TimeInfo a, TimeInfo b) {
    if (a.hour == b.hour) {
        if (a.minute == b.minute) return a.second >= b.second;
        return a.minute > b.minute;
    }
    return a.hour > b.hour;
}

bool isGreaterTime(TimeInfo a, TimeInfo b) {
    if (a.hour == b.hour) {
        if (a.minute == b.minute) return a.second > b.second;
        return a.minute > b.minute;
    }
    return a.hour > b.hour;
}

int solution(vector<string> lines) {
    priority_queue<TimeInfo, vector<TimeInfo>, compare> pq;
    vector<TimeInfo> timeLines, startTimeLines;
    int answer = 0;

    for (const string &str : lines)
        timeLines.push_back(stringToTimeInfo(str));

    for (int k = 0; k < lines.size(); k++) {
        double processTime = spliceProcessTime(lines[k]);
        TimeInfo startInfo = calculStartTime(timeLines[k], processTime);

        pq.push(startInfo);
        startTimeLines.push_back(startInfo);
        pq.push(timeLines[k]);
    }

    while (!pq.empty()) {
        TimeInfo endNorm = pq.top();
        pq.pop();
        TimeInfo startNorm = calculStartTime(endNorm, 1);
        int count = 0;

        for (int k = lines.size() - 1; k >= 0; k--) {
            TimeInfo endTime = timeLines[k], startTime = startTimeLines[k];
            if (isGreaterTime(startNorm, endTime)) break;
            if (
                    (isGreaterEqualTime(endTime, startNorm) && isGreaterEqualTime(endNorm, endTime)) ||
                    (isGreaterEqualTime(startTime, startNorm) && isGreaterEqualTime(endNorm, startTime)) ||
                    (isGreaterEqualTime(startNorm, startTime) && isGreaterEqualTime(endTime, endNorm))
                    )
                count++;
        }
        answer = max(answer, count);
    }

    return answer;
}
```

