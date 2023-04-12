T = int(input()) # 테스트 케이스 수

for test_case in range(1, T + 1):
    
    sum = 0    
    nums = list(map(int, input().split()))
    
    for i in nums:
        if i % 2 != 0:
            sum += i
            
    print(f"#{test_case} {sum}")    
    