a = list(input())
b = []
c = []
d = True

for i in range(0, len(a)):
    if (i == 0):
        b.append('(')

    if (a[i] == '-'):
        b.append(')')

    b.append(a[i])

    if (a[i] == '-'):
        b.append('(')

    if (i == len(a) - 1):
        b.append(')')

for i in b:
    if (i == '(' or i == ')'):
        c.append(i)
    elif (i == '0' and d == True):
        continue
    elif (i == '+' or i == '-'):
        d = True
        c.append(i)
    else:
        c.append(i)
        d = False

c = "".join(c)

print(eval(c))
