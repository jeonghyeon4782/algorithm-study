n, m = map(int, input().split())
visits = list(map(int, input().split()))

window = sum(visits[:m])
max_window = window
cnt = 1

for i in range(n-m):
    window = window + visits[i + m] - visits[i]
    if max_window == window:
        cnt += 1
    else:
        cnt = 1
    max_window = max(window, max_window)

if max_window == 0:
    print('SAD')
else:
    print(max_window)
    print(cnt)
