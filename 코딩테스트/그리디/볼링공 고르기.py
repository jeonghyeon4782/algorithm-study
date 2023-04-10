# ============================ 내 코드 ==============================
from itertools import combinations # 조합 사용을 위한 내장 라이브러리

n, m = map(int, input().split()) # n : 볼링공 갯수, m : 공의 최대 무게
sets = list(map(int, input().split())) # 각각의 공들의 무게
data = list(combinations(sets, 2)) # 라이브러리를 이용한 조합
count = 0 # 중복이 없는 조합의 수

for i in data:
    if i[0] != i[1]:
        count+=1

print(count)

# ============================ 정답 코드 ============================

n, m = map(int, input().split())
data = list(map(int, input().split()))

# 1부터 10까지의 무게를 담을 수 있는 리스트
array = [0] * 11

for x in data:
    # 각 무게에 해당하는 볼링공의 개수 카운트
    array[x] += 1

result = 0
# 1부터 m까지의 각 무게에 대하여 처리
for i in range(1, m + 1):
    n -= array[i] # 무게가 i인 볼링공의 개수(A가 선택할 수 있는 개수) 제외
    result += array[i] * n # B가 선택하는 경우의 수와 곱해주기

print(result)

# ============================ 해설 =================================
# 이 문제는 풀이와 내가 푼 방식이 많이 다른 것 같다.
# 내가 푼 방식은 모든 조합을 만든 뒤 조합의 갯수에서 조합이 같은 무게이면
# 빼주는 방식을 택했다. << 조합 라이브러리를 선택해서 풀었지만 시간복잡도에서 문제가 있는 것 같다.
