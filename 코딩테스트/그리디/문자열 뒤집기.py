# ============================ 내 코드 ==============================

numList = list(input())  # 문자 리스트 받기
zeroCount = 0  # 0의 묶음 수
oneCount = 0  # 1의 묶음 수

# 첫 문자 확인 후 묶음 추가
if numList[0] == '0':
    zeroCount += 1
elif numList[0] == '1':
    oneCount += 1

# 앞 글자와 비교하면서 묶음 처리
for i in range(1, len(numList)):
    if numList[i] == '0':
        if numList[i] == numList[i-1]:
            continue
        else:
            zeroCount += 1
    else:
        if numList[i] == numList[i-1]:
            continue
        else:
            oneCount += 1

print(min(oneCount, zeroCount))

# ============================ 정답 코드 ============================

data = input()
count0 = 0  # 전부 0으로 바꾸는 경우
count1 = 0  # 전부 1로 바꾸는 경우

# 첫 번째 원소에 대해서 처리
if data[0] == '1':
    count0 += 1
else:
    count1 += 1

# 두 번째 원소부터 모든 원소를 확인하며
for i in range(len(data) - 1):
    if data[i] != data[i + 1]:
        # 다음 수에서 1로 바뀌는 경우
        if data[i + 1] == '1':
            count0 += 1
        # 다음 수에서 0으로 바뀌는 경우
        else:
            count1 += 1

print(min(count0, count1))


c = input().count
print(max(c("01"), c("10")))


# ============================ 해설 =================================

# 이 문제를 풀기 위해서는 0과 1의 묶음 수를 먼저 파악해야한다.
# 반복을 실행할 때 만약 다음 수와 같지 않고 1이면 1카운트 0이면 0카운트 같으면 통과
