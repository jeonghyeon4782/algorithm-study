from collections import deque

a, b = map(int, input().split())
q = deque()
q.append((a, 1))

while(q):
    na, nd = q.popleft()
    if na > b:
        continue
    if na == b:
        print(nd)
        break
    q.append((na * 2, nd + 1))
    q.append((na * 10 + 1, nd + 1))
else:
    print(-1)