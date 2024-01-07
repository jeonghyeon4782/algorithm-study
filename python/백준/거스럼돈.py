coin = int(input())
ans = 0

while coin > 0:
    if coin % 5 == 0:
        ans += coin // 5
        break
    else:
        coin -= 2
        ans += 1
        if coin < 0:
            ans = -1
            
print(ans)
    
    