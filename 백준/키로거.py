for _ in range(int(input())):
    password = input()
    stack1 = [] # 문자 모음
    stack2 = [] # 나온 문자 모음
    
    for i in password:
        if i == '<':
            if len(stack1) > 0:
                stack2.append(stack1.pop())
        elif i == '>':
            if len(stack2) > 0:
                stack1.append(stack2.pop())
        elif i == '-':
            if len(stack1) > 0:
                stack1.pop()
        else:
            stack1.append(i)

    print(''.join(stack1)+ ''.join(reversed(stack2)))