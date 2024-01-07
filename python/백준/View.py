for i in range(1, 11):
    n = int(input())
    floors = list(map(int, input().split()))
    ans = 0
    
    
    for j in range(2, n - 2):
        view = min(floors[j] - floors[j - 2], floors[j] - floors[j - 1], floors[j] - floors[j + 2], floors[j] - floors[j + 1])
        if view > 0:
            ans += view
            
    print(f'#{i} {ans}')
                  