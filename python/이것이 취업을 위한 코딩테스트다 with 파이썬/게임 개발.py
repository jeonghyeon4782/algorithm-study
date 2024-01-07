dx = (-1, 0, 1, 0)
dy = (0, 1, 0, -1)

n, m = map(int, input().split())
x, y, d = map(int, input().split()) # d = 0 : 북 0 동 1 남 2 서 3
adj = [list(map(int, input().split())) for _ in range(n)]
ans = 1

def is_valid(x, y):
    return 0 <= x < n and 0 <= y < m

def dfs(x, y):
    global ans
    for i in range(4):
        nx = x + dx[i]
        ny = y + dy[i]
        if is_valid(nx, ny) and adj[nx][ny] == 0:
            adj[nx][ny] = 1
            ans += 1
            dfs(nx, ny)

adj[x][y] = 1
dfs(x, y)
print(ans)