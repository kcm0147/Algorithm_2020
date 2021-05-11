<h1>1082. 방 번호</h1>

#### [분류]
- 그리디
<br><br>

#### [문제링크]
- https://www.acmicpc.net/problem/1082
<br><br>


#### [요구사항]
- 첫째 줄에 백은진이 가지고 있는 돈으로 만들 수 있는 가장 큰 수를 출력한다.<br><br> 

#### [풀이]

1. 메모리가 다소 적어 문자열에 추가하는 방식으로도 풀 수 있지만, 그냥 int 배열로 풀었다.<br><br>

2. 우선, 무조건 자리수가 많아야 되는 것이 핵심이다. 그래서 금액이 가장 적게 최대한 많은 자릿수를 확보한다.<br><br>

3. 그 다음에 맨 앞부터 최대한 큰 수로 바꿔줘야한다. <br><br>

4. 만약 0이 다른 수로 안바뀌면 자릿수를 줄여서라도 맨 앞자리를 바꿔줘야해서 숫자를 되팔고 start를 한 칸 뒤로 옮기며 계속한다.<br><br>

5. 그러나, 0밖에 안될 경우에는 0을 출력한다.<br><br>


#### [코드]
```java
//baekjoon_1082_방번호

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int n, k=0;
    static Node[] node;
    static int min = 50;
    static Node[] arr;
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] s = br.readLine().split(" ");
        n = Integer.parseInt(s[0]);

        s = br.readLine().split(" ");
        node = new Node[n];
        for(int m=0; m<n; m++){
            node[m] = new Node(m, Integer.parseInt(s[m]));
        }

        s = br.readLine().split(" ");
        int total = Integer.parseInt(s[0]);

        //min을 구한다.
        int idx=0;
        for(int i=0;i<node.length;i++){
            if(min>=node[i].price){
               idx = i;
               min = node[i].price;
            }
        }

        //우선 가장 많은 자릿수를 만들어야한다, min으로 full로 채운다.
        while(total>=0){
            k++;
            total-=min;
        }
        total+=min;
        arr = new Node[--k];
        for(int i=0;i<k;i++){
            arr[i] = new Node(idx, min);
        }

        //이제 맨 앞에서부터 수를 교체해보면서 가장 큰수로 바꾼다.
        int start = 0;
        for(int i=0;i<arr.length;i++){
            for(int j=n-1;j>=0;j--){
                if(i==0 && j==0) continue;
                int tmp = total + min - node[j].price;
                if(tmp>=0){
                    total = tmp;
                    arr[i] = node[j];
                    break;
                }
            }

            //맨 앞 자리를 0으로 둘 수는 없으니까 되팔고 단위를 줄여서라도 새로운 수를 맨 앞에 넣어야한다.
            if(arr[start].num == 0){
                start++;
                total = total + arr[i].price;
            }
        }

        if(start == arr.length){
            System.out.println(0);
            return;
        }

        for(int i=start;i<arr.length;i++)
        System.out.print(arr[i].num);

    }

    private static class Node{
        int num;
        int price;

        private Node(int num, int price){
            this.num = num;
            this.price = price;
        }
    }
}
```
<br><br>

#### [통과여부]
![image](https://user-images.githubusercontent.com/54053016/117828747-149f4280-b2ad-11eb-8d8e-19567e20c9c4.png)
<br><br>

#### [느낀점]
조건을 생각해보자.