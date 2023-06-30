import heapq

hq = []
n = int(input())
res = 0

li = [list(map(int, input().split())) for _ in range(n)]

for i in range(n):
    for j in range(n):
        heapq.heappush(hq, -li[i][j])

for _ in range(n):
    res = heapq.heappop(hq)
    
print(-res)

# 정답 코드
import heapq

hq = []
n = int(input())

for _ in range(n):
    for ch in map(int, input().split()):
        if len(hq) >= n:
            heapq.heappushpop(hq, ch)
        else:
            heapq.heappush(hq, ch)

print(heapq.heappop(hq))


