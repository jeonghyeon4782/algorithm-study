for test in range(1, int(input()) + 1):
    n , m = map(int, input().split())
    adj = [list(map(int, input().split())) for _ in range(n)]
    ans = 0

    def chk(adj):
        global ans
        for row in adj:
            cnt = 0
            for i in range(n):
                if row[i] == 1:
                    cnt += 1
                else:
                    if cnt == m: ans += 1
                    cnt = 0
            if cnt == m: ans += 1

    chk(adj)
    chk(zip(*adj))
    print(f'#{test} {ans}')
