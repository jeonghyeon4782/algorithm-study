stack = []

n = int(input())
st = input()

n1 = 0
n2 = 0
n3 = 0

for _ in range(n):
    int(input())
    
for ch in st:
    if ch == '+':
        n2 = stack.pop()
        n1 = stack.pop()
        n3 = n1 + n2
        stack.append(n3)
    elif ch == '-':
        n2 = stack.pop()
        n1 = stack.pop()
        n3 = n1 - n2
        stack.append(n3)
    elif ch == '*':
        n2 = stack.pop()
        n1 = stack.pop()
        n3 = n1 * n2
        stack.append(n3)
    elif ch == '/':
        n2 = stack.pop()
        n1 = stack.pop()
        n3 = n1 / n2
        stack.append(n3)
    else:
        stack.append(ord(ch)-64)
    
print(f'{stack.pop():.2f}')

# 정답 코드
n = int(input())  # 피연산자의 개수
st = input()  # 후위 표기식

stack = []

# 피연산자에 대응하는 값들을 입력받아 리스트에 저장
values = [0] * n
for i in range(n):
    values[i] = int(input())

# 후위 표기식 계산
for ch in st:
    if ch.isalpha():  # 알파벳인 경우(피연산자)
        stack.append(values[ord(ch) - ord('A')])  # 해당 피연산자에 대응하는 값 스택에 저장
    else:  # 연산자인 경우
        n2 = stack.pop()  # 스택에서 피연산자 꺼내기
        n1 = stack.pop()
        if ch == '+':
            n3 = n1 + n2
        elif ch == '-':
            n3 = n1 - n2
        elif ch == '*':
            n3 = n1 * n2
        else:  # ch == '/'
            n3 = n1 / n2
        stack.append(n3)  # 계산 결과 스택에 저장

print(f'{stack.pop():.2f}')  # 계산 결과 출력
    