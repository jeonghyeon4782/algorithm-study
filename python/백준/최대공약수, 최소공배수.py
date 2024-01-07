def minNum(x, y):
    for i in range(max(x, y), x * y + 1):
        if i % x == 0 and i % y == 0:
            return i
def maxNum(x, y):
    for i in range(min(x, y), 0, -1):
        if x % i == 0 and y % i == 0:
            return i

n , m = map(int, input().split())
print(maxNum(n, m))
print(minNum(n, m))