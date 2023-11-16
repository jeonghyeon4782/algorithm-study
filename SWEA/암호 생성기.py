from collections import deque

for test in range(10):
    dq = deque()
    idx = 0

    n = int(input())
    for num in list(map(int, input().split())):
        dq.append(num)

    while True:
        idx = (idx % 5) + 1
        num = dq.popleft() - idx
        if num < 1:
            dq.append(0)
            break
        else:
            dq.append(num)

    ans = ' '.join(map(str, dq))
    print(f'#{n} {ans}')