for _ in range(int(input())):
    n = int(input())
    scores = list(map(int, input().split()))
    scores_rank = [0 for _ in range(101)]
    max_rank = 0
    ans = 0
    
    for i in scores:
        scores_rank[i] += 1
        
    for i in range(0, 101):
        if scores_rank[i] >= max_rank:
            max_rank = scores_rank[i]
            ans = i
    
    print(f'#{n} {ans}')