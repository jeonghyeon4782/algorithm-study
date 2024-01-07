import sys
input = sys.stdin.readline
sys.setrecursionlimit(10**6)

dx = (1, -1, 0, 0)
dy = (0, 0, 1, -1)

n, m, k = map(int, input().split())
adj = [[False for _ in range(m)] for _ in range(n)]
chk = [[False for _ in range(m)] for _ in range(n)]
count = 0
ans = 0
for _ in range(k):
    x, y = map(int, input().split())
    adj[x-1][y-1] = True
    
def is_valid(x, y):
    return 0 <= x < n and 0 <= y < m

def dfs(x, y):
    global count
    count += 1
    chk[x][y] = True
    
    for i in range(4):
        nx = x + dx[i]
        ny = y + dy[i]
        
        if is_valid(nx, ny) and not chk[nx][ny] and adj[nx][ny]:
            dfs(nx, ny)
            
for i in range(n):
    for j in range(m):
        if not chk[i][j] and adj[i][j]:
            count = 0
            dfs(i, j)
            ans = max(ans, count)
            
print(ans)
            