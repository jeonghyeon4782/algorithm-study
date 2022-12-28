N, M, K = map(int, input().split())
arr = list(map(int, input().split()))
arr.sort(reverse=True)

sum = (arr[0]*K+5) * (M//(K+1)) + (M % (K+1) * 6)

print(sum)