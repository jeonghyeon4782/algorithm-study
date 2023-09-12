from itertools import combinations

n , m = map(int, input().split())
houses = []
chickens = []

for x in range(n):
    for y, val in enumerate(list(map(int, input().split()))):
        if val == 1:
            houses.append((x, y))
        elif val == 2:
            chickens.append((x, y))
            
def get_dist(dist1, dist2):
    return abs(dist1[0] - dist2[0]) + abs(dist1[1] - dist2[1])
            
ans = 987654321
for comb in combinations(chickens, m):
    tot = 0
    for house in houses:
        tot += min(get_dist(house, chicken) for chicken in comb)
    ans = min(ans, tot)
    
print(ans)