def search(arr, item):
    left = 0
    right = len(arr) - 1
    mid = (left + right) // 2
    
    while left <= right:
        if arr[mid] == item:
            print(arr[mid])
            break
        elif arr[mid] > item:
            right = mid - 1
        else:
            left = mid + 1
        
        mid = (left + right) // 2
        
arr = [0,1,2,3,4,5,6]
search(arr, 3)