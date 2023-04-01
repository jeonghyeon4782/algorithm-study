n = list(input()) # N을 입력
m = sorted(n, reverse=True) # 입력 받은 N을 내림차순 정렬
k = int(''.join(m)) # 가장 큰수를 정수화

if '0' in m:
    num = k # 정수형 num
    for _ in range(k):
        if num % 30 == 0:
            num_list = list(str(num))
            num_list = sorted(num_list, reverse=True)
            if m == num_list:
                print(num)
                break
        num -= 1
    
else:
    print("-1")
    
print("======================================================")

# 30의 배수가 되기 위한 조건은 0이 항상 포함 되어야 한다.
# 30의 배수가 되기 위한 조건은 모든 수의 합이 3의 배수여야 한다.

n = list(input())

sum = 0

if '0' not in n:
    print('-1')

else:
    for i in n:
        sum += int(i)
    
    if sum % 3 == 0:
        n.sort(reverse=True)
        result = int(''.join(n))
        print(result)
    else:
        print('-1')
        





