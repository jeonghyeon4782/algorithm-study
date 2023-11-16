for t in range(1, int(input()) + 1):
    n = int(input())
    costs = list(map(int, input().split()))
    ans = 0
    max_cost = costs[-1]
    for cost in costs[-2 : : -1]:
        if cost > max_cost:
            max_cost = cost
        else:
            ans += (max_cost - cost)
    print(f'#{t} {ans}')