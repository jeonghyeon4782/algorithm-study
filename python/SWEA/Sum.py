for _ in range(10):
    n = int(input())
    adj = [list(map(int, input().split())) for _ in range(100)]
    max_value = 0
    
    row_sums = max([sum(row) for row in adj])
    col_sums = max([sum(col) for col in zip(*adj)])
    left_x = sum([adj[i][i] for i in range(100)] )
    right_x = sum([adj[99-i][99-i] for i in range(100)])
    
    max_value = max(row_sums, col_sums, left_x, right_x)
    
    print(f'#{n} {max_value}')