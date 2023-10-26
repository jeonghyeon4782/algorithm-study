n = int(input()) # 2 * n 직사각형
cache = [-1 for _ in range(1001)]
cache[1] = 1
cache[2] = 3

def dp(x):
    if cache[x] != -1:
        return cache[x]
    cache[x] = (dp(x - 1) + dp(x - 2) * 2) % 10007
    return cache[x]

print(dp(n))