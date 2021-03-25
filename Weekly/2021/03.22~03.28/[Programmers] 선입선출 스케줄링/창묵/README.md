
* 이분탐색


놀이공원문제와 동일한 문제풀이 형식입니다.

이분탐색을 이용해서 `N분에 n개의 작업을 할 수 있는지`를 찾습니다.

N분 바로 직전 즉 N-1분까지 몇개의 작업을 한 후에, N분째에 cores의 첫번째 코어부터 작업을 할 수 있는지 없는지 count 합니다.

count를 진행하면서 n번째 작업을 완료했을 때의 core를 return 하여 답을 출력합니다.





```java

class Solution {

    static int[] cores;
    static int n;

    public int solution(int In, int[] Icores) {
        cores=Icores;
        n=In;

        if(In<=Icores.length){
            return In;
        }
        else {
            int time = findTime();
            return calc(time);
        }

    }

    public int findTime(){
        int left=0;
        int right=100000000;
        int returnValue=0;

        while(left<=right){
            int answer=cores.length;

            int mid=(left+right)/2;

            for(int i=0;i<cores.length;i++){
                answer+=(mid)/cores[i];
            }

            if(answer>=n){
                right=mid-1;
                returnValue=mid;
            }
            else
                left=mid+1;
        }

        return returnValue;
    }

    public int calc(int time){
        int answer=cores.length;

        for(int i=0;i<cores.length;i++){
            answer+=(time-1)/cores[i];
        }

        for(int i=0;i<cores.length;i++){
            if((time)%cores[i]==0){
                answer++;

                if(answer==n){
                    return i+1;
                }
            }
        }

        return -1;
    }


}

```