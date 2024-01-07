import sys

input = sys.stdin.readline
tip = []
n = int(input())
ans = 0
idx = 1

for _ in range(n):
    coin = int(input())
    tip.append(coin)
    
tip.sort(reverse=True)

for coin in tip:
    if coin - (idx - 1) > 0:
        ans += coin - (idx - 1)
    idx += 1
    
print(ans)