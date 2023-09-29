n = int(input())
s_num = 0

for i in range(1, 987654321):
    s_num += i
    if s_num > n:
        print(i-1)
        break