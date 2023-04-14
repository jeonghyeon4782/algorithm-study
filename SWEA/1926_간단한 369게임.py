n = int(input())

for i in range(1, n+1):
    
    sum = 0 
    i = str(i)
    sum = i.count('3') + i.count('6') + i.count('9')
    
    if sum == 0:
        print(i, end=' ')
        
    else:
        print('-' * sum, end=' ')
        