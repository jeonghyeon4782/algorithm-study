n = int(input())
drinks = list(map(int, input().split()))
drinks.sort()

for i in range(n - 1):
    drinks[-1] += (drinks[i] / 2)
    
if drinks[-1].is_integer():
    print(drinks[-1])
else:
    print(f'{drinks[-1]:.1f}')