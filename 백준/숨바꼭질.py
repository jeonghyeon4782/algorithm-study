from collections import deque

n, k = map(int, input().split())

def bfs(x, d):
    chk = [False for _ in range(100001)]
    chk[x] = True
    dq = deque()
    dq.append((x, d))
    
    while dq:
        x, d = dq.popleft()
        
        if x == k:
            return d
        
        for i in (x + 1, x - 1, x * 2):
            if 0 <= i <= 100000 and not chk[i]:
                dq.append((i, d + 1))
                chk[i] = True
            
print(bfs(n, 0))

        
    
    
