### 프로젝트 인원 및 기여도

- 프론트엔드 / 백엔드 : 1명

### 제작 이유 

- Spring / Spring boot 와 JPA를 학습을 목적으로 했습니다
- 

### 구현 기능  
- 사용자의 인증과 인가를 위해 Spring Security 을 적용
- 유기동물 검색 기능의 개발 편의성을 높이기 Querydsl를 적용
- SFTP를 이용한 수동 방식에서 Git Branch와 Jenkins를 연동하여 자동화로 변경
- 기존 은행명과 계좌번호만 DB에 저장하는 기능을 Kakao API와 Spring scheduler로 정기 결제 자동화를 구현
- 이전 프로젝트의 후원 기능의 문제점을 파악하고 해결
 

### 개발 과정 중 생긴 문제와 해결 내용
- 일시 후원 기능에서 외부 API인 아이엠포트에서 Kakao API와 결제 진행 후 봄365 서버에 결과만 저장하는 방식에 해킹에 의한 변조를 막고자 
봄365 서버에서 Kakao API를 직접 활용해 결제하는 방식으로 수정했습니다. 
- 

### 아쉬운 점과 개선되어야할 점
-유기 동물 검색 쿼리의 다중 Where의 복잡함을 QueryDsl을 사용해 편의성을 높였지만 QueryDsl을 더 이상 활용하지 않았습니다.
문자열 대신 자바 코드로 작성해 컴파일 단계에서 오류를 잡을 수 있는 장점이 있었지만, QueryDsl을 사용하기 위해서 Q.class에 의존한다는 점과 
유기 동물 검색 기능에 추가 조건을 변경하는 빈도가 적다는 점에서 JPQL을 사용하는 것이 개발 시간을 줄일 수 있지 않았을까 생각하고 있습니다. 
새로운 기능을 배웠다는 점에서 만족하고 있습니다. 

