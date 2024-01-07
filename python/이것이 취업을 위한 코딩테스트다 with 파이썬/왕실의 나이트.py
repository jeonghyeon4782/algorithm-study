dx = (-1, -2, -2, -1, 1, 2, 2, 1)
dy = (-2, -1, 1, 2, 2, 1, -1, -2)

def is_valid(x, y):
    return 1 <= x < 9 and 1 <= y < 9

d = input()
x = int(d[1])
y = ord(d[0]) - 96
ans = 0

for i in range(8):
    if is_valid(x + dx[i], y + dy[i]):
        ans += 1

print(ans)