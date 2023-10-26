def  함수(n, m):
    ans = 0
    for i in range(n, m + 1):
        ans += (i + n)
    return ans

print(함수(6 , 9))