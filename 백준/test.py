dictionary = {}

def init():
    for _ in range(5):
        korean, english = map(str, input('한글과 영어를 입력하세요 : ').split())
        dictionary[korean] = english
        
def searchEnglish():
    korean = input('한글 단어를 입력하세요 : ')
    print(dictionary[korean])
    return dictionary[korean]

def searchAll():
    for korean, english in dictionary.items():
        print(f'{korean}({english})')
    
def addWord():
    korean, english = map(str, input('한글과 영어를 입력하세요 : ').split())
    dictionary[korean] = english
    
def deleteWord():
    korean = input('한글 단어를 입력하세요 : ')
    del dictionary[korean]

while(True):
    choice = ''
    print('''- 단어입력 : i
- 단어삭제 : d
- 전체사전조회 : a
- 한단어조회 : (사전에 있는 한글단어 입력)
- 프로그램종료 : q''')
    choice = input('위 보기에서 선택하세요 : ')
    
    if choice == 'i': addWord()
    elif choice == 'd': deleteWord()
    elif choice == 'a': searchAll()
    elif choice == 'q': 
        print('프로그램 종료')
        break
    else:
        result = dictionary.get(choice)
        if result is not None:
            print(result)
        else:
            print('다시 입력해주세요')
     
    
