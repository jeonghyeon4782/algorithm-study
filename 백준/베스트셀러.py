books = dict()

for _ in range(int(input())):
    name = input()
    
    if name in books:
        books[name] += 1
    else:
        books[name] = 1

maxCount = max(books.values())
maxBooks = []

for n, m in books.items():
    if m == maxCount:
        maxBooks.append(n)

maxBooks.sort()
print(maxBooks[0])

# 정답 코드
books = dict()
for _ in range(int(input())):
    name = input()
    if name in books:
        books[name] += 1
    else:
        books[name] = 1

max_val = max(books.values())
arr = []
for k, v in books.items():
    if v == max_val:
        arr.append(k)

arr.sort()
print(arr[0])
