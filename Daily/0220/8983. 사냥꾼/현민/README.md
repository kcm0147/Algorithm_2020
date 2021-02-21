# 8983 사냥꾼

## 조건

* 사대와 동물의 거리는 다음과 같이 나타낸다.

    * |x - a| + b 
    
    * 사대는 (x,0) 위치에 있다.
    
    * 동물은 (a,b) 위치에 있다.
    
* 사대에서 동물을 맞출 수 있는 거리는 L이하여야 한다.

## 풀어야하는 것

* 사대들과 동물들의 위치정보들이 주어질 때 맞출 수 있는 동물들의 수를 구하시오.

## 아이디어

1. 사대를 말고 거꾸로 동물의 입장에서 살펴본다.

2. 동물이 맞춰질 수 있으려면 다음 조건을 만족하는 사대가 있어야 한다.

    `a+b-L <= x <= a-b+L`
   
3. 이를 만족하는 사대를 x를 기준으로 이분 탐색하여 찾아낸다.

## 느낀 점

1. 이 문제는 풀지 못하고 결국 답안을 봐버린 문제였다.

2. 진짜 거꾸로 생각하는 문제 저번에도 본 거 같은데 진짜 너무 굉장하다.

3. 다음에 알고리즘 고안할때 이렇게 2가지 포커스가 주어지면 반대 입장으로도 고려해봐야 할 거 같다.

## 코드

```java
package daily.day0220.boj8983;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    private static int[] junbidenSaroButa;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] numbers = br.readLine().split(" ");
        int M = stoi(numbers[0]);
        int N = stoi(numbers[1]);
        int L = stoi(numbers[2]);

        junbidenSaroButa = new int[M];
        numbers = br.readLine().split(" ");
        for(int i=0; i<M; i++) junbidenSaroButa[i] = stoi(numbers[i]);
        Arrays.sort(junbidenSaroButa);

        int answer=0;
        for(int i=0; i<N; i++){
            numbers = br.readLine().split(" ");
            int a = stoi(numbers[0]);
            int b = stoi(numbers[1]);

            int left=0, right=M-1;
            while(left<=right){
                int mid=(left+right)/2;
                if(junbidenSaroButa[mid] < a+b-L) left = mid + 1;
                else if(junbidenSaroButa[mid] > a-b+L) right = mid-1;
                else{
                    answer++;
                    break;
                }
            }
        }
        System.out.println(answer);
    }

    private static int stoi(String s) { return Integer.parseInt(s); }
}
```