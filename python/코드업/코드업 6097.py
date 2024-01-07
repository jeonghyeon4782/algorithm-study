h, w = map(int, input().split())  # h: 격자판 세로 길이, w: 격자판 가로 길이

n = int(input())  # n: 놓을 수 있는 막대 수

# 막대에 의해 가려진 경우 1, 아닌 경우 0으로 출력한다.

arr = [[0 for i in range(w)] for i in range(h)]

for i in range(n):
    # l : 막대길이, d: 방향(가로:0, 세로:1), x: x좌표, y: y좌표
    l, d, x, y = map(int, input().split())
    if d == 0:
        for j in range(l):
            arr[x-1][y-1+j] = 1
    else:
        for k in range(l):
            arr[x-1+k][y-1] = 1
            
for i in range(h):
    for j in range(w):
        print(arr[i][j], end=" ")
    print()