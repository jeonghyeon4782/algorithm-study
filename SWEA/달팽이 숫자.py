for t in range(1, int(input()) + 1):
    dx = (0, 1, 0, -1)
    dy = (1, 0, -1, 0)
    x = 0
    y = 0
    idx = 0
    n = int(input())
    adj = [[0 for _ in range(n)] for _ in range(n)]
    
    def is_valid(x, y):
        return 0 <= x < n and 0 <= y < n
    
    for i in range(1, n ** 2 + 1):
        
        adj[x][y] = i
        
        nx = x + dx[idx]
        ny = y + dy[idx]
        
        if is_valid(nx, ny) and not adj[nx][ny]:
            x = nx
            y = ny
        else:
            idx = (idx + 1) % 4
            x += dx[idx] 
            y += dy[idx]
    
    print(f'#{t}')
    for i in adj:
        print(' '.join(map(str, i)))
            
    