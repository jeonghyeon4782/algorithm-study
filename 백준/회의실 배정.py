import sys

input = sys.stdin.readline
meeting = []
count = 0
index = 0

for _ in range(int(input())):
    a, b = map(int,input().split())
    meeting.append((b, a))

meeting.sort()

for end, start in meeting:
    if start >= index:
        count += 1
        index = end

print(count)

# 정답코드
import sys

input = sys.stdin.readline
meetings = []
for _ in range(int(input())):
    start, end = map(int, input().split())
    meetings.append((end, start))

meetings.sort()
t = 0
ans = 0
for end, start in meetings:
    if t <= start:
        ans += 1
        t = end

print(ans)
