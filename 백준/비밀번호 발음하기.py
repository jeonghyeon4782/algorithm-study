def one(s):
        for c in s:
            if c in a:
                return True
        return False

def two(s):
    for i in range(len(s) - 2):
        if (s[i] in a) == (s[i + 1] in a) == (s[i + 2] in a):
            return False
    return True

def three(s):
    for i in range(len(s) - 1):
        if s[i] == s[i+1] and s[i] != 'e' and s[i] !='o':
            return False
    return True

while True:
    s = input()
    a = ['a', 'e', 'i', 'o', 'u'] # 모음

    if s == 'end':
        break

    if one(s) and two(s) and three(s):
        print(f'<{s}> is acceptable.')
    else:
        print(f'<{s}> is not acceptable.')
        
            