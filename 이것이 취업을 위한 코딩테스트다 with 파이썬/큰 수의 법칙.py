n, m, k = map(int, input().split())
adj = list(map(int, input().split()))
adj.sort()
print((m // k) * (adj[-1] * k) + (m % k) * adj[-2])