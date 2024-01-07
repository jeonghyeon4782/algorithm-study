import sys
sys.setrecursionlimit(10**6)
input = sys.stdin.readline

for _ in range(int(input())):
    dx = (1, -1, 0, 0)
    dy = (0, 0, 1, -1)

    m, n, k = map(int, input().split())
    adj = [[0 for _ in range(m)] for _ in range(n)]
    chk = [[False for _ in range(m)] for _ in range(n)]
    ans = 0

    def is_valid(x, y):
        return 0 <= x < n and 0 <= y < m

    def dfs(x, y):
        chk[x][y] = True
        
        for i in range(4):
            nx = x + dx[i] 
            ny = y + dy[i]
            
            if is_valid(nx, ny) and adj[nx][ny] and not chk[nx][ny]:
                dfs(nx, ny)
            
    for _ in range(k):
        a, b = map(int, input().split())
        adj[b][a] = 1
        
    for i in range(n):
        for j in range(m):
            if not chk[i][j] and adj[i][j]:
                dfs(i, j)
                ans += 1

    print(ans)
    

    
