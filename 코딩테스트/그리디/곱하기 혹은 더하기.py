# ============================ 내 코드 ==============================

numList = list(input()) # 문자열 리스트로 입력
result = 0 # 결과 값

for i in numList:
    if i == 0 or result == 0:
        result += int(i)
    else:
        result *= int(i)

print(result)

# ============================ 정답 코드 ============================

data = input()

# 첫 번째 문자를 숫자로 변경하여 대입
result = int(data[0])

for i in range(1, len(data)):
    # 두 수 중에서 하나라도 '0' 혹은 '1'인 경우, 곱하기보다는 더하기 수행
    num = int(data[i])
    if num <= 1 or result <= 1:
        result += num
    else:
        result *= num

print(result)

# ============================ 해설 =================================

# 1. 만약 result가 0이거나 문자가 0이라면 더하기 실행
# 2. 다른 경우는 곱하기 실행