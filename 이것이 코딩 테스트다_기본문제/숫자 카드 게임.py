# 내 코드 
n, m = map(int, input().split())

arr = [list(map(int, input().split())) for _ in range(n)] #2차원 배열 입력받기
arr2 = []

for i in range(n):
    arr[i].sort()
    arr2.append(arr[i][0])

arr2.sort(reverse=True)

print(arr2[0])

###########################################################################

# 정답 코드
# N, M을 공백을 기준으로 구분하여 입력 받기
n, m = map(int, input().split())

result = 0
# 한 줄씩 입력 받아 확인하기
for i in range(n):
    data = list(map(int, input().split()))
    # 현재 줄에서 '가장 작은 수' 찾기
    min_value = min(data)
    # '가장 작은 수'들 중에서 가장 큰 수 찾기
    result = max(result, min_value)

print(result) # 최종 답안 출력

