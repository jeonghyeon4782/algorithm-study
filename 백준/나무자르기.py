n, m = map(int, input().split())
tree = list(map(int, input().split()))

def get_total_tree(h):
    ans = 0
    for t in tree:
        if t > h:
            ans += (t - h)
    return ans

def search(tree):
    lo = 0
    hi = max(tree)
    ans = 0
    mid = (lo + hi) // 2
    while lo <= hi:
        if get_total_tree(mid) >= m:
            ans = mid
            lo = mid + 1
        else:
            hi = mid - 1
        mid = (lo + hi) // 2 
    return ans
    
print(search(tree))