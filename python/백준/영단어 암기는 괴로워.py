import sys

input = sys.stdin.readline
n ,m = map(int, input().split())
dic = {}

for _ in range(n):
    word = input().strip()
    if len(word) < m:
        continue
    else:
        if word not in dic:
            dic[word] = 1
        else:
            dic[word] += 1

dic = sorted(dic.items(), key=lambda x : (-x[1], -len(x[0]), x[0]))

for i in dic:
    print(i[0])
