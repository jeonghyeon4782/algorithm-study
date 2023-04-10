# ============================ 내 코드 ==============================

n = int(input()) # 동전의 갯수 (최대 1000개)
coin = list(map(int, input().split())) # 동전리스트
coin.sort()
taget = 1

# 만약 target 보다 코인이 더 크면  그 타겟은 만들지 못한다.
for i in coin:
    if i > taget:
        break
    taget += i

print(taget)

# ============================ 정답 코드 ============================

n = int(input())
data = list(map(int, input().split()))
data.sort()

target = 1
for x in data:
    # 만들 수 없는 금액을 찾았을 때 반복 종료
    if target < x:
        break
    target += x

# 만들 수 없는 금액 출력
print(target)

# ============================ 해설 =================================

# 타겟 : 1, 2, 4, 6, 9, 13, 18, 28 
# 동전 : 1, 2, 2, 3, 4, 5,  10, 30

# 27까지는 만들 수 있다는 소리