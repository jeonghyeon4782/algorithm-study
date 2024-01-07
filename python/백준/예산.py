import sys

input = sys.stdin.readline
n = int(input())
country = list(map(int, input().split()))
cost = int(input())
total = 0

def search(start, end):
    global total
    global cost
    ans = 0
    while(start <= end):
        total = 0
        mid = (start + end) // 2
        
        for c in country:
            if c  > mid:
                total += mid
            else:
                total += c
            
        if total > cost:
            end = mid - 1
        else:
            ans = mid
            start = mid + 1
           
    return ans

print(search(0, max(country)))