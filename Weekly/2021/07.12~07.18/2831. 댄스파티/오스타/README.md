# 2831. 등굣길

###### 작성자 : O-star

<br/>

<br/>

### 풀이 : 

**남성, 여성 파티원들의 키 선호를 따져 짝을 지어주는 문제이다.**

**우선 남자와 여자의 신장 정보를 활용하여 PersonInfo(신장, 키 선호, 매칭 여부 정보를 포함) 구조체를 만들어서 사용한다.**

**남성의 경우는 신장 오름차순 우선순위 큐를 만들어서 활용했으며, 여성의 경우는 신장 오름차순으로 정렬된 PersonInfo 구조체 배열을 활용한다.**

**매칭의 방법은 우선순위 큐와 소팅된 배열을 완성하게 되면 생각보다 간단하다.**

1. **우선순위 큐에서 신장이 작은 남성의 정보부터 차례로 꺼낸다.**
2. **남성이 작은 키를 선호할 경우에는 womanStartIdx 변수부터 차례로 여성 정보 배열을 훑어가며 매칭할 수 있는 정보를 찾는다 -> womanStartIdx는 여성 정보 배열의 가장 앞에서부터 설정되어 탐색한다.**
3. **남성이 큰 키를 선호할 경우에는 womanEndIdx 변수부터 차례로 여성 정보 배열을 훑어가면 매칭 정보를 찾는다 -> womanEndIdx 또한 여성 정보 배열 가장 앞에서부터 탐색한다.**
4. **신장 정보 순으로 소팅되어 있는 자료구조를 사용하기 때문에 탐색된 정보는 다시 탐색할 필요가 없다. 따라서 반복적인 탐색이 이루어지지 않아 시간복잡도를 효과적으로 줄일 수 있다.**

<br/>

<br/>

### 코드 : 

```c++
#include <iostream>
#include <queue>
#include <vector>
#include <algorithm>

using namespace std;
struct PersonInfo {
    int height;
    bool likeGreater, isMatched;
};

struct compare {
    bool operator()(PersonInfo a, PersonInfo b) {
        return a.height > b.height;
    }
};

int n;

bool sorting(PersonInfo a, PersonInfo b) {
    return a.height < b.height;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    int input, womanStartIdx = 0, womanEndIdx = 0, answer = 0;
    priority_queue<PersonInfo, vector<PersonInfo>, compare> pq;
    vector<PersonInfo> womanVec;
    cin >> n;

    for (int i = 0; i < n; i++) {
        cin >> input;
        if (input < 0) {
            pq.push(PersonInfo{input * -1, false, false});
        } else pq.push(PersonInfo{input, true, false});
    }

    for (int i = 0; i < n; i++) {
        cin >> input;
        if (input < 0) {
            womanVec.push_back(PersonInfo{input * -1, false, false});
        } else womanVec.push_back(PersonInfo{input, true, false});
    }
    sort(womanVec.begin(), womanVec.end(), sorting);

    while (!pq.empty()) {
        PersonInfo curPerson = pq.top();
        pq.pop();

        if (curPerson.likeGreater) {
            while (true) {
                if (womanEndIdx >= n) break;
                else if (womanVec[womanEndIdx].height > curPerson.height && !womanVec[womanEndIdx].likeGreater &&
                         !womanVec[womanEndIdx].isMatched) {
                    womanVec[womanEndIdx].isMatched = true;
                    womanEndIdx++;
                    answer++;
                    break;
                }
                womanEndIdx++;
            }
        } else {
            while (true) {
                if (womanVec[womanStartIdx].height < curPerson.height && womanVec[womanStartIdx].likeGreater &&
                    !womanVec[womanStartIdx].isMatched) {
                    womanVec[womanStartIdx].isMatched = true;
                    womanStartIdx++;
                    answer++;
                    break;
                } else if (womanVec[womanStartIdx].height >= curPerson.height) break;
                womanStartIdx++;
            }
        }
    }

    cout << answer << '\n';
    return 0;
}
```

