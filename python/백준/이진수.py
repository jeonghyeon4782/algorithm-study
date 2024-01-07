for _ in range(int(input())):
    n = int(input())
    idx = 0
    
    while n > 0:
        if n % 2 == 1:
            print(idx, end=' ')
        n = n // 2
        idx += 1