arr = [0, 1, 2, 3, 4, 5, 6]

def search(x, adj):
    l = 0
    r = len(adj) - 1
    m = (l + r) // 2
    
    while l <= r:
        if adj[m] == x:
            print(f'{x} 찾았습니다.')
            return
        elif adj[m] > x:
            r = m - 1
        else:
            l = m + 1
        m = (r + l) // 2
    
    print("탐색 실패")
    
search(3, arr)