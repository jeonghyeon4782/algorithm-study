import heapq

arr = []
hq = []
n = int(input())

for _ in range(n):
    num = int(input())
    
    if num != 0:
        arr.append(num)
        heapq.heappush(hq, abs(num))
    
    else:
        if len(hq) > 0:
            value = heapq.heappop(hq)
            if -value in arr:
                print(-value)
                arr.remove(-value)
            else:
                print(value)
                arr.remove(value)
        else:
            print(0)

# 정답 코드
import heapq, sys

hq = []
input = sys.stdin.readline

for i in range(int(input())):
    value = int(input())
    if value == 0:
        print(heapq.heappop(hq)[1] if len(hq) > 0 else 0)
    else:
        heapq.heappush(hq, (abs(value), value))
