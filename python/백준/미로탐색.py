from collections import deque

dx = (1, -1, 0, 0)
dy = (0, 0, -1, 1)

n, m = map(int, input().split()) # n X m
adj = [input() for _ in range(n)]
chk = [[False] * m for _ in range(n)]

def is_valid(x, y):
    return 0 <= x < n and 0 <= y < m

def bfs(x, y, d):
    dq = deque()

    dq.append((x, y, d))
    chk[x][y] = True
    
    while dq:
        xx, yy, dd = dq.popleft()
        
        if xx == n - 1 and yy == m - 1:
            print(dd)
            return 
        
        for i in range(4):
            nx = xx + dx[i]
            ny = yy + dy[i]
            nd = dd + 1
            
            if is_valid(nx, ny) and adj[nx][ny] == '1' and not chk[nx][ny]:
                dq.append((nx, ny, nd))
                chk[nx][ny] = True
                
bfs(0, 0, 1)
                