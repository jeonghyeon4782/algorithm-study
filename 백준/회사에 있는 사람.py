import sys

s = set()
input = sys.stdin.readline

for _ in range(int(input())):
    log = list(input().split())
    
    if log[1] == 'enter':
        s.add(log[0])
    else:
        s.remove(log[0])

peo = list(s)
peo.sort(reverse=True)

for i in peo:
    print(i)
    
# 정답
import sys

input = sys.stdin.readline
s = set()
for _ in range(int(input())):
    name, el = input().split()
    if el == 'enter':
        s.add(name)
    else:
        s.remove(name)

for name in sorted(s, reverse=True):
    print(name)
