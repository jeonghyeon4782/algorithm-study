for _ in range(int(input())):
    nums = []
    num = int(input())
    ans = 0

    for i in range(1, 46):
        nums.append(i * (i+1) // 2)
        
    for i in range(45):
        for j in range(i, 45):
            for k in range(j, 45):
                if nums[i] + nums[j] + nums[k] == num:
                    ans = 1
                    break

    print(ans)