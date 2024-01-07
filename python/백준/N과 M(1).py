from itertools import permutations

n, m = map(int, input().split())
adj = [i + 1 for i in range(n)]
ans = list(permutations(adj, m))

for i in ans:
    for j in i:
        print(j, end=' ')
    print()