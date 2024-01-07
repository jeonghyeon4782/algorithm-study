n, c = map(int, input().split())
adj = list(map(int, input().split()))
cnt_dic = {}

for i in adj:
    if i not in cnt_dic:
        cnt_dic[i] = 1
    else:
        cnt_dic[i] += 1
        
for key, value in sorted(cnt_dic.items(), key = lambda x : x[1], reverse=True):
    for _ in range(value):
        print(key, end=' ')