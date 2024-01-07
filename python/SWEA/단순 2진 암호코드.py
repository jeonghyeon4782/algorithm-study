for t in range(1, int(input()) + 1):
    n, m = map(int, input().split())
    dic = {'1011000':0, '1001100':1, '1100100':2, '1011110':3, '1100010':4, '1000110':5, '1111010':6, '1101110':7, '1110110':8, '1101000':9}
    chk = []
    adj = [list(map(int, input())) for _ in range(n)]
    found = False
    
    for i in range(n - 1, -1, -1):
        for j in range(m - 1, -1, -1):
            if adj[i][j] == 1:
                for k in range(56):
                    chk.append(adj[i][j - k])
                found = True 
                break  
        if found:
            break
    
    h_num = dic[''.join(map(str, chk[7:14]))] + dic[''.join(map(str, chk[21:28]))] + dic[''.join(map(str, chk[35:42]))] + dic[''.join(map(str, chk[49:56]))]
    z_num = dic[''.join(map(str, chk[0:7]))] + dic[''.join(map(str, chk[14:21]))] + dic[''.join(map(str, chk[28:35]))] + dic[''.join(map(str, chk[42:49]))]
    
    ans = h_num + z_num if (h_num * 3 + z_num) % 10 == 0 else 0
    print(f'#{t} {ans}')
        
        
                