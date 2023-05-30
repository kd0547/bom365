
### 구현코드(일부)

## 스프링 스케줄러
```JAVA
@Component
public class RegularSupportScheduler {
	
	@Autowired RegularSupportService regularSupportService;
	
	@Autowired RegularSupportHistoryService historyService;
	
	@Autowired KakaoPayRequest kakaoPayRequest;
	
	@Async
	@Scheduled(cron = "0 30 0 * * *")
	@Transactional
	public void subscription() throws InterruptedException {
		
		LocalDateTime startTime = LocalDateTime.now().withHour(0).withMinute(0).withSecond(0);
		LocalDateTime endTime = LocalDateTime.now().withHour(23).withMinute(59).withSecond(59);
		
		List<RegularSupport> regularSupports = regularSupportService.findByNextAtBetween(startTime, endTime);
		
		for(RegularSupport regularSupport : regularSupports) {
			ReadyRequestSubscription readyRequestSubscription 
				= new ReadyRequestSubscription
						.Builder()
						.cid(regularSupport.getCid())
						.sid(regularSupport.getSid())
						.partnerOrderId(regularSupport.getPartner_order_id())
						.partnerUserId(regularSupport.getSupporterId())
						.quantity(regularSupport.getQuantity())
						.totalAmount(regularSupport.getAmount_id().getTotal())
						.taxFreeAmount(regularSupport.getAmount_id().getTax_free())
						.build();
			
			
			
			try {
				ReadyResponseSubscription readyResponseSubscription = (ReadyResponseSubscription) kakaoPayRequest.payReady(new URI("https://kapi.kakao.com/v1/payment/subscription"), readyRequestSubscription, new ReadyResponseSubscription());
				
				historyService.save(readyResponseSubscription);
				
			
			} catch (RestClientException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
```
- 정기결제는 `Kakao API`를 이용했습니다
- `@Scheduled(cron = "0 30 0 * * *")`을 추가해 00시 30분에 결제 정보를 받아와 순차적으로 결제를 진행합니다.
- 


### 개발 환경
- 언어 : Java(JDK11), Javascript, thymeleaf, HTML/CSS
- 서버 : Tomcat, AWS EC2, RDS, ubuntu
- 프레임워크 : Spring Boot
- DB : Mysql
- API, 라이브러리 : JPA, Querydsl, Spring security, Spring scheduler


### 프로젝트 인원 및 기여도

- 프론트엔드 / 백엔드 : 1명
- 나의 역할 및 기여도 : JSP -> thymeleaf로 변환 / 백엔드의 전반

### 제작 이유 

- Spring MVC 패턴과 전체적인 서비스 흐름, 기능등을 개발하면서 언어와 프레임 워크를 숙련도와 
Spring의 주요 요소인 IoC/DI, 컴포넌트와 컴포넌트 기술들을 이해하기 위해서 제작하게 되었습니다.
- JPA와 QueryDsl의 숙련도와 사용법을 향상시키기 위해서 개발하게 되었습니다.

### 구현 기능  
- 사용자의 인증과 인가를 위해 Spring Security 을 적용
- 유기동물 검색 기능의 개발 편의성을 높이기 Querydsl를 적용
- SFTP를 이용한 수동 방식에서 Git Branch와 Jenkins를 연동하여 자동화로 변경
- 기존 은행명과 계좌번호만 DB에 저장하는 기능을 Kakao API와 Spring scheduler로 정기 결제 자동화를 구현
 

### 개발 과정 중 생긴 문제와 해결 내용
- 일시 후원 기능에서 외부 API인 아이엠포트에서 Kakao API와 결제 진행 후 봄365 서버에 결과만 저장하는 방식에 해킹에 의한 변조를 막고자 
봄365 서버에서 Kakao API를 직접 활용해 결제하는 방식으로 수정했습니다. 
- 

### 아쉬운 점과 개선되어야할 점
- Kakao API 뿐만 아닌 Naver 등의 결제 기능도 활용하기 위해 외부결제 API를 추상화하려고 했지만 디자인 패턴의 활용 경험 부족으로 
 구현하지 못한 것입니다. 이를 보완하기 위해 디자인 패턴 등을 학습할 것입니다. 
- 팀 프로젝트 당시 Ajax를 활용해 
- 유기 동물 검색 쿼리의 다중 Where의 복잡함을 QueryDsl을 사용해 편의성을 높였지만 QueryDsl을 더 이상 활용하지 않았습니다.
문자열 대신 자바 코드로 작성해 컴파일 단계에서 오류를 잡을 수 있는 장점이 있었지만, QueryDsl을 사용하기 위해서 Q.class에 의존한다는 점과 
유기 동물 검색 기능에 추가 조건을 변경하는 빈도가 적다는 점에서 JPQL을 사용하는 것이 개발 시간을 줄일 수 있지 않았을까 생각하고 있습니다. 
새로운 기능을 배웠다는 점에서 만족하고 있습니다. 

