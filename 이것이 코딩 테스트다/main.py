stack = []

stack.append(5)
stack.append(1)
stack.append(3)
stack.pop()
stack.append(6)

print(stack) # [5, 1, 6]
print(stack[::-1]) # [6, 1, 5]


