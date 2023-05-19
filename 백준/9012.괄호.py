def test(arr):
    stack = []
    
    for i in range(len(str)):
        if str[i] == '(':
            stack.append(str[i])
        else:
            if len(stack) == 0:
                return 'NO'
            else:
                stack.pop()
                
    if len(stack) != 0:
        return 'NO'
    else:
        return 'YES'
                
t = int(input()) # 테스트 케이스 갯수

for _ in range(t):
    str = list(input())
    print(test(str))
    
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

    
    