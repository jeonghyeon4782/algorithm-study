n = int(input())
costs = []
ans = 0
for _ in range(n):
    costs.append(int(input()))
    
costs.sort(reverse=True)

for i in range(2, n, 3):
    ans += costs[i]

print(sum(costs) - ans)