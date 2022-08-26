package com.bom365.config;




import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.web.SecurityFilterChain;

import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.bom365.service.MemberService;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

	private final MemberService memberService;

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.ALWAYS);

		// https://velog.io/@kyungwoon/Spring-Security-%EB%8F%99%EC%9E%91-%EC%9B%90%EB%A6%AC
		// 로그인 시도 -> username, password 정보를 HTTP body로 전달
		// 인증 관리 -> UserDetailsService에게 username을 전달하고 회원 상세정보를 요청
		// 회원 DB에서 회원 조회 -> 조회된 정보를 UserDetails로 변환
		// 인증 관리자가 인증 처리 -> UserDetailsService가 전달해준 UserDetails의 정보와
		// 클라이언트가 시도한 username,password 일치 여부 확인
		// UserDetails의 password는 암호문이기에 클라이언트가 보낸 password를 암호화 하여 비교

		http
			.formLogin()
				.loginPage("/member/login")
					.defaultSuccessUrl("/")
					.usernameParameter("supporterId")
					.passwordParameter("supporterPassword")
					.failureUrl("/member/login/error")
				.and()
					.logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))
				.logoutSuccessUrl("/");

		http
			.authorizeRequests()
				.mvcMatchers("/member/update").authenticated()
				.mvcMatchers("/", "/member/**","/board/list","/support/**","/regular/**","/temporary/**").permitAll()
				;


		return http.build();
	}
	/*
	 @Bean
   public AuthenticationManager authenticationManager(
           AuthenticationConfiguration authenticationConfiguration) throws Exception {
       return authenticationConfiguration.getAuthenticationManager();
   }
	
	
	
	@Bean
    public TokenBasedRememberMeServices tokenBasedRememberMeServices() {
        TokenBasedRememberMeServices rememberMeServices = new TokenBasedRememberMeServices(REMEMBER_ME_KEY, userDetailsService);
        rememberMeServices.setAlwaysRemember(true);
        rememberMeServices.setTokenValiditySeconds(60 * 60 * 24 * 31);
        rememberMeServices.setCookieName(Constants.SPRING_REMEMBER_ME_COOKIE);
        return rememberMeServices;
    }
	
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        List<AuthenticationProvider> authenticationProviders = new ArrayList<>();
        authenticationProviders.add(customerAuthProvider);
        authenticationProviders.add(userAuthProvider);
        return new ProviderManager(authenticationProviders);
    }
    @Bean
	public AuthenticationFilter authenticationFilterBean() throws Exception {
    final AuthenticationFilter authenticationFilter = new AuthenticationFilter();
    authenticationFilter.setAuthenticationManager(this.authenticationManagerBean());
    return authenticationFilter;
	}
	@Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(memberService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setHideUserNotFoundExceptions(false);
        return daoAuthenticationProvider;
    }
	*/
	
	
	
	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {
		return (web) -> web.ignoring().antMatchers("/css/**", "/js/**", "/img/**");
	}
	// 참고 사이트
	// https://velog.io/@kyungwoon/Spring-Security-%EB%8F%99%EC%9E%91-%EC%9B%90%EB%A6%AC
	// https://spring.io/blog/2022/02/21/spring-security-without-the-websecurityconfigureradapter
	// https://www.marcobehler.com/guides/spring-security
	// https://stackoverflow.com/questions/72381114/spring-security-upgrading-the-deprecated-websecurityconfigureradapter-in-spring
	// https://medium.com/chequer/spring-security-remember-me-key-%EC%84%A4%EC%A0%95-6abd48e3274a
	// https://twer.tistory.com/entry/Security-%EC%8A%A4%ED%94%84%EB%A7%81-%EC%8B%9C%ED%81%90%EB%A6%AC%ED%8B%B0%EC%9D%98-%EC%95%84%ED%82%A4%ED%85%8D%EC%B2%98%EA%B5%AC%EC%A1%B0-%EB%B0%8F-%ED%9D%90%EB%A6%84
	// https://github.com/spring-projects/spring-security/issues/4516
	/*
	@Bean
	public UserDetailsService userDetailsService() {
		return memberService;
	}
	*/
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
