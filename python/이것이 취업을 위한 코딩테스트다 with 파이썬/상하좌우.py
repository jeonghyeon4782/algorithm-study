move = {'L':(0, -1), 'R':(0, 1), 'U':(-1,0), 'D':(1, 0)}
n = int(input())
x = y = 1
def is_valid(x, y):
    return 1 <= x < n + 1 and 1 <= y < n + 1
adj = list(input().split())
for d in adj:
    dx, dy = list(move[d])
    if is_valid(x + dx, y + dy):
        x = x + dx
        y = y + dy
print(x, y)