import sys
sys.setrecursionlimit(10**6)

dx = (1, -1, 0, 0)
dy = (0, 0, 1, -1)

n = int(input())
adj = [list(map(int, input().split())) for _ in range(n)]
chk = [[False for _ in range(n)] for _ in range(n)]
cnt = 0

def is_valid(x, y):
    return 0 <= x < n and 0 <= y < n

def dfs(x, y):
    chk[x][y] = True
    
    for i in range(4):
        nx = x + dx[i]
        ny = y + dy[i]
        
        if is_valid(nx, ny) and not chk[nx][ny] and adj[nx][ny] - n > 0:
            dfs(nx, ny)
            
for i in range(n):
    for j in range(n):
        if not chk[i][j] and adj[i][j] - n > 0:
            dfs(i, j)
            cnt += 1
            
print(cnt)