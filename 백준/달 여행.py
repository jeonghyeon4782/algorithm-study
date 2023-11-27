n, m = map(int, input().split())
adj = [list(map(int, input().split())) for _ in range(n)]
chk = [[False for _ in range(m)] for _ in range(n)]

def is_valid(x, y):
    return 0 <= x < n and 0 <= y < m

ans = []
def dfs(x, y, d, c):
    if x == n - 1:
        ans.append(d)
    if c == 'v':
        for nx, ny, nc in [(x + 1, y - 1, '<'), (x + 1, y + 1, '>')]:
            if is_valid(nx, ny) and not chk[nx][ny]:
                chk[nx][ny] = True
                dfs(nx, ny, d + adj[nx][ny], nc)
                chk[nx][ny] = False
    elif c == '<':
        for nx, ny, nc in [(x + 1, y, 'v'), (x + 1, y + 1, '>')]:
            if is_valid(nx, ny) and not chk[nx][ny]:
                chk[nx][ny] = True
                dfs(nx, ny, d + adj[nx][ny], nc)
                chk[nx][ny] = False
    elif c == '>':
        for nx, ny, nc in [(x + 1, y - 1, '<'), (x + 1, y, 'v')]:
            if is_valid(nx, ny) and not chk[nx][ny]:
                chk[nx][ny] = True
                dfs(nx, ny, d + adj[nx][ny], nc)
                chk[nx][ny] = False
    else:
        for nx, ny, nc in [(x + 1, y - 1, '<'), (x + 1, y, 'v'), (x + 1, y + 1, '>')]:
            if is_valid(nx, ny) and not chk[nx][ny]:
                chk[nx][ny] = True
                dfs(nx, ny, d + adj[nx][ny], nc)
                chk[nx][ny] = False

for i in range(m):
    dfs(0, i, adj[0][i], 'start')

print(min(ans))