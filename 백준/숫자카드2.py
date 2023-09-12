from bisect import bisect_left, bisect_right

n = int(input())
arr = list(map(int, input().split()))
arr.sort()
m = int(input())
ans = []
for i in list(map(int, input().split())):
    ans.append(bisect_right(arr, i) - bisect_left(arr, i))

print(' '.join(map(str, ans)))