for _ in range(int(input())):
    n = int(input())
    
    adj = [0 for _ in range(10001)]
    adj[1] = 1
    adj[2] = 2
    adj[3] = 4
    
    def dp(x):
        if adj[x] != 0:
            return adj[x]
        return dp(x - 1) + dp(x - 2) + dp(x - 3)
        
    print(dp(n))
    
    
    
