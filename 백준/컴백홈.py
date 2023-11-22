import sys
input = sys.stdin.readline
sys.setrecursionlimit(10**6)

dx = (0, 0, 1, -1)
dy = (1, -1, 0, 0)
r, c, k = map(int, input().split())
adj = [list(input()) for _ in range(r)]
ans = 0

def is_valid(x, y):
    return 0 <= x < r and 0 <= y < c

def dfs(x, y, d):
    global ans
    if d == k and x == 0 and y == c - 1:
        ans += 1
        return
    for i in range(4):
        nx = x + dx[i]
        ny = y + dy[i]
        if is_valid(nx, ny) and adj[nx][ny] =='.':
            adj[nx][ny] = 'T'
            dfs(nx, ny, d + 1)
            adj[nx][ny] = '.'

adj[r - 1][0] = 'T'
dfs(r - 1, 0, 1)
print(ans)