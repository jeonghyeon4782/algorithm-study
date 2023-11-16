for t in range(1, 11):
    n = int(input())
    floors = list(map(int, input().split()))
    for _ in range(n):
        floors.sort()
        floors[-1] -= 1
        floors[0] += 1
        
    print(f'#{t} {max(floors) - min(floors)}')
         