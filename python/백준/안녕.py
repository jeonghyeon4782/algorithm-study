n = int(input())

hps = [0]
happys = [0]
hps.extend(list(map(int, input().split())) ) # kg
happys.extend(list(map(int, input().split()))) # 가치
dp = [[0 for _ in range(101)] for _ in range(n + 1)]
for i in range(1, n + 1):
    for j in range(101):
        hp = hps[i]
        happy = happys[i]
        if j <= hp:
            dp[i][j] = dp[i - 1][j]
        else:
            dp[i][j] = max(dp[i - 1][j], dp[i - 1][j - hp] + happy)

ans = 0
for d in dp:
    for cnt in d:
        if cnt > ans:
            ans = cnt

print(ans)