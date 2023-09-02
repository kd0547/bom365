### 개발 환경
- 언어 : Java(JDK11), Javascript, thymeleaf, HTML/CSS
- 서버 : Tomcat, AWS EC2, RDS, ubuntu
- 프레임워크 : Spring Boot
- DB : Mysql
- API, 라이브러리 : JPA, Querydsl, Spring security, Spring scheduler
- ERD : https://dbdiagram.io/d/6476dcf7722eb774941daa29
## Spring security
다. 


### 프로젝트 인원 및 기여도

- 프론트엔드 / 백엔드 : 1명
- 나의 역할 및 기여도 : JSP -> thymeleaf로 변환 / 백엔드 전체

### 제작 이유 

- Spring MVC 패턴과 전체적인 서비스 흐름, 기능등을 개발하면서 언어와 프레임 워크를 숙련도와 
Spring의 주요 요소인 IoC/DI, 컴포넌트와 컴포넌트 기술들을 이해하기 위해서 제작하게 되었습니다.
- JPA와 QueryDsl의 숙련도와 사용법을 향상시키기 위해서 개발하게 되었습니다.

### 구현 기능 요약

- 사용자의 인증과 인가를 위해 Spring Security 을 적용
- 유기동물 검색 기능의 개발 편의성을 높이기 Querydsl를 적용
- SFTP를 이용한 수동 방식에서 Git Branch와 Jenkins를 연동하여 자동화로 변경
- 기존 은행명과 계좌번호만 DB에 저장하는 기능을 Kakao API와 Spring scheduler로 정기 결제 자동화를 구현
 
