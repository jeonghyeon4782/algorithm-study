import sys
input = sys.stdin.readline

n, x = map(int, input().split())
visitor = list(map(int, input().split()))

temp = 0
cnt = 0

for i in range(n - x + 1):
    sum_visitor = sum(visitor[i : i + x])
    temp = max(temp, sum_visitor)
    
for i in range(n - x + 1):
    sum_visitor = sum(visitor[i : i + x])
    if temp == sum_visitor:
        cnt += 1
    
print(temp)
print(cnt)
    