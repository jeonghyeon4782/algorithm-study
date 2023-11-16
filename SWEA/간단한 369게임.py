for i in range(1, int(input()) + 1):
    cnt3 = str(i).count('3')
    cnt6 = str(i).count('6')
    cnt9 = str(i).count('9')
    sum_cnt = cnt3 + cnt6 + cnt9
    if not sum_cnt:
        print(i, end=' ')
    else:
        for _ in range(sum_cnt):
            print('-', end='')
        print(' ', end='')