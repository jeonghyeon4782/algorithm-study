import sys

input = sys.stdin.readline
for _ in range(int(input())):
    n = int(input())
    prices = list(map(int, input().split()))
    max_price = prices[-1]
    ans = 0
    
    for i in range(n-1, -1, -1):
        if prices[i] > max_price:
            max_price = prices[i]
        else:
            ans += (max_price - prices[i])
    
    print(ans)