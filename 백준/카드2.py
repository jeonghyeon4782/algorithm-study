from collections import deque

dq = deque()
N = int(input())

for i in range(1, N+1):
    dq.append(i)
    
while len(dq) > 1:
    dq.popleft()
    dq.append(dq.popleft())

print(dq[0])

# 정답 코드
from collections import deque

dq = deque()
for i in range(1, int(input()) + 1):
    dq.append(i)

while len(dq) > 1:
    dq.popleft()
    dq.append(dq.popleft())

print(dq.pop())
