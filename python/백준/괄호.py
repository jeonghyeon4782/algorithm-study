def judge(data):
    stk = []
    ans = 'YES'
    for i in data:
        if i == '(':
            stk.append(i)
        else:
            if len(stk):
                stk.pop()
            else:
                ans = 'NO'
    if len(stk):
        ans = 'NO'
    return ans

for _ in range(int(input())):
    n = input()
    print(judge(n))