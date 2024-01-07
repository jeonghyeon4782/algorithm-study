for test in range(1, int(input()) + 1):
    p, q, r, s, w = map(int, input().split())
    A = w * p
    add_cost = 0 if w <= r else (w - r)  * s
    B = q + add_cost
    print(f'#{test} {min(A, B)}')