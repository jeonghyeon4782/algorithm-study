# 2차원 배열 값 넣기
arr = [list(map(int, input().split())) for i in range(19)]

n = int(input())

for i in range(n) :
    x, y = map(int, input().split())
    
    for j in range(19):
        num = arr[x-1][j]
        if num == 0:
            arr[x-1][j] = 1
        else :
            arr[x-1][j] = 0

    for k in range(19):
        num = arr[k][y-1]
        if num == 0:
            arr[k][y-1] = 1
        else :
            arr[k][y-1] = 0
            
            
for i in range(19) :
    for j in range(19) :
        print(arr[i][j], end=" ")
    print()