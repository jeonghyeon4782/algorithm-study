import sys

input = sys.stdin.readline
N = int(input()) # 회의 갯수
arr = [] # 회의 리스트
index = 0
count = 0

for _ in range(N):
    s, e = map(int, input().split()) # 시작시간, 종료시간
    arr.append((e, s)) # 회의시간, 시작시간, 종료시간

arr.sort()

for e, s in arr:
    if s < index:
        continue
    count += 1
    index = e
    
print(count)