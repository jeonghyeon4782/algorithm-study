T = int(input())

for test_case in range(1, T+1):
    graph = []
    check = 1
    
    for i in range(9):
        graph.append(list(map(int, input().split())))
        
    for i in range(9):
        arr = [1, 2, 3, 4, 5, 6, 7, 8, 9]
        for j in range(9):
            if graph[i][j] in arr:
                arr.remove(graph[i][j])
            else:
                check = 0
    
    for i in range(9):
        arr = [1, 2, 3, 4, 5, 6, 7, 8, 9]
        for j in range(9):
            if graph[j][i] in arr:
                arr.remove(graph[j][i])
            else:
                check = 0
                
    for i in range(9):
        arr = [1, 2, 3, 4, 5, 6, 7, 8, 9]
        for j in range(9):
            if graph[j][i] in arr:
                arr.remove(graph[j][i])
            else:
                check = 0
                
    for i in range(0, 9, 3):
        for j in range(0, 9, 3):
            data = [graph[i][j], graph[i][j+1], graph[i][j+2], graph[i+1][j], graph[i+1][j+1], graph[i+1][j+2], graph[i+2][j], graph[i+2][j+1], graph[i+2][j+2]]
            arr = [1, 2, 3, 4, 5, 6, 7, 8, 9]
            for k in range(9):
                if data[k] in arr:
                    arr.remove(data[k])
                else:
                    check = 0
            
    print(f'#{test_case} {check}')
                
    
                