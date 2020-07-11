# 7432. 디스크 트리

# How To Solve

1) 문제를 보고 tree 형태로 풀면 좋을 듯 하였다. 

   input : 여러개의 full path 
   
   output : directory 구조를 자식부모간에 공백으로 구분하여 콘솔에 출력
   
2) node를 처음에는 data와 children으로 구성하였으나, data는 굳이 필요없다고 생각이 들어 제거하였다.

   children : treeMap 형태 -> child가 있는지 없는지 검색 가능, 자동으로 sorted된다.
   
3) parsing은 먼저 input을 split한 다음, 재귀적으로 input의 string들을 추가하였다.

   만약 현재 추가할 자식이 존재하지 않으면 추가하고, 재귀호출을 한다.
   
   putChildIfAbsent() : map의 putIfAbsent()기능에 더해 새로운 데이터를 추가하였을 때도 value를 반환하게 하였다.
   
4) 출력은 재귀함수를 이용하였다. 도우미 함수인 printChildren을 이용하여 호출자가 공백 리터럴을 사용하여 함수를 호출하지 않아도 되도록 하였다.

# 느낀점

1) 처음에는 input이 전체 경로인지 모르고 쉽지 않은걸 했는데 다시 읽어보니 문제를 잘못 읽음을 알 수 있었다.

   언제나 문제를 잘 읽어보고 input과 output이 뭔지 적어놓는 습관을 들일 필요가 있는 것 같다.
