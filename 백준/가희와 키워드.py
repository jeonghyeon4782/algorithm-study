import sys

n , m = map(int, sys.stdin.readline().split())
keyword = set()

for _ in range(n):
    word = sys.stdin.readline().rstrip()
    keyword.add(word)
    
for _ in range(m):
    word = list(map(str, sys.stdin.readline().rstrip().split(',')))
    keyword = keyword - set(word)
    print(len(keyword))