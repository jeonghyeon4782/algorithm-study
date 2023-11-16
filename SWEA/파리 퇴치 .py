for t in range(1, int(input()) + 1):
    n , m = map(int, input().split())
    adj = [list(map(int, input().split())) for _ in range(n)]
    max_num = 0
    
    for i in range(n - m + 1):
        for j in range(n - m + 1):
            sum_num = 0
            for k in range(m):
                for o in range(m):
                    sum_num += adj[i + k][j + o]
            if sum_num > max_num:
                max_num = sum_num
                
    print(f'#{t} {max_num}')