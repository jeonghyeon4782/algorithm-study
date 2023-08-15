import sys

stk = []
input = sys.stdin.readline

def push(x):
    stk.append(x)
def pop():
    if len(stk):
        print(stk.pop())
    else:
        print(-1)
def size():
    print(len(stk))
def empty():
    if len(stk):
        print(0)
    else:
        print(1)
def top():
    if len(stk):
        print(stk[-1])
    else:
        print(-1)
        
for _ in range(int(input())):
    ans = list(input().split())
    if ans[0] == 'push':
        push(ans[1])
    elif ans[0] == 'pop':
        pop()
    elif ans[0] == 'size':
        size()
    elif ans[0] == 'empty':
        empty()
    elif ans[0] == 'top':
        top()
            