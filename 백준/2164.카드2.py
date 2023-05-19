from collections import deque

n = int(input())
dq = deque() # 덱 생성

# 1부터 N까지 dq에 삽입
for i in range(1, n+1):
    dq.append(i)

# dq의 요소가 하나 남으면 반복문 종료
while(len(dq) > 1):
    dq.popleft()
    second = dq.popleft()
    dq.append(second)
    
print(dq.popleft())
    
# 정답 코드
from collections import deque

dq = deque()
for i in range(1, int(input()) + 1):
    dq.append(i)

while len(dq) > 1:
    dq.popleft()
    dq.append(dq.popleft())

print(dq.pop())
