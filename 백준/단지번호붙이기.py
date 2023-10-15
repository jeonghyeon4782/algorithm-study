dx = (1, -1, 0, 0)
dy = (0, 0, 1, -1)

n = int(input())
houses = []
cnt = 0
adj = [list(map(int, input())) for _ in range(n)]
chk = [[False for _ in range(n)] for _ in range(n)]

def is_valid(x, y):
    return 0 <= x < n and 0 <= y < n

def dfs(x, y):
    global cnt
    cnt += 1
    
    for k in range(4):
        nx = x + dx[k]
        ny = y + dy[k]
        
        if is_valid(nx, ny) and not chk[nx][ny] and adj[nx][ny]:
            chk[nx][ny] = True
            dfs(nx, ny)
            
for i in range(n):
    for j in range(n):
        cnt = 0
        if not chk[i][j] and adj[i][j]:
            chk[i][j] = True
            dfs(i, j)
            houses.append(cnt)
            
houses.sort()
print(len(houses))
for i in houses:
    print(i)