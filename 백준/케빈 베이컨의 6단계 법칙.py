from collections import deque

n , m  = map(int, input().split())
adj = [[False for _ in range(n)] for _ in range(n)]
for _ in range(m):
    a, b = map(int, input().split())
    adj[a-1][b-1] = adj[b-1][a-1] = True
    
ans = 0
ans_tot = 987654321
dist = [[0 for _ in range(n)] for _ in range(n)]

def bfs(start, end):
    chk = [False for _ in range(n)]
    dq = deque()
    chk[start] = True
    dq.append((start, 0))
    
    while dq:
        now, d = dq.popleft()
        if now == end:
            return d
        
        for nxt in range(n):
            if adj[now][nxt] and not chk[nxt]:
                chk[nxt] = True
                dq.append((nxt, d + 1))

for i in range(n):
    tot = 0
    for j in range(n):
        if i != j:
            if dist[i][j] == 0:
                dist[i][j] = dist[j][i] = bfs(i, j)
                
            tot += dist[i][j]
    
    if tot < ans_tot:
        ans = i
        ans_tot = tot
        
print(ans + 1)