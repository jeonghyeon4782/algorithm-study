import heapq, sys

h = []
input = sys.stdin.readline

for _ in range(int(input())):
    n = int(input())
    
    if n != 0:
        heapq.heappush(h, (abs(n), n))
    else:
        if len(h) > 0:
            print(heapq.heappop(h)[1])
        else:
            print(0)
            
# 정답 코드
import sys, heapq

input = sys.stdin.readline
hq = []
for _ in range(int(input())):
    x = int(input())
    if x == 0:
        print(heapq.heappop(hq)[1] if len(hq) else 0)
    else:
        heapq.heappush(hq, (abs(x), x))