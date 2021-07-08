<h1> 2812. 크게 만들기 </h1>


#### [분류]
- greedy
- stack
<br><br>

#### [문제링크]
- https://www.acmicpc.net/problem/2812
<br><br>


#### [요구사항]
- 만들 수 있는 가장 큰 수를 출력한다.<br><br> 

#### [풀이]

1. 스택을 이용하여 앞에서부터 큰 수를 집어넣는다.<br><br>

2. stack의 top이 push하려는 숫자보다 작으면 pop한다. 이 때, delete한 횟수를 세어주는 것이 중요하다. delete한 횟수가 초과할 수는 없기 때문에 그 경우 그대로 push만 한다.<br><br>

3. stack의 최대 크기(n-k)보다 많이 넣을 수 없는 것도 꼭 주의한다.<br><br>

4. stack을 최종적으로 구하면 역순으로 출력한다.<br><br>


#### [코드]
```java
//BOJ_2812_크게만들기

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        Stack<Integer> stack = new Stack<>();

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        char[] val = br.readLine().toCharArray();
        int del = 0;

        for(int i=0;i<n;i++){
            int value = val[i] - '0';

            if(del==k) {
                stack.add(value);
            }
            else {
                while (del<k) {
                    if(stack.isEmpty()){
                        break;
                    }
                    int top = stack.peek();
                    if (top < value) {
                        stack.pop();
                        del++;
                    }else{
                        break;
                    }
                }
                if(stack.size()<n-k) stack.add(value);
            }
        }

        StringBuilder sb = new StringBuilder();
        while(!stack.isEmpty()) {
            int a = stack.pop();
            sb.append(a);
        }
        System.out.println(sb.reverse());
    }
}
```
<br><br>

#### [통과여부]
![image](https://user-images.githubusercontent.com/54053016/124893390-a7423080-e015-11eb-97a9-8731aeb6f74e.png)
<br><br>

#### [느낀점]
앞에서부터 큰 수를 사용해서 수를 만들어야한다는 느낌은 왔지만 스택이라는 자료구조는 익숙하지가 않아서 바로 적용할 생각을 못했다. 그리고 특히, 마지막에 delete한 횟수가 오히려 너무 적을 때, 뒤의 숫자들이 전부 출력하는 것을 stack의 size제한으로 제어해야했는데 그걸 생각해내는 것이 이상하게 어려웠다.