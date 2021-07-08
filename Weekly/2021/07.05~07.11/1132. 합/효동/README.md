<h1> 1132. 합 </h1>

#### [분류]
- greedy
<br><br>

#### [문제링크]
- https://www.acmicpc.net/problem/1132
<br><br>


#### [요구사항]
- 코드의 인덴트를 올바르게 고치는 편집 회수의 최소값을 구한다.<br><br> 

#### [풀이]

1. 각 알파벳의 기여도를 구하는 것이 문제의 핵심이다.<br><br>

2. 1의자리,10의자리,100의자리... 순서로 알파벳이 있으니까 우선 알파벳마다 10의 제곱승을 곱하는 방식으로 기여도를 모두 구한다.<br><br>

3. 이후에 기여도를 기준으로 내림차순 소팅을 하고, 앞에서부터 차례로 9부터 숫자를 부여한다. 이 때, 0이 되도 되는 것들은 가장 뒤에 있으니까 그것을 0으로 준다.<br><br>

4. 이후 모든 수를 더해서 답을 도출한다.<br><br>

#### [코드]
```java
//BOJ_1132_합

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        Info arr[] = new Info[10];
        for(int i=0;i<10;i++){
            arr[i] = new Info(i, 0);
        }

        boolean check[] = new boolean[10];

        for(int i=0;i<n;i++){
            char[] chars = br.readLine().toCharArray();
            long mult = 1;

            for(int j=chars.length-1;j>=0;j--){
                int index = chars[j]-'A';
                arr[index].power+=mult;
                mult*=10;

                if(j==0) check[index] = true; //0이 들어갈 수 없는 맨 앞에 오는 알파벳 체크
            }
        }

        Arrays.sort(arr, new Comparator<Info>() {
            @Override
            public int compare(Info o1, Info o2) {
                if(o1.power>o2.power) return -1;
                else if(o1.power<o2.power) return 1;
                else return 0;
            }
        });
        //영향력이 큰 알파벳부터 내림차순으로 소팅

        int[] multNums = new int[10];
        Arrays.fill(multNums, -1);

        for(int i=9;i>=0;i--){
            if(!check[arr[i].idx]){
                multNums[i] = 0;
                break;
            }
        }

        int num = 9;
        for(int i=0;i<10;i++){
            if(multNums[i]==-1) multNums[i]=num--;
        }//앞에서부터 알파벳에 해당하는 숫자를 부여한다. 이때 없는 알파벳도 다 부여해줘도 상관없다. 어짜피 안쓰니까

        long res=0;
        for(int i=0;i<10;i++){
            res+=arr[i].power*multNums[i];
        }//어짜피 없는 알파벳은 power가 0이라서 굳이 따로 고려안해주어도 상관없다.
        System.out.println(res);
    }

    public static class Info{
        int idx;
        long power;

        public Info(int idx, long power){
            this.idx = idx;
            this.power = power;
        }
    }
}
```
<br><br>

#### [통과여부]
![image](https://user-images.githubusercontent.com/54053016/124955126-c5c61d00-e051-11eb-9823-fea812301131.png)
<br><br>

#### [느낀점]
너무 어렵다. 어떻게 이런 발상을 할 수 있는지 모르겠다ㅋㅋ 그리고 맨 앞이 0이 안되게 하려고 추가적인 검증코드를 짜는 것이 생각보다 잘 안되서 너무 오래걸리고 힘들었다.