from collections import deque

dx = (1, 2, 2, 1, -1, -2, -2, -1)
dy = (-2, -1, 1, 2, 2, 1, -1, -2)
s = 0

def is_valid(x, y):
    return 0 <= x < s and 0 <= y < s

for _ in range(int(input())):
    
    s = int(input()) # 체스판 크기 s X s
    x, y = map(int, input().split()) # 현재 위치
    n, m = map(int, input().split()) # 이동할 위치
    
    board = [[0] * s for _ in range(s)] # 체스판
    chk = [[False] * s for _ in range(s)] # 방문 체크
    dq = deque()
    dq.append((x, y, 0))
    chk[x][y] = True
    
    while len(dq) > 0:
        nx, ny, nd = dq.popleft()
        
        if nx == n and ny == m:
            print(nd)
            break
        
        for i in range(8):
            mx = nx + dx[i]
            my = ny + dy[i]
            md = nd + 1
            
            if is_valid(mx, my) and not chk[mx][my]:
                chk[mx][my] = True
                dq.append((mx, my, md))
            
        
    
    
    
    
