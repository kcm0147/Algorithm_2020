# 파이프 옮기기2

# 풀이법

1. 단순 DFS문제 + dir을 고려하여 3차원 배열로 visit[] 배열을 생성하여 DFS로 풀면 된다고 생각했다.

2. 파이프 옮기기1은 배열 범위가 작아서 돌아갔는데 파이프 옮기기2는 배열의 범위가 커서 시간초과가 되었다.

3. 그래서 생각을 했는데 DFS로 진행하다보면 중복해서 판단하는 구간이 있을 거라고 생각을 했고 DFS를 이용하여 목적지까지 이동하는데 배열에 값이 존재하면 바로 return 을
    시켜서 탐색을 진행하지 않도록 하였다. 
    탐색이 종료되면 이동한 경로에 값을 +1을 시켜서 배열에 값을 저장시킨다.


    public static long dfs(int nx,int ny,int dir){

    if(length[nx][ny][dir]!=-1){ // 값이 존재할때 바로 return 시킨다.
      return length[nx][ny][dir];
    }

    if(nx==N-1 && ny==N-1){ // perfect serch end 목적지까지 탐색이 계속된다는 것은 목적지까지가는데 dfs() 탐색을 한번도 진행한 적이없을 때를 의미하니깐 return 1;을 해준다.
      return 1;
    }

    length[nx][ny][dir]=0; // 값을 0으로 초기화

    for(int i=0;i<3;i++){ // horizon, vertical,diagonal
      if(dir==horizon && i==vertical)
        continue;
      if(dir==vertical && i==horizon)
        continue;

      if(mapCheck(nx,ny,i)){ // 다음 칸으로 이동한다.
        if(i==vertical){
          length[nx][ny][dir]+=dfs(nx+1,ny,vertical);
        }
        else if(i==horizon){
          length[nx][ny][dir]+=dfs(nx,ny+1,horizon);
        }
        else if(i==diagonal){
          length[nx][ny][dir]+=dfs(nx+1,ny+1,diagonal);
        }
      }
    }

    return length[nx][ny][dir]; // 현재 칸에서 for문 (4방향 탐색) 이 종료되면 경로의 값을 return 시킨다.

  }



# 실수

1. 로직이 분명 맞는데 왜 자꾸 큰 케이스에서 답이 틀렸다고 나오는지 의문이 들었다...
결국 실수는 int형으로 해서 오버플로우때문에 발생한 문제였다. 화가났다.

