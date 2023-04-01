n = int(input()) # 사람의 수
time_arr = list(map(int, input().split())) # 각각의 사람이 돈을 인출하는데 걸리는 시간
sum = 0
result = 0
time_arr.sort()
for i in time_arr:
    sum += i
    result += sum

print(result)