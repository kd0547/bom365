### 개발 환경
- 언어 : Java(JDK11), Javascript, thymeleaf, HTML/CSS
- 서버 : Tomcat, AWS EC2, RDS, ubuntu
- 프레임워크 : Spring Boot
- DB : Mysql
- API, 라이브러리 : JPA, Querydsl, Spring security, Spring scheduler
- ERD : https://dbdiagram.io/d/6476dcf7722eb774941daa29
## Spring security

### CSRF 설정
```JAVA
http.headers().and()
	.csrf()
	.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
	.and();
```

### 세션 설정 
```JAVA
http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.ALWAYS);
```

## 구현코드
> 기능의 추가 또는 개선한 코드를 정리했습니다. 



### ID 중복 체크 변경
> 비동기 방식으로 아이디 중복 검사를 구현했습니다. POST 전송을 위해 csrf 토큰 값을 가져와 요청 헤더에 넣어 전송합니다. 
```javascript
<script th:inline="javascript">
	function sendDuplicateCode() {
		//POST방식 데이터 전송에 필요한 CSRF 토큰 값 조회
		var token = $("meta[name='_csrf']").attr("content");
		var header = $("meta[name='_csrf_header']").attr("content");

		var url = "/member/duplicate"
		$.ajax({
			url: url,
			type: "POST",
			data: {
				id: $("#id").val()
			},
			/* 데이터를 전송하기 전에 헤더에 csrf값을 설정 */
			beforeSend: function (xhr) {
				xhr.setRequestHeader(header, token)
			},
			success: function (result, status, xhr) {
				if (result.status == 200) {
					idCheckResult(result.message, result.duplicate);
				}
			},
			error: function () {console.log(arguments);}
		});
	}
	function idCheckResult(data, result) {
		document.getElementById('idCheckResult').innerText = data;
		document.querySelector('input[name=duplicate]').value = result;
	}
</script>
```

### 일시 후원 기능 개선

