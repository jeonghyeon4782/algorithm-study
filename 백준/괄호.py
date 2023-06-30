for _ in range(int(input())):
    stack = []
    ans = 'YES'
    li = list(input())
    
    for i in range(len(li)):
        if li[i] == '(':
            stack.append(li[i])
        else:
            if len(li) > 0:
                stack.pop()
            else:
                ans = 'NO'
    
    if len(stack) > 0:
        ans = 'NO'
        
    print(ans)
    
# 정답 코드
for _ in range(int(input())):
    stk = []
    ans = 'YES'
    for ch in input():
        if ch == '(':
            stk.append(ch)
        else:
            if len(stk) > 0:
                stk.pop()
            else:
                ans = 'NO'

    if len(stk) > 0:
        ans = 'NO'

    print(ans)

        
    
    
