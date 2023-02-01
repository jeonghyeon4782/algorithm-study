n, k = map(int, input().split())

arr = []

count = 0

for i in range(n):
    arr.append(int(input()))
    
for j in range(n):
    if k > arr[n-1-j]:
        count += (k // arr[n-1-j])
        k = k % arr[n-1-j]
    
    if k == arr[n-1-j]:
        count += (k // arr[n-1-j])
        break
    
    if k == 0:
        break
    
print(count)


    