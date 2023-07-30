from collections import deque

dx = (0, 0, -1, 1)
dy  =(1, -1, 0, 0)

n , m = map(int, input().split())
board = [input() for _ in range(n)]
chk = [[False] * m for _ in range(n)]

def is_valid(mx, my):
    