T = int(input()) # 테스트 케이스 수

for test_case in range(1, T+1):
    n, m = map(int, input().split()) # N*N 배열의 크기, M*M 파리채의 크기
    graph = [list(map(int, input().split())) for _ in range(n)] 
    maxFly = 0 # 최대 파리 수
    
    # 각각의 자리에서 파리채만큼 더한 값을 비교
    for i in range(n-m+1):
        for j in range(n-m+1):
            data = []
        
            for k in range(m):
                for l in range(m):
                    data.append(graph[i+k][j+l])
            
            if sum(data) > maxFly:
                maxFly = sum(data)
                
    print(f'#{test_case} {maxFly}')