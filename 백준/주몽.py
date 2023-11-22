n = int(input())
m = int(input())
nums = list(map(int, input().split()))
nums.sort()
s = 0
e = len(nums) - 1
ans = 0

while s < e:
    if nums[s] + nums[e] == m:
        ans += 1
        s += 1
        e -= 1
    elif nums[s] + nums[e] < m:
        s += 1
    else:
        e -= 1

print(ans)