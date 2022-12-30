# 내 코드

n = int(input())
arr = list(input().split())
x, y = 1, 1

arr2 = ['R', 'L', 'D', 'U']
dx = [0, 0, 1, -1]
dy = [1, -1, 0, 0]

for i in arr:
    for j in range(4):
        if (i == arr2[j]):
            a = x + dx[j]
            b = y + dy[j]
        
    if (a < 1 or b < 1 or a > n or b > n):
            continue
    
    x = a
    y = b

print(x, y)

#############################################################

# 정답 코드

# N 입력받기
n = int(input())
x, y = 1, 1
plans = input().split() # 자동 리스트

# L, R, U, D에 따른 이동 방향
dx = [0, 0, -1, 1]
dy = [-1, 1, 0, 0]
move_types = ['L', 'R', 'U', 'D']

# 이동 계획을 하나씩 확인
for plan in plans:
    # 이동 후 좌표 구하기
    for i in range(len(move_types)):
        if plan == move_types[i]:
            nx = x + dx[i]
            ny = y + dy[i]
    # 공간을 벗어나는 경우 무시
    if nx < 1 or ny < 1 or nx > n or ny > n:
        continue
    # 이동 수행
    x, y = nx, ny

print(x, y)


        