<h1> 6137. 문자열 생성 </h1>


#### [분류]
- greedy
- two-pointer
<br><br>

#### [문제링크]
- https://www.acmicpc.net/problem/6137
<br><br>


#### [요구사항]
- 사전순으로 가장 빠른 문자열을 출력한다. 80글자마다 새줄 문자를 출력해야 한다.<br><br> 

#### [풀이]

1. 그리디를 이용해서 문제를 해결한다.<br><br>

2. 양 끝점부터 시작한다. 두 문자가 다를 때는 당연히 사전에서 더 앞에 오는 알파벳을 먼저 붙여 문자열을 만든다.<br><br>

3. 두 문자가 동일할 경우 그 다음 문자를 봐야하는데, 서로 다른 문자가 나올 때 까지 계속 이동한다.<br><br>

4. 다른 문자가 나오면 더 빠른 문자쪽에 해당하는 문자를 추가한다.<br><br>

5. 만약 전부 같은 문자여서 중간에서 만나거나 서로 넘어가버리면 거기서 중단하고 왼쪽에 있는 문자를 넣기로 설정하면 된다.<br><br>

#### [코드]
```java
//BOJ_6137_문자열생성

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        char[] str = new char[n];
        StringBuilder t = new StringBuilder();
        for(int i=0;i<n;i++){
            str[i] = br.readLine().charAt(0);
        }

        int s=0, e=n-1;
        while(s<=e){
            if(str[s]<str[e]){
                t.append(str[s]);
                s++;
            }else if(str[s]>str[e]){
                t.append(str[e]);
                e--;
            }else{
                int i=s, j=e;

                while(str[i]==str[j]){
                    i++;j--;
                    if(j<=i) break;
                }
                if(j<=i || str[i]<str[j]){
                    t.append(str[s++]);
                }else{
                    t.append(str[e--]);
                }
            }
        }

        int len = t.length();

        StringBuilder res = new StringBuilder();

        if(len<=80){
            System.out.println(t.toString());
        }else{

            int cnt = len/80;
            int i = 0;
            for(i=0;i<cnt;i++){
                res.append(t.substring(i*80, (i+1)*80) + "\n");
            }
            res.append(t.substring(i*80,len));
        }

        System.out.println(res.toString());

    }
}


```
<br><br>

#### [통과여부]
![image](https://user-images.githubusercontent.com/54053016/124716090-a5596e00-df3e-11eb-9091-4fca3b9a2254.png)
<br><br>

#### [느낀점]
분명히 문제를 푸는 방법은 어렵지 않은데 80개 이상일 때 엔터 출력, 또 중간에서 만날 때 처리하는 부분에서 너무 시간이 오래걸려서 힘들었다.