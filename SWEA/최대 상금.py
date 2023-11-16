for t in range(1, int(input()) + 1):
    num, n = map(int, input().split()) # 숫자
    
    for _ in range(n):
        num_list = list(map(int, str(num))) # 숫자를 리스트로 변환
        num_max = [] # 각각의 변환 상태를 추가할 리스트
        for i in range(len(num_list) - 1):
            for j in range(i + 1, len(num_list)):
                num_list[i], num_list[j] = num_list[j], num_list[i]
                num_max.append(int(''.join(map(str, num_list))))
                num_list[i], num_list[j] = num_list[j], num_list[i]
        num = max(num_max)
        print(num)
    
    print(num)
                