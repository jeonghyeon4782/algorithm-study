T = int(input())

# 우, 하, 좌, 상
dx = [0, 1, 0, -1]
dy = [1, 0, -1, 0]

for test_case in range(1, T+1):
    n = int(input()) # 가로 세로의 길이
    graph = [[0] * n for _ in range(n)] # n X n 의 2차원 배열 생성   
    dst = 0 # 이동 방향 (시작은 오른쪽 이동)
    x = y = 0 # x와 y의 값을 0으로 초기화
    
    for i in range(1, n**2 + 1):
        
        # 1부터 n의 제곱의 수만큼 배열에 대입
        graph[x][y] = i
        
        # 방향으로 x,y를 이동
        x += dx[dst]
        y += dy[dst]
        
        # 만약 배열을 벗어났거나 벌써 값 대입이 되어있다면?
        if x < 0 or y < 0 or x >= n or y >= n or graph[x][y] != 0:
            
            # x와 y값을 이전으로 되돌린다
            x -= dx[dst]
            y -= dy[dst]
            
            # 만약 dst가 4가 되면 0으로 변경
            dst = (dst + 1) % 4
            
            # 방향으로 x,y를 다시 이동
            x += dx[dst]
            y += dy[dst]
    
    print(f"#{test_case}")
    
    # [] 빼고 행 순서대로 출력
    for row in graph:
        print(*row)
        