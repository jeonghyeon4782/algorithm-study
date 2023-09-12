idx = 1

while True:
    l, p, v = map(int, input().split())
    ans = 0
    
    if l == 0 and p == 0 and v == 0:
        break
    
    ans = ((v // p) * l)
    
    if (v % p) > l:
        ans += l
    else:
        ans += (v % p)
        
    print(f'Case {idx}: {ans}')
    idx += 1
    