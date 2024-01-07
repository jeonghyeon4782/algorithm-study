import sys
input = sys.stdin.readline

stk = []
for _ in range(int(input())):
    x = int(input())
    if x == 0:
        stk.pop()
    else:
        stk.append(x)
    
print(sum(stk))
