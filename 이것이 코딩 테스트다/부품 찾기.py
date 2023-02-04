def binary_search(arr, target, start, end):
    if start > end:
        return None
    mid = (end + start) // 2
    if arr[mid] == target:
        return mid
    elif arr[mid] > target:
        return binary_search(arr, target, start, mid - 1)
    else:
        return binary_search(arr, target, mid + 1, end)
        

n = int(input()) # 부품 갯수
arr = list(map(int, input().split())) # 부품 번호
arr.sort() # 오름차순 정렬

m = int(input()) # 찾을 부품 갯수
arr1 = list(map(int, input().split())) # 찾을 부품 번호

for i in arr1:
    a = binary_search(arr, i, 0, n-1)

    if a == None:
       print("no", end=' ')
    else:
        print("yes", end=' ')

    