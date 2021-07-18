## 1464. 뒤집기



```java

public class Main{


    static String input;

    public static void main(String[] argv) throws IOException {
        init();
        System.out.println(calc());
    }

    public static void init() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        input=br.readLine();
    }

    public static String calc(){
        StringBuilder sb = new StringBuilder();
        sb.append(input.charAt(0));

        for(int i=1;i<input.length();i++){
            if(sb.charAt(i-1)<input.charAt(i)){
                sb=sb.reverse();
                sb=sb.append(input.charAt(i)).reverse();
            }
            else{
                sb.append(input.charAt(i));
            }
        }

        return sb.reverse().toString();
    }
}



```

<br/>

문제의 난이도에 비해서 상당히 어려웠다.

문자열을 뒤집을 때 사전순으로 가장 빠른 문자열이 되려면, 맨 마지막에 뒤집기전에 최대한 뒤쪽으로 사전순으로 빠른 문자를 붙여야 된다고 생각했다.

뒤집을 때마다 어떠한 규칙이 발생하는지 찾아봤는데 다음과 같은 방식을 사용했다.

--- 

예를 들어 a1 a2 a3 에서 a4를 붙일 차례의 경우 

1) a3 < a4 (a3이 a4보다 사전순으로 빠른 경우 일 때) : 이때는 a3를 뒤쪽으로 보내야하기 때문에

a1~a3까지 문자열을 뒤로 뒤집은 후에 a4를 붙이고, 다시 a4를 붙이면서 문자열을 뒤집는다.

즉 a3일때도 문자열을 뒤집고 a4일때도 문자열을 뒤집는다. 이렇게 하면 a3 a2 a1에서 a4를 붙이고 뒤집으면 a4 a1 a2 a3가 된다.

--- 
2) a3 > a4 (a4가 a3보다 사전순으로 빠른 경우 일 때) : 이때는 a4가 사전순으로 제일 빠르기 때문에 맨뒤에 그냥 붙이면 된다.


최종적으로 마지막 문자까지 위와 같이 처리를 한 후에 문자열을 뒤집으면 답이 나온다.

