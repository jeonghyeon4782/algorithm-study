T = int(input())

for test_case in range(1, T+1):
    data = list(map(int, input().split()))
    avg = int(round((sum(data) / 10), 0))
    
    print(f"#{test_case} {avg}")
    