from collections import deque

n = int(input())
a, b = map(int, input().split())
m = int(input())
adj = [[False for _ in range(n + 1)] for _ in range(n + 1)]
chk = [False for _ in range(n + 1)]

for _ in range(m):
    x, y = map(int, input().split())
    adj[x][y] = True
    adj[y][x] = True

def bfs(x, d):
    q = deque()
    chk[x] = True
    q.append((x, d))

    while q:
        nx, nd = q.popleft()
        if nx == b: return nd

        for i in range(1, n + 1):
            if not chk[i] and adj[nx][i]:
                chk[i] = True
                q.append((i, nd + 1))

    return -1

print(bfs(a, 0))