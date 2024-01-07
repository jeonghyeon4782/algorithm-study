# 완전탐색 풀이법
n, m = map(int,input().split())
arr = list(map(int, input().split()))
sum = 0

for c1 in arr:
    for c2 in arr:
        if c2 == c1:
            continue
        for c3 in arr:
            if c3 == c1 or c3 == c2:
                continue
            
            if c1 + c2 + c3 > sum and c1 + c2 + c3 <= m:
                sum = c1 + c2 + c3
                    
print(sum)

# 조합 풀이법
from itertools import combinations

n, m = map(int,input().split())
arr = list(map(int, input().split()))
combi = list(combinations(arr, 3))
ans = 0

for i in combi:
    if sum(i) > ans and sum(i) <= m:
        ans = sum(i)

print(ans)