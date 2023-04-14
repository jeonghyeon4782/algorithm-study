T = int(input())

for test_case in range(1, T+1):
    
    date = input()
    
    year = date[0:4]
    month = date[4:6]
    day = date[6:]
    
    days = {1:31,2:28,3:31,4:30,5:31,6:30,7:31,8:31,9:30,10:31,11:30,12:31}
    
    if 1 <= int(month) <= 12 and 1 <= int(day) <= days[int(month)]:
        print(f'#{test_case} {year}/{month}/{day}')
    else:
        print(f'#{test_case} -1')