import sys
from collections import deque

input = sys.stdin.readline

n , m = map(int, input().split())
adj = [[False for _ in range(n + 1)] for _ in range(n + 1)]
chk = [False for _ in range(n + 1)] 
ans = 0

for _ in range(m):
    a, b = map(int, input().split())
    adj[a][b] = adj[b][a] = True
    
def bfs(x):
    dq = deque()
    chk[x] = True
    dq.append(x)
    
    while dq:
        num = dq.popleft()
        
        for i in range(1, n + 1):
            if num != i and not chk[i] and adj[num][i]:
                dq.append(i)
                chk[i] = True
                
for i in range(1, n + 1):
    if not chk[i]:
        bfs(i)
        ans += 1
        
print(ans)