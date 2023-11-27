n, m = map(int, input().split())
ans = 0
while True:
    if n == 1: break
    if n % m == 0: n //= m
    else: n -= 1
    ans += 1
print(ans)