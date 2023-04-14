T = int(input())

for test_case in range(1, T+1):
    
    n, m = map(int, input().split())
    graph = []
    
    for i in range(n):
        graph.append(list(map(int, input().split())))
        
    
    