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
