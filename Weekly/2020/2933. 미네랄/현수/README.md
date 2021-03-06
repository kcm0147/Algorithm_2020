# 2933. 미네랄

<h2>느낀점</h2>
    상당한 구현력이 필요한 문제였다. BFS를 사용하는 것이라는 점은 금방 파악했으나, 이를 어떻게 적용할지를 떠올리는 것이 어려웠다. 특히 클러스터임을 찾을 수 있는 방법을 떠올리기 쉽지 않았다. 컨셉을 모두 정하고 이를 구현하는 것도 만만치 않았다. x, y 순으로 정렬한 클러스터 좌표들을 가지고 어떻게 낙하시킬지를 고민하는 부분에서도 2시간 정도? 쓴 거 같다. 최종적으로 이 문제에 대한 느낌은 상당한 구현이 필요한 녀석이었다는 것!
<hr>
<h2>풀이</h2>
<ol type="I">
    <li> 왼쪽 오른쪽 순으로 막대를 던져 깨지는 미네랄의 좌표를 저장한다. </li>
    <li> 상하좌우 방향으로 BFS를 시행하여 바닥에 도착 가능한지 확인한다 </li>
    <li> 바닥에 도착가능하다면 클러스터x, 바닥에 도착이 안된다면 공중에 떠있는 것이므로 클러스터이다 </li>
    <li> 클러스터인지 확인하는 함수에서 해당 클러스터에 대한 좌표들을 벡터에 저장하여 반환한다. </li>
    <li> 클러스터를 구성하는 좌표들을 x, y 순으로 정렬하여 각 세로줄 마다 가장 아래쪽에 있는 좌표에서 최소 낙하거리를 구한다. </li>
    <li> 모든 클러스터의 좌표들을 최소 낙하거리만큼 내려준다. 이 때 모양은 변하지 않는다. </li>
</ol>
<hr>
<h2> 실수 </h2>
클러스터의 모양이 변하지 않는다는 것을 간과했다. 낙하하면서 각 컬럼에 있는 좌표들을 모두 부딪힐 때까지 다 내렸었다. 이런 로직으로 테케에서는 통과했어서 이 점을 놓쳤었다! 다른 반례를 통해 잘못되었다는 것을 발견해서 수정할 수 있었다.

