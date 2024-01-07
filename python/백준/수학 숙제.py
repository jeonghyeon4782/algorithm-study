ans = []

for _ in range(int(input())):
    s = input()
    st = ''
    arr = []
    zero = False
    for c in s:
        if not c.isalpha():
            st += c
        else:
            if st != '':
                arr.append(st)
            st = ''
    if st != '':
        arr.append(st)
    for num in arr:
        ans.append(int(num))

ans.sort()
for i in ans:
    print(i)