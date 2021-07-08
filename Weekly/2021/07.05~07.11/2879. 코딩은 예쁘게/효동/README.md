<h1> 2879. 코딩은 예쁘게 </h1>

#### [분류]
- greedy
<br><br>

#### [문제링크]
- https://www.acmicpc.net/problem/2879
<br><br>


#### [요구사항]
- 코드의 인덴트를 올바르게 고치는 편집 회수의 최소값을 구한다.<br><br> 

#### [풀이]

1. 우선 최종 목표와 현재 숫자의 차이인 diff배열을 생성한다.<br><br>

2. 이제 diff[0]부터 diff[n-1]까지 같은 그룹이라고 생각하고 diff[0]부터 하나씩 쳐낸다.<br><br>

3. diff[i-1]을 prev로 두고 diff[i]와 비교해서 다른 부호면 애초에 같이 tab을 더하거나 빼줄 수 없으므로 같은 그룹이 아니기 때문에 res에 diff[i-1] == prev를 더해주고 제외시킨다.<br><br>

4. 또는, prev와 diff[i] 모두 양수일 때는 오름차순, 모두 음수일 때는 내림차순이여야 뒤의 것들도 계속해서 쳐낼 수 있기 때문에 그렇게 되지 않으면 diff[i]-prev의 절댓값만큼 res에 더해준다. 이것은 diff[i]와 prev의 차이를 0으로 만든다는 의미로, 뒤의 계산 과정에서 커버할 수 있게 해주는 것이다.<br><br>

5. 둘 다 해당하지 않으면 그냥 prev를 diff[i]로 옮겨주는 식으로 계속 진행한다.<br><br>

6. 마지막에 하나남은 prev의 절댓값을 res에 더해주고 출력한다.<br><br>

#### [코드]
```java
//BOJ_2879_코딩은예쁘게

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] diff = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0;i<n;i++){
            diff[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for(int i=0;i<n;i++){
            diff[i] = Integer.parseInt(st.nextToken()) - diff[i];
        }

        int prev = diff[0];
        int res = 0;
        for(int i=1;i<n;i++){
            if(prev*diff[i]<0){//앞의 것과 지금 것이 부호가 다르면 같은 그룹 X -> 즉, 앞에꺼는 따로 처리
                res+=Math.abs(prev);
                prev = diff[i];
            }
            else if(prev*(prev-diff[i])>0){//양수일 때 오름차순, 음수일 때 내림차순으로 되지 않으면 역시 그룹X
                // -> 앞에꺼가 지금꺼랑 같은 값 될 수 있을 때 까지만 움직이기(앞에 곱해준 prev는 단순히 부호용
                res+=Math.abs(prev - diff[i]);
                prev = diff[i];
            }
            else{//정상적이면 뒤에서 움직일 때 prev도 같이 커버되니까 넘어가기(같은그룹O)
                prev = diff[i];
            }
        }
        res+=Math.abs(prev);//맨 마지막에 남은거 하나 절댓값으로 더하기

        System.out.println(res);
    }
}
```
<br><br>

#### [통과여부]
![image](https://user-images.githubusercontent.com/54053016/124877420-05671780-e006-11eb-8cd6-28702cd8530b.png)
<br><br>

#### [느낀점]
처음에는 dp로 풀려고 했지만 그룹지은 모든 숫자들을 전부 배열로 둔다는 것이 도저히 불가능하다고 판단했고, 도저히 뭘 속성으로 써야하는지 몰라서 포기했다.
이후에 greedy의 느낌으로 앞에서부터 하나씩 쳐낸다는 생각으로 구현을 해보자고 생각했다. greedy로 생각하는게 뭔가 확실성, 정당성이 있어야해서 어려운 것 같다.