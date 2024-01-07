dx = (1, -1, 0, 0)
dy = (0, 0, 1, -1)

n = int(input())
adj = [list(map(int, input())) for _ in range(n)]
chk = [[False for _ in range(n)] for _ in range(n)]
cnt = 0
ans = []

def is_valid(x, y):
    return 0 <= x < n and 0 <= y < n

def dfs(x, y):
    global cnt
    cnt += 1
    chk[x][y] = True
    
    for i in range(4):
        nx = x + dx[i]
        ny = y + dy[i]
        
        if is_valid(nx, ny) and adj[nx][ny] and not chk[nx][ny]:
            dfs(nx, ny)
            
for i in range(n):
    for j in range(n):
        if adj[i][j] and not chk[i][j]:
            cnt = 0
            dfs(i, j)
            ans.append(cnt)

print(len(ans))
ans.sort()
for k in ans:
    print(k)