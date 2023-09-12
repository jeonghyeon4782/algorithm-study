# 탑다운
import sys

sys.setrecursionlimit(10**6)
n = int(input())
INT = 987654321
cache = [INT for _ in range(n + 1)]
cache[1] = 0

def dp(x):
    if cache[x] != INT:
        return cache[x]
    elif x % 6 == 0:
        cache[x] = min(dp(x // 3), dp(x // 2)) + 1
    elif x % 3 == 0:
        cache[x] = min(dp(x // 3), dp(x - 1)) + 1
    elif x % 2 == 0:
        cache[x] = min(dp(x // 2), dp(x - 1)) + 1  
    else:
        cache[x] = dp(x - 1) + 1
    return cache[x]

dp(n)
print(cache[n])

# 바텀업
n = int(input())
INT = 987654321
dp = [INT for _ in range(n + 1)]
dp[1] = 0
for i in range(2, n + 1):
    if i % 6 == 0:
        dp[i] = min(dp[i//3], dp[i//2]) + 1
    elif i % 3 == 0:
        dp[i] = min(dp[i//3], dp[i-1]) + 1
    elif i % 2 == 0:
        dp[i] = min(dp[i//2], dp[i-1]) + 1
    else:
        dp[i] = dp[i - 1] + 1
print(dp[n])