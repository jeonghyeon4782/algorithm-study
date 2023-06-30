n, m = map(int, input().split())

peo = [i for i in range(1, n+1)]
ans = []
index = 0

for _ in range(n):
    index = (index + m - 1) % len(peo)
    ans.append(peo.pop(index))
    
print(f"<{', '.join(map(str, ans))}>")

# 정답 코드
N, K = map(int, input().split())
peo = [i for i in range(1, N + 1)]
pt = 0
ans = []
for _ in range(N):
    pt += K - 1
    pt %= len(peo)
    ans.append(peo.pop(pt))

print(f"<{', '.join(map(str, ans))}>")
    
