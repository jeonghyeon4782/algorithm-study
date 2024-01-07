n, l = map(int, input().split())
coord = [False] * 1001
x = 0
cnt = 0

for i in map(int, input().split()):
    coord[i] = True
    
while x <= 1000:
    if coord[x]:
        cnt += 1
        x += l
    else:
        x += 1
        
print(cnt)