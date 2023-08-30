from collections import deque

dx = (1, -1, 0, 0)
dy = (0, 0, -1, 1)

r, c = map(int, input().split())
adj = [input() for _ in range(r)]
chk = [[set() for _ in range(c)] for _ in range(r)]
ans = 0

def is_valid(x, y):
    return 0 <= x < r and 0 <= y < c

def bfs(x, y, s):
    global ans
    dq = deque()
    dq.append((x, y, s))
    chk[x][y].add(s)
    
    while dq:
        x, y, s = dq.popleft()
        ans = max(ans, len(s))
        
        for i in range(4):
            nx = x + dx[i]
            ny = y + dy[i]
            
            if is_valid(nx, ny) and adj[nx][ny] not in s:
                ns = s + adj[nx][ny]
                if ns not in chk[nx][ny]:
                    chk[nx][ny].add(ns)
                    dq.append((nx, ny, ns))
        
bfs(0, 0, adj[0][0])            
print(ans)
print(chk)
    