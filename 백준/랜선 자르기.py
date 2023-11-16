import sys

input = sys.stdin.readline
k, n = map(int, input().split())
lines = []

for _ in range(k):
    lines.append(int(input()))
    
# 길이가 x일 경우 나오는 랜선 갯수 구하기 함수
def cut(x):
    cnt = 0
    for i in lines:
        cnt += (i // x)
    return cnt

s = 1
e = max(lines)
ans = 0

while s <= e:
    m = (s + e) // 2
    
    if cut(m) < n:
        e = m - 1
    else:
        s = m + 1
        
print(e)
