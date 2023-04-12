T = int(input())
for test_case in range(1, T+1):
    
    num = int(input())
    count = [0] * 101 # 점수들의 빈수를 저장
    data = list(map(int, input().split())) # 점수들을 저장
    
    for i in data:
        count[i] += 1
        
    score = 0 # 최고 점수
    frequency = 0 # 최빈 수
        
    for i in range(1, 101):
        if frequency < count[i]:
            frequency = count[i]
            score = i
        
        elif frequency == count[i]:
            score = max(i, score)
            
    print(f"#{num} {score}")
        
    
    

            
    
    