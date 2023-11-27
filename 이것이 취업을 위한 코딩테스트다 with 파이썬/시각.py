h = 0
m = 0
s = 0
ans = 0
n = int(input())

while True:
    nh = s // 3600
    nm = s % 3600 // 60
    ns = s % 60
    if nh == n + 1:
        break
    if '3' in str(nh) + str(nm) + str(ns):
        ans += 1
    s += 1

print(ans)