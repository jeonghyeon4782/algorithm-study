from itertools import combinations

li = [i * (i+1) // 2 for i in (range(46))]

for _ in range(int(input())):
    num = int(input())
    found = False
    
    for i in combinations(li, 3):
        if sum(i) == num:
            found = True
            break
        
    print(1 if found == True else 0)
    
# 정답코드

# T = [n * (n + 1) // 2 for n in range(46)]


# def is_possible(K):
#     for i in range(1, 46):
#         for j in range(i, 46):
#             for k in range(j, 46):
#                 if T[i] + T[j] + T[k] == K:
#                     return 1

#     return 0


# for _ in range(int(input())):
#     print(is_possible(int(input())))


    