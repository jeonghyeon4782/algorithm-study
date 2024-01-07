import sys
from collections import deque
dq = deque()
input = sys.stdin.readline

for _ in range(int(input())):
    a = list(input().split())
    if a[0] == 'push':
        dq.append(a[1])
    elif a[0] == 'pop':
        if len(dq):
            print(dq.popleft())
        else:
            print(-1)
    elif a[0] == 'size':
        print(len(dq))
    elif a[0] == 'empty':
        if len(dq):
            print(0)
        else:
            print(1)
    elif a[0] == 'front':
        if len(dq):
            print(dq[0])
        else:
            print(-1)
    elif a[0] == 'back':
        if len(dq):
            print(dq[-1])
        else:
            print(-1)
        