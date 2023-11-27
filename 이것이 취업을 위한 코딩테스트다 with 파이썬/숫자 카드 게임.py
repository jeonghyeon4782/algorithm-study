n, m = map(int, input().split())
adj = [list(map(int, input().split())) for _ in range(n)]
ans = max([min(i) for i in adj])
print(ans)