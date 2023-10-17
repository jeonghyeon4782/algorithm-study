import sys
input = sys.stdin.readline

n = int(input()) # 스위치 수
switchs = list(map(int, input().split()))
m = int(input()) # 학생 수
cnt = 0

for _ in range(m):
    gender, s_num = map(int, input().split())
    
    if gender == 1:
        for i in range(s_num - 1, n, s_num):
            switchs[i] = 1 if switchs[i] == 0 else 0
            
    else:
        for i in range(1, 50):
            if s_num - 1 - i < 0 or s_num - 1 + i >= n:
                break
            if switchs[s_num - 1 - i] == switchs[s_num - 1 + i]: cnt += 1

        switchs[s_num - 1] = 1 if switchs[s_num - 1] == 0 else 0

        for j in range(1, cnt + 1):
            switchs[s_num - 1 + j] = 1 if switchs[s_num - 1 + j] == 0 else 0
            switchs[s_num - 1 - j] = 1 if switchs[s_num - 1 - j] == 0 else 0 

print(' '.join(map(str, switchs)))