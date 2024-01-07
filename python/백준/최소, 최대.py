n = int(input())
adj = list(map(int, input().split()))
adj.sort()

print(adj[0], end=' ')
print(adj[n-1])