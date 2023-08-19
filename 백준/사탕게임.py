n = int(input())
arr = [list(input()) for _ in range(n)]
max_count = 0

# 우, 좌, 상, 하
dx = [0, 0, 1, -1]
dy = [1, -1, 0, 0]

def is_valid(x, y):
    return x >= 0 and x < n and y >= 0 and y < n 

def check(arr):
    global max_count
    
    for i in range(n):
        
        count = 1
        # 가로 검사
        for j in range(1, n):
            if arr[i][j] == arr[i][j-1]:
                count += 1
            else:
                count = 1
                
            if max_count < count:
                max_count = count
            
        count = 1
        # 세로 검사
        for j in range(1, n):
            if arr[j][i] == arr[j-1][i]:
                count += 1
            else:
                count = 1
            if max_count < count:
                max_count = count
        
               
for x in range(n):
    for y in range(n):
        for i in range(4):
            nx = x + dx[i]
            ny = y + dy[i]
            
            if is_valid(nx, ny):
                arr[x][y], arr[nx][ny] = arr[nx][ny], arr[x][y]
                check(arr)
                arr[x][y], arr[nx][ny] = arr[nx][ny], arr[x][y]
                
print(max_count)