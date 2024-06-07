# 단위테스트 - XP 미션 제출 B831051 김승완, B911397 김동혁

## 역할
1. 김승완 : Book 클래스를 작성하고, BookManager 클래스를 작성함. 전자는 책의 id, 제목, 저자, 출판년도를 속성으로 가짐. 후자 클래스는 CurrentHashMap 자료구조에 저장함.<br>
BookManager는 책을 저장하는 `addBook()`, `searchBook()`, `deleteBook()` 메서드를 가지며, 내부적으로 존재하지 않는 책을 조회하거나 삭제하려는 경우 오류를 반환하고, 이미 존재하는 책을 추가하려는 경우에도 오류를 반환함
2. 김동혁 : BookManager 클래스를 활용하여 여러 책을 저장하고, 조회하고, 삭제하는 테스트 코드를 작성함 -> BookManagerTest 클래스
## 제출자
1. B831051 김승완
2. B911397 김동혁


## Trouble Shooting
1. jenkins 파일의 빌드 과정을 일일히 적기 어려움 : Gradle 빌드 툴로 대체, 이는 jenkins의 gradle 설치 플러그인으로 해결
2. 테스트 결과를 확인할 수 있어야 함 : 빌드 결과물을 수집하고 실행하는 `post`를 통해 설정함. 기본적으로 gradle은 테스트를 진행하면 build 디렉터리의 test-results/test 하위 디렉터리에 xml 양식으로 테스트 결과물을 만들어냄
3. github webhooks가 제대로 연동되지 않음 : post 요청의 양식(application/json), 302 에러(마지막 엔드포인트에 `/` 를 포함하지 않은 문제).