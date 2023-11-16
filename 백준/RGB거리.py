n = int(input())
cache = [987654321 for _ in range(n + 1)]

for i in range(1, n + 1):
    r, g, b = map(int, input().split())
    