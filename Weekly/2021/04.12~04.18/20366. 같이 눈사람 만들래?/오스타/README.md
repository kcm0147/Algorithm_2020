# 20366. 같이 눈사람 만들래?

###### 작성자 : O-star

<br/>

<br/>

#### 풀이 : 

**오... 굉장히 쌈빡한 문제였다.**

**일단 처음에 봤을 땐 너 이녀석 DP로 풀어주지라고 하면서 호기롭게 덤볐지만 감조차 오지 않았다**

**문제는 생각보다 간단한 로직을 가지고 있는 듯하다. 물론 풀이는 여러 방식이 있겠지만 내가 생각한 방식은 다음과 같다.**

1. **입력받은 지름들이 저장된 배열을 오름차순 정렬해준다.**
2. **정렬된 배열에서 두개의 지름을 선택한다. => 조합 방식 사용**
3. **두개의 지름 중 더 왼쪽의 인덱스를 가진 지름을 'selA'라고 지칭하고, 더 오른쪽 인덱스를 'selB'라고 지칭할 때 selA의 바로 왼쪽 인덱스를 leftA, selB의 바로 오른쪽 인덱스를 rightB라고 지칭한다.**
4. **아래 그림과 같이 지칭했을 경우 leftA와 rightB의 인덱스를 옮겨가면서 최소 차이 지름 쌍들을 찾아낼 수 있다.**
   1. **(leftA + rightB) - (selA + selB) > 0 일 경우, leftA--  => 지름합의 차이가 0보다 클 경우 (leftA + rightB)의 값이 더 작아지는 경우가 최소 차이에 가까워지는 경우이기 때문에 leftA의 위치를 한 칸 앞으로 옮겨주는 것이다. 정렬되어있기 때문에 leftA를 한칸 옮기면 (leftA + rightB)의 값이 더 작아지게 된다.**
   2. **(leftA + rightB) - (selA + selB) =< 0 일 경우, (leftA + rightB)의 값이 더 커져야 하기 때문에 반대로 rightB의 인덱스를 한 칸 뒤로 옮겨주는 것이다.**
   3. **1, 2번 과정을 거쳐줄 때 마다 최소 차이값이 되는지 계속 비교해준다.**

![image](https://user-images.githubusercontent.com/57346455/114369550-750d6800-9bb9-11eb-95c6-1f8a7d5b9b11.png)

<br/>

<br/>

#### 코드 : 

```c++
#include <iostream>
#include <algorithm>

#define MAX 2147483640

using namespace std;
int n, inputs[600], mindiff = MAX;

void calculMinDiff(int selA, int selB) {
    int leftA = selA - 1, rightB = selB + 1, orgsum = inputs[selA] + inputs[selB];
    while (true) {
        if (leftA < 0 || rightB >= n) break;
        int curdif = (inputs[leftA] + inputs[rightB]) - orgsum;
        mindiff = min(mindiff, abs(curdif));
        if (curdif > 0) leftA--;
        else rightB++;
    }
}

int main() {
    cin >> n;
    for (int i = 0; i < n; i++)
        cin >> inputs[i];

    sort(inputs, inputs + n);

    for (int i = 0; i < n; i++)
        for (int j = i + 1; j < n; j++)
            calculMinDiff(i, j);

    cout << mindiff << '\n';
    return 0;
}
```

