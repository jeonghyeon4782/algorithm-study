for test in range(1, int(input()) + 1):
    s = input()

    for i in range(1, 11):
        if s[0:i] == s[i:i+i]:
            print(f'#{test} {len(s[0:i])}')
            break