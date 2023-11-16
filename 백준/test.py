from itertools import combinations

a = (1, 2, 3)
b = (4, 5, 6)

c = list(a + b)

print(list(combinations(c, 2)))