import sys
input = sys.stdin.readline

for test in range(int(input())):
    n = int(input())
    p = 5
    ans = 0

    while n >= p:
        ans += (n // p)
        p = p * 5

    print(ans)