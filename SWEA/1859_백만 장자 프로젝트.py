T = int(input()) # 테스트케이스 수

for test_case in range(1, T+1):
    
    n = int(input()) # 매매가 수
    data = list(map(int, input().split())) # 각각의 매매가
    maxData = data[-1] # 마지막날 매매가를 최댓값으로 가정
    sum = 0 # 취하는 이득
    
    for i in range(len(data) -2, -1, -1):
        # 만약 이 날의 매매가가 maxData보다 크면 maxData 변경
        if data[i] >= maxData:
            maxData = data[i]
        # 만약 최댓값보다 작다면 최댓값 - 매매가를 이득에 더한다
        else:
            sum += maxData - data[i]
    
    print(f"#{test_case} {sum}")
        