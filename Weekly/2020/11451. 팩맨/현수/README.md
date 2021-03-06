# 11451. 팩맨
<h2>느낀점</h2>
처음에 조건을 하나하나 따져보면서 "음 복잡하군" 싶었고, 유령과 라이프 조건을 봤을 때 "음 어렵군" 싶었고, 테케 다 통과하고 제출하고 메모리 초과 떴을 때 "아 조졌다" 싶었다.
    
<hr>
<h2>풀이</h2>
<ol type="I">
    <li> 두 팩맨은 한 번의 이동으로 같이 움직이므로 이들의 정보와 이동한 경로를 함께 저장하는 구조체를 만들어 보관한다. </li>
    <li> BFS를 돌면서 2개의 팩맨 중 장외를 벗어나면 반대편에서 나타나도록 조정해주고, 벽을 만나면 제자리에서 멈추도록 조정해주었다. </li>
    <li> int visit[x1][y1][x2][y2] 를 통해 팩맨1과 팩맨2의 현재 좌표를 기준으로 처음 방문했을 때의 비용을 저장해준다. </li>
    <li> 이 후, 해당 비용을 토대로 다음으로 방문할 지역을 가지치기 한다. <li>
    <li> 두 팩맨이 만나게 되면 그 때 까지의 경로정보를 출력한다. </li>
</ol>
<hr>
<h2>실수</h2>
큐에 위치정보를 저장할 때 방문정보를 기준으로 하는 가지치기를 잘못 했다. 
(이차원 배열인 visit1과 visit2를 이용한 방문 정보, 두 팩맨이 모두 새로운 좌표로 이동하였을 때) 의 경우에만 가지치기를 하여서 큐에 너무 많은 쓸데없는 정보(중복)가 담겨서 메모리가 초과가 계속 났었다. 
