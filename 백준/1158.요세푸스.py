n, k = map(int, input().split()) # N명의 사람, K번째 사람
arr = [i for i in range(1, n+1)] # 총 n명의 사람의 순서
index = -1 # arr에서 찾아야 할 인덱스
result = [] # 출력 리스트

for i in range(n):
    index += k # index에 k를 더한다
    index %= n # index가 k이상이 될 수도 있으니 %연산자를 해준다.
    
    result.append(arr.pop(index)) # index번째의 숫자를 pop해준뒤 result에 넣는다.
    
    n -= 1 # pop을 해준 뒤 n의 수를 1 줄여준다.
    index -= 1 # 길이가 줄어 들었으므로 index 또한 1을 줄여준다.
    
print(f"<{', '.join(map(str, result))}>")

# 정답 코드
N, K = map(int, input().split())
peo = [i for i in range(1, N + 1)]
pt = 0
ans = []
for _ in range(N):
    pt += K - 1
    pt %= len(peo)
    ans.append(peo.pop(pt))

print(f"<{', '.join(map(str, ans))}>")
