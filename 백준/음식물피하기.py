# n, m, k = map(int, input().split()) # N X M, k : 쓰레기 갯수
# board = [[0] * m for _ in range(n)]
# visited = [[False] * m for _ in range(n)]
# dx = (0, 0, -1, 1)
# dy = (1, -1, 0, 0)
# count = 0

# for _ in range(k):
#     x, y = map(int, input().split())
#     board[x-1][y-1] = 1
    
# def is_valid(mx, my):
#     return 0 <= mx < n and 0 <= my < m
    
# def dfs(mx, my, md):
#     global count
#     for i in range(4):
#         nx = mx + dx[i]
#         ny = my + dy[i]
#         nd = md + 1
        
#         if is_valid(nx, ny) and board[nx][ny] == 1 and not visited[nx][ny]:
#             count = max(count, nd)
#             visited[nx][ny] = True
#             dfs(nx, ny, nd)

# for i in range(n):
#     for j in range(m):
#         dfs(i, j, 1)

# print(count)        

# 정답 코드
import sys
sys.setrecursionlimit(10**6)

s, ans = 0, 0
dx = (0, 0, -1, 1)
dy = (1, -1, 0, 0)
n, m , k = map(int, input().split())
board = [[0] * m for _ in range(n)]
chk = [[False] * m for _ in range(n)]

def is_valid(nx, ny):
    return 0 <= nx < n and 0 <= ny < m

def dfs(nx, ny):
    global s, ans
    chk[nx][ny] = True
    s += 1
    ans = max(ans, s)
    
    for i in range(4):
        mx = nx + dx[i]
        my = ny + dy[i]
        
        if is_valid(mx, my) and chk[mx][my] == False and board[mx][my] == 1:
            dfs(mx, my)

for _ in range(k):
    x, y = map(int, input().split())
    board[x - 1][y - 1] = 1
    

for i in range(n):
    for j in range(m):
        if not chk[i][j] and board[i][j] == 1:
            s = 0
            dfs(i, j)
        
print(ans)

