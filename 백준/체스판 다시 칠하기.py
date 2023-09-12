n , m = map(int, input().split())

adj = [input() for _ in range(n)]
ans = n * m

def search(x, y, ch):
    global ans
    cnt = 0
    for i in range(8):
        for j in range(8):
            # 홀수
            if (i + j) % 2:
                if adj[x + i][y + j] == ch:
                    cnt += 1
            # 짝수
            else:
                if adj[x + i][y + j] != ch:
                    cnt += 1
    ans = min(ans, cnt)

for x in range(n - 7):
    for y in range(m - 7):
        search(x, y, 'W')
        search(x, y, 'B')
        
print(ans)