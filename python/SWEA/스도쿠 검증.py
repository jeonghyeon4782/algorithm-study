import numpy as np

def chk(arr):
    for i in arr:
        if arr.count(i) > 1:
            return 0
    return 1

for t in range(1, int(input()) + 1):
    adj = [list(map(int, input().split())) for _ in range(9)]
    ans = 1

    for i in range(9):
        if chk(adj[i]) == 0:
            ans = 0
    
    for i in range(9):
        if chk([x[i] for x in adj]) == 0:
            ans = 0
    
    for i in range(0, 9, 3):
        for j in range(0, 9, 3):
            if chk([adj[i][j], adj[i][j+1], adj[i][j+2], adj[i+1][j], adj[i+1][j+1], adj[i+1][j+2], adj[i+2][j], adj[i+2][j+1], adj[i+2][j+2]]) == 0:
                ans = 0
                
    print(f'#{t} {ans}')

