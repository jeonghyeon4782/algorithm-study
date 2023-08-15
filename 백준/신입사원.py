import sys
input = sys.stdin.readline

for _ in range(int(input())):
    arr = []
    count = 0

    N = int(input())
    for _ in range(N):
        a, b = map(int, input().split())
        arr.append((a, b))

    arr.sort()
    minInterviewRank = arr[0][1]  

    for x, y in arr:
        if y < minInterviewRank:
            count += 1
            minInterviewRank = y

    print(count)