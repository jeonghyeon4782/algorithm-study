# from collections import deque

n, m = map(int, input().split())
board = [input() for _ in range(n)]
dx = [0, 0, -1, 1]
dy = [1, -1, 0, 0]
chk = [[False] * m for _ in range(n)]

def is_vaild(sx, sy):
    return 0 <= sx < n and 0 <= sy < m

def dfs(sx, sy, sd):
    if sx == n - 1 and sy == m - 1:
        print(sd)
        return
    
    for i in range(4):
        nx = dx[i] + sx
        ny = dy[i] + sy
        nd = sd + 1
        
        if is_vaild(nx, ny) and not chk[nx][ny] and board[nx][ny] == '1':
            chk[nx][ny] = True
            dfs(nx, ny, nd)
            
dfs(0,0,1)

dq = deque()
dq.append((0, 0, 1))
chk[0][0] = True

while len(dq) > 0:
    x, y, d = dq.popleft()
        
    if x == n - 1 and y == m - 1:
        print(d)
        break
        
    for i in range(4):
        sx = dx[i] + x
        sy = dy[i] + y
        sd = d + 1
            
        if is_vaild(sx, sy) and not chk[sx][sy] and board[sx][sy] == '1':
            chk[sx][sy] == True
            dq.append((sx, sy, sd))
            
# 정답 코드
from collections import deque

dy = (-1, 1, 0, 0)
dx = (0, 0, 1, -1) # 좌우상하

n, m = map(int, input().split())
board = [input() for _ in range(n)]
chk = [[False] * m for _ in range(n)]
dq = deque()
dq.append((0, 0, 1))
chk[0][0] = True

def is_valid(y, x):
    return 0 <= y < n and 0 <= x < m

while len(dq) > 0:
    y, x, d = dq.popleft()
    
    if (y == n - 1 and x == m - 1):
        print(d)
        break      
    
    for k in range(4):
        ny = y + dy[k]
        nx = x + dx[k]
        nd = d +  1
        
        if is_valid(ny, nx) and board[ny][nx] == '1' and not chk[ny][nx]:
            chk[ny][nx] = True
            dq.append((ny, nx, nd))
                