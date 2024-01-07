while True:
    st = input()
    ans = 'yes'
    if st[-1] == '.' and len(st) == 1:
        break
    stk = []
    for c in st:
        if c == '(' or c == '[':
            stk.append(c)
        elif c == ')':
            if not stk or stk[-1] != '(':
                ans = 'no'
                break
            stk.pop()
        elif c == ']':
            if not stk or stk[-1] != '[':
                ans = 'no'
                break
            stk.pop()
    if stk:
        ans = 'no'
    print(ans)
    
            