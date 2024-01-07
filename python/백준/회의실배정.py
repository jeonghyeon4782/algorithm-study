import sys

input = sys.stdin.readline
arr = []
c_e = 0
cnt = 0

for _ in range(int(input())):
    s, e = map(int, input().split())
    arr.append((e, s))
    
arr.sort()

for e, s in arr:
    if s >= c_e:
        cnt += 1
        c_e = e

print(cnt)
        