for idx in range(int(input())):
    n = int(input())
    prices = list(map(int, input().split()))
    maxPrice = prices[-1]
    ans = 0
    
    for i in range(n-2, -1, -1):
        if prices[i] > maxPrice:
            maxPrice = prices[i]
        else:
            ans += (maxPrice - prices[i])
    
    print(f'#{idx+1} {ans}')
    