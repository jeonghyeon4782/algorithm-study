for _ in range(int(input())):
    ans = 0
    adj = list(map(int, input().split()))

    for i in range(1, 21):
        for j in range(i, 0, -1):
            if adj[i] < adj[j]:
                ans += (i - 1)
                break
    print(adj[0], end=' ')
    print(ans)