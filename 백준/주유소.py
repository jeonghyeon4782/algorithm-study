N = int(input())
d = list(map(int, input().split()))
p = list(map(int, input().split()))

m = p[0]
res = d[0] * m

for i in range(1, N-1):
    if p[i] < m:
        m = p[i]
    res += (m * d[i])
    
print(res)





