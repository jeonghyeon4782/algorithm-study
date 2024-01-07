n , m = map(int, input().split())
max_basket = m
min_basket = 1
ans = 0

for _ in range(int(input())):
    k = int(input())        

    if min_basket <= k <= max_basket:
        continue
    else:
        if k > max_basket:
            l = k - max_basket
            max_basket += l
            min_basket += l
            ans += l
        else:
            l = min_basket - k
            min_basket -= l
            max_basket -= l
            ans += l
    
print(ans)
        
    