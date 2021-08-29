# 자물쇠와 열쇠 - 2020 KAKAO BLIND

###### 작성자 : O-star

<br/>

<br/>

### 풀이 : 

**전에 남는 자투리 시간에 풀어보려다가 촉박하게 풀려고 하니 풀이가 생각나지 않아 잠시 접어두고 있던 문제이다.**

**우선 문제를 차근차근 읽다보니 자물쇠와 열쇠 크기 자체가 20 이하로 크지 않고 회전과 이동 가능 경우의 수도 크지 않기 때문에 전체 탐색으로 문제를 해결할 수 있을 것 같았다.**

**그래서 본 문제는 자물쇠의 위치를 고정해두고 회전 키들을 이동해가며 자물쇠, 키 중 자물쇠 위치에서 1을 하나만 가지도록 하는 경우의 수가 존재하는지 판별해주었다.**

**<br/>**

**![image](https://user-images.githubusercontent.com/57346455/131236463-35da31e2-4cf5-4897-9001-25c29d583c5b.png)**

**=> 먼저 회전 키는 다음과 같이 구현해주었다. 배열 회전의 경우 몇 가지의 인덱스만 진행해보면 다음과 같이 90도 배열 회전을 구현해줄 수 있다.**

**<br/>**

**![image](https://user-images.githubusercontent.com/57346455/131236539-11ddcb25-38e0-4c7f-8a5b-7262d8329e9c.png)**

**=> 본 문제에서 헷갈릴 수 있는 것은 바로 key의 이동에 따라 자물쇠를 풀 수 있는지 키와 자물쇠의 절대 위치를 잘 파악해 주어야 하는 점이다.**

**=> 위 그림과 같이 key, lock의 시작 위치를 변수화해서 잘 정리해두어야 한다. 여기서 lock위치는 고정시키고 key의 이동 경우의 수만을 고려해주었다. 따라서 key의 이동에 따라 keyCurX, keyCurY 인덱스 정보를 계산하는 방법이 중요하다.**

<br/>

<br/>

### 코드 : 

```c++
#include <utility>
#include <vector>

using namespace std;
vector<vector<vector<int>>> keyVec(4);
int keySize, lockSize, lockStartX, lockStartY;

void createRotateKey() {
    for (int k = 1; k < 4; k++) {
        keyVec[k].resize(keySize);
        for (int i = 0; i < keySize; i++) {
            keyVec[k][i].resize(keySize);
        }
        for (int i = 0; i < keySize; i++) {
            for (int j = 0; j < keySize; j++) {
                keyVec[k][j][keySize - i - 1] = keyVec[k - 1][i][j];
            }
        }
    }
}

bool isSolveLock(int keyIdx, int keyStartX, int keyStartY, vector<vector<int>> *lock) {
    int keyMaxX = keyStartX + keySize, keyMaxY = keyStartY + keySize;

    for (int i = 0; i < lockSize; i++) {
        for (int j = 0; j < lockSize; j++) {
            int curX = lockStartX + i, curY = lockStartY + j;
            int keyCurX = curX - keyStartX, keyCurY = curY - keyStartY;

            if (0 <= keyCurX && curX < keyMaxX && 0 <= keyCurY && curY < keyMaxY) {
                if ((keyVec[keyIdx][keyCurX][keyCurY] && (*lock)[i][j]) ||
                    (!keyVec[keyIdx][keyCurX][keyCurY] && !(*lock)[i][j])) {
                    return false;
                }
            } else {
                if (!(*lock)[i][j]) {
                    return false;
                }
            }
        }
    }
    return true;
}

bool solution(vector<vector<int>> key, vector<vector<int>> lock) {
    keySize = key.size();
    lockSize = lock.size();
    keyVec[0] = move(key);
    int totalSize = keySize + lockSize;

    createRotateKey();

    lockStartX = keySize - 1;
    lockStartY = lockStartX;
    for (int k = 0; k < 4; k++) {
        for (int keyStartX = 0; keyStartX < totalSize; keyStartX++) {
            for (int keyStartY = 0; keyStartY < totalSize; keyStartY++) {
                if (isSolveLock(k, keyStartX, keyStartY, &lock)) return true;
            }
        }
    }
    return false;
}
```

