for i in range(1, int(input()) + 1):
    adj = list(map(int, input().split()))
    sum_num = 0
    for j in adj:
        if j % 2 == 1:
            sum_num += j
    print(f'#{i} {sum_num}')
               