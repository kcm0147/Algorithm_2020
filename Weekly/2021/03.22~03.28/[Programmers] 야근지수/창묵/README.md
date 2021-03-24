

* 우선순위 큐

이 문제는 운이 좋게도 풀이법을 생각해내는데 오래걸리지 않았습니다.

제곱의 합을 최소화 하기 위해서는 아무래도 우선적으로 큰 수부터 줄여 나가는 것이 좋습니다.

하나씩 줄일때마다 sorting을 진행하는 것은 비효율적이라고 생각하였습니다.

`우선순위 큐`를 이용해서 내림차순으로 정렬을 하고, n만큼 숫자를 제거한 후에 나머지 큐에있는 수들을 제곱을 해서 더하면 끝입니다.




```java

public class Main {



    static int[] works = {1,1};
    static int n=3;


    public static void main(String[] args) throws IOException {

        Solution solution = new Solution();
        System.out.println(solution.solution(n,works));
    }

}


class Solution {

    PriorityQueue<Integer> que;

    public long solution(int n, int[] works) {


        que=new PriorityQueue<Integer>(Collections.reverseOrder());

        for(int i=0;i<works.length;i++){
            que.add(works[i]);
        }

        return calc(n);

    }

    public long calc(int n){
        long sum=0;

        for(int i=0;i<n;i++){
            if(que.isEmpty())
                break;

            int cur = que.poll();

            if(cur-1>0)
                que.add(cur-1);
        }

        while(!que.isEmpty()){
            sum+=Math.pow(que.poll(),2);
        }

        return sum;
    }


}


```