# 내 코드

data = input()
a = [] # 문자
b = [] # 숫자

for i in data:
    if(i.isdigit() == False) : # 숫자로만 구성 >> true, isalpha() >> 문자로만 구성 >> true
        a.append(i)
    else :
        b.append(i)

a.sort()
        
for i in range(len(a)):
    print(a[i], end='')
    
sum = 0    
    
for i in range(len(b)):
    sum += int(b[i])

print(sum)

########################################################

# 정답 코드

data = input()
result = []
value = 0

# 문자를 하나씩 확인하며
for x in data:
    # 알파벳인 경우 결과 리스트에 삽입
    if x.isalpha():
        result.append(x)
    # 숫자는 따로 더하기
    else:
        value += int(x)

# 알파벳을 오름차순으로 정렬
result.sort()

# 숫자가 하나라도 존재하는 경우 가장 뒤에 삽입
if value != 0:
    result.append(str(value))

# 최종 결과 출력(리스트를 문자열로 변환하여 출력)
print(''.join(result))