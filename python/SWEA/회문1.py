def check(adj):
    idx = 1
    for i in range(len(adj) // 2):
        if adj[i] != adj[-idx]:
            return False
        idx += 1
    return True

for test in range(1, 11):
    ans = 0
    n = int(input())
    adj = [input() for _ in range(8)]

    for row in adj:
        for i in range(9-n):
            if check(row[i:i+n]):
                ans += 1

    for col in zip(*adj):
        for i in range(9-n):
            if check(col[i:i+n]):
                ans += 1

    print(f'#{test} {ans}')
