for t in range(1, 11):
    n = int(input())
    ans = 0
    floors = list(map(int, input().split()))
    for i in range(2, n - 2):
        x = min(floors[i] - floors[i + 1], floors[i] - floors[i + 2], floors[i] - floors[i - 1], floors[i] - floors[i - 2])
        x = x if x >0 else 0
        ans += x
    print(f'#{t} {ans}')
        