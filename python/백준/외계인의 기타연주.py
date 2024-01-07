import sys

input = sys.stdin.readline
n, p = map(int, input().split())
stack = [[] for _ in range(7)]
cnt = 0
            
for _ in range(n):
    a, b = map(int, input().split())
    
    if not stack[a]:
        stack[a].append(b)
        cnt += 1
    else:
        while(True):
            if not stack[a]:
                stack[a].append(b)
                cnt += 1
            elif stack[a][-1] < b:
                stack[a].append(b)
                cnt += 1
                break
            elif stack[a][-1] > b:
                stack[a].pop()
                cnt += 1
            else:
                break
     
print(cnt)
        