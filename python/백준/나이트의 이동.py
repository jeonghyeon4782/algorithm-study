from collections import deque
import sys

input = sys.stdin.readline
l = 0
dx = (1, 2, 2, 1, -1, -2, -2, -1)
dy = (-2, -1, 1, 2, 2, 1, -1, -2)

def is_valid(x, y):
        return 0 <= x < l and 0 <= y < l

for _ in range(int(input())):

    l = int(input())
    sx, sy = map(int, input().split())
    ex, ey = map(int, input().split())
    
    chk = [[False for _ in range(l)] for _ in range(l)]
    
    dq = deque()
    chk[sx][sy] = True
    dq.append((sx, sy, 0))
        
    while dq:
        x, y, d = dq.popleft()
            
        if x == ex and y == ey:
            print(d)
            break
        
        for i in range(8):
            nx = x + dx[i]
            ny = y + dy[i]
            nd = d + 1
                
            if is_valid(nx, ny) and not chk[nx][ny]:
                chk[nx][ny] = True
                dq.append((nx, ny, nd))
    
                    