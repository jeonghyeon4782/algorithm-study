n , m = map(int, input().split())
arr = [0]

for i in range(1, 46):
    for _ in range(1, i + 1):
        arr.append(i)
        
print(sum(arr[n:m+1]))