### 정기 후원 기능 추가
> 스프링 스케줄러를 활용하여 DB에 있는 결제 정보를 가져와 순차적으로 결제를 진행하는 코드를 구현했습니다. 이 기능은 매일 00시 30분에 실행되도록 설정되어 있습니다.
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
				= new ReadyRequestSubscription.Builder()
					.cid(regularSupport.getCid())
					.sid(regularSupport.getSid())
					.partnerOrderId(regularSupport.getPartner_order_id())
					.partnerUserId(regularSupport.getSupporterId())
					.quantity(regularSupport.getQuantity())
					.totalAmount(regularSupport.getAmount_id().getTotal())
					.taxFreeAmount(regularSupport.getAmount_id().getTax_free())
					.build();
			try {
				ReadyResponseSubscription readyResponseSubscription = 
					(ReadyResponseSubscription) kakaoPayRequest
					.payReady(new URI("https://kapi.kakao.com/v1/payment/subscription"), 
					readyRequestSubscription, 
					new ReadyResponseSubscription());
				
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

### 이메일 인증
> 이메일 인증을 구현해 실제 가입자의 이메일인지 확인하는 절차를 추가했습니다. 임시 회원 테이블에 데이터를 저장하고 인증이 완료되면 실제 회원 테이블에 저장하는 방식으로 구현했습니다.

![제목 없는 다이어그램 (1)](https://github.com/kd0547/bom365/assets/86393702/e09e8fad-157f-4cc2-b80e-d0b1f5f5a40e)

```JAVA
@PostMapping("/signup")
public String signup(@Valid MemberFormDto memberFormDto, BindingResult bindingResult,Model model) {
		
	String email = memberFormDto.getEmail();
	Member member = memberRepository.findByEmail(email);
		
	if(bindingResult.hasErrors() || memberFormDto.isDuplicateCheck() || member != null) {
		return "member/signupForm";
	}
	
	TempMember tempMember = tempMemberService.save(memberFormDto);
	AuthToken authToken = authService.createToken(tempMember);
		
	emailService.sendAuthEmail(email, authToken);
		
	model.addAttribute("successCode","이메일 인증을 완료해주세요");
		
	return "/";
}
```
- 회원 email에 인증코드를 전송합니다. 인증코드는 `Email`에 가입 시간을 추가해 해시 결과가 동일하지 않도록 했습니다.

```JAVA
@GetMapping("authEmail")
public String authEmail(@RequestParam("code")String code,@RequestParam("email") String email) {

	if (isNotValidateToken(email,code)) {
		throw new IllegalArgumentException("토큰이 만료되었습니다.");
	}
	TempMember findTempMember = tempMemberService.findEmail(email);
	memberService.saveMember(findTempMember);
	authService.invalidateToken(email);
	
	return "member/signupDone";
}
```
- 토큰을 검사하고 `TempMember` 에 있는 회원 정보를 `Member`에 저장합니다. 
- 사용한 토큰은 `authService.invalidateToken()`으로 만료처리 합니다. 

### AnimalSearch
> 유기 동물 검색 쿼리의 다중 Where 절의 복잡함을 QueryDSL을 사용하여 편의성을 높였습니다.
```JAVA
@Override
public List<Animal> searchWhereAnimal(AnimalSearchDto animalSearchDto) {
	return jpaQueryFactory.select(animal)
		.from(animal)
		where( animalNameEq(animalSearchDto.getAnimalName()),
		      animalSpeciesEq(animalSearchDto.getAnimalSpecies()),
		      animalGenderEq(animalSearchDto.getAnimalGender()),
		      animalWeightBetween(animalSearchDto.getAnimalWeight()),
		      animalAgeBetween(animalSearchDto.getAnimalAge()))
		.fetch();
}
```

### 프로젝트 인원 및 기여도

- 프론트엔드 / 백엔드 : 1명
- 나의 역할 및 기여도 : JSP -> thymeleaf로 변환 / 백엔드 전체

### 제작 이유 

- Spring MVC 패턴과 전체적인 서비스 흐름, 기능등을 개발하면서 언어와 프레임 워크를 숙련도와 
Spring의 주요 요소인 IoC/DI, 컴포넌트와 컴포넌트 기술들을 이해하기 위해서 제작하게 되었습니다.
- JPA와 QueryDsl의 숙련도와 사용법을 향상시키기 위해서 개발하게 되었습니다.

### 구현 기능  
- 사용자의 인증과 인가를 위해 Spring Security 을 적용
- 유기동물 검색 기능의 개발 편의성을 높이기 Querydsl를 적용
- SFTP를 이용한 수동 방식에서 Git Branch와 Jenkins를 연동하여 자동화로 변경
- 기존 은행명과 계좌번호만 DB에 저장하는 기능을 Kakao API와 Spring scheduler로 정기 결제 자동화를 구현
 

### 개발 중 기능을 수정 또는 변경한 이유
- 정기 후원 기능과 일시 후원 기능을 KAKAO API를 사용한 이유는 기존 방식의 불안정함과 보안의 취약점이 있다고 판단하여 선택했습니다.
- 

### 아쉬운 점과 개선되어야할 점
- 정기 후원 기능을 구현하면서 한 가지 아쉬웠던 점은, 최초 결제한 시각에 바로 결제가 진행되도록 구현하고 싶었지만, 스케줄러의 동작 방식 때문에 실시간으로 처리가 어렵다는 점입니다. 새로운 방법을 찾아내거나 기존 방식을 개선하여 보다 원하는 동작을 구현할 수 있도록 노력하고 있습니다.
- 유기 동물 검색 쿼리의 다중 Where의 복잡함을 QueryDsl을 사용해 편의성을 높였지만 QueryDsl을 더 이상 활용하지 않았습니다.
문자열 대신 자바 코드로 작성해 컴파일 단계에서 오류를 잡을 수 있는 장점이 있었지만, QueryDsl을 사용하기 위해서 Q.class에 의존한다는 점과 
유기 동물 검색 기능에 추가 조건을 변경하는 빈도가 적다는 점에서 JPQL을 사용하는 것이 개발 시간을 줄일 수 있지 않았을까 생각하고 있습니다. 
새로운 기능을 배웠다는 점에서 만족하고 있습니다. 

