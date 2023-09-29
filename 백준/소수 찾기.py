cnt = 0

def search(x):
    global cnt
    if x == 1: return
    for i in range(2, x // 2 + 1):
        if x % i == 0:
            return
    cnt += 1
    
n = int(input())
for i in map(int, input().split()):
    search(i)
    
print(cnt)