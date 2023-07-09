N, L = map(int, input().split())
li = list(map(int,input().split()))
li.sort()

count = 1
index = li[0] + L - 0.5

for i in range(1, N):
    if li[i] < index:
        continue
    else:
        count += 1
        index = li[i] + L - 0.5
        
        
print(count)

# 정답 코드