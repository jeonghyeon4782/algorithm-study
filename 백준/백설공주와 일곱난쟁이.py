from itertools import combinations

num = []
res = tuple()
for _ in range(9):
    num.append(int(input()))
    
for i in combinations(num, 7):
    if sum(i) == 100:
        res = i

for i in res:
    print(i)
    
# 정답 코드
T = [n * (n + 1) // 2 for n in range(46)]


def is_possible(K):
    for i in range(1, 46):
        for j in range(i, 46):
            for k in range(j, 46):
                if T[i] + T[j] + T[k] == K:
                    return 1

    return 0


for _ in range(int(input())):
    print(is_possible(int(input())))
