[요구사항]

G라는 몸무게가 주어지는데, G킬로그램은 현재 몸무게에서 과거 성원이가 기억하고 있는 몸무게의 제곱을 뺀 값을 의미합니다

성원이가 현재 몸무게로 고려할 수 있는 경우를 구해야합니다


<br/>

문제를 봤을때, `현재 몸무게의 제곱 - 과거 몸무게의 제곱`에서 현재 몸무게로 가능한 모든 것을 출력해야하는데

일단 수의 범위가 주어지지 않아서 언제까지 연산을 진행해야하는지 기준을 찾는 것이 어려웠습니다

예제를 보면 G가 15임을 확인 했을 때, 값으로 출력되는것이 15이하인 수 4,8인것을 보고 어떻게든 범위를 정하는 방법을 찾아야한다고 생각했습니다.

순간 `현재 몸무게의 제곱 - 과거 몸무게의 제곱`을 `(현재몸무게+과거몸무게) * (현재몸무게-과거몸무게)`로 계산을 할 수 있다는 것을 생각하고 이를 이용하여 코딩을 하였습니다.

```java

while(true){
        right=left+1;

        if(left+right>G)
              break;

        while((right+left)*(right-left)<=G){
            long cost = (right+left)*(right-left);
            if(cost==G){                    
                ans.add(right);
            }
            right++;
        }
       left++;
}

```

결론부터 말씀드리자면 현재몸무게+과거몸무게가 주어진 G값을 넘어설때 연산을 멈출 수 있습니다.

예로 들어 G가 15이고 현재 몸무게가 8, 과거 몸무게가 7임을 봤을떄 (8+7)(8-7)=15로 현재 몸무게 8이 답이 될 수 있지만

`현재 몸무게 - 과거 몸무게가 1이라고 가정 했을때, 현재 몸무게 + 과거 몸무게가 G 이 나온다면 G가 15로 나올수가 없습니다`

이를 이용해서 left를 1로 잡고, right를 left+1로 설정 후 1씩 늘리면서, (현재몸무게 + 과거몸무게 < G) 일때 까지만 연산을 진행하도록 하였습니다.

수학적인 지식이 어느정도 필요한 문제였다고 생각합니다


<br/> <br/>

```java

public class Main {


    static ArrayList<Long> ans;
    static int G;

    public static void main(String[] argv) throws IOException {

        init();
        calc();

        if(ans.size()==0)
            System.out.println(-1);
        else {
            for (int i = 0; i < ans.size(); i++) {
                System.out.println(ans.get(i));
            }
        }

    }

    public static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        G=stoi(br.readLine());
        ans = new ArrayList<>();

    }

    public static void calc(){
        long left=1;
        long right;

        while(true){
            right=left+1;

            if(left+right>G)
                break;

            while((right+left)*(right-left)<=G){
                long cost = (right+left)*(right-left);
                if(cost==G){
                    ans.add(right);
                }
                right++;
            }
            left++;
        }


    }

    public static int stoi(String string){
        return Integer.parseInt(string);
    }

}

